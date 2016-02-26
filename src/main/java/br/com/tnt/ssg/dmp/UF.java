package br.com.tnt.ssg.dmp;

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
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "uf")
@NamedQueries({ @NamedQuery(name = "UF.findAll", query = "select uf from UF uf order by uf.nome") })
public class UF implements BaseEntity {

	private static final long serialVersionUID = 7923855363031851829L;

	@Id
	@SequenceGenerator(sequenceName = "uf_id_seq", allocationSize = 1, name = "uf_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uf_seq_gen")
	@Column(name = "id")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "sigla", length = 2)
	private String sigla;

	@OneToMany(mappedBy = "uf", cascade = CascadeType.ALL)
	@XmlTransient
	private List<Cidade> cidades;

	public UF() {

	}

	public UF(Long id) {
		setId(id);
	}

	public UF(String nome, String sigla) {
		setNome(nome);
		setSigla(sigla);
	}

	public UF(Long id, String nome, String sigla) {
		setId(id);
		setNome(nome);
		setSigla(sigla);
	}

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public String toString() {
		return this.sigla;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
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
		UF other = (UF) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
