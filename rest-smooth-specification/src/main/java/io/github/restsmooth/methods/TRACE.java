package io.github.restsmooth.methods;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The TRACE method is used to invoke a remote, application-layer loop- back of
 * the request message. The final recipient of the request SHOULD reflect the
 * message received back to the client as the entity-body of a 200 (OK)
 * response. The final recipient is either the
 * 
 * origin server or the first proxy or gateway to receive a Max-Forwards value
 * of zero (0) in the request (see section 14.31). A TRACE request MUST NOT
 * include an entity.
 * 
 * TRACE allows the client to see what is being received at the other end of the
 * request chain and use that data for testing or diagnostic information. The
 * value of the Via header field is of particular interest,
 * since it acts as a trace of the request chain. Use of the Max-Forwards header
 * field allows the client to limit the length of the request chain, which is
 * useful for testing a chain of proxies forwarding messages in an infinite
 * loop.
 * 
 * If the request is valid, the response SHOULD contain the entire request
 * message in the entity-body, with a Content-Type of "message/http". Responses
 * to this method MUST NOT be cached.
 * 
 * @author Avishek Seal
 * @since Mar 9, 2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TRACE {

	String headers() default "*";
}
