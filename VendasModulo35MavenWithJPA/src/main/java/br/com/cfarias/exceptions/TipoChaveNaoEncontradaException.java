package br.com.cfarias.exceptions;

public class TipoChaveNaoEncontradaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6626214212115912338L;
	
	public TipoChaveNaoEncontradaException(String msg, Exception e) {
		super(msg,e);
	}

}
