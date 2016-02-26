package br.com.tnt.ssg.dao.factory;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import br.com.tnt.ssg.dmp.BaseEntity;

public abstract class GenericDAO<T extends BaseEntity> {

	protected static Logger logger = Logger.getLogger(GenericDAONonJTA.class
			.getName());

	protected EntityManager em;

	protected Class<T> entityClass;

	public GenericDAO(EntityManager em, Class<T> entityClass) {
		this.em = em;
		this.entityClass = entityClass;
	}

	protected abstract void initTransaction();

	protected abstract void commitTransaction();

	protected abstract void rollbackTransaction();

	public final void save(T entity) throws Exception {
		try {
			initTransaction();
			if (entity.getId() == null) {
				em.persist(entity);
			} else {
				em.merge(entity);
			}

			commitTransaction();
		} catch (Exception e) {
			logger.error("Operação não executada ->", e);
			rollbackTransaction();
		}
	}

	public final void remove(Long id) throws Exception {
		try {
			T entity = findById(id);
			if (entity != null) {
				initTransaction();
				em.remove(entity);
				commitTransaction();
			}
		} catch (Exception e) {
			logger.error("Operação não executada ->", e);
			rollbackTransaction();
		}
	}

	public T findById(Long id) throws Exception {
		try {
			return em.find(entityClass, id);
		} catch (Exception e) {
			logger.error("Operação não executada ->", e);
		}
		return null;
	}

	public List<T> findByNamedQuery(String namedQuery,
			Map<String, Object> parameters) throws Exception {
		TypedQuery<T> query = em.createNamedQuery(namedQuery, entityClass);

		if (parameters != null) {
			for (String parameterName : parameters.keySet()) {
				query
						.setParameter(parameterName, parameters
								.get(parameterName));
			}
		}

		return query.getResultList();
	}

	public List<T> findByHql(String hql, Map<String, Object> parameters)
			throws Exception {
		TypedQuery<T> query = em.createQuery(hql, entityClass);

		if (parameters != null) {
			for (String parameterName : parameters.keySet()) {
				query
						.setParameter(parameterName, parameters
								.get(parameterName));
			}
		}

		return query.getResultList();
	}
}
