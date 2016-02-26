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
import br.com.tnt.ssg.dmp.Autonomo;

@Stateless
public class AutonomoDAO {

	@PersistenceContext
	private EntityManager em;

	private GenericDAO<Autonomo> autonomoDAO;

	@PostConstruct
	public void init() {
		this.autonomoDAO = GenericDAOFactory.getGenericDAO(em, Autonomo.class,
				TransactionStrategyEnum.JTA);
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

	public List<Autonomo> findAll(Autonomo criteria) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT a FROM Autonomo a ");
		hql.append("WHERE 1 = 1 ");

		if (criteria.getNomeFantasia() != null
				&& !criteria.getNomeFantasia().trim().isEmpty()) {
			hql.append("AND LOWER(a.nomeFantasia) LIKE :nomeFantasia ");
			parameters.put("nomeFantasia", "%"
					+ criteria.getNomeFantasia().toLowerCase() + "%");
		}

		if (criteria.getCpf() != null && !criteria.getCpf().trim().isEmpty()) {
			hql.append("AND a.cpf LIKE :cpf ");
			parameters.put("cpf", "%" + criteria.getCpf() + "%");
		}

		return autonomoDAO.findByHql(hql.toString(), parameters);
	}

	public Autonomo findAutonomoByLogin(String login) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();

		hql.append("SELECT a FROM Autonomo a ");
		hql.append("JOIN a.usuario u ");
		hql.append("WHERE u.login = :login ");

		parameters.put("login", login);

		List<Autonomo> autonomos = autonomoDAO.findByHql(hql.toString(),
				parameters);

		if (autonomos != null && !autonomos.isEmpty()) {
			return autonomos.get(0);
		}

		return null;
	}
}
