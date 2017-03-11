package io.github.restsmooth.exceptions;

public class AmbiguousPathException extends RuntimeException {

	private static final long serialVersionUID = -3723625116688341185L;

	public AmbiguousPathException(String resourceName, String path, String methodNameOne, String methodNameTwo) {
		super(resourceName + " Resource has two defined methods for path '"
				+ path + "' Method 1 : " + methodNameOne + " Method 2 : "
				+ methodNameTwo);
	}
}
