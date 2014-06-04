package br.gov.mds.sebas.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;

@Entity
public class Presidente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="presidenteid")
	private Long id;
	
	private String nome;
	
	@Temporal(TemporalType.DATE)
	private Calendar nascimento = Calendar.getInstance();
	
	private String cpf;
	
	private String rg;
	
	private String orgaoExpeditor;
	
	@Temporal(TemporalType.DATE)
	private Calendar inicioMandato = Calendar.getInstance();
	
	@Temporal(TemporalType.DATE)
	private Calendar fimMandato = Calendar.getInstance();
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Contato> contatos;
	@Email
	private String email;
	private String telefone;
	
	@OneToOne(mappedBy="presidente")
	private Entidade entidade;
	
	@NotNull
	private char ativo;

	public Entidade getEntidade() {
		return entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getNascimento() {
		return nascimento;
	}

	public void setNascimento(Calendar nascimento) {
		this.nascimento = nascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpeditor() {
		return orgaoExpeditor;
	}

	public void setOrgaoExpeditor(String orgaoExpeditor) {
		this.orgaoExpeditor = orgaoExpeditor;
	}

	public Calendar getInicioMandato() {
		return inicioMandato;
	}

	public void setInicioMandato(Calendar inicioMandato) {
		this.inicioMandato = inicioMandato;
	}

	public Calendar getFimMandato() {
		return fimMandato;
	}

	public void setFimMandato(Calendar fimMandato) {
		this.fimMandato = fimMandato;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Presidente)) {
			return false;
		}
		Presidente other = (Presidente) object;
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
