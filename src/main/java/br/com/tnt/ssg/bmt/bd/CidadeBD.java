package br.com.tnt.ssg.bmt.bd;

import java.util.List;

import br.com.tnt.ssg.bmt.bo.CidadeBO;
import br.com.tnt.ssg.bmt.bo.UFBO;
import br.com.tnt.ssg.dmp.Cidade;
import br.com.tnt.ssg.dmp.UF;

public class CidadeBD {

	private CidadeBO cidadeBO;
	private UFBO ufBO;

	public CidadeBD() {
		this.cidadeBO = new CidadeBO();
		this.ufBO = new UFBO();
	}

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

	public List<UF> findAllUF() throws Exception {
		return ufBO.findAll();
	}
}
