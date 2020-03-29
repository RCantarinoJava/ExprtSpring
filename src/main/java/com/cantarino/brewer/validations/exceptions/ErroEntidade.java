package com.cantarino.brewer.validations.exceptions;

public class ErroEntidade extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroEntidade(String erro) {
		super(erro);
	}

}
