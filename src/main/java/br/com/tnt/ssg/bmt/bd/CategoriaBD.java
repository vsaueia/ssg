package br.com.tnt.ssg.bmt.bd;

import java.util.List;

import br.com.tnt.ssg.bmt.bo.CategoriaBO;
import br.com.tnt.ssg.dmp.Categoria;

public class CategoriaBD {

	private CategoriaBO categoriaBO;

	public CategoriaBD() {
		this.categoriaBO = new CategoriaBO();
	}

	public void save(Categoria categoria) throws Exception {
		categoriaBO.save(categoria);
	}

	public void remove(Categoria categoria) throws Exception {
		categoriaBO.remove(categoria);
	}

	public Categoria findById(Long id) throws Exception {
		return categoriaBO.findById(id);
	}

	public List<Categoria> findAll() throws Exception {
		return categoriaBO.findAll();
	}
}
