package br.com.tnt.ssg.cmt.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.dao.CidadeDAO;
import br.com.tnt.ssg.dmp.Cidade;

@Stateless
public class CidadeBO {

	@EJB
	private CidadeDAO cidadeDAO;

	public void save(Cidade cidade) throws Exception {
		cidadeDAO.save(cidade);
	}

	public void remove(Cidade cidade) throws Exception {
		cidadeDAO.remove(cidade);
	}

	public Cidade findById(Long id) throws Exception {
		return cidadeDAO.findById(id);
	}

	public List<Cidade> findAll() throws Exception {
		return cidadeDAO.findAll();
	}

	public List<Cidade> findAll(Cidade criteria) throws Exception {
		return cidadeDAO.findAll(criteria);
	}
}
