package br.com.tnt.ssg.cmt.bd;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.tnt.ssg.cmt.bo.CategoriaBO;
import br.com.tnt.ssg.dmp.Categoria;

@Stateless
public class CategoriaBD {

	@EJB
	private CategoriaBO categoriaBO;

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

	public List<Categoria> findAll(Categoria criteria) throws Exception {
		return categoriaBO.findAll(criteria);
	}
}
