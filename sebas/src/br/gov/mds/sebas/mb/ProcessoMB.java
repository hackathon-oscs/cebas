package br.gov.mds.sebas.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIForm;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mds.cebas.enumeration.TiposProcesso;
import br.gov.mds.sebas.dao.AssuntoDAO;
import br.gov.mds.sebas.dao.EntidadeDAO;
import br.gov.mds.sebas.dao.ProcessoDAO;
import br.gov.mds.sebas.dao.ValidacaoDAO;
import br.gov.mds.sebas.modelo.Assunto;
import br.gov.mds.sebas.modelo.Entidade;
import br.gov.mds.sebas.modelo.Processo;
import br.gov.mds.sebas.modelo.Validacao;
import br.gov.mds.sebas.seguranca.Autorizacao;
import br.gov.mds.sebas.seguranca.Permissao;
import br.gov.mds.sebas.seguranca.Sessao;
import br.gov.mds.sebas.util.Mensagem;
import br.gov.mds.sebas.util.Status;
import br.gov.mds.sebas.util.Texto;
import br.gov.mds.sebas.util.UtilController;

@Named
@ViewScoped
public class ProcessoMB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	Sessao sessao;
	@EJB
	private EntidadeDAO entidadeDAO;
	@EJB
	private ProcessoDAO processoDAO;
	@EJB
	private ValidacaoDAO validacaoDAO;
	
	@EJB
	private AssuntoDAO assuntoDAO;
	@Inject
	Autorizacao autorizacao;
	@EJB
	Texto texto;
	private Validacao validacao;
	private Permissao permissao;
	private List<Assunto> listaAssuntos;
	private List<Entidade> listaEntidades;
	private UIForm form = new UIForm();
	private UtilController utilController = new UtilController();
	private Processo processo;
	private List<Processo> listaProcessos;
	
	@PostConstruct
	public void init() {
		utilController.setStatus(Status.LISTING);
		permissao = autorizacao.verificaAutorizacao(sessao.getModulosUsuario(), sessao.getPermissoesUsuario());
		buscaTodos();
	}
	public void verificaProcesso(){
		processo.setTipoProcesso(TiposProcesso.Requerimento);
		
	}
	
	public void buscaTodos(){
		try {
			listaProcessos = processoDAO.recuperarPorTodos();
		} catch (Exception e) {
			Mensagem.mostrarErro(texto.getValor("falha_ao_listar_registros"), e.getMessage());
		}
	}
	
	public String voltarHome(){
		return "/index.xhtml";
	}
	public void enviarProblema(){
		init();
		
	}
	public void criar() {
		try {
			setProcesso(new Processo());
			getProcesso().setAtivo('s');
			listaAssuntos = assuntoDAO.recuperarPorTodos();
			listaEntidades = entidadeDAO.recuperarPorTodos();
			setValidacao(new Validacao());
			utilController.setStatus(Status.INSERTING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao tentar incluir registro." + e.getMessage());
		}
	}

	public void editar() {
		try {
			Mensagem.mostrarAviso("Editar: "+ getProcesso().getId().toString());
			utilController.setStatus(Status.EDITING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao tentar editar registro." + e.getMessage());
		}
	}

	public void preparaParaDesativar() {
		try {
			Mensagem.mostrarAviso("Preparando para Desativar: ", getProcesso().getId().toString());
			utilController.setStatus(Status.DELETING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao preparar remoção de registro: " + e.getMessage());
		}
	}

	public void desativar() {
		try {
			if (utilController.getStatus() == Status.DELETING) {
				this.processoDAO.excluir(getProcesso().getId());
				Mensagem.mostrarAviso("Registro desativado com sucesso:", getProcesso().getId().toString());
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
				this.processoDAO.salvar(getProcesso());

				voltarLista();
			}
			utilController.setStatus(Status.LISTING);
		} catch (Exception exeption) {
			Mensagem.mostrarErro("ERRO: ", "ao salvar!");
			System.out.println("Aqui"+exeption.getMessage());
		}
	}

	public void voltarLista() {
		buscaTodos();
		utilController.setStatus(Status.LISTING);
	}
	
	//get e set
	public UIForm getForm() {
		return form;
	}
	public void setForm(UIForm form) {
		this.form = form;
	}
	public UtilController getUtilController() {
		return utilController;
	}
	public void setUtilController(UtilController utilController) {
		this.utilController = utilController;
	}
	public Processo getProcesso() {
		return processo;
	}
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	public List<Processo> getListaProcessos() {
		return listaProcessos;
	}
	public void setListaProcessos(List<Processo> listaProcessos) {
		this.listaProcessos = listaProcessos;
	}
	public Permissao getPermissao() {
		return permissao;
	}

	public List<Entidade> getListaEntidades() {
		return listaEntidades;
	}

	public void setListaEntidades(List<Entidade> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}

	public List<Assunto> getListaAssuntos() {
		return listaAssuntos;
	}

	public void setListaAssuntos(List<Assunto> listaAssuntos) {
		this.listaAssuntos = listaAssuntos;
	}

	public Validacao getValidacao() {
		return validacao;
	}

	public void setValidacao(Validacao validacao) {
		this.validacao = validacao;
	}
	
	

}
