package br.com.tnt.ssg.exceptions;

public class ServicoException extends Exception {
	private static final long serialVersionUID = -4628551873430565614L;

	private boolean servicoComSolicitacoes;

	public boolean isServicoComSolicitacoes() {
		return servicoComSolicitacoes;
	}

	public void setServicoComSolicitacoes(boolean servicoComSolicitacoes) {
		this.servicoComSolicitacoes = servicoComSolicitacoes;
	}
}
