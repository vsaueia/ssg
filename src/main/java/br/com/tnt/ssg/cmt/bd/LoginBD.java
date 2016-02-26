package br.com.tnt.ssg.cmt.bd;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.bo.AutonomoBO;
import br.com.tnt.ssg.cmt.bo.ClienteBO;
import br.com.tnt.ssg.dmp.Autonomo;
import br.com.tnt.ssg.dmp.Cliente;

@Stateless
public class LoginBD {

	@EJB
	private AutonomoBO autonomoBO;

	@EJB
	private ClienteBO clienteBO;

	public Autonomo getAutonomoByLogin(String login) throws Exception {
		return autonomoBO.findAutonomoByLogin(login);
	}

	public Cliente getClienteByLogin(String login) throws Exception {
		return clienteBO.findClienteByLogin(login);
	}
}
