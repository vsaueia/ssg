package br.com.tnt.ssg.bmt.bo;

import java.util.List;

import br.com.tnt.ssg.bmt.dao.CidadeDAO;
import br.com.tnt.ssg.dmp.Cidade;

public class CidadeBO {

	private CidadeDAO cidadeDAO;

	public CidadeBO() {
		this.cidadeDAO = new CidadeDAO();
	}

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
}
