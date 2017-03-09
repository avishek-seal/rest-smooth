package io.github.restsmooth.methods;

/**
 * This specification reserves the method name CONNECT for use with a proxy that
 * can dynamically switch to being a tunnel (e.g. SSL tunneling).
 * 
 * @author Avishek Seal
 * @since Mar 9, 2017
 */
public @interface CONNECT {
	
	String headers() default "*";
}
