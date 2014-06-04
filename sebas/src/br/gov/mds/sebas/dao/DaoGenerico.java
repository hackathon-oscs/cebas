package br.gov.mds.sebas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.mds.sebas.seguranca.Usuario;

public abstract class DaoGenerico<T> {
	protected abstract EntityManager getEntityManager();

	protected abstract Class<T> getClasseDominio();

	public List<T> recuperarPorTodos() throws Exception {
		try {
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> cq = cb.createQuery(getClasseDominio());
			Root<T> root = cq.from(getClasseDominio());
			cq.select(root);

			return em.createQuery(cq).getResultList();
		} catch (Exception e) {
			throw new Exception("Erro na query: " + e);
		}
	}

	public List<T> recuperarPorFiltro(String tipoFiltro, String filtro)
			throws Exception {

		try {
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> cq = cb.createQuery(getClasseDominio());
			Root<T> root = cq.from(getClasseDominio());
			cq.select(root);

			Predicate predicate = cb.like(
					cb.upper(root.<String> get(tipoFiltro)),
					"%" + filtro.toUpperCase() + "%");
			cq.where(predicate);
			return em.createQuery(cq).getResultList();
		} catch (Exception e) {
			throw new Exception("Erro na query: " + e.getMessage());
		}
	}

	public List<T> recuperarPorUsuario(String tipoFiltro, String filtro,
			Usuario usuario) throws Exception {

		try {
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> cq = cb.createQuery(getClasseDominio());
			Root<T> root = cq.from(getClasseDominio());
			cq.select(root);

			Predicate predicate = cb.and(
					cb.like(cb.upper(root.<String> get(tipoFiltro)), "%"
							+ filtro.toUpperCase() + "%"),
					cb.equal(root.get("usuarios"), usuario));
			cq.where(predicate);
			return em.createQuery(cq).getResultList();
		} catch (Exception e) {
			throw new Exception("Erro na query: " + e.getMessage());
		}
	}

	public List<T> recuperarTodosPorUsuario(Usuario usuario) throws Exception {
		try {
			EntityManager em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> cq = cb.createQuery(getClasseDominio());
			Root<T> root = cq.from(getClasseDominio());
			cq.select(root);

			Predicate predicate = cb.equal(root.get("usuarios"), usuario);
			cq.where(predicate);

			return em.createQuery(cq).getResultList();
		} catch (Exception e) {
			throw new Exception("Erro na query: " + e);
		}
	}

	public T recuperarPorId(Long id) throws Exception {
		EntityManager em = getEntityManager();
		try {
			return (T) em.find(getClasseDominio(), id);
		} catch (Exception e) {
			throw new Exception("Objeto não localizado pelo id: "
					+ e.getMessage());
		}
	}
	
	public T recuperarPorChaveUnica(String tipoFiltro,String chave) throws Exception {
		EntityManager em = getEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> cq = cb.createQuery(getClasseDominio());
			Root<T> root = cq.from(getClasseDominio());
			cq.select(root);

			Predicate predicate = cb.equal(cb.upper(root.<String> get(tipoFiltro)), chave.toUpperCase());
			cq.where(predicate);

			return em.createQuery(cq).getSingleResult();
		} catch (Exception e) {
			throw new Exception("Objeto não localizado pelo id: "
					+ e.getMessage());
		}
	}
	
	public void salvar(T objeto) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.merge(objeto);
		} catch (Exception e) {
			throw new Exception("Erro ao salvar: " + e.getMessage());
		}
	}

	public void excluir(Long id) throws Exception {
		EntityManager em = getEntityManager();
		try {
			T objetoRemover = em.find(getClasseDominio(), id);
			em.remove(objetoRemover);
		} catch (Exception e) {
			throw new Exception("Erro ao remover: " + e.getMessage());
		}
	}

}
