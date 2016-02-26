package br.com.tnt.ssg.cmt.bd;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.bo.AutonomoBO;
import br.com.tnt.ssg.cmt.bo.CategoriaBO;
import br.com.tnt.ssg.cmt.bo.CidadeBO;
import br.com.tnt.ssg.cmt.bo.ServicoBO;
import br.com.tnt.ssg.cmt.bo.UFBO;
import br.com.tnt.ssg.dmp.Autonomo;
import br.com.tnt.ssg.dmp.Categoria;
import br.com.tnt.ssg.dmp.Cidade;
import br.com.tnt.ssg.dmp.Servico;
import br.com.tnt.ssg.dmp.UF;

@Stateless
public class ServicoBD {

	@EJB
	private ServicoBO servicoBO;

	@EJB
	private AutonomoBO autonomoBO;

	@EJB
	private CategoriaBO categoriaBO;

	@EJB
	private UFBO ufBO;

	@EJB
	private CidadeBO cidadeBO;

	public void save(Servico servico) throws Exception {
		servicoBO.save(servico);
	}

	public void remove(Servico servico) throws Exception {
		servicoBO.remove(servico);
	}

	public Servico findById(Long id) throws Exception {
		return servicoBO.findById(id);
	}

	public Servico findByIdWithCidades(Long id) throws Exception {
		return servicoBO.findByIdWithCidades(id);
	}

	public List<Servico> findAll() throws Exception {
		return servicoBO.findAll();
	}

	public List<Servico> findAll(Servico criteria) throws Exception {
		return servicoBO.findAll(criteria);
	}

	public List<Autonomo> findAllAutonomo() throws Exception {
		return autonomoBO.findAll();
	}

	public List<Categoria> findAllCategoria() throws Exception {
		return categoriaBO.findAll();
	}

	public List<UF> findAllUF() throws Exception {
		return ufBO.findAll();
	}

	public List<Cidade> findByUF(UF uf) throws Exception {
		Cidade criteria = new Cidade();

		criteria.setUf(uf);

		return cidadeBO.findAll(criteria);
	}

	public Autonomo findAutonomoByLogin(String login) throws Exception {
		return autonomoBO.findAutonomoByLogin(login);
	}
}
