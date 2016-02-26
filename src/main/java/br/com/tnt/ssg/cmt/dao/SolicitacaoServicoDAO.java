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
import br.com.tnt.ssg.dmp.Cliente;
import br.com.tnt.ssg.dmp.Servico;
import br.com.tnt.ssg.dmp.SolicitacaoServico;
import br.com.tnt.ssg.enums.SituacaoServico;

@Stateless
public class SolicitacaoServicoDAO {

	@PersistenceContext
	private EntityManager em;

	private GenericDAO<SolicitacaoServico> solicitacaoServicoDAO;

	@PostConstruct
	public void init() {
		this.solicitacaoServicoDAO = GenericDAOFactory.getGenericDAO(em,
				SolicitacaoServico.class, TransactionStrategyEnum.JTA);
	}

	public void save(SolicitacaoServico solicitacaoServico) throws Exception {
		solicitacaoServicoDAO.save(solicitacaoServico);
	}

	public void remove(SolicitacaoServico solicitacaoServico) throws Exception {
		solicitacaoServicoDAO.remove(solicitacaoServico.getId());
	}

	public SolicitacaoServico findById(Long id) throws Exception {
		return solicitacaoServicoDAO.findById(id);
	}

	public List<SolicitacaoServico> findAll() throws Exception {
		return solicitacaoServicoDAO.findByNamedQuery(
				"SolicitacaoServico.findAll", null);
	}

	public List<SolicitacaoServico> findAll(SolicitacaoServico criteria)
			throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT ss FROM SolicitacaoServico ss ");
		hql.append("JOIN ss.servico s ");
		hql.append("WHERE 1 = 1 ");

		if (criteria.getServico() != null) {
			hql.append("AND ss.servico = :servico ");
			parameters.put("servico", criteria.getServico());
		}

		if (criteria.getCliente() != null) {
			hql.append("AND ss.cliente = :cliente ");
			parameters.put("cliente", criteria.getCliente());
		}

		if (criteria.getDataSolicitacao() != null) {
			hql.append("AND ss.dataSolicitacao = :dataSolicitacao ");
			parameters.put("dataSolicitacao", criteria.getDataSolicitacao());
		}

		if (criteria.getSituacaoServico() != null) {
			hql.append("AND ss.situacaoServico = :situacaoServico ");
			parameters.put("situacaoServico", criteria.getSituacaoServico());
		}

		if (criteria.getAutonomo() != null) {
			hql.append("AND s.autonomo = :autonomo");
			parameters.put("autonomo", criteria.getAutonomo());
		}

		return solicitacaoServicoDAO.findByHql(hql.toString(), parameters);
	}

	public List<SolicitacaoServico> findAll(SituacaoServico situacaoServico,
			Cliente cliente) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cliente", cliente);
		if (situacaoServico != null) {
			params.put("situacaoServico", situacaoServico);
			return solicitacaoServicoDAO.findByNamedQuery(
					"SolicitacaoServico.findBySituacaoAndCliente", params);
		}
		return solicitacaoServicoDAO.findByNamedQuery(
				"SolicitacaoServico.findAllByCliente", params);
	}

	public List<SolicitacaoServico> findAllExecutadosByServico(Servico servico)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("servico", servico);
		params.put("situacaoServico", SituacaoServico.EXECUTADO);
		return solicitacaoServicoDAO.findByNamedQuery(
				"SolicitacaoServico.findAllExecutadosByServico", params);
	}
}
