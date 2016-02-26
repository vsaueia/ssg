package br.com.tnt.ssg.cmt.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.dao.UFDAO;
import br.com.tnt.ssg.dmp.UF;

@Stateless
public class UFBO {

	@EJB
	private UFDAO ufDAO;

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

	public List<UF> findAll(UF criteria) throws Exception {
		return ufDAO.findAll(criteria);
	}
}
