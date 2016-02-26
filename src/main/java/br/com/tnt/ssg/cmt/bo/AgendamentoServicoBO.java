package br.com.tnt.ssg.cmt.bo;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.sf.jasperreports.engine.JasperPrint;
import br.com.tnt.ssg.cmt.dao.AgendamentoServicoDAO;
import br.com.tnt.ssg.dmp.AgendamentoServico;
import br.com.tnt.ssg.dmp.Autonomo;
import br.com.tnt.ssg.util.JasperUtils;

@Stateless
public class AgendamentoServicoBO {

	@EJB
	private AgendamentoServicoDAO agendamentoServicoDAO;

	public void save(AgendamentoServico agendamentoServico) throws Exception {
		agendamentoServicoDAO.save(agendamentoServico);
	}

	public void remove(AgendamentoServico agendamentoServico) throws Exception {
		agendamentoServicoDAO.remove(agendamentoServico);
	}

	public AgendamentoServico findById(Long id) throws Exception {
		return agendamentoServicoDAO.findById(id);
	}

	public List<AgendamentoServico> findAll() throws Exception {
		return agendamentoServicoDAO.findAll();
	}

	public List<AgendamentoServico> findAll(AgendamentoServico criteria)
			throws Exception {
		return agendamentoServicoDAO.findAll(criteria);
	}

	public byte[] buildAgendaReport(Autonomo autonomo) throws Exception {
		AgendamentoServico criteria = new AgendamentoServico();
		criteria.setAutonomo(autonomo);

		List<AgendamentoServico> agendamentos = agendamentoServicoDAO
				.findAll(criteria);

		try {

			InputStream agendaReport = AgendamentoServicoBO.class
					.getResourceAsStream("/reports/agenda.jrxml");

			Map<String, Object> params = new HashMap<>();

			JasperPrint jasperPrint = JasperUtils.generateJasperPrint(
					agendamentos, params, agendaReport);

			return JasperUtils.exportPdf(jasperPrint);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
