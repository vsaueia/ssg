package br.com.tnt.ssg.cmt.bd;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.bo.UFBO;
import br.com.tnt.ssg.dmp.UF;

@Stateless
public class UFBD {

	@EJB
	private UFBO ufBO;

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

	public List<UF> findAll(UF criteria) throws Exception {
		return ufBO.findAll(criteria);
	}

}
