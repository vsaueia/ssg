package br.com.tnt.ssg.cmt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.tnt.ssg.dao.factory.GenericDAO;
import br.com.tnt.ssg.dao.factory.GenericDAOFactory;
import br.com.tnt.ssg.dao.factory.TransactionStrategyEnum;
import br.com.tnt.ssg.dmp.UF;

@Stateless
public class UFDAO {

	@PersistenceContext
	private EntityManager em;

	private GenericDAO<UF> ufDAO;

	@PostConstruct
	public void init() {
		this.ufDAO = GenericDAOFactory.getGenericDAO(em, UF.class,
				TransactionStrategyEnum.JTA);
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

	public List<UF> findAll(UF criteria) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT uf FROM UF uf ");
		hql.append("WHERE 1 = 1 ");

		if (criteria.getNome() != null && !criteria.getNome().trim().isEmpty()) {
			hql.append("AND LOWER(uf.nome) LIKE :nome ");
			parameters
					.put("nome", "%" + criteria.getNome().toLowerCase() + "%");
		}

		if (criteria.getSigla() != null
				&& !criteria.getSigla().trim().isEmpty()) {
			hql.append("AND LOWER(uf.sigla) LIKE :sigla ");
			parameters.put("sigla", "%" + criteria.getSigla().toLowerCase()
					+ "%");
		}

		return ufDAO.findByHql(hql.toString(), parameters);
	}
}
