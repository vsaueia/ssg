package br.com.tnt.ssg.bmt.bd;

import java.util.List;

import br.com.tnt.ssg.bmt.bo.AutonomoBO;
import br.com.tnt.ssg.dmp.Autonomo;

public class AutonomoBD {

	private AutonomoBO autonomoBO;

	public AutonomoBD() {
		this.autonomoBO = new AutonomoBO();
	}

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
}
