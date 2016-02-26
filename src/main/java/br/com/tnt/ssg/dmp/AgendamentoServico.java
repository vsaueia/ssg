package br.com.tnt.ssg.dmp;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "agendamento_servico")
@NamedQueries({ @NamedQuery(name = "AgendamentoServico.findAll", query = "select s from AgendamentoServico s") })
public class AgendamentoServico implements BaseEntity {

	private static final long serialVersionUID = 1153423737025936635L;

	@Id
	@SequenceGenerator(sequenceName = "agendamento_servico_id_seq", allocationSize = 1, name = "agendamento_servico_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "agendamento_servico_seq_gen")
	@Column(name = "id")
	private Long id;

	@Column(name = "data_cricao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Column(name = "data_agendamento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAgendamento;

	@Column(name = "confirmado")
	private Boolean confirmado;

	@ManyToOne
	@JoinColumn(name = "solicitacao_servico_id")
	private SolicitacaoServico solicitacaoServico;

	@Transient
	private Autonomo autonomo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Boolean getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}

	public SolicitacaoServico getSolicitacaoServico() {
		return solicitacaoServico;
	}

	public void setSolicitacaoServico(SolicitacaoServico solicitacaoServico) {
		this.solicitacaoServico = solicitacaoServico;
	}

	public Autonomo getAutonomo() {
		return autonomo;
	}

	public void setAutonomo(Autonomo autonomo) {
		this.autonomo = autonomo;
	}
}
