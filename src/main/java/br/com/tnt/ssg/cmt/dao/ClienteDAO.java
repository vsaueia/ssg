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
import br.com.tnt.ssg.dmp.Cliente;
import br.com.tnt.ssg.dmp.Usuario;

@Stateless
public class ClienteDAO {

	@PersistenceContext
	private EntityManager em;

	private GenericDAO<Cliente> clienteDAO;

	@PostConstruct
	public void init() {
		this.clienteDAO = GenericDAOFactory.getGenericDAO(em, Cliente.class,
				TransactionStrategyEnum.JTA);
	}

	public void save(Cliente cliente) throws Exception {
		clienteDAO.save(cliente);
	}

	public void remove(Cliente cliente) throws Exception {
		clienteDAO.remove(cliente.getId());
	}

	public Cliente findById(Long id) throws Exception {
		return clienteDAO.findById(id);
	}

	public List<Cliente> findAll() throws Exception {
		return clienteDAO.findByNamedQuery("Cliente.findAll", null);
	}

	public List<Cliente> findAll(Cliente criteria) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT a FROM Cliente a ");
		hql.append("WHERE 1 = 1 ");

		if (criteria.getNome() != null && !criteria.getNome().trim().isEmpty()) {
			hql.append("AND LOWER(a.nome) LIKE :nome ");
			parameters
					.put("nome", "%" + criteria.getNome().toLowerCase() + "%");
		}

		if (criteria.getCpf() != null && !criteria.getCpf().trim().isEmpty()) {
			hql.append("AND a.cpf LIKE :cpf ");
			parameters.put("cpf", "%" + criteria.getCpf() + "%");
		}

		return clienteDAO.findByHql(hql.toString(), parameters);
	}

	public Cliente findByUsuario(Usuario usuario) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("usuario", usuario);
		try {
			return clienteDAO.findByNamedQuery("Cliente.findByUsuario",
					parameters).get(0);
		} catch (NullPointerException ne) {
			return null;
		}
	}

	public Cliente findClienteByLogin(String login) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();

		hql.append("SELECT c FROM Cliente c ");
		hql.append("JOIN c.usuario u ");
		hql.append("WHERE u.login = :login ");

		parameters.put("login", login);

		List<Cliente> clientes = clienteDAO.findByHql(hql.toString(),
				parameters);

		if (clientes != null && !clientes.isEmpty()) {
			return clientes.get(0);
		}

		return null;
	}
}
