package br.com.tnt.ssg.dmp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.tnt.ssg.enums.TipoContato;

@Entity
@Table(name = "contato")
@NamedQueries({ @NamedQuery(name = "Contato.findAll", query = "select c from Contato c") })
public class Contato implements BaseEntity, Comparable<Contato> {

	private static final long serialVersionUID = 57797183050954282L;

	@Id
	@SequenceGenerator(sequenceName = "contato_id_seq", allocationSize = 1, name = "contato_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "contato_seq_gen")
	@Column(name = "id")
	private Long id;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Enumerated(EnumType.STRING)
	private TipoContato tipo;

	@ManyToOne
	@JoinColumn(name = "autonomo_id")
	private Autonomo autonomo;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoContato getTipo() {
		return tipo;
	}

	public void setTipo(TipoContato tipo) {
		this.tipo = tipo;
	}

	public Autonomo getAutonomo() {
		return autonomo;
	}

	public void setAutonomo(Autonomo autonomo) {
		this.autonomo = autonomo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int compareTo(Contato contato) {
		if (this.tipo.equals(contato.getTipo())) {
			return this.descricao.compareTo(contato.getDescricao());
		}

		return this.tipo.compareTo(contato.getTipo());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Contato other = (Contato) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
}
