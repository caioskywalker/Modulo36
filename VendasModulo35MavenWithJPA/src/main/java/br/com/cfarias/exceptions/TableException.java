package br.com.cfarias.exceptions;

public class TableException extends Exception {
	
	/**
	 * 
	 */

	private static final long serialVersionUID = 2895432706749619012L;

	public TableException(String msg, Exception e) {
		super(msg,e);
			
	}

}
