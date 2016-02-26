package br.com.tnt.ssg.bmt.bo;

import java.util.List;

import br.com.tnt.ssg.bmt.dao.CategoriaDAO;
import br.com.tnt.ssg.dmp.Categoria;

public class CategoriaBO {

	private CategoriaDAO categoriaDAO;

	public CategoriaBO() {
		this.categoriaDAO = new CategoriaDAO();
	}

	public void save(Categoria categoria) throws Exception {
		categoriaDAO.save(categoria);
	}

	public void remove(Categoria categoria) throws Exception {
		categoriaDAO.remove(categoria);
	}

	public Categoria findById(Long id) throws Exception {
		return categoriaDAO.findById(id);
	}

	public List<Categoria> findAll() throws Exception {
		return categoriaDAO.findAll();
	}
}
