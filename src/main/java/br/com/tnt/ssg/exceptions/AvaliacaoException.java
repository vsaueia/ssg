package br.com.tnt.ssg.exceptions;

public class AvaliacaoException extends Exception {
	private static final long serialVersionUID = 8173055174238262908L;

	private boolean avaliacaoComSolicitacao;

	public boolean isAvaliacaoComSolicitacao() {
		return avaliacaoComSolicitacao;
	}

	public void setAvaliacaoComSolicitacao(boolean avaliacaoComSolicitacao) {
		this.avaliacaoComSolicitacao = avaliacaoComSolicitacao;
	}
}
