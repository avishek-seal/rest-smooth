package io.github.restsmooh.rules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation will be used to map a java class reference to a value
 * used in URL path.
 * 
 * @author Avishek Seal
 * @since Mar 9, 2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PathVariable {

	String value();
}
