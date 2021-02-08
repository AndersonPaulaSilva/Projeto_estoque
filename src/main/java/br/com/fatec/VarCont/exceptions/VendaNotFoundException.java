package br.com.fatec.VarCont.exceptions;

public class VendaNotFoundException extends Exception {

	private static final long serialVersionUID = 7433177906152602927L;

	public VendaNotFoundException() {
		super();
	}

	public VendaNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public VendaNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public VendaNotFoundException(String message) {
		super(message);
	}

	public VendaNotFoundException(Throwable cause) {
		super(cause);
	}

}
