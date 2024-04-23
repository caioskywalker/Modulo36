package br.com.cfarias.exceptions;

public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6412869169568453973L;
	
	public DaoException(String msg, Exception e) {
		super(msg,e);
	}

}
