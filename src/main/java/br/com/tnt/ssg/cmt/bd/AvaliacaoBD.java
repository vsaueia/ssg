package br.com.tnt.ssg.cmt.bd;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.bo.AvaliacaoBO;
import br.com.tnt.ssg.dmp.Avaliacao;
import br.com.tnt.ssg.dmp.SolicitacaoServico;

@Stateless
public class AvaliacaoBD {

	@EJB
	private AvaliacaoBO avaliacaoBO;

	public void save(Avaliacao avaliacao) throws Exception {
		avaliacaoBO.save(avaliacao);
	}

	public void remove(Avaliacao avaliacao) throws Exception {
		avaliacaoBO.remove(avaliacao);
	}

	public Avaliacao findById(Long id) throws Exception {
		return avaliacaoBO.findById(id);
	}

	public List<Avaliacao> findAll() throws Exception {
		return avaliacaoBO.findAll();
	}

	public List<Avaliacao> findAll(Avaliacao criteria) throws Exception {
		return avaliacaoBO.findAll(criteria);
	}

	public Avaliacao findBySolicitacao(SolicitacaoServico ss) throws Exception {
		return avaliacaoBO.findBySolicitacao(ss);
	}
}