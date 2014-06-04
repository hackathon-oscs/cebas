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

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.gov.mds.sebas.dao.ModuloDAO;
import br.gov.mds.sebas.util.LimpaComponentes;
import br.gov.mds.sebas.util.Mensagem;
import br.gov.mds.sebas.util.Status;
import br.gov.mds.sebas.util.Texto;
import br.gov.mds.sebas.util.UtilController;

@Named
@ViewScoped
public class ModuloMB  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	Sessao sessao;
	@EJB
	private ModuloDAO moduloDAO;
	@Inject
	Autorizacao autorizacao;
	@EJB
	Texto texto;

	private Permissao permissao;
	private UIForm form = new UIForm();
	private Modulo modulo;
	private UtilController utilController = new UtilController();
	private List<Modulo> listaModulos;
	private List<Modulo> listaModulosSub;

	private String filtro;
	private String tipoFiltro;
	private List<String> listaPesquisa = new ArrayList<String>();
	
	private TreeNode arvoreModulos;

	@PostConstruct
	public void init() {
		utilController.setStatus(Status.LISTING);
		permissao = autorizacao.verificaAutorizacao(sessao.getModulosUsuario(), sessao.getPermissoesUsuario());
		buscaTodos();
	}

	public void buscarPorModulos(){
		try {
			listaModulosSub = moduloDAO.recuperarPorModulo(modulo.getSubModulo());
		} catch (Exception e) {
			Mensagem.mostrarErro("FALHA: Ao tentar listar registros. ", e.getMessage());
		}
	}
	
	public void buscaTodos(){
		try {
			listaModulos = moduloDAO.recuperarPorTodos();
			arvoreModulos = new DefaultTreeNode("root", null);
			carregarArvoreModulos(listaModulos, arvoreModulos, true);
		} catch (Exception e) {
			Mensagem.mostrarErro(texto.getValor("falha_ao_listar_registros"), e.getMessage());
		}
	}
	
	/**
	 * Carrega árvore de módulos
	 * Primeiro recupera todos os elementos da raíz, pelo primeiro chamado do método,
	 * depois recupera os filhos de cada um
	 * @param modulos
	 * @param noPai
	 * @para elementoRaiz (para saber se é elemento da raiz)
	 */
	private void carregarArvoreModulos(List<Modulo> modulos, TreeNode noPai, boolean elementoRaiz) {
		for (Modulo m : modulos) {
			if (m.getAtivo() == 's') {
				if ((elementoRaiz && m.getNivel() == 0) || !elementoRaiz) {
					noPai.setExpanded(true);
					TreeNode noFilho = new DefaultTreeNode(m, noPai);
					if (m.getModulos().size() > 0) {
						carregarArvoreModulos(m.getModulos(), noFilho, false);
					}
				}
			}
		}
	}

	public String voltarHome(){
		return "/index.xhtml";
	}
	
	public void criar() {
		try {
			setModulo(new Modulo());
			getModulo().setAtivo('s');

			utilController.setStatus(Status.INSERTING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao tentar incluir registro." + e.getMessage());
		}
	}

	public void editar() {
		try {
			Mensagem.mostrarAviso("Editar: "+ getModulo().getNome());
			utilController.setStatus(Status.EDITING);
			buscarPorModulos();
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao tentar editar registro." + e.getMessage());
		}
	}

	public void preparaParaDesativar() {
		try {
			Mensagem.mostrarAviso("Preparando para Desativar: ", getModulo().getNome());
			utilController.setStatus(Status.DELETING);
		} catch (Exception e) {
			Mensagem.mostrarErro("Falha: ", "Erro ao preparar remoção de registro: " + e.getMessage());
		}
	}

	public void desativar() {
		try {
			if (utilController.getStatus() == Status.DELETING) {
				this.moduloDAO.excluir(getModulo().getId());
				Mensagem.mostrarAviso("Registro desativado com sucesso:", getModulo().getNome());
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
				if (modulo.getSubModulo() != null) {
					modulo.setNivel( modulo.getSubModulo().getNivel()+1);
				}else{
					modulo.setNivel( 0);
				}
				this.moduloDAO.salvar(getModulo());
				Mensagem.mostrarAviso("Registro salvo com sucesso:", getModulo().getNome());
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

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public UtilController getUtilController() {
		return utilController;
	}

	public void setUtilController(UtilController utilController) {
		this.utilController = utilController;
	}

	public List<Modulo> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<Modulo> listaModulos) {
		this.listaModulos = listaModulos;
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

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public List<Modulo> getListaSubModulos() throws Exception {
		return moduloDAO.recuperarPorTodos();
	}

	public List<Modulo> getListaModulosSub() {
		return listaModulosSub;
	}

	public void setListaModulosSub(List<Modulo> listaModulosSub) {
		this.listaModulosSub = listaModulosSub;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public TreeNode getArvoreModulos() {
		return arvoreModulos;
	}

	public void setArvoreModulos(TreeNode arvoreModulos) {
		this.arvoreModulos = arvoreModulos;
	}
	
}
