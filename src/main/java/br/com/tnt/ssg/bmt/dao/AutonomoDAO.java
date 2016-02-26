package br.com.tnt.ssg.bmt.dao;

import java.util.List;

import br.com.tnt.ssg.dao.factory.GenericDAO;
import br.com.tnt.ssg.dao.factory.GenericDAOFactory;
import br.com.tnt.ssg.dao.factory.TransactionStrategyEnum;
import br.com.tnt.ssg.dmp.Autonomo;
import br.com.tnt.ssg.em.MyEntityManager;

public class AutonomoDAO {

	private GenericDAO<Autonomo> autonomoDAO;

	public AutonomoDAO() {
		this.autonomoDAO = GenericDAOFactory.getGenericDAO(MyEntityManager
				.getEm(), Autonomo.class, TransactionStrategyEnum.NON_JTA);
	}

	public void save(Autonomo autonomo) throws Exception {
		autonomoDAO.save(autonomo);
	}

	public void remove(Autonomo autonomo) throws Exception {
		autonomoDAO.remove(autonomo.getId());
	}

	public Autonomo findById(Long id) throws Exception {
		return autonomoDAO.findById(id);
	}

	public List<Autonomo> findAll() throws Exception {
		return autonomoDAO.findByNamedQuery("Autonomo.findAll", null);
	}
}
