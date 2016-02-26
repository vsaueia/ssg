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
import br.com.tnt.ssg.dmp.Servico;

@Stateless
public class ServicoDAO {

	@PersistenceContext
	private EntityManager em;

	private GenericDAO<Servico> servicoDAO;

	@PostConstruct
	public void init() {
		this.servicoDAO = GenericDAOFactory.getGenericDAO(em, Servico.class,
				TransactionStrategyEnum.JTA);
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

	public List<Servico> findAll(Servico criteria) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT s FROM Servico s join s.cidadeServicoList cs ");

		hql.append("WHERE 1 = 1 ");

		if (criteria.getCidade() != null) {
			hql.append(" and cs.cidade = :cidade");
			parameters.put("cidade", criteria.getCidade());
		}

		if (criteria.getUf() != null) {
			hql.append(" and cs.cidade.uf = :uf");
			parameters.put("uf", criteria.getUf());
		}

		if (criteria.getDescricao() != null
				&& !criteria.getDescricao().trim().isEmpty()) {
			hql.append("AND LOWER(s.descricao) LIKE :descricao ");
			parameters.put("descricao", "%"
					+ criteria.getDescricao().toLowerCase() + "%");
		}

		if (criteria.getCategoria() != null) {
			hql.append("AND s.categoria = :categoria ");
			parameters.put("categoria", criteria.getCategoria());
		}

		if (criteria.getAutonomo() != null) {
			hql.append("AND s.autonomo = :autonomo ");
			parameters.put("autonomo", criteria.getAutonomo());
		}

		return servicoDAO.findByHql(hql.toString(), parameters);
	}

	public Servico findByIdWithCidades(Long id) throws Exception {
		Servico servico = findById(id);

		servico.getCidadeServicoList().size();

		return servico;
	}
}
