package br.com.tnt.ssg.cmt.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.dao.AvaliacaoDAO;
import br.com.tnt.ssg.dmp.Avaliacao;
import br.com.tnt.ssg.dmp.SolicitacaoServico;

@Stateless
public class AvaliacaoBO {

	@EJB
	private AvaliacaoDAO avaliacaoDAO;

	public void save(Avaliacao avaliacao) throws Exception {
		avaliacaoDAO.save(avaliacao);
	}

	public void remove(Avaliacao avaliacao) throws Exception {
		avaliacaoDAO.remove(avaliacao);
	}

	public Avaliacao findById(Long id) throws Exception {
		return avaliacaoDAO.findById(id);
	}

	public List<Avaliacao> findAll() throws Exception {
		return avaliacaoDAO.findAll();
	}

	public List<Avaliacao> findAll(Avaliacao criteria) throws Exception {
		return avaliacaoDAO.findAll(criteria);
	}

	public Avaliacao findBySolicitacao(SolicitacaoServico ss) throws Exception {
		return avaliacaoDAO.findBySolicitacao(ss);
	}
}
