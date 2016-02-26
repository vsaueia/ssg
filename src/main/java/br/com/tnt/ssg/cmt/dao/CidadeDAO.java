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
import br.com.tnt.ssg.dmp.Cidade;

@Stateless
public class CidadeDAO {

	@PersistenceContext(unitName = "ssg")
	private EntityManager em;

	private GenericDAO<Cidade> cidadeDAO;

	@PostConstruct
	public void init() {
		cidadeDAO = GenericDAOFactory.getGenericDAO(em, Cidade.class,
				TransactionStrategyEnum.JTA);
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

	public List<Cidade> findAll(Cidade criteria) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT c FROM Cidade c ");
		hql.append("WHERE 1 = 1 ");

		if (criteria.getNome() != null && !criteria.getNome().trim().isEmpty()) {
			hql.append("AND LOWER(c.nome) LIKE :nome ");
			parameters
					.put("nome", "%" + criteria.getNome().toLowerCase() + "%");
		}

		if (criteria.getUf() != null) {
			hql.append("AND c.uf = :uf ");
			parameters.put("uf", criteria.getUf());
		}

		return cidadeDAO.findByHql(hql.toString(), parameters);
	}
}
