package br.com.tnt.ssg.bmt.dao;

import java.util.List;

import br.com.tnt.ssg.dao.factory.GenericDAO;
import br.com.tnt.ssg.dao.factory.GenericDAOFactory;
import br.com.tnt.ssg.dao.factory.TransactionStrategyEnum;
import br.com.tnt.ssg.dmp.UF;
import br.com.tnt.ssg.em.MyEntityManager;

public class UFDAO {

	private GenericDAO<UF> ufDAO;

	public UFDAO() {
		this.ufDAO = GenericDAOFactory.getGenericDAO(MyEntityManager.getEm(),
				UF.class, TransactionStrategyEnum.NON_JTA);
	}

	public void save(UF uf) throws Exception {
		ufDAO.save(uf);
	}

	public void remove(UF uf) throws Exception {
		ufDAO.remove(uf.getId());
	}

	public UF findById(Long id) throws Exception {
		return ufDAO.findById(id);
	}

	public List<UF> findAll() throws Exception {
		return ufDAO.findByNamedQuery("UF.findAll", null);
	}
}
