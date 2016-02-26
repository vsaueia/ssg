package br.com.tnt.ssg.bmt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.tnt.ssg.dao.factory.GenericDAO;
import br.com.tnt.ssg.dao.factory.GenericDAOFactory;
import br.com.tnt.ssg.dao.factory.TransactionStrategyEnum;
import br.com.tnt.ssg.dmp.Categoria;
import br.com.tnt.ssg.dmp.Cidade;
import br.com.tnt.ssg.dmp.Servico;
import br.com.tnt.ssg.em.MyEntityManager;
import br.com.tnt.ssg.exceptions.CategoriaException;

public class CategoriaDAO {

	private GenericDAO<Categoria> categoriaDAO;

	private GenericDAO<Servico> servicoDAO;

	public CategoriaDAO() {
		this.categoriaDAO = GenericDAOFactory.getGenericDAO(MyEntityManager
				.getEm(), Categoria.class, TransactionStrategyEnum.NON_JTA);
		this.servicoDAO = GenericDAOFactory.getGenericDAO(MyEntityManager
				.getEm(), Servico.class, TransactionStrategyEnum.NON_JTA);
	}

	public void save(Categoria categoria) throws Exception {
		categoriaDAO.save(categoria);
	}

	public void remove(Categoria categoria) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("categoria", categoria);

		List<Servico> servicos = servicoDAO.findByNamedQuery(
				"Servico.findByCategoria", parameters);

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

	public List<Cidade> findAll(Categoria criteria) {
		return null;
	}
}
