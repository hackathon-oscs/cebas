package br.gov.mds.sebas.dao;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.gov.mds.sebas.seguranca.Perfil;

@Stateless
public class PerfilDAO extends DaoGenerico<Perfil> implements Serializable {

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
	protected Class<Perfil> getClasseDominio() {
		// TODO Auto-generated method stub
		return Perfil.class;
	}

}
