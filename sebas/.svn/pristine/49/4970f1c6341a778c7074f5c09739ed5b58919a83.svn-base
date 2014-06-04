package br.gov.mds.sebas.modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Validacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="validacaoid")
	private Long id;
	private String observacao;
	private String decisao;
	private String motivo;
	private Calendar dataAlteracao;
	@ManyToOne
	@JoinColumn(name ="processoid")
	private Processo processo;
	@ManyToOne
	private DocumentoNecessario documentoNecessario;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getDecisao() {
		return decisao;
	}
	public void setDecisao(String decisao) {
		this.decisao = decisao;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public DocumentoNecessario getDocumentoNecessario() {
		return documentoNecessario;
	}
	public void setDocumentoNecessario(DocumentoNecessario documentoNecessario) {
		this.documentoNecessario = documentoNecessario;
	}
	
	
	
	
}
