package br.com.tnt.ssg.dao.factory;

import javax.persistence.EntityManager;

import br.com.tnt.ssg.dmp.BaseEntity;

public class GenericDAOFactory {

	public static <T extends BaseEntity> GenericDAO<T> getGenericDAO(
			EntityManager em, Class<T> clazz,
			TransactionStrategyEnum transactionStrategy) {
		
		JPAFactory jpaFactory;
		
		if (TransactionStrategyEnum.JTA == transactionStrategy) {
			jpaFactory = new JPAJTAFactory();
		} else {
			jpaFactory = new JPANonJTAFactory();
		}

		return jpaFactory.createGenericDAO(em, clazz);
	}
}
