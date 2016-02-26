package br.com.tnt.ssg.cmt.bd;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.bo.AgendamentoServicoBO;
import br.com.tnt.ssg.cmt.bo.AutonomoBO;
import br.com.tnt.ssg.cmt.bo.SolicitacaoServicoBO;
import br.com.tnt.ssg.dmp.AgendamentoServico;
import br.com.tnt.ssg.dmp.Autonomo;
import br.com.tnt.ssg.dmp.Cliente;
import br.com.tnt.ssg.dmp.Servico;
import br.com.tnt.ssg.dmp.SolicitacaoServico;
import br.com.tnt.ssg.enums.SituacaoServico;

@Stateless
public class SolicitacaoServicoBD {

	@EJB
	private SolicitacaoServicoBO solicitacaoServicoBO;

	@EJB
	private AutonomoBO autonomoBO;

	@EJB
	private AgendamentoServicoBO agendamentoServicoBO;

	public void save(SolicitacaoServico solicitacaoServico) throws Exception {
		solicitacaoServicoBO.save(solicitacaoServico);
	}

	public void remove(SolicitacaoServico solicitacaoServico) throws Exception {
		solicitacaoServicoBO.remove(solicitacaoServico);
	}

	public SolicitacaoServico findById(Long id) throws Exception {
		return solicitacaoServicoBO.findById(id);
	}

	public List<SolicitacaoServico> findAll() throws Exception {
		return solicitacaoServicoBO.findAll();
	}

	public List<SolicitacaoServico> findAll(SolicitacaoServico criteria)
			throws Exception {
		return solicitacaoServicoBO.findAll(criteria);
	}

	public Autonomo findAutonomoByLogin(String login) throws Exception {
		return autonomoBO.findAutonomoByLogin(login);
	}

	public void save(AgendamentoServico agendamentoServico) throws Exception {
		agendamentoServicoBO.save(agendamentoServico);
	}

	public List<SolicitacaoServico> findAll(SituacaoServico situacaoServico,
			Cliente cliente) throws Exception {
		return solicitacaoServicoBO.findAll(situacaoServico, cliente);
	}

	public List<SolicitacaoServico> findAllExecutadosByServico(Servico servico)
			throws Exception {
		return solicitacaoServicoBO.findAllExecutadosByServico(servico);
	}
}
