package br.gov.mds.sebas.seguranca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIForm;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.gov.mds.sebas.dao.PerfilDAO;
import br.gov.mds.sebas.dao.UsuarioDAO;
import br.gov.mds.sebas.util.Mensagem;
import br.gov.mds.sebas.util.Status;
import br.gov.mds.sebas.util.Texto;
import br.gov.mds.sebas.util.UtilController;
import br.gov.mds.sebas.util.UtilMD5;

@Named
@ViewScoped
public class UsuarioMB  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsuarioDAO usuarioDAO;
	@EJB
	private PerfilDAO perfilDAO;
	@Inject
	Autorizacao autorizacao;
	@Inject
	Sessao sessao;
	@EJB
	Texto texto;
	
	private Permissao permissao;
	private UIForm form = new UIForm();
	private Usuario usuario;
	private UtilController utilController = new UtilController();
	private List<Usuario> listaUsuarios;
	private String filtro;
	private String tipoFiltro;
	private List<Perfil> listaTodosPerfis;
	private DualListModel<Perfil> perfis;
	
	@PostConstruct
	public void init() {
		utilController.setStatus(Status.LISTING);
		permissao = autorizacao.verificaAutorizacao(sessao.getModulosUsuario(), sessao.getPermissoesUsuario());
		buscarTodos();
	}
	
	public void buscarTodos(){
		try {
			listaUsuarios = usuarioDAO.recuperarPorTodos();
		} catch (Exception e) {
			Mensagem.mostrarErro("FALHA: Ao tentar listar registros. ", e.getMessage());
		}
	}
	
	public String voltarHome(){
		return "/index.xhtml";
	}
	
	public void novo() {
		try {
			setUsuario(new Usuario());
			getUsuario().setCadastrador(sessao.getUsuarioLogado());
			carregaPerfil();
			Mensagem.mostrarAviso("Incluindo Novo Registro.");
			utilController.setStatus(Status.INSERTING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao tentar incluir registro." + e.getMessage());
		}
	}
	
	public void editar() {
		try {
			carregaPerfil();
			Mensagem.mostrarAviso("Editar: ", getUsuario().getLogin());
			utilController.setStatus(Status.EDITING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao tentar editar registro." + e.getMessage());
		}
	}
	
	public void preparaParaDesativar() {
		try {
			carregaPerfil();
			Mensagem.mostrarAviso("Preparando para Desativar: ", getUsuario().getLogin());
			utilController.setStatus(Status.DELETING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao preparar remoção de registro: " + e.getMessage());
		}
	}

	public void desativar() {
		try {
			if (utilController.getStatus() == Status.DELETING) {
				this.usuarioDAO.excluir(getUsuario().getId());
				Mensagem.mostrarAviso("Registro desativado com sucesso:", getUsuario().getLogin());
				voltarLista();
				utilController.setStatus(Status.LISTING);
			}
		} catch (Exception e) {
			Mensagem.mostrarErro("FALHA: ", "Erro ao tentar excluir registro." + e.getMessage());
		}
	}
	
	public void salvar() {
		try {
			if ((utilController.getStatus() == Status.INSERTING)
					|| (utilController.getStatus() == Status.EDITING)) {
				this.usuario.setSenha(UtilMD5.produzirChaveMD5(this.usuario.getSenha()));
				this.usuarioDAO.salvar(getUsuario());
				Mensagem.mostrarAviso("Registro salvo com sucesso:", getUsuario().getLogin());
				voltarLista();
			}
			utilController.setStatus(Status.LISTING);
		} catch (Exception exeption) {
			Mensagem.mostrarErro("ERRO: ", "ao salvar!");
		}
	}
	
	public void voltarLista() {
		utilController.setStatus(Status.LISTING);
	}
	
	public void carregaPerfil() {
		try {
			List<Perfil> perfisSource = new ArrayList<Perfil>();  
			List<Perfil> perfisTarget = new ArrayList<Perfil>(); 

			setListaTodosPerfis(perfilDAO.recuperarPorTodos());

			if(usuario.getPerfis() != null ){
				for(Perfil p : usuario.getPerfis()){
					perfisTarget.add(p);
				}
			}

			for(Perfil p : getListaTodosPerfis()){
				if(!perfisTarget.contains(p)){
					perfisSource.add(p);
				}
			}

			perfis = new DualListModel<Perfil>(perfisSource, perfisTarget);
		} catch (Exception e) {
			Mensagem.mostrarErro(texto.getValor("falha_ao_carregar_perfil"), e.getMessage());
		}
	}

	//get e set
	public UtilController getUtilController() {
		return utilController;
	}

	public void setUtilController(UtilController utilController) {
		this.utilController = utilController;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Perfil> getListaPerfis() throws Exception {
		return perfilDAO.recuperarPorTodos();
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public List<Perfil> getListaTodosPerfis() {
		return listaTodosPerfis;
	}

	public void setListaTodosPerfis(List<Perfil> listaTodosPerfis) {
		this.listaTodosPerfis = listaTodosPerfis;
	}

	public DualListModel<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(DualListModel<Perfil> perfis) {
		this.perfis = perfis;
	}

}
