package br.com.tnt.ssg.cmt.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.dao.AutonomoDAO;
import br.com.tnt.ssg.cmt.dao.UsuarioDAO;
import br.com.tnt.ssg.dmp.Autonomo;

@Stateless
public class AutonomoBO {

	@EJB
	private AutonomoDAO autonomoDAO;

	@EJB
	private UsuarioDAO usuarioDAO;

	public void save(Autonomo autonomo) throws Exception {
		autonomoDAO.save(autonomo);

		usuarioDAO.grant(autonomo.getUsuario(), "SSG_WEB", "AUTONOMO");
	}

	public void remove(Autonomo autonomo) throws Exception {
		autonomoDAO.remove(autonomo);
	}

	public Autonomo findById(Long id) throws Exception {
		return autonomoDAO.findById(id);
	}

	public List<Autonomo> findAll() throws Exception {
		return autonomoDAO.findAll();
	}

	public List<Autonomo> findAll(Autonomo criteria) throws Exception {
		return autonomoDAO.findAll(criteria);
	}

	public Autonomo findAutonomoByLogin(String login) throws Exception {
		return autonomoDAO.findAutonomoByLogin(login);
	}
}
