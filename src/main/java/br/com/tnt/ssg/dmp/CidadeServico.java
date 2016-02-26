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
@Table(name = "cidade_servico")
@NamedQueries({ @NamedQuery(name = "CidadeServico.findAll", query = "select cs from CidadeServico cs order by cs.id") })
public class CidadeServico implements BaseEntity, Comparable<CidadeServico> {
	private static final long serialVersionUID = -6427082168808753116L;

	@Id
	@SequenceGenerator(sequenceName = "cidade_servico_id_seq", allocationSize = 1, name = "cidade_servico_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cidade_servico_seq_gen")
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "servico_id")
	private Servico servico;

	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public int compareTo(CidadeServico cidadeServico) {
		if (this.cidade == null || cidadeServico.getCidade() == null) {
			return 0;
		}

		if (this.cidade.getUf() == null
				|| cidadeServico.getCidade().getUf() == null) {
			return 0;
		}

		if (this.cidade.getUf().equals(cidadeServico.getCidade().getUf())) {
			return this.cidade.getNome().compareTo(
					cidadeServico.getCidade().getNome());
		}

		return this.cidade.getUf().getNome()
				.compareTo(cidadeServico.getCidade().getUf().getNome());
	}
}
