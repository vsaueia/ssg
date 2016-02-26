package br.com.tnt.ssg.dmp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_permissao")
@NamedQueries({ @NamedQuery(name = "UsuarioPermissao.findAll", query = "select up from UsuarioPermissao up order by up.id") })
public class UsuarioPermissao implements BaseEntity {
	private static final long serialVersionUID = -2871742698783746505L;

	@Id
	@SequenceGenerator(sequenceName = "usuario_permissao_id_seq", allocationSize = 1, name = "usuario_permissao_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "usuario_permissao_seq_gen")
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "permissao_id")
	private Permissao permissao;

	public UsuarioPermissao() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
}
