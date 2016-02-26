package br.com.tnt.ssg.cmt.bd;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.bo.CidadeBO;
import br.com.tnt.ssg.cmt.bo.UFBO;
import br.com.tnt.ssg.dmp.Cidade;
import br.com.tnt.ssg.dmp.UF;

@Stateless
public class CidadeBD {

	@EJB
	private CidadeBO cidadeBO;

	@EJB
	private UFBO ufBO;

	public void save(Cidade cidade) throws Exception {
		cidadeBO.save(cidade);
	}

	public void remove(Cidade cidade) throws Exception {
		cidadeBO.remove(cidade);
	}

	public Cidade findById(Long id) throws Exception {
		return cidadeBO.findById(id);
	}

	public List<Cidade> findAll() throws Exception {
		return cidadeBO.findAll();
	}

	public List<Cidade> findAll(Cidade criteria) throws Exception {
		return cidadeBO.findAll(criteria);
	}

	public List<UF> findAllUF() throws Exception {
		return ufBO.findAll();
	}
}
