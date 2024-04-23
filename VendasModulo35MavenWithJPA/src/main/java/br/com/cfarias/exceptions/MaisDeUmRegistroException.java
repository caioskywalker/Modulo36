package br.com.cfarias.exceptions;

public class MaisDeUmRegistroException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4179072742756627505L;
	
	public MaisDeUmRegistroException(String msg, Exception e) {
		super(msg,e);
	}

}
