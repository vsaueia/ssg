package br.com.tnt.ssg.bmt.bd;

import java.util.List;

import br.com.tnt.ssg.bmt.bo.UFBO;
import br.com.tnt.ssg.dmp.UF;

public class UFBD {

	private UFBO ufBO;

	public UFBD() {
		this.ufBO = new UFBO();
	}

	public void save(UF uf) throws Exception {
		ufBO.save(uf);
	}

	public void remove(UF uf) throws Exception {
		ufBO.remove(uf);
	}

	public UF findById(Long id) throws Exception {
		return ufBO.findById(id);
	}

	public List<UF> findAll() throws Exception {
		return ufBO.findAll();
	}
}
