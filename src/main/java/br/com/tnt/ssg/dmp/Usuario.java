package br.com.tnt.ssg.dmp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "usuario")
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "select u from Usuario u order by u.login") })
public class Usuario implements BaseEntity {

	private static final long serialVersionUID = -7589061020764543643L;

	@Id
	@SequenceGenerator(sequenceName = "usuario_id_seq", allocationSize = 1, name = "usuario_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "usuario_seq_gen")
	@Column(name = "id")
	private Long id;

	@Column(name = "login", nullable = false)
	private String login;

	@Column(name = "senha", nullable = false)
	private String senha;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<UsuarioPermissao> usuarioPermissaoList = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<UsuarioPermissao> getUsuarioPermissaoList() {
		return usuarioPermissaoList;
	}

	public void setUsuarioPermissaoList(
			List<UsuarioPermissao> usuarioPermissaoList) {
		this.usuarioPermissaoList = usuarioPermissaoList;
	}

	public void addUsuarioPermissao(UsuarioPermissao usuarioPermissao) {
		this.usuarioPermissaoList.add(usuarioPermissao);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
