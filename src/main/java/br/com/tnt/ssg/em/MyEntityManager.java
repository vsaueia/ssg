package br.com.tnt.ssg.em;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class MyEntityManager {

	private static EntityManager em;

	private MyEntityManager() {
		em = Persistence.createEntityManagerFactory("ssg")
				.createEntityManager();
	}

	public static EntityManager getEm() {
		if (em == null) {
			new MyEntityManager();
		}
		return em;
	}
}
