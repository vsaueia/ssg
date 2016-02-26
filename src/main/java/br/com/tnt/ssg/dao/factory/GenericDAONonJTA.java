package br.com.tnt.ssg.dao.factory;

import javax.persistence.EntityManager;

import br.com.tnt.ssg.dmp.BaseEntity;

public class GenericDAONonJTA<E extends BaseEntity> extends GenericDAO<E> {

	public GenericDAONonJTA(EntityManager em, Class<E> entityClass) {
		super (em, entityClass);
	}
	
	@Override
	protected void initTransaction() {
		this.em.getTransaction().begin();
	}
	
	@Override
	protected void commitTransaction() {
		this.em.getTransaction().commit();
	}
	
	@Override
	protected void rollbackTransaction() {
		this.em.getTransaction().rollback();
	}
}