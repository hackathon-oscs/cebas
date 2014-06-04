package br.gov.mds.sebas.dao;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.gov.mds.sebas.modelo.AtividadeEconomica;

@Stateless
public class AtividadeEconomicaDAO extends DaoGenerico<AtividadeEconomica> implements Serializable {

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	@Override
	protected Class<AtividadeEconomica> getClasseDominio() {
		// TODO Auto-generated method stub
		return AtividadeEconomica.class;
	}

}
