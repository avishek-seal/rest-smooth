package io.github.restsmooth.methods;

/**
 * The POST method is used to request that the origin server accept the entity
 * enclosed in the request as a new subordinate of the resource identified by
 * the Request-URI in the Request-Line. POST is designed to allow a uniform
 * method to cover the following functions:
 * 
 * - Annotation of existing resources; - Posting a message to a bulletin board,
 * newsgroup, mailing list, or similar group of articles; - Providing a block of
 * data, such as the result of submitting a form, to a data-handling process; -
 * Extending a database through an append operation. The actual function
 * performed by the POST method is determined by the server and is usually
 * dependent on the Request-URI. The posted entity is subordinate to that URI in
 * the same way that a file is subordinate to a directory containing it, a news
 * article is subordinate to a newsgroup to which it is posted, or a record is
 * subordinate to a database.
 * 
 * The action performed by the POST method might not result in a resource that
 * can be identified by a URI. In this case, either 200 (OK) or 204 (No Content)
 * is the appropriate response status, depending on whether or not the response
 * includes an entity that describes the result. Responses to this method are
 * not cacheable, unless the response includes appropriate Cache-Control or
 * Expires header fields. However, the 303 (See Other) response can be used to
 * direct the user agent to retrieve a cacheable resource.
 * 
 * @author Avishek Seal
 * @since Mar 9, 2017
 */
public @interface POST {

	String path() default "/";
	
	String headers() default "*";
}
