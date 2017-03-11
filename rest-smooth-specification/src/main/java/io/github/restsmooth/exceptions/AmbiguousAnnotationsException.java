package io.github.restsmooth.exceptions;

import java.lang.annotation.Annotation;

public class AmbiguousAnnotationsException extends RuntimeException {

	private static final long serialVersionUID = 2829383334532951845L;

	public AmbiguousAnnotationsException(String resourceName, String methodName, Annotation[] annotations) {
		super(methodName + " of " + resourceName + " Resource " + " has "
				+ annotations.length + " Annotations, Expected 1");
	}

}
