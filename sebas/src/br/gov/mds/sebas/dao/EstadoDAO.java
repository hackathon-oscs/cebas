package br.gov.mds.sebas.dao;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.gov.mds.sebas.modelo.Estado;

@Stateless
public class EstadoDAO extends DaoGenerico<Estado> implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	@Override
	protected Class<Estado> getClasseDominio() {
		// TODO Auto-generated method stub
		return Estado.class;
	}
}
