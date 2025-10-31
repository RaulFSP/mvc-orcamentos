package io.github.app.exceptions;

public class ClienteValidacaoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteValidacaoException (String msg) {
		super(msg);
	}

	public ClienteValidacaoException(String msg, Throwable cause) {
	    super(msg, cause);
	}


	
}
