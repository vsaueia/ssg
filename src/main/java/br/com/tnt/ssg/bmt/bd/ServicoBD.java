package br.com.tnt.ssg.bmt.bd;

import java.util.List;

import br.com.tnt.ssg.bmt.bo.AutonomoBO;
import br.com.tnt.ssg.bmt.bo.CategoriaBO;
import br.com.tnt.ssg.bmt.bo.ServicoBO;
import br.com.tnt.ssg.dmp.Autonomo;
import br.com.tnt.ssg.dmp.Categoria;
import br.com.tnt.ssg.dmp.Servico;

public class ServicoBD {

	private ServicoBO servicoBO;
	private AutonomoBO autonomoBO;
	private CategoriaBO categoriaBO;

	public ServicoBD() {
		this.servicoBO = new ServicoBO();
		this.autonomoBO = new AutonomoBO();
		this.categoriaBO = new CategoriaBO();
	}

	public void save(Servico servico) throws Exception {
		servicoBO.save(servico);
	}

	public void remove(Servico servico) throws Exception {
		servicoBO.remove(servico);
	}

	public Servico findById(Long id) throws Exception {
		return servicoBO.findById(id);
	}

	public List<Servico> findAll() throws Exception {
		return servicoBO.findAll();
	}

	public List<Autonomo> findAllAutonomo() throws Exception {
		return autonomoBO.findAll();
	}

	public List<Categoria> findAllCategoria() throws Exception {
		return categoriaBO.findAll();
	}
}
