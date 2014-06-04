package br.gov.mds.sebas.dao;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.mds.sebas.modelo.Entidade;

@Stateless
public class EntidadeDAO extends DaoGenerico<Entidade> implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	@Override
	protected Class<Entidade> getClasseDominio() {
		// TODO Auto-generated method stub
		return Entidade.class;
	}
	
	public Entidade recuperarPorCNPJ(String cnpj) throws Exception {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Entidade> cq = cb.createQuery(Entidade.class);
			Root<Entidade> root = cq.from(Entidade.class);
			Predicate predicate = cb.equal(root.get("cnpj"), cnpj);
			cq.where(predicate);

			return em.createQuery(cq).getSingleResult();
		} catch (Exception e) {
			throw new Exception("Erro na query: " + e);
		}
	}

}
