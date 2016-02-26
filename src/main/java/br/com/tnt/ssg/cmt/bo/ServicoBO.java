package br.com.tnt.ssg.cmt.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.dao.ServicoDAO;
import br.com.tnt.ssg.cmt.dao.SolicitacaoServicoDAO;
import br.com.tnt.ssg.dmp.Servico;
import br.com.tnt.ssg.dmp.SolicitacaoServico;
import br.com.tnt.ssg.exceptions.ServicoException;

@Stateless
public class ServicoBO {

	@EJB
	private ServicoDAO servicoDAO;

	@EJB
	private SolicitacaoServicoDAO solicitacaoServicoDAO;

	public void save(Servico servico) throws Exception {
		servicoDAO.save(servico);
	}

	public void remove(Servico servico) throws Exception {
		SolicitacaoServico criteria = new SolicitacaoServico();
		criteria.setServico(servico);

		List<SolicitacaoServico> solicitacoes = solicitacaoServicoDAO
				.findAll(criteria);

		if (solicitacoes != null && !solicitacoes.isEmpty()) {
			ServicoException e = new ServicoException();
			e.setServicoComSolicitacoes(true);
			throw e;
		}

		servicoDAO.remove(servico);
	}

	public Servico findById(Long id) throws Exception {
		return servicoDAO.findById(id);
	}

	public List<Servico> findAll() throws Exception {
		return servicoDAO.findAll();
	}

	public List<Servico> findAll(Servico criteria) throws Exception {
		return servicoDAO.findAll(criteria);
	}

	public Servico findByIdWithCidades(Long id) throws Exception {
		return servicoDAO.findByIdWithCidades(id);
	}
}
