package io.github.restsmooh.rules;

import io.github.restsmooth.constants.ContentType;

public @interface Resource {

	String name();
	
	ContentType produces() default ContentType.JSON;
	
	ContentType consumes() default ContentType.JSON;
}
