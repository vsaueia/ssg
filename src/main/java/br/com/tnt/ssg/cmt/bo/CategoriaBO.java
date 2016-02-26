package br.com.tnt.ssg.cmt.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.dao.CategoriaDAO;
import br.com.tnt.ssg.dmp.Categoria;

@Stateless
public class CategoriaBO {

	@EJB
	private CategoriaDAO categoriaDAO;

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

	public List<Categoria> findAll(Categoria criteria) throws Exception {
		return categoriaDAO.findAll(criteria);
	}
}
