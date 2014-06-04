package br.gov.mds.sebas.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.gov.mds.sebas.dao.TipoContatoDAO;
import br.gov.mds.sebas.modelo.Contato;
import br.gov.mds.sebas.modelo.TipoContato;
import br.gov.mds.sebas.util.Mensagem;

@Named
@ViewScoped
public class ContatoMB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private TipoContatoDAO tipoContatoDAO;
	
	private Contato contato;
	private List<TipoContato> listaTipoContatos;
	
	public void criar(){
		setContato(new Contato());
		getContato().setAtivo('s');
		
		buscaTodosTipoContatos();
	}
	
	public void buscaTodosTipoContatos(){
		try {
			listaTipoContatos = tipoContatoDAO.recuperarPorTodos();
		} catch (Exception e) {
			Mensagem.mostrarErro("Erro ao lista tipo contatos");
		}
	}
	
	//get e set
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public List<TipoContato> getListaTipoContatos() {
		return listaTipoContatos;
	}
	public void setListaTipoContatos(List<TipoContato> listaTipoContatos) {
		this.listaTipoContatos = listaTipoContatos;
	}

}
