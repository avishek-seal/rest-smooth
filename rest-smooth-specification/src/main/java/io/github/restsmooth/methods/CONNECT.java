package io.github.restsmooth.methods;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This specification reserves the method name CONNECT for use with a proxy that
 * can dynamically switch to being a tunnel (e.g. SSL tunneling).
 * 
 * @author Avishek Seal
 * @since Mar 9, 2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CONNECT {
	
	String path() default "/";
	
	String headers() default "*";
}
