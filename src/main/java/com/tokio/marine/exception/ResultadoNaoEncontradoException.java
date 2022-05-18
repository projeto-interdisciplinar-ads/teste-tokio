package com.tokio.marine.exception;

public class ResultadoNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8786199100588780612L;
	
	public ResultadoNaoEncontradoException() {
		super();
	}
	
	public ResultadoNaoEncontradoException(String message) {
		super(message);
	}
	
	public ResultadoNaoEncontradoException(Throwable e) {
		super(e);
	}
	
	public ResultadoNaoEncontradoException(String message, Throwable e) {
		super(message, e);
	}
}
