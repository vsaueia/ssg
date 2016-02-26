package br.com.tnt.ssg.cmt.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.dao.ClienteDAO;
import br.com.tnt.ssg.cmt.dao.UsuarioDAO;
import br.com.tnt.ssg.dmp.Cliente;
import br.com.tnt.ssg.dmp.Usuario;

@Stateless
public class ClienteBO {

	@EJB
	private ClienteDAO clienteDAO;

	@EJB
	private UsuarioDAO usaurioDAO;

	public void save(Cliente cliente) throws Exception {
		clienteDAO.save(cliente);

		usaurioDAO.grant(cliente.getUsuario(), "SSG_WEB", "CLIENTE");
	}

	public void remove(Cliente cliente) throws Exception {
		clienteDAO.remove(cliente);
	}

	public Cliente findById(Long id) throws Exception {
		return clienteDAO.findById(id);
	}

	public List<Cliente> findAll() throws Exception {
		return clienteDAO.findAll();
	}

	public List<Cliente> findAll(Cliente criteria) throws Exception {
		return clienteDAO.findAll(criteria);
	}

	public Cliente findByUsuario(Usuario usuario) throws Exception {
		return clienteDAO.findByUsuario(usuario);
	}

	public Cliente findClienteByLogin(String login) throws Exception {
		return clienteDAO.findClienteByLogin(login);
	}
}
