package br.gov.mds.sebas.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Entidade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="entidadeid")
	private Long id;
	
	@NotBlank
	private String cnpj;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataAbertura = Calendar.getInstance();
	
	private String cnpjMatriz;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataFundacao = Calendar.getInstance();
	
	private String nomeEmpresarial;
	
	private String nomeFantasia;
	
	@ManyToOne
	@JoinColumn(name="atividadeeconomicaid")
	private AtividadeEconomica atividadeEconomica;
	
	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="enderecoid")
	private Endereco endereco;
	
	@ManyToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="presidenteid")
	private Presidente presidente;
	
	@OneToMany(mappedBy="entidade")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade(CascadeType.ALL)
	private List<Contato> contatos;
	
	@OneToMany(mappedBy="entidade")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade(CascadeType.ALL)
	private List<DocumentoNecessario> documentoNecessarios;
	
	@NotNull
	private char ativo;

	public List<DocumentoNecessario> getDocumentoNecessarios() {
		return documentoNecessarios;
	}

	public void setDocumentoNecessarios(
			List<DocumentoNecessario> documentoNecessarios) {
		this.documentoNecessarios = documentoNecessarios;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Calendar getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Calendar dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getCnpjMatriz() {
		return cnpjMatriz;
	}

	public void setCnpjMatriz(String cnpjMatriz) {
		this.cnpjMatriz = cnpjMatriz;
	}

	public Calendar getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Calendar dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public String getNomeEmpresarial() {
		return nomeEmpresarial;
	}

	public void setNomeEmpresarial(String nomeEmpresarial) {
		this.nomeEmpresarial = nomeEmpresarial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public AtividadeEconomica getAtividadeEconomica() {
		return atividadeEconomica;
	}

	public void setAtividadeEconomica(AtividadeEconomica atividadeEconomica) {
		this.atividadeEconomica = atividadeEconomica;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Presidente getPresidente() {
		return presidente;
	}

	public void setPresidente(Presidente presidente) {
		this.presidente = presidente;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}
	
	public void adicionarContatos(Contato contato){
		if(contatos == null)
			contatos = new ArrayList<>();
		contato.setEntidade(this);
		contatos.add(contato);
	}
	
	public void adicionarDocumentos(DocumentoNecessario documentoNecessario){
		if(documentoNecessarios == null)
			documentoNecessarios = new ArrayList<>();
		documentoNecessario.setEntidade(this);
		documentoNecessarios.add(documentoNecessario);
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Entidade)) {
			return false;
		}
		Entidade other = (Entidade) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return getId().toString();
	}

}
