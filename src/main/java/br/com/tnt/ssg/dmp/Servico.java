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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "servico")
@NamedQueries({
		@NamedQuery(name = "Servico.findAll", query = "select s from Servico s order by s.descricao"),
		@NamedQuery(name = "Servico.findByCategoria", query = "select s from Servico s where s.categoria = :categoria"),
		@NamedQuery(name = "Servico.findByAutonomo", query = "select s from Servico s where s.autonomo = :autonomo") })
public class Servico implements BaseEntity {

	private static final long serialVersionUID = -3177844409573572254L;

	@Id
	@SequenceGenerator(sequenceName = "servico_id_seq", allocationSize = 1, name = "servico_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "servico_seq_gen")
	@Column(name = "id")
	private Long id;

	@Column(name = "descricao", length = 256)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "autonomo_id")
	private Autonomo autonomo;

	@OneToMany(mappedBy = "servico", cascade = CascadeType.ALL)
	private List<CidadeServico> cidadeServicoList = new ArrayList<>();

	@Transient
	private Cidade cidade;

	@Transient
	private UF uf;

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Autonomo getAutonomo() {
		return autonomo;
	}

	public void setAutonomo(Autonomo autonomo) {
		this.autonomo = autonomo;
	}

	public List<CidadeServico> getCidadeServicoList() {
		return cidadeServicoList;
	}

	public void setCidadeServicoList(List<CidadeServico> cidadeServicoList) {
		this.cidadeServicoList = cidadeServicoList;
	}

	public void addCidadeServico(CidadeServico cidadeServico) {
		this.cidadeServicoList.add(cidadeServico);
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
		Servico other = (Servico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}
}
