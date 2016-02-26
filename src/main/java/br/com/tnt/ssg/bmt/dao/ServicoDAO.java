package br.com.tnt.ssg.bmt.dao;

import java.util.List;

import br.com.tnt.ssg.dao.factory.GenericDAO;
import br.com.tnt.ssg.dao.factory.GenericDAOFactory;
import br.com.tnt.ssg.dao.factory.TransactionStrategyEnum;
import br.com.tnt.ssg.dmp.Servico;
import br.com.tnt.ssg.em.MyEntityManager;

public class ServicoDAO {

	private GenericDAO<Servico> servicoDAO;

	public ServicoDAO() {
		this.servicoDAO = GenericDAOFactory.getGenericDAO(MyEntityManager
				.getEm(), Servico.class, TransactionStrategyEnum.NON_JTA);
	}

	public void save(Servico servico) throws Exception {
		servicoDAO.save(servico);
	}

	public void remove(Servico servico) throws Exception {
		servicoDAO.remove(servico.getId());
	}

	public Servico findById(Long id) throws Exception {
		return servicoDAO.findById(id);
	}

	public List<Servico> findAll() throws Exception {
		return servicoDAO.findByNamedQuery("Servico.findAll", null);
	}
}
