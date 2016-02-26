package br.com.tnt.ssg.cmt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.tnt.ssg.dao.factory.GenericDAO;
import br.com.tnt.ssg.dao.factory.GenericDAOFactory;
import br.com.tnt.ssg.dao.factory.TransactionStrategyEnum;
import br.com.tnt.ssg.dmp.Categoria;
import br.com.tnt.ssg.dmp.Servico;
import br.com.tnt.ssg.exceptions.CategoriaException;

@Stateless
public class CategoriaDAO {

	@PersistenceContext
	private EntityManager em;

	private GenericDAO<Categoria> categoriaDAO;

	@EJB
	private ServicoDAO servicoDAO;

	@PostConstruct
	public void init() {
		this.categoriaDAO = GenericDAOFactory.getGenericDAO(em,
				Categoria.class, TransactionStrategyEnum.JTA);
	}

	public void save(Categoria categoria) throws Exception {
		categoriaDAO.save(categoria);
	}

	public void remove(Categoria categoria) throws Exception {
		Servico criteria = new Servico();
		criteria.setCategoria(categoria);

		List<Servico> servicos = servicoDAO.findAll(criteria);

		if (servicos.isEmpty()) {
			categoriaDAO.remove(categoria.getId());
		} else {
			CategoriaException ce = new CategoriaException();
			ce.setCategoriaComServicos(true);

			throw ce;
		}
	}

	public Categoria findById(Long id) throws Exception {
		return categoriaDAO.findById(id);
	}

	public List<Categoria> findAll() throws Exception {
		return categoriaDAO.findByNamedQuery("Categoria.findAll", null);
	}

	public List<Categoria> findAll(Categoria criteria) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT c FROM Categoria c ");
		hql.append("WHERE 1 = 1 ");

		if (criteria.getNome() != null && !criteria.getNome().trim().isEmpty()) {
			hql.append("AND LOWER(c.nome) LIKE :nome ");
			parameters
					.put("nome", "%" + criteria.getNome().toLowerCase() + "%");
		}

		return categoriaDAO.findByHql(hql.toString(), parameters);
	}
}
