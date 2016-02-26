package br.com.tnt.ssg.bmt.bo;

import java.util.List;

import br.com.tnt.ssg.bmt.dao.ServicoDAO;
import br.com.tnt.ssg.dmp.Servico;

public class ServicoBO {

	private ServicoDAO servicoDAO;

	public ServicoBO() {
		this.servicoDAO = new ServicoDAO();
	}

	public void save(Servico servico) throws Exception {
		servicoDAO.save(servico);
	}

	public void remove(Servico servico) throws Exception {
		servicoDAO.remove(servico);
	}

	public Servico findById(Long id) throws Exception {
		return servicoDAO.findById(id);
	}

	public List<Servico> findAll() throws Exception {
		return servicoDAO.findAll();
	}
}
