package br.gov.mds.sebas.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.gov.mds.sebas.dao.EntidadeDAO;
import br.gov.mds.sebas.modelo.Entidade;
import br.gov.mds.sebas.util.Mensagem;
import br.gov.mds.sebas.util.Status;
import br.gov.mds.sebas.util.Texto;

@Named
@ViewScoped
public class ConsultaMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private EntidadeDAO entidadeDAO;
	@EJB
	Texto texto;
	@PostConstruct
	public void init() {
		cnpj ="";
	}
	private Entidade entidade;
	private List<Entidade> listaEntidades;
	private String cnpj;
	
	public void buscaPorCnpj(){
		try {
			entidade = entidadeDAO.recuperarPorCNPJ(cnpj);
		} catch (Exception e) {
			Mensagem.mostrarErro("Erro ao buscar por CNPJ");
		}
	}
	
	
	
	
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
	
	
	
}
