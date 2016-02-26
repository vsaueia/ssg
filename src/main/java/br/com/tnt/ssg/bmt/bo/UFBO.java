package br.com.tnt.ssg.bmt.bo;

import java.util.List;

import br.com.tnt.ssg.bmt.dao.UFDAO;
import br.com.tnt.ssg.dmp.UF;

public class UFBO {

	private UFDAO ufDAO;

	public UFBO() {
		this.ufDAO = new UFDAO();
	}

	public void save(UF uf) throws Exception {
		ufDAO.save(uf);
	}

	public void remove(UF uf) throws Exception {
		ufDAO.remove(uf);
	}

	public UF findById(Long id) throws Exception {
		return ufDAO.findById(id);
	}

	public List<UF> findAll() throws Exception {
		return ufDAO.findAll();
	}
}
