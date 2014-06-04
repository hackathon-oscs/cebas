package br.gov.mds.sebas.seguranca;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;


@Entity
public class Modulo implements Serializable{
	private static final long serialVersionUID = -1293096069905445914L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(length=255)
	private String nome;
	
	private String url;
	
	@NotNull
	private int nivel;
	
	@NotNull
	private int ordem;
	
	@NotNull
	private boolean acessivel;
	
	@ManyToOne
	private Modulo subModulo;
	
	@OneToMany(mappedBy="modulo")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Permissao> permissoes;
	
	@OneToMany(mappedBy="subModulo")
	@Cascade(CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Modulo> modulos;
	
	@NotNull
	private char ativo;
	
	public Modulo(String nome, String url, int ordem){
		this.nome = nome;
		this.url = url;
		this.ordem = ordem;
	}
	
	public Modulo(){
		
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public boolean isAcessivel() {
		return acessivel;
	}

	public void setAcessivel(boolean acessivel) {
		this.acessivel = acessivel;
	}

	public Modulo getSubModulo() {
		return subModulo;
	}

	public void setSubModulo(Modulo subModulo) {
		this.subModulo = subModulo;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Modulo)) {
			return false;
		}
		Modulo other = (Modulo) object;
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