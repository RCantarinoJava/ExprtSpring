package com.cantarino.brewer.validations.exceptions;

public class ClienteJaCadastradoException  extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ClienteJaCadastradoException(String erro) {
		super(erro);
	}

}
