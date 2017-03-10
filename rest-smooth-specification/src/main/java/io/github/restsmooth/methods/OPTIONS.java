package io.github.restsmooth.methods;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The OPTIONS method represents a request for information about the
 * communication options available on the request/response chain identified by
 * the Request-URI. This method allows the client to determine the options
 * and/or requirements associated with a resource, or the capabilities of a
 * server, without implying a resource action or initiating a resource
 * retrieval.
 * 
 * Responses to this method are not cache-able.
 * 
 * If the OPTIONS request includes an entity-body (as indicated by the presence
 * of Content-Length or Transfer-Encoding), then the media type MUST be
 * indicated by a Content-Type field. Although this specification does not
 * define any use for such a body, future extensions to HTTP might use the
 * OPTIONS body to make more detailed queries on the server. A server that does
 * not support such an extension MAY discard the request body.
 * 
 * If the Request-URI is an asterisk ("*"), the OPTIONS request is intended to
 * apply to the server in general rather than to a specific resource. Since a
 * server's communication options typically depend on the resource, the "*"
 * request is only useful as a "ping" or "no-op" type of method; it does nothing
 * beyond allowing the client to test the capabilities of the server. For
 * example, this can be used to test a proxy for HTTP/1.1 compliance (or lack
 * thereof).
 * 
 * If the Request-URI is not an asterisk, the OPTIONS request applies only to
 * the options that are available when communicating with that resource.
 * 
 * A 200 response SHOULD include any header fields that indicate optional
 * features implemented by the server and applicable to that resource (e.g.,
 * Allow), possibly including extensions not defined by this specification. The
 * response body, if any, SHOULD also include information about the
 * communication options. The format for such a
 * 
 * body is not defined by this specification, but might be defined by future
 * extensions to HTTP. Content negotiation MAY be used to select the appropriate
 * response format. If no response body is included, the response MUST include a
 * Content-Length field with a field-value of "0".
 * 
 * The Max-Forwards request-header field MAY be used to target a specific proxy
 * in the request chain. When a proxy receives an OPTIONS request on an
 * absoluteURI for which request forwarding is permitted, the proxy MUST check
 * for a Max-Forwards field. If the Max-Forwards field-value is zero ("0"), the
 * proxy MUST NOT forward the message; instead, the proxy SHOULD respond with
 * its own communication options. If the Max-Forwards field-value is an integer
 * greater than zero, the proxy MUST decrement the field-value when it forwards
 * the request. If no Max-Forwards field is present in the request, then the
 * forwarded request MUST NOT include a Max-Forwards field.
 * 
 * @author Avishek Seal
 * @since Mar 9, 2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OPTIONS {

}
