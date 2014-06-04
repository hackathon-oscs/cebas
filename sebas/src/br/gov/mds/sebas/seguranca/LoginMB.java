package br.gov.mds.sebas.seguranca;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import br.gov.mds.sebas.dao.LoginDAO;
import br.gov.mds.sebas.excecao.ExcecaoFalhaGeralLogin;
import br.gov.mds.sebas.excecao.ExcecaoLoginInexistente;
import br.gov.mds.sebas.excecao.ExcecaoLoginSemPerfil;
import br.gov.mds.sebas.excecao.ExcecaoSemAcessoModulo;
import br.gov.mds.sebas.excecao.ExcecaoSenhaIncorreta;
import br.gov.mds.sebas.util.UtilMD5;

@Model
public class LoginMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private LoginDAO loginDAO;
	private Usuario usuario;
	private String login;
	private String senha;
	@Inject
	private Sessao sessao;

	public void login() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			acessar(this.login, this.senha);
		} catch (ExcecaoLoginInexistente e) {
			context.addMessage("form: Login", new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Usuário Inexistente",
					"O login informado não existe."));
		} catch (ExcecaoSenhaIncorreta e) {
			context.addMessage("form: Senha", new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Senha Incorreta",
					"A senha informada está incorreta."));
		} catch (ExcecaoSemAcessoModulo e) {
			context.addMessage("form: Usuario", new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Sem acesso",
					"Usuário não possui acesso a nenhum módulo "));
		} catch (ExcecaoLoginSemPerfil e) {
			context.addMessage("form: Usuario", new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Sem perfil",
					"Não foi possível efetuar o acesso, pois o usuário "
							+ usuario.getLogin() + " não possui perfis de acesso."));
		} catch (ExcecaoFalhaGeralLogin e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Falha", "Ocorreu um erro interno no mecanismo de login. Favor contactar os administradores do sistema."));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void acessar(String login_, String senha_)
			throws ExcecaoLoginInexistente, ExcecaoFalhaGeralLogin,
			ExcecaoSemAcessoModulo, ExcecaoSenhaIncorreta,
			ExcecaoLoginSemPerfil, IOException{
		try {
			usuario = loginDAO.recuperarPorLogin(login_);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String senhaMd5 = null;
		try {
			if (senha_ != null)
				senhaMd5 = UtilMD5.produzirChaveMD5(senha_);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new ExcecaoFalhaGeralLogin();
		}
		if (this.usuario == null) {
			throw new ExcecaoLoginInexistente();
		} else {
			if ((senhaMd5 != null) && (senhaMd5.equals(this.usuario.getSenha()))) {
				if (this.usuario.getPerfis().isEmpty()) {
					throw new ExcecaoLoginSemPerfil();
				} else {
					try {
						HttpServletRequest request = (HttpServletRequest) FacesContext
								.getCurrentInstance().getExternalContext().getRequest();
						
						request.login(login_, senhaMd5);
						
					} catch (Throwable e) {
						throw new ExcecaoFalhaGeralLogin();
					}
					this.sessao.setUsuarioLogado(usuario);
					this.sessao.construirMenu();
					if (this.sessao.getModulosUsuario().isEmpty()) {
						throw new ExcecaoSemAcessoModulo();
					}
					FacesContext faces = FacesContext.getCurrentInstance();
					ExternalContext context = faces.getExternalContext();
					String caminho = context.getRequestContextPath();
					context.redirect(caminho + "/index2.xhtml");
				}
			} else {
				throw new ExcecaoSenhaIncorreta();
			}
		}
	}

	public void logout() {
		try {
			HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			request.logout();
			sessao.setUsuarioLogado(null);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		try {
			FacesContext faces = FacesContext.getCurrentInstance();
			ExternalContext context = faces.getExternalContext();
			context.redirect("/sebas/login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}