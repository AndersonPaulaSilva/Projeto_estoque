package br.com.fatec.VarCont.exceptions;

public class VendaResourceException extends Exception{

	private static final long serialVersionUID = 1771834762401999043L;

	public VendaResourceException() {
		super();
	}

	public VendaResourceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public VendaResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public VendaResourceException(String message) {
		super(message);
	}

	public VendaResourceException(Throwable cause) {
		super(cause);
	}

}
