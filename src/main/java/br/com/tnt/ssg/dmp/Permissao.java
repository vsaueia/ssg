package br.com.tnt.ssg.dmp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "permissao")
@NamedQueries({ @NamedQuery(name = "Permissao.findAll", query = "select p from Permissao p order by p.nome") })
public class Permissao implements BaseEntity {

	private static final long serialVersionUID = -7589061020764543643L;

	@Id
	@SequenceGenerator(sequenceName = "permissao_id_seq", allocationSize = 1, name = "permissao_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "permissao_seq_gen")
	@Column(name = "id")
	private Long id;

	@Column(name = "nome", length = 20, nullable = false)
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return this.nome;
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
		Permissao other = (Permissao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
