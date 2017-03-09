package io.github.restsmooh.rules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.restsmooth.constants.ContentType;

/**
 * Resource annotation over a class will ensure that class will behave like
 * a Resource of REST API. By default this annotation will make a singleton
 * object of that class.
 * 
 * @author Avishek Seal
 * @since Mar 9, 2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Resource {

	String name();
	
	ContentType produces() default ContentType.JSON;
	
	ContentType consumes() default ContentType.JSON;
}
