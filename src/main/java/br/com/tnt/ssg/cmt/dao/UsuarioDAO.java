package br.com.tnt.ssg.cmt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.tnt.ssg.dao.factory.GenericDAO;
import br.com.tnt.ssg.dao.factory.GenericDAOFactory;
import br.com.tnt.ssg.dao.factory.TransactionStrategyEnum;
import br.com.tnt.ssg.dmp.Permissao;
import br.com.tnt.ssg.dmp.Usuario;
import br.com.tnt.ssg.dmp.UsuarioPermissao;

@Stateless
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;

	private GenericDAO<Usuario> usuarioDAO;

	@EJB
	private PermissaoDAO permissaoDAO;

	@PostConstruct
	public void init() {
		this.usuarioDAO = GenericDAOFactory.getGenericDAO(em, Usuario.class,
				TransactionStrategyEnum.JTA);
	}

	public void save(Usuario usuario) throws Exception {
		usuarioDAO.save(usuario);
	}

	public void remove(Usuario usuario) throws Exception {
		usuarioDAO.remove(usuario.getId());
	}

	public Usuario findById(Long id) throws Exception {
		return usuarioDAO.findById(id);
	}

	public List<Usuario> findAll() throws Exception {
		return usuarioDAO.findByNamedQuery("Usuario.findAll", null);
	}

	public List<Usuario> findAll(Usuario criteria) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT u FROM Usuario u ");
		hql.append("WHERE 1 = 1 ");

		if (criteria.getLogin() != null
				&& !criteria.getLogin().trim().isEmpty()) {
			hql.append("AND LOWER(u.login) LIKE :nome ");
			parameters.put("nome", "%" + criteria.getLogin().toLowerCase()
					+ "%");
		}

		return usuarioDAO.findByHql(hql.toString(), parameters);
	}

	public void grant(Usuario usuario, String... permissoes) throws Exception {
		if (permissoes != null) {
			for (String permissao : permissoes) {
				Permissao p = new Permissao();
				p.setNome(permissao);

				List<Permissao> perms = permissaoDAO.findAll(p);

				if (!perms.isEmpty()) {
					UsuarioPermissao usuarioPermissao = new UsuarioPermissao();
					usuarioPermissao.setUsuario(usuario);
					usuarioPermissao.setPermissao(perms.get(0));

					usuario.addUsuarioPermissao(usuarioPermissao);
				}
			}

			save(usuario);
		}

	}
}
