package br.gov.mds.sebas.dao;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.gov.mds.sebas.modelo.Assunto;

@Stateless
public class AssuntoDAO extends DaoGenerico<Assunto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	protected Class<Assunto> getClasseDominio() {
		return Assunto.class;
	}
}
