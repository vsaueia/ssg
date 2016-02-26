package br.com.tnt.ssg.bmt.dao;

import java.util.List;

import br.com.tnt.ssg.dao.factory.GenericDAO;
import br.com.tnt.ssg.dao.factory.GenericDAOFactory;
import br.com.tnt.ssg.dao.factory.TransactionStrategyEnum;
import br.com.tnt.ssg.dmp.Cidade;
import br.com.tnt.ssg.em.MyEntityManager;

public class CidadeDAO {

	private GenericDAO<Cidade> cidadeDAO;

	public CidadeDAO() {
		this.cidadeDAO = GenericDAOFactory.getGenericDAO(MyEntityManager
				.getEm(), Cidade.class, TransactionStrategyEnum.NON_JTA);
	}

	public void save(Cidade cidade) throws Exception {
		cidadeDAO.save(cidade);
	}

	public void remove(Cidade cidade) throws Exception {
		cidadeDAO.remove(cidade.getId());
	}

	public Cidade findById(Long id) throws Exception {
		return cidadeDAO.findById(id);
	}

	public List<Cidade> findAll() throws Exception {
		return cidadeDAO.findByNamedQuery("Cidade.findAll", null);
	}
}
