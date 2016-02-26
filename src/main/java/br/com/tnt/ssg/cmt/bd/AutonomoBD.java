package br.com.tnt.ssg.cmt.bd;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.bo.AutonomoBO;
import br.com.tnt.ssg.dmp.Autonomo;

@Stateless
public class AutonomoBD {

	@EJB
	private AutonomoBO autonomoBO;

	public void save(Autonomo autonomo) throws Exception {
		autonomoBO.save(autonomo);
	}

	public void remove(Autonomo autonomo) throws Exception {
		autonomoBO.remove(autonomo);
	}

	public Autonomo findById(Long id) throws Exception {
		return autonomoBO.findById(id);
	}

	public List<Autonomo> findAll() throws Exception {
		return autonomoBO.findAll();
	}

	public List<Autonomo> findAll(Autonomo criteria) throws Exception {
		return autonomoBO.findAll(criteria);
	}
}
