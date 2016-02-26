package br.com.tnt.ssg.cmt.bd;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.bo.AgendamentoServicoBO;
import br.com.tnt.ssg.dmp.AgendamentoServico;

@Stateless
public class AgendamentoServicoBD {

	@EJB
	private AgendamentoServicoBO agendamentoServicoBO;

	public void save(AgendamentoServico agendamentoServico) throws Exception {
		agendamentoServicoBO.save(agendamentoServico);
	}

	public void remove(AgendamentoServico agendamentoServico) throws Exception {
		agendamentoServicoBO.remove(agendamentoServico);
	}

	public AgendamentoServico findById(Long id) throws Exception {
		return agendamentoServicoBO.findById(id);
	}

	public List<AgendamentoServico> findAll() throws Exception {
		return agendamentoServicoBO.findAll();
	}

	public List<AgendamentoServico> findAll(AgendamentoServico criteria)
			throws Exception {
		return agendamentoServicoBO.findAll(criteria);
	}
}
