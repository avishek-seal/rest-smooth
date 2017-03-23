package io.github.restsmooth.exceptions;

public class InvalidContentType extends RestSmoothException{

	private static final long serialVersionUID = -6320715183160025684L;

	public InvalidContentType(String expected, String got) {
		super("Invalid Content type. Expected " + expected + ", Got " + got);
	}
	
	@Override
	public int getCode() {
		return 501;
	}
}
