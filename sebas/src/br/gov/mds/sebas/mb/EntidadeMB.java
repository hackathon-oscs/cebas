package br.gov.mds.sebas.mb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIForm;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.gov.mds.sebas.dao.AtividadeEconomicaDAO;
import br.gov.mds.sebas.dao.EntidadeDAO;
import br.gov.mds.sebas.dao.EstadoDAO;
import br.gov.mds.sebas.dao.TipoDocumentoDAO;
import br.gov.mds.sebas.modelo.AtividadeEconomica;
import br.gov.mds.sebas.modelo.DocumentoNecessario;
import br.gov.mds.sebas.modelo.Endereco;
import br.gov.mds.sebas.modelo.Entidade;
import br.gov.mds.sebas.modelo.Estado;
import br.gov.mds.sebas.modelo.Presidente;
import br.gov.mds.sebas.modelo.TipoDocumento;
import br.gov.mds.sebas.seguranca.Autorizacao;
import br.gov.mds.sebas.seguranca.Permissao;
import br.gov.mds.sebas.seguranca.Sessao;
import br.gov.mds.sebas.util.LimpaComponentes;
import br.gov.mds.sebas.util.Mensagem;
import br.gov.mds.sebas.util.Status;
import br.gov.mds.sebas.util.Texto;
import br.gov.mds.sebas.util.UtilController;

@Named
@ViewScoped
public class EntidadeMB implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	Sessao sessao;
	@EJB
	private EntidadeDAO entidadeDAO;
	@EJB
	private EstadoDAO estadoDAO;
	@EJB
	private AtividadeEconomicaDAO AtividadeEconomicaDAO;
	@EJB
	private TipoDocumentoDAO tipoDocumentoDAO;
	@Inject
	Autorizacao autorizacao;
	@Inject
	private ContatoMB contatoMB;
	@EJB
	Texto texto;

	private Permissao permissao;
	private UIForm form = new UIForm();
	private UtilController utilController = new UtilController();
	private Entidade entidade;
	private Estado estado;
	private DocumentoNecessario documentoNecessario;
	private List<Entidade> listaEntidades;
	private List<Estado> listaEstados;
	private List<AtividadeEconomica> listaAtividadeEconomicas;
	private List<TipoDocumento> listaTipoDocumentos;

	private UploadedFile file;
	private String destination="C:\\cebas\\";

	@PostConstruct
	public void init() {
		utilController.setStatus(Status.LISTING);
		permissao = autorizacao.verificaAutorizacao(sessao.getModulosUsuario(), sessao.getPermissoesUsuario());
		buscaTodos();
	}

	public void buscaTodos(){
		try {
			listaEntidades = entidadeDAO.recuperarPorTodos();
		} catch (Exception e) {
			Mensagem.mostrarErro(texto.getValor("falha_ao_listar_registros"), e.getMessage());
		}
	}

	public String voltarHome(){
		return "/index.xhtml";
	}

	public void criar() {
		try {
			setEntidade(new Entidade());
			getEntidade().setAtivo('s');
			getEntidade().setEndereco(new Endereco());
			getEntidade().getEndereco().setAtivo('s');
			getEntidade().setEndereco(getEntidade().getEndereco());
			getEntidade().setPresidente(new Presidente());
			getEntidade().getPresidente().setAtivo('s');
			getEntidade().setPresidente(getEntidade().getPresidente());
			contatoMB.criar();
			criarDocumento();
			buscaTodosEstados();
			buscaTodasAtividadesEconomicas();
			buscaTodosTiposEntidades();

			utilController.setStatus(Status.INSERTING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao tentar incluir registro." + e.getMessage());
		}
	}
	
	public void criarDocumento(){
		setDocumentoNecessario(new DocumentoNecessario());
		getDocumentoNecessario().setAtivo('s');
	}

	public void editar() {
		try {
			contatoMB.criar();
			buscaTodosEstados();
			buscaTodasAtividadesEconomicas();
			buscaTodosTiposEntidades();
			Mensagem.mostrarAviso("Editar: "+ getEntidade().getNomeFantasia());
			utilController.setStatus(Status.EDITING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao tentar editar registro." + e.getMessage());
		}
	}

	public void preparaParaDesativar() {
		try {
			buscaTodosEstados();
			buscaTodasAtividadesEconomicas();
			buscaTodosTiposEntidades();
			Mensagem.mostrarAviso("Preparando para Desativar: ", getEntidade().getNomeFantasia());
			utilController.setStatus(Status.DELETING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao preparar remoção de registro: " + e.getMessage());
		}
	}

	public void desativar() {
		try {
			if (utilController.getStatus() == Status.DELETING) {
				this.entidadeDAO.excluir(getEntidade().getId());
				Mensagem.mostrarAviso("Registro desativado com sucesso:", getEntidade().getNomeFantasia());
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
				
				this.entidadeDAO.salvar(getEntidade());
				Mensagem.mostrarAviso("Registro salvo com sucesso:", getEntidade().getNomeFantasia());
				sessao.construirMenu();

				voltarLista();
			}
			utilController.setStatus(Status.LISTING);
		} catch (Exception exeption) {
			Mensagem.mostrarErro("ERRO: ", "ao salvar!");
		}
	}

	public void voltarLista() {
		LimpaComponentes.cleanSubmittedValues(form);
		buscaTodos();
		utilController.setStatus(Status.LISTING);
	}

	public void buscaTodosEstados(){
		try {
			listaEstados = estadoDAO.recuperarPorTodos();
		} catch (Exception e) {
			Mensagem.mostrarErro("Erro ao listar estado");
		}
	}

	public void buscaTodasAtividadesEconomicas(){
		try {
			listaAtividadeEconomicas = AtividadeEconomicaDAO.recuperarPorTodos();
		} catch (Exception e) {
			Mensagem.mostrarErro("Erro ao listar atividade econômica");
		}
	}

	public void buscaTodosTiposEntidades(){
		try {
			listaTipoDocumentos = tipoDocumentoDAO.recuperarPorTodos();
		} catch (Exception e) {
			Mensagem.mostrarErro("Erro ao listar tipo entidade");
		}
	}

	public void adicionaContato(){
		entidade.adicionarContatos(contatoMB.getContato());
		contatoMB.criar();
	}

//	public void upload(FileUploadEvent event) { 
//		System.out.println("entrou no metodo upload");
//		try {
//			setFile(event);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void copyFile(String fileName, InputStream in) {
		System.out.println("entrou no metodo copyFile");
		try {
			OutputStream out = new FileOutputStream(new File(destination + fileName));
			System.out.println("depois do out");
			documentoNecessario.setCaminho(destination + fileName);
			System.out.println("Caminho do documento: "+documentoNecessario.getCaminho());

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("New file created!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public void handleFileUpload(FileUploadEvent event){
		System.out.println("chegou no file upload");
		file =event.getFile();
		
	}
	public void adicionaDocumento(){
		System.out.println("entrou no metodo adiciona documento");
		if(file == null)
			System.out.println("file null");
		else
			System.out.println("file normal: "+file.getFileName());
		
		try {
//			System.out.println("File Input: "+file.getInputstream());
			System.out.println("antes de chamar o metodo copyFile");
			copyFile(file.getFileName(), file.getInputstream());
		} catch (IOException e) {
			System.out.println("Erro ao copiar: "+e.getMessage());
		}
		entidade.adicionarDocumentos(getDocumentoNecessario());
		criarDocumento();
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
	public Entidade getEntidade() {
		return entidade;
	}
	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}
	public List<Entidade> getListaEntidades() {
		return listaEntidades;
	}
	public void setListaEntidades(List<Entidade> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}
	public Permissao getPermissao() {
		return permissao;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public List<AtividadeEconomica> getListaAtividadeEconomicas() {
		return listaAtividadeEconomicas;
	}

	public void setListaAtividadeEconomicas(
			List<AtividadeEconomica> listaAtividadeEconomicas) {
		this.listaAtividadeEconomicas = listaAtividadeEconomicas;
	}

	public DocumentoNecessario getDocumentoNecessario() {
		return documentoNecessario;
	}

	public void setDocumentoNecessario(DocumentoNecessario documentoNecessario) {
		this.documentoNecessario = documentoNecessario;
	}

	public List<TipoDocumento> getListaTipoDocumentos() {
		return listaTipoDocumentos;
	}

	public void setListaTipoDocumentos(List<TipoDocumento> listaTipoDocumentos) {
		this.listaTipoDocumentos = listaTipoDocumentos;
	}


	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}


}
