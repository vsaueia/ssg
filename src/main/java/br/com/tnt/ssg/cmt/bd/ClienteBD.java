package br.com.tnt.ssg.cmt.bd;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.bo.ClienteBO;
import br.com.tnt.ssg.dmp.Cliente;
import br.com.tnt.ssg.dmp.Usuario;

@Stateless
public class ClienteBD {

	@EJB
	private ClienteBO clienteBO;

	public void save(Cliente cliente) throws Exception {
		clienteBO.save(cliente);
	}

	public void remove(Cliente cliente) throws Exception {
		clienteBO.remove(cliente);
	}

	public Cliente findById(Long id) throws Exception {
		return clienteBO.findById(id);
	}

	public List<Cliente> findAll() throws Exception {
		return clienteBO.findAll();
	}

	public List<Cliente> findAll(Cliente criteria) throws Exception {
		return clienteBO.findAll(criteria);
	}

	public Cliente findByUsuario(Usuario usuario) throws Exception {
		return clienteBO.findByUsuario(usuario);
	}
}
