package br.gov.mds.sebas.seguranca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIForm;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.treetable.TreeTable;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.gov.mds.sebas.dao.ModuloDAO;
import br.gov.mds.sebas.dao.PerfilDAO;
import br.gov.mds.sebas.dao.PermissaoDAO;
import br.gov.mds.sebas.util.LimpaComponentes;
import br.gov.mds.sebas.util.Mensagem;

@Named
@ViewScoped
public class PermissaoMB implements Serializable{
	private static final long serialVersionUID = -8609268745673490033L;
	@Inject
	Sessao sessao;
	@Inject
	Autorizacao autorizacao;
	@EJB
	private PerfilDAO perfilDAO;
	@EJB
	private PermissaoDAO permissaoDAO;
	@EJB
	private ModuloDAO moduloDAO;
	
	private Permissao permissaoAcesso;
	private UIForm form = new UIForm();
	private Perfil perfilSelecionado;
	private Permissao permissaoSelecionada;
	private Modulo moduloSelecionado;
	private List<Perfil> listaPerfis;
	private List<Modulo> modulosPerfil;
	private Set<Modulo> modulosSelecionados;
	List<Permissao> listaPermissoesPorPerfil;
	
	private TreeNode root;
	private TreeNode selectedNode;
	private TreeNode rootModulo;
	private TreeNode[] selectedNodeModulos;
	private TreeTable treeModulo = new TreeTable();
	
	@PostConstruct
	public void init() {
		permissaoAcesso = autorizacao.verificaAutorizacao(sessao.getModulosUsuario(), sessao.getPermissoesUsuario());
	}
	
	public void carregaModulo(){
		LimpaComponentes.cleanSubmittedValues(form);
		selectedNodeModulos = null;
		modulosPerfil = new ArrayList<>();
		listaPermissoesPorPerfil = new ArrayList<>();
		List<Modulo> listaModulos = new ArrayList<>();
		
		listaPermissoesPorPerfil = perfilSelecionado.getPermissoes();
		for(Permissao p : listaPermissoesPorPerfil){
			modulosPerfil.add(p.getModulo());
		}
		try {
			listaModulos = moduloDAO.recuperarPorTodos();
		} catch (Exception e) {
			Mensagem.mostrarErro("Erro ao listar módulos: ", e.getMessage());
		}
		rootModulo = new DefaultTreeNode("rootModulo", null);
		for(Modulo m : listaModulos){
			if (m.getNivel() == 0) {
				TreeNode node = new DefaultTreeNode(m, rootModulo);
				if(modulosPerfil.contains(m)){
					node.setSelected(true);
				} 
				if(m.getModulos().size() > 0){
					carregaModuloFilho(m.getModulos(), node);
				}
			}
		}
		carregaPermisao(listaPermissoesPorPerfil);
	}
	
	public void carregaModuloFilho(List<Modulo> modulos, TreeNode nodePai){
		for(Modulo m2 : modulos){
			if(m2.getAtivo() == 's'){
				TreeNode nodeFilho = new DefaultTreeNode(m2, nodePai);
				nodeFilho.setSelected(false);
				if(modulosPerfil.contains(m2)){
					nodeFilho.setSelected(true);
				}
				if(m2.getModulos().size() > 0){
					carregaModuloFilho(m2.getModulos(), nodeFilho);
				}
			}
		}
	}
	
	public void carregaPermisao(List<Permissao> listaPermissoesPorPerfil){
		root = new DefaultTreeNode("root", null);
//		List<Permissao> listaPermissoesPorPerfil = new ArrayList<>();
//		listaPermissoesPorPerfil = perfilSelecionado.getPermissoes();
		
		for (Permissao perm : listaPermissoesPorPerfil) {
			if(perm.getModulo().getNivel() == 0){
				TreeNode node = new DefaultTreeNode(perm.getModulo(), root);
				if(perm.getModulo().getModulos().size() > 0){
					carregaPermissaoFilho(perm.getModulo().getModulos(), node);
				}
			}
		}
	}
	
	public void carregaPermissaoFilho(List<Modulo> modulos, TreeNode nodePai){
		for(Modulo m : modulos){
			if(verificaPermissao(m)){
				TreeNode node = new DefaultTreeNode(m, nodePai);
				if(m.getModulos().size() > 0){
					carregaPermissaoFilho(m.getModulos(), node);
				}
			}
		}
	}
	
	public boolean verificaPermissao(Modulo mod){
		for(Permissao p : mod.getPermissoes()){
			if(perfilSelecionado.getPermissoes().contains(p)){
				return true;
			}
		}
		return false;
	}
	
	public void salvarPermissaoAcesso(){
		modulosSelecionados = new HashSet<>();
		List<Permissao> permissoesExcluidas = new ArrayList<>();
		for(TreeNode tree : selectedNodeModulos){
//			Modulo m = (Modulo) tree.getData();
//			System.out.println("Modulo selecionado: "+m.getNome());
//			modulosSelecionados.add(m);
//			if(!modulosPerfil.contains(m)){
//				adicionaPermissao(m);
//				System.out.println("Novo modulo: "+m.getNome());
//			}
			if(tree.isLeaf()){
				Modulo m = (Modulo) tree.getData();
				modulosSelecionados.add(m);
				if(!modulosPerfil.contains(m)){
					adicionaPermissao(m);
				}
				salvarPermissaoAcessoPai(tree.getParent());
			}
		}
		
		for(Permissao p : listaPermissoesPorPerfil){
			if(!modulosSelecionados.contains(p.getModulo())){
				permissoesExcluidas.add(p);
			}
		}
		
		try {
			perfilDAO.salvar(getPerfilSelecionado());
			for(Permissao p : permissoesExcluidas){
				this.permissaoDAO.excluir(p.getId());
			}
		} catch (Exception e) {
			Mensagem.mostrarErro("Erro ao excluir");
		}
		
		sessao.construirMenu();
		setPerfilSelecionado(null);
	}
	
	public void salvarPermissaoAcessoPai(TreeNode treePai){
		if(!treePai.equals(rootModulo)){
			Modulo m = (Modulo) treePai.getData();
			modulosSelecionados.add(m);
			if(!modulosPerfil.contains(m)){
				adicionaPermissao(m);
			}
			salvarPermissaoAcessoPai(treePai.getParent());
		}
	}
	
	public void adicionaPermissao(Modulo m){
		Permissao p = new Permissao();
		p.setAtivo('s');
		p.setAcesso('s');
		p.setInclusao('n');
		p.setAlteracao('n');
		p.setExclusao('n');
		p.setExecucao('n');
		p.setModulo(m);
		perfilSelecionado.adicionaPermissao(p);
	}
	
	public void editar(){
		moduloSelecionado = (Modulo) selectedNode.getData();
		permissaoSelecionada = permissaoDAO.recuperarPermissaoPorModuloPerfil(getModuloSelecionado(), getPerfilSelecionado());
	}
	
	public void salvar(){
		try {
			permissaoDAO.salvar(getPermissaoSelecionada());
		} catch (Exception e) {
			Mensagem.mostrarErro("Erro ao salvar: "+e.getMessage());
		}
	}
	
	public void todosSim(){
		permissaoSelecionada.setAcesso('s');
		permissaoSelecionada.setInclusao('s');
		permissaoSelecionada.setAlteracao('s');
		permissaoSelecionada.setExclusao('s');
		permissaoSelecionada.setExecucao('s');
	}
	
	public void todosNao(){
		permissaoSelecionada.setAcesso('n');
		permissaoSelecionada.setInclusao('n');
		permissaoSelecionada.setAlteracao('n');
		permissaoSelecionada.setExclusao('n');
		permissaoSelecionada.setExecucao('n');
	}

	/* GET E SET */
	public List<Perfil> getListaPerfis() {
		if(listaPerfis == null){
			try {
				listaPerfis = perfilDAO.recuperarPorTodos();
			} catch (Exception e) {
				Mensagem.mostrarErro("Erro ao listar perfis");
			}
		}
		return listaPerfis;
	}

	public void setListaPerfis(List<Perfil> listaPerfis) {
		this.listaPerfis = listaPerfis;
	}

	public Perfil getPerfilSelecionado() {
		return perfilSelecionado;
	}

	public void setPerfilSelecionado(Perfil perfilSelecionado) {
		this.perfilSelecionado = perfilSelecionado;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public TreeNode getRoot() {
		return root;
	}

	public Permissao getPermissaoAcesso() {
		return permissaoAcesso;
	}

	public Permissao getPermissaoSelecionada() {
		return permissaoSelecionada;
	}

	public void setPermissaoSelecionada(Permissao permissaoSelecionada) {
		this.permissaoSelecionada = permissaoSelecionada;
	}

	public Modulo getModuloSelecionado() {
		return moduloSelecionado;
	}

	public void setModuloSelecionado(Modulo moduloSelecionado) {
		this.moduloSelecionado = moduloSelecionado;
	}

	public TreeNode getRootModulo() {
		return rootModulo;
	}

	public void setRootModulo(TreeNode rootModulo) {
		this.rootModulo = rootModulo;
	}

	public TreeNode[] getSelectedNodeModulos() {
		return selectedNodeModulos;
	}

	public void setSelectedNodeModulos(TreeNode[] selectedNodeModulos) {
		this.selectedNodeModulos = selectedNodeModulos;
	}

	public List<Modulo> getModulosPerfil() {
		return modulosPerfil;
	}

	public void setModulosPerfil(List<Modulo> modulosPerfil) {
		this.modulosPerfil = modulosPerfil;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public TreeTable getTreeModulo() {
		return treeModulo;
	}

	public void setTreeModulo(TreeTable treeModulo) {
		this.treeModulo = treeModulo;
	}

	public Set<Modulo> getModulosSelecionados() {
		return modulosSelecionados;
	}

	public void setModulosSelecionados(Set<Modulo> modulosSelecionados) {
		this.modulosSelecionados = modulosSelecionados;
	}
}
