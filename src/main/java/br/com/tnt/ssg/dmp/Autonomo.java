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
@Table(name = "autonomo")
@NamedQueries({ @NamedQuery(name = "Autonomo.findAll", query = "select a from Autonomo a order by a.nomeFantasia") })
public class Autonomo implements BaseEntity {

	private static final long serialVersionUID = -2635762515365426736L;

	@Id
	@SequenceGenerator(sequenceName = "autonomo_id_seq", allocationSize = 1, name = "autonomo_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "autonomo_seq_gen")
	@Column(name = "id")
	private Long id;

	@Column(unique = true, name = "nome_fantasia", length = 128, nullable = false)
	private String nomeFantasia;

	@Column(name = "cpf", length = 11, nullable = false)
	private String cpf;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "usuario_id")
	private Usuario usuario = new Usuario();

	@OneToMany(mappedBy = "autonomo", cascade = CascadeType.ALL)
	private List<Contato> contatos = new ArrayList<>();

	@OneToMany(mappedBy = "autonomo", cascade = CascadeType.ALL)
	private List<Servico> servicos;

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

	public String getCpfFormatado() {
		return this.cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
				"$1.$2.$3-$4");
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	@Override
	public String toString() {
		return this.nomeFantasia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
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
		Autonomo other = (Autonomo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
