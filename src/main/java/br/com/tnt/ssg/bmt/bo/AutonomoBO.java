package br.com.tnt.ssg.bmt.bo;

import java.util.List;

import br.com.tnt.ssg.bmt.dao.AutonomoDAO;
import br.com.tnt.ssg.dmp.Autonomo;

public class AutonomoBO {

	private AutonomoDAO autonomoDAO;

	public AutonomoBO() {
		this.autonomoDAO = new AutonomoDAO();
	}

	public void save(Autonomo autonomo) throws Exception {
		autonomoDAO.save(autonomo);
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
}
