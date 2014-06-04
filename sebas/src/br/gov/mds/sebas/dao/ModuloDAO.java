package br.gov.mds.sebas.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.mds.sebas.seguranca.Modulo;

@Stateless
public class ModuloDAO extends DaoGenerico<Modulo> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	@Override
	protected Class<Modulo> getClasseDominio() {
		// TODO Auto-generated method stub
		return Modulo.class;
	}
	
	public List<Modulo> recuperarPorModulo(Modulo moduloPai) throws Exception {
		try {
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Modulo> cq = cb.createQuery(getClasseDominio());
			Root<Modulo> root = cq.from(getClasseDominio());
			cq.select(root);

			Predicate predicate = cb.equal( root.get("subModulo") , moduloPai);			
			cq.where(predicate);
			cq.orderBy(cb.asc(root.get("nivel")));
			cq.orderBy(cb.asc(root.get("ordem")));
			return em.createQuery(cq).getResultList();
		} catch (Exception e) {
			throw new Exception("Erro na query: " + e.getMessage());
		}
	}

}
