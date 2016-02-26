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
import br.com.tnt.ssg.dmp.Avaliacao;
import br.com.tnt.ssg.dmp.SolicitacaoServico;

@Stateless
public class AvaliacaoDAO {

	@PersistenceContext
	private EntityManager em;

	private GenericDAO<Avaliacao> avaliacaoDAO;

	@PostConstruct
	public void init() {
		this.avaliacaoDAO = GenericDAOFactory.getGenericDAO(em,
				Avaliacao.class, TransactionStrategyEnum.JTA);
	}

	public void save(Avaliacao avaliacao) throws Exception {
		avaliacaoDAO.save(avaliacao);
	}

	public void remove(Avaliacao avaliacao) throws Exception {
		avaliacaoDAO.remove(avaliacao.getId());
	}

	public Avaliacao findById(Long id) throws Exception {
		return avaliacaoDAO.findById(id);
	}

	public List<Avaliacao> findAll() throws Exception {
		return avaliacaoDAO.findByNamedQuery("Avaliacao.findAll", null);
	}

	public List<Avaliacao> findAll(Avaliacao criteria) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT av FROM Avaliacao av ");
		hql.append("JOIN av.solicitacaoServico ss ");
		hql.append("JOIN ss.servico s ");
		hql.append("WHERE 1 = 1 ");

		if (criteria.getSolicitacaoServico() != null) {
			SolicitacaoServico ss = criteria.getSolicitacaoServico();
			if (ss.getServico() != null) {
				hql.append("AND ss.servico = :servico ");
				parameters.put("servico", ss.getServico());
			}

			if (ss.getCliente() != null) {
				hql.append("AND ss.cliente = :cliente ");
				parameters.put("cliente", ss.getCliente());
			}

			if (ss.getDataSolicitacao() != null) {
				hql.append("AND ss.dataSolicitacao = :dataSolicitacao ");
				parameters.put("dataSolicitacao", ss.getDataSolicitacao());
			}

			if (ss.getSituacaoServico() != null) {
				hql.append("AND ss.situacaoServico = :situacaoServico ");
				parameters.put("situacaoServico", ss.getSituacaoServico());
			}
			if (ss.getServico() != null) {
				if (ss.getServico().getAutonomo() != null) {
					hql.append("AND s.autonomo = :autonomo");
					parameters.put("autonomo", ss.getServico().getAutonomo());
				}
			}
		}

		return avaliacaoDAO.findByHql(hql.toString(), parameters);
	}

	public Avaliacao findBySolicitacao(SolicitacaoServico ss) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("solicitacaoServico", ss);
		try {
			return avaliacaoDAO.findByNamedQuery("Avaliacao.findBySolicitacao",
					params).get(0);
		} catch (NullPointerException e) {
			return null;
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}

	}
}
