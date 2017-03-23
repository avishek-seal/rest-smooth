package io.github.restsmooth.exceptions;

public abstract class RestSmoothException extends Exception{

	private static final long serialVersionUID = -2657901769310438535L;

	public RestSmoothException(String message) {
		super(message);
	}
	
	public abstract int getCode();
}
