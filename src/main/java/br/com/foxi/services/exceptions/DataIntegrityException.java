package br.com.foxi.services.exceptions;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = -2687676761803780390L;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
}	
