package br.gov.mds.sebas.seguranca;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Permissao implements Serializable{
	private static final long serialVersionUID = -638616824414008670L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	private Modulo modulo;
	
	@NotNull
	private char acesso;
	
	@NotNull
	private char execucao;
	
	@NotNull
	private char inclusao;
	
	@NotNull
	private char alteracao;
	
	@NotNull
	private char exclusao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="perfilid")
	private Perfil perfil;
	
	@NotNull
	private char ativo;

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public char getAcesso() {
		return acesso;
	}

	public void setAcesso(char acesso) {
		this.acesso = acesso;
	}

	public char getExecucao() {
		return execucao;
	}

	public void setExecucao(char execucao) {
		this.execucao = execucao;
	}

	public char getInclusao() {
		return inclusao;
	}

	public void setInclusao(char inclusao) {
		this.inclusao = inclusao;
	}

	public char getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(char alteracao) {
		this.alteracao = alteracao;
	}

	public char getExclusao() {
		return exclusao;
	}

	public void setExclusao(char exclusao) {
		this.exclusao = exclusao;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Permissao)) {
			return false;
		}
		Permissao other = (Permissao) object;
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
