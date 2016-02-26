package br.com.tnt.ssg.dmp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
@NamedQueries({ @NamedQuery(name = "Cliente.findAll", query = "select c from Cliente c"), 
	@NamedQuery(name="Cliente.findByUsuario", query="select c from Cliente c where c.usuario = :usuario" )})
public class Cliente implements BaseEntity {
	private static final long serialVersionUID = -352691082203889783L;

	@Id
	@SequenceGenerator(sequenceName = "cliente_id_seq", allocationSize = 1, name = "cliente_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cliente_seq_gen")
	@Column(name = "id")
	private Long id;

	@Column(name = "nome", length = 64, nullable = false)
	private String nome;

	@Column(name = "cpf", length = 11)
	private String cpf;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario = new Usuario();

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Contato> contatos = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

}
