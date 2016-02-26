package br.com.tnt.ssg.dao.factory;

import javax.persistence.EntityManager;

import br.com.tnt.ssg.dmp.BaseEntity;

public class GenericDAOJTA<E extends BaseEntity> extends GenericDAO<E> {

	public GenericDAOJTA(EntityManager em, Class<E> entityClass) {
		super(em, entityClass);
	}

	@Override
	protected void commitTransaction() {
		
	}

	@Override
	protected void initTransaction() {
		
	}

	@Override
	protected void rollbackTransaction() {
		
	}
}