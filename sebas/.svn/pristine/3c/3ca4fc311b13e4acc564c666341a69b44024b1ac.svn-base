package br.gov.mds.sebas.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.mds.sebas.seguranca.Modulo;
import br.gov.mds.sebas.seguranca.Perfil;
import br.gov.mds.sebas.seguranca.Permissao;
import br.gov.mds.sebas.util.Texto;

@Stateless
public class PermissaoDAO extends DaoGenerico<Permissao> implements Serializable {
	private static final long serialVersionUID = -872215681189298424L;

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	Texto texto;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	protected Class<Permissao> getClasseDominio() {
		return Permissao.class;
	}
	
	public List<Permissao> recuperarPorFiltroModulo(String tipoFiltro, String filtro) throws Exception {
		try {
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Permissao> cq = cb.createQuery(getClasseDominio());
			Root<Permissao> root = cq.from(getClasseDominio());
			cq.select(root);

			Predicate predicate = cb.like(cb.upper(root.get("modulo").<String> get(tipoFiltro)), "%"
							+ filtro.toUpperCase() + "%");
			cq.where(predicate);
			return em.createQuery(cq).getResultList();
		} catch (Exception e) {
			throw new Exception("Erro na query: " + e.getMessage());
		}
	}
	
	public List<Permissao> recuperarPermissaoPorUsuario(Perfil perfil) {
		String sql = "SELECT p FROM Permissao p "
				+ "inner join p.modulo modulo "
				+ "inner join p.perfis perfil "
				+ "where perfil.id=:perfil "
				+ "group by p ";
		Query query = em.createQuery(sql);
		query.setParameter("perfil", perfil.getId());
		@SuppressWarnings("unchecked")
		List<Permissao> permissoes = query.getResultList();
		return permissoes;
	}
	
	public List<Permissao> recuperarPorPerfil(Perfil perfil) throws Exception {
		String sql = "SELECT p FROM Permissao p "
				+ "inner join p.modulo modulo "
				+ "inner join p.perfis perfil "
				+ "where perfil.id=:perfil AND "
				+ "modulo.nivel = "+ 0;
//				+ " group by p.modulo.ordem ";
		Query query = em.createQuery(sql);
		query.setParameter("perfil", perfil.getId());
		@SuppressWarnings("unchecked")
		List<Permissao> permissoes = query.getResultList();
		return permissoes;
	}
	
	public Permissao recuperarPermissaoPorModuloPerfil(Modulo modulo, Perfil perfil){
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Permissao> cq = cb.createQuery(getClasseDominio());
		Root<Permissao> root = cq.from(getClasseDominio());
		cq.select(root);

		Predicate predicate = cb.and(cb.equal(root.get("modulo"), modulo), 
				cb.equal(root.get("perfil"), perfil));
		cq.where(predicate);
		return em.createQuery(cq).getSingleResult();
	}
	
	public void excluir(Permissao permissao) throws Exception {
		EntityManager em = getEntityManager();
		try {
			String sql = "DELETE FROM Permissao p WHERE p.id =:id";
			Query query = em.createQuery(sql);
			query.setParameter("id", permissao.getId());
			query.executeUpdate();
			
			
		} catch (Exception e) {
			throw new Exception(texto.getValor("erro_comando_sql") + e.getMessage());
		}
	}

}
