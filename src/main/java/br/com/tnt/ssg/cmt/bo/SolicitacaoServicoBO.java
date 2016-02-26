package br.com.tnt.ssg.cmt.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.dao.SolicitacaoServicoDAO;
import br.com.tnt.ssg.dmp.Cliente;
import br.com.tnt.ssg.dmp.Servico;
import br.com.tnt.ssg.dmp.SolicitacaoServico;
import br.com.tnt.ssg.enums.SituacaoServico;

@Stateless
public class SolicitacaoServicoBO {

	@EJB
	private SolicitacaoServicoDAO solicitacaoServicoDAO;

	public void save(SolicitacaoServico solicitacaoServico) throws Exception {
		solicitacaoServicoDAO.save(solicitacaoServico);
	}

	public void remove(SolicitacaoServico solicitacaoServico) throws Exception {
		solicitacaoServicoDAO.remove(solicitacaoServico);
	}

	public SolicitacaoServico findById(Long id) throws Exception {
		return solicitacaoServicoDAO.findById(id);
	}

	public List<SolicitacaoServico> findAll() throws Exception {
		return solicitacaoServicoDAO.findAll();
	}

	public List<SolicitacaoServico> findAll(SolicitacaoServico criteria)
			throws Exception {
		return solicitacaoServicoDAO.findAll(criteria);
	}

	public List<SolicitacaoServico> findAll(SituacaoServico situacaoServico,
			Cliente cliente) throws Exception {
		return solicitacaoServicoDAO.findAll(situacaoServico, cliente);
	}

	public List<SolicitacaoServico> findAllExecutadosByServico(Servico servico)
			throws Exception {
		return solicitacaoServicoDAO.findAllExecutadosByServico(servico);
	}
}
