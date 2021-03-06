package io.github.restsmooth.methods;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The GET method means retrieve whatever information (in the form of an entity)
 * is identified by the Request-URI. If the Request-URI refers to a
 * data-producing process, it is the produced data which shall be returned as
 * the entity in the response and not the source text of the process, unless
 * that text happens to be the output of the process.
 * 
 * The semantics of the GET method change to a "conditional GET" if the request
 * message includes an If-Modified-Since, If-Unmodified-Since, If-Match,
 * If-None-Match, or If-Range header field. A conditional GET method requests
 * that the entity be transferred only under the circumstances described by the
 * conditional header field(s). The conditional GET method is intended to reduce
 * unnecessary network usage by allowing cached entities to be refreshed without
 * requiring multiple requests or transferring data already held by the client.
 * 
 * The semantics of the GET method change to a "partial GET" if the request
 * message includes a Range header field. A partial GET requests that only part
 * of the entity be transferred, as described in section 14.35. The partial GET
 * method is intended to reduce unnecessary network usage by allowing
 * partially-retrieved entities to be completed without transferring data
 * already held by the client.
 * 
 * The response to a GET request is cache-able if and only if it meets the
 * 
 * @author Avishek Seal
 * @since Mar 9, 2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GET {
	
	String path() default "/";
	
	boolean cached() default false;
	
	String expiresOn() default "";
	
	String headers() default "*";
}
