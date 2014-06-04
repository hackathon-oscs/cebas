package br.gov.mds.sebas.seguranca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mds.sebas.dao.PerfilDAO;
import br.gov.mds.sebas.util.Mensagem;
import br.gov.mds.sebas.util.Status;
import br.gov.mds.sebas.util.UtilController;

@Named
@ViewScoped
public class PerfilMB  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public PerfilMB(){
		listaPesquisa.add("nome");
		listaPesquisa.add("observacao");
	}
	
	@EJB
	private PerfilDAO perfilDAO;
	@Inject
	Sessao sessao;
	@Inject
	Autorizacao autorizacao;

	private Permissao permissao;
	private Perfil perfil;
	private UtilController utilController = new UtilController();
	@SuppressWarnings("unused")
	private List<Perfil> listaPerfis;
	
	private String filtro;
	private String tipoFiltro;
	private List<String> listaPesquisa = new ArrayList<String>();
	
	@PostConstruct
	public void init() {
		utilController.setStatus(Status.LISTING);
		permissao = autorizacao.verificaAutorizacao(sessao.getModulosUsuario(), sessao.getPermissoesUsuario());
	}
	
	public void buscarPorFiltro(){
		try {
			listaPerfis = perfilDAO.recuperarPorFiltro(tipoFiltro, filtro);
		} catch (Exception e) {
			Mensagem.mostrarErro("FALHA: Ao tentar listar registros. ", e.getMessage());
		}
	}
	
	public String voltarHome(){
		return "/template/index.xhtml";
		
		
	}
	public void criar() {
		try {
			setPerfil(new Perfil());
			
			Mensagem.mostrarAviso("Incluindo Novo Registro.");
			utilController.setStatus(Status.INSERTING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao tentar incluir registro." + e.getMessage());
		}
	}
	
	public void editar() {
		try {
			Mensagem.mostrarAviso("Editar: ", getPerfil().getNome());
			utilController.setStatus(Status.EDITING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao tentar editar registro." + e.getMessage());
		}
	}
	
	public void preparaParaDesativar() {
		try {
			Mensagem.mostrarAviso("Preparando para Desativar: ", getPerfil().getNome());
			utilController.setStatus(Status.DELETING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao preparar remoção de registro: " + e.getMessage());
		}
	}

	public void desativar() {
		try {
			if (utilController.getStatus() == Status.DELETING) {
				this.perfilDAO.excluir(getPerfil().getId());
				Mensagem.mostrarAviso("Registro desativado com sucesso:", getPerfil().getNome());
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

				this.perfilDAO.salvar(getPerfil());
				Mensagem.mostrarAviso("Registro salvo com sucesso:", getPerfil().getNome());
				voltarLista();
			}
			utilController.setStatus(Status.LISTING);
		} catch (Exception exeption) {
			Mensagem.mostrarErro("ERRO: ", "ao salvar!");
		}
	}
	
	public void voltarLista() {
		setFiltro("");
		listaPerfis = null;
		utilController.setStatus(Status.LISTING);
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public UtilController getUtilController() {
		return utilController;
	}

	public void setUtilController(UtilController utilController) {
		this.utilController = utilController;
	}

	public List<Perfil> getListaPerfis() throws Exception {
		return perfilDAO.recuperarPorTodos();
	}

	public void setListaPerfis(List<Perfil> listaPerfis) {
		this.listaPerfis = listaPerfis;
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

	public List<String> getListaPesquisa() {
		return listaPesquisa;
	}

	public void setListaPesquisa(List<String> listaPesquisa) {
		this.listaPesquisa = listaPesquisa;
	}

	public Permissao getPermissao() {
		return permissao;
	}
}
