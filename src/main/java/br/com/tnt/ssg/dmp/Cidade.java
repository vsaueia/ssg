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
@Table(name = "cidade")
@NamedQueries({ @NamedQuery(name = "Cidade.findAll", query = "select c from Cidade c order by c.nome") })
public class Cidade implements BaseEntity, Comparable<Cidade> {

	private static final long serialVersionUID = 3518667521061205931L;

	@Id
	@SequenceGenerator(sequenceName = "cidade_id_seq", allocationSize = 1, name = "cidade_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cidade_seq_gen")
	@Column(name = "id")
	private Long id;

	@Column(nullable = false, name = "nome", length = 100)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "uf_id")
	private UF uf;

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

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	@Override
	public int compareTo(Cidade cidade) {
		if (this.uf == null || cidade.getUf() == null) {
			return 0;
		}

		if (this.nome == null || cidade.getNome() == null) {
			return 0;
		}

		if (this.uf.equals(cidade.getUf())) {
			return this.nome.compareTo(cidade.getNome());
		}

		return this.uf.getNome().compareTo(cidade.getUf().getNome());
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
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
