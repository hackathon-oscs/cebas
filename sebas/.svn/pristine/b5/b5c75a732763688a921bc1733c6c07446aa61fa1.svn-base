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
import br.gov.mds.sebas.seguranca.Permissao;
import br.gov.mds.sebas.util.LimpaComponentes;
import br.gov.mds.sebas.util.Mensagem;
import br.gov.mds.sebas.util.Status;
import br.gov.mds.sebas.util.Texto;
import br.gov.mds.sebas.util.UtilController;

@Named
@ViewScoped
public class EntidadePublicaMB implements Serializable{
	private static final long serialVersionUID = 1L;

	@EJB
	private EntidadeDAO entidadeDAO;
	@EJB
	private EstadoDAO estadoDAO;
	@EJB
	private AtividadeEconomicaDAO AtividadeEconomicaDAO;
	@EJB
	private TipoDocumentoDAO tipoDocumentoDAO;
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

	private FileUploadEvent file;
	private String destination="C:\\cebas\\";

	@PostConstruct
	public void init() {
		utilController.setStatus(Status.LISTING);
		criar();
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

	public void salvar() {
		try {
			this.entidadeDAO.salvar(getEntidade());
			voltarLista();
		} catch (Exception exeption) {
			Mensagem.mostrarErro("ERRO: ", "ao salvar!");
		}
	}

	public void voltarLista() {
		LimpaComponentes.cleanSubmittedValues(form);
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

	public void upload(FileUploadEvent event) { 
		System.out.println("entrou no metodo upload");
		try {
			setFile(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setarCaminho(String fileName){
		documentoNecessario.setCaminho(destination + fileName);
		try {
			copyFile(file.getFile().getFileName(), file.getFile().getInputstream());
		} catch (IOException e) {
			Mensagem.mostrarErro("Erro ao anexar arquivo");
		}
	}

	public void copyFile(String fileName, InputStream in) {
		try {
			OutputStream out = new FileOutputStream(new File(destination + fileName));

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

	public void adicionaDocumento(){
		System.out.println("entrou no metodo adiciona documento");
		setarCaminho(file.getFile().getFileName());
		entidade.adicionarDocumentos(getDocumentoNecessario());
		criarDocumento();
	}
	
	public String index(){
		return "/index.xhtml";
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

	public FileUploadEvent getFile() {
		return file;
	}

	public void setFile(FileUploadEvent file) {
		this.file = file;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}


}
