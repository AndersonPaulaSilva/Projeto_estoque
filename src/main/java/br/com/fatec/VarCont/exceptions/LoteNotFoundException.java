package br.com.fatec.VarCont.exceptions;

public class LoteNotFoundException extends Exception{
	private static final long serialVersionUID = 2520703952432225874L;

	public LoteNotFoundException() {
		super();
	}

	public LoteNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoteNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoteNotFoundException(String message) {
		super(message);
	}

	public LoteNotFoundException(Throwable cause) {
		super(cause);
	}

}
