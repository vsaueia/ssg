package br.com.tnt.ssg.exceptions;

public class CategoriaException extends Exception {
	private static final long serialVersionUID = -6407593480429333691L;

	private boolean categoriaComServicos;

	public boolean isCategoriaComServicos() {
		return categoriaComServicos;
	}

	public void setCategoriaComServicos(boolean categoriaComServicos) {
		this.categoriaComServicos = categoriaComServicos;
	}
}
