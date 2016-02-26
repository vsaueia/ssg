package br.com.tnt.ssg.dao.factory;

import javax.persistence.EntityManager;

import br.com.tnt.ssg.dmp.BaseEntity;

public class JPAJTAFactory implements JPAFactory {

	@Override
	public <T extends BaseEntity> GenericDAO<T> createGenericDAO(
			EntityManager em, Class<T> entityClass) {
		return new GenericDAOJTA<T>(em, entityClass);
	}

}
