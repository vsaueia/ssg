package br.com.tnt.ssg.dao.factory;

import javax.persistence.EntityManager;

import br.com.tnt.ssg.dmp.BaseEntity;

public interface JPAFactory {

	<T extends BaseEntity> GenericDAO<T> createGenericDAO(EntityManager em, Class<T> entityClass);
}
