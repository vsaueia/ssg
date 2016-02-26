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
import br.com.tnt.ssg.dmp.Permissao;

@Stateless
public class PermissaoDAO {

	@PersistenceContext
	private EntityManager em;

	private GenericDAO<Permissao> permissaoDAO;

	@PostConstruct
	public void init() {
		this.permissaoDAO = GenericDAOFactory.getGenericDAO(em,
				Permissao.class, TransactionStrategyEnum.JTA);
	}

	public void save(Permissao permissao) throws Exception {
		permissaoDAO.save(permissao);
	}

	public void remove(Permissao permissao) throws Exception {
		permissaoDAO.remove(permissao.getId());
	}

	public Permissao findById(Long id) throws Exception {
		return permissaoDAO.findById(id);
	}

	public List<Permissao> findAll() throws Exception {
		return permissaoDAO.findByNamedQuery("Permissao.findAll", null);
	}

	public List<Permissao> findAll(Permissao criteria) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT p FROM Permissao p ");
		hql.append("WHERE 1 = 1 ");

		if (criteria.getNome() != null && !criteria.getNome().trim().isEmpty()) {
			hql.append("AND LOWER(p.nome) LIKE :nome ");
			parameters
					.put("nome", "%" + criteria.getNome().toLowerCase() + "%");
		}

		return permissaoDAO.findByHql(hql.toString(), parameters);
	}
}
