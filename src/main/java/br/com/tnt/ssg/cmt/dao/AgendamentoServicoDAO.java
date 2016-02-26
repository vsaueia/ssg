package br.com.tnt.ssg.cmt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.tnt.ssg.dao.factory.GenericDAO;
import br.com.tnt.ssg.dao.factory.GenericDAOFactory;
import br.com.tnt.ssg.dao.factory.TransactionStrategyEnum;
import br.com.tnt.ssg.dmp.AgendamentoServico;

@Stateless
public class AgendamentoServicoDAO {

	@PersistenceContext
	private EntityManager em;

	private GenericDAO<AgendamentoServico> agendamentoServicoDAO;

	@PostConstruct
	public void init() {
		this.agendamentoServicoDAO = GenericDAOFactory.getGenericDAO(em,
				AgendamentoServico.class, TransactionStrategyEnum.JTA);
	}

	public void save(AgendamentoServico agendamentoServico) throws Exception {
		agendamentoServicoDAO.save(agendamentoServico);
	}

	public void remove(AgendamentoServico agendamentoServico) throws Exception {
		agendamentoServicoDAO.remove(agendamentoServico.getId());
	}

	public AgendamentoServico findById(Long id) throws Exception {
		return agendamentoServicoDAO.findById(id);
	}

	public List<AgendamentoServico> findAll() throws Exception {
		return agendamentoServicoDAO.findByNamedQuery(
				"AgendamentoServico.findAll", null);
	}

	public List<AgendamentoServico> findAll(AgendamentoServico criteria) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT ags FROM AgendamentoServico ags ");
		hql.append("JOIN ags.solicitacaoServico ss ");
		hql.append("JOIN ss.servico s ");
		hql.append("WHERE 1 = 1 ");

		if (criteria.getSolicitacaoServico() != null) {
			hql.append("AND ags.solicitacaoServico = :solicitacaoServico ");
			parameters.put("solicitacaoServico", criteria
					.getSolicitacaoServico());
		}

		if (criteria.getAutonomo() != null) {
			hql.append("AND s.autonomo = :autonomo ");
			parameters.put("autonomo", criteria.getAutonomo());
		}

		hql.append("ORDER BY ags.dataAgendamento ");

		return agendamentoServicoDAO.findByHql(hql.toString(), parameters);
	}
}
