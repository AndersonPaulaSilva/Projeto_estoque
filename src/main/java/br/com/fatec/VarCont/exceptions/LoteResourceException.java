package br.com.fatec.VarCont.exceptions;

public class LoteResourceException extends Exception {
	private static final long serialVersionUID = 5355539536519210430L;

	public LoteResourceException() {
		super();
	}

	public LoteResourceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoteResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoteResourceException(String message) {
		super(message);
	}

	public LoteResourceException(Throwable cause) {
		super(cause);
	}

}
