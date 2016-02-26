package br.com.tnt.ssg.dmp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "avaliacao")
@NamedQueries({
		@NamedQuery(name = "Avaliacao.findAll", query = "select a from Avaliacao a order by a.id"),
		@NamedQuery(name = "Avaliacao.findBySolicitacao", query = "SELECT a from Avaliacao a where a.solicitacaoServico = :solicitacaoServico") })
public class Avaliacao implements BaseEntity {
	private static final long serialVersionUID = 2857539507946731968L;

	@Id
	@SequenceGenerator(sequenceName = "avaliacao_id_seq", allocationSize = 1, name = "avaliacao_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "avaliacao_seq_gen")
	@Column(name = "id")
	private Long id;

	@Column(name = "nota", nullable = false)
	private Integer nota;

	@Column(name = "comentario", length = 512, nullable = false)
	private String comentario;

	@Column(name = "data_avaliacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAvaliacao;

	@OneToOne(orphanRemoval = true)
	@JoinColumn(name = "solicitacao_servico_id")
	private SolicitacaoServico solicitacaoServico = new SolicitacaoServico();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public SolicitacaoServico getSolicitacaoServico() {
		return solicitacaoServico;
	}

	public void setSolicitacaoServico(SolicitacaoServico solicitacaoServico) {
		this.solicitacaoServico = solicitacaoServico;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}
}
