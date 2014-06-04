package br.gov.mds.sebas.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.mds.sebas.seguranca.Modulo;
import br.gov.mds.sebas.seguranca.Permissao;
import br.gov.mds.sebas.seguranca.Usuario;

@Stateless
public class LoginDAO extends DaoGenerico<Usuario> implements Serializable{
	private static final long serialVersionUID = -34688285306341813L;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
	protected Class<Usuario> getClasseDominio() {
		return Usuario.class;
	}
	
	public Usuario recuperarPorLogin(String login) throws Exception {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
			Root<Usuario> root = cq.from(Usuario.class);
			Predicate predicate = cb.equal(root.get("login"), login);
			cq.where(predicate);

			return em.createQuery(cq).getSingleResult();
		} catch (Exception e) {
			throw new Exception("Erro na query: " + e);
		}
	}
	
	public List<Modulo> recuperarModuloPorUsuario(Usuario usuario) {
		String sql = "SELECT modulo FROM Modulo modulo join modulo.permissoes p join p.perfil.usuarios usuario "
				+ "where usuario.id=:usuario and p.acesso=:acesso "
				+ "group by modulo order by modulo.ordem";
		Query query = em.createQuery(sql);
		query.setParameter("usuario", usuario.getId());
		query.setParameter("acesso", 's');

		@SuppressWarnings("unchecked")
		List<Modulo> modulos = query.getResultList();
		return modulos;
	}
	
	public List<Permissao> recuperarPermissaoPorUsuario(Usuario usuario) {
		String sql = "SELECT p FROM Permissao p "
				+ "inner join p.modulo modulo "
				+ "inner join p.perfil.usuarios usuario "
				+ "where usuario.id=:usuario "
				+ "group by p";
		Query query = em.createQuery(sql);
		query.setParameter("usuario", usuario.getId());
		@SuppressWarnings("unchecked")
		List<Permissao> permissoes = query.getResultList();
		return permissoes;
	}
	
	public List<Modulo> recuperarItemModuloPorBase(Usuario usuario, Long id) {
		String sql = "SELECT modulo FROM Modulo modulo join modulo.permissoes p join p.perfil.usuarios usuario "
				+ "where usuario.id=:usuario and p.acesso=:tipoAcao "
				+ "and modulo.subModulo.id=:itemBase "
				+ "group by modulo order by modulo.ordem";
		Query query = em.createQuery(sql);
		query.setParameter("usuario", usuario.getId());
		query.setParameter("tipoAcao", 's');
		query.setParameter("itemBase", id);
		@SuppressWarnings("unchecked")
		List<Modulo> modulos = query.getResultList();
		return modulos;
	}
}