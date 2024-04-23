package br.com.cfarias.exceptions;

public class TipoElementoNaoConhecido extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5156095255278920050L;
	
	public TipoElementoNaoConhecido(String msg, Exception e) {
		super(msg,e);
		
	}

}
