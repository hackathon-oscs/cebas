package br.gov.mds.sebas.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.gov.mds.cebas.enumeration.TiposProcesso;

@Entity
public class Processo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="processoid")
	private Long id;
	@Column(name = "iniciocertificacao")
	@Temporal(TemporalType.DATE)
	private Calendar inicioCertificacao;
	@Column(name = "fimcertificacao")
	@Temporal(TemporalType.DATE)
	private Calendar fimCertificacao;
	@Temporal(TemporalType.DATE)
	@Column(name = "datapublicacao")
	private Calendar dataPublicacaoDOU;
	@OneToMany(mappedBy = "processo")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade(CascadeType.ALL)
	private List<Validacao> listaValidacao;
	@ManyToOne
	private Entidade entidade;
	private TiposProcesso tipoProcesso;
	@ManyToOne
	private Assunto assuntoProcesso;
	private char ativo;
	@ManyToOne()
	private StatusProcesso status;
	
	public StatusProcesso getStatus() {
		return status;
	}
	public void setStatus(StatusProcesso status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getInicioCertificacao() {
		return inicioCertificacao;
	}
	public void setInicioCertificacao(Calendar inicioCertificacao) {
		this.inicioCertificacao = inicioCertificacao;
	}
	public Calendar getFimCertificacao() {
		return fimCertificacao;
	}
	public void setFimCertificacao(Calendar fimCertificacao) {
		this.fimCertificacao = fimCertificacao;
	}
	public Calendar getDataPublicacaoDOU() {
		return dataPublicacaoDOU;
	}
	public void setDataPublicacaoDOU(Calendar dataPublicacaoDOU) {
		this.dataPublicacaoDOU = dataPublicacaoDOU;
	}
	public List<Validacao> getListaValidacao() {
		return listaValidacao;
	}
	public void setListaValidacao(List<Validacao> listaValidacao) {
		this.listaValidacao = listaValidacao;
	}

	public TiposProcesso getTipoProcesso() {
		return tipoProcesso;
	}
	public void setTipoProcesso(TiposProcesso tipoProcesso) {
		this.tipoProcesso = tipoProcesso;
	}
	public Assunto getAssuntoProcesso() {
		return assuntoProcesso;
	}
	public void setAssuntoProcesso(Assunto assuntoProcesso) {
		this.assuntoProcesso = assuntoProcesso;
	}
	public char getAtivo() {
		return ativo;
	}
	
	public Entidade getEntidade() {
		return entidade;
	}
	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}
	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}
	
	
}
