package br.com.tnt.ssg.dmp;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.tnt.ssg.enums.SituacaoServico;

@Entity
@Table(name = "solicitacao_servico")
@NamedQueries({
		@NamedQuery(name = "SolicitacaoServico.findAll", query = "select ss from SolicitacaoServico ss"),
		@NamedQuery(name = "SolicitacaoServico.findBySituacaoAndCliente", query = "select ss from SolicitacaoServico ss where ss.cliente = :cliente and ss.situacaoServico = :situacaoServico order by ss.dataSolicitacao"),
		@NamedQuery(name = "SolicitacaoServico.findAllByCliente", query = "select ss from SolicitacaoServico ss where ss.cliente = :cliente order by ss.dataSolicitacao"),
		@NamedQuery(name = "SolicitacaoServico.findAllExecutadosByServico", query = "select distinct ss from SolicitacaoServico ss where ss.servico = :servico and ss.situacaoServico = :situacaoServico order by ss.dataSolicitacao") })
public class SolicitacaoServico implements BaseEntity {
	private static final long serialVersionUID = -7886051123193047014L;

	@Id
	@SequenceGenerator(sequenceName = "solicitacao_servico_id_seq", allocationSize = 1, name = "solicitacao_servico_seq_gen")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "solicitacao_servico_seq_gen")
	@Column(name = "id")
	private Long id;

	@Column(name = "data_solicitacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSolicitacao;

	@ManyToOne
	@JoinColumn(name = "servico_id")
	private Servico servico;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@Column(name = "situacao_servico")
	@Enumerated(EnumType.STRING)
	private SituacaoServico situacaoServico;

	@Transient
	private Autonomo autonomo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public SituacaoServico getSituacaoServico() {
		return situacaoServico;
	}

	public void setSituacaoServico(SituacaoServico situacaoServico) {
		this.situacaoServico = situacaoServico;
	}

	public Autonomo getAutonomo() {
		return autonomo;
	}

	public void setAutonomo(Autonomo autonomo) {
		this.autonomo = autonomo;
	}
}
