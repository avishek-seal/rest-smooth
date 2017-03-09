package io.github.restsmooth.response;

/**
 * This type of response contains all the properties of application response
 * as well mandatory security token is passed with the response.
 * 
 * @author Avishek Seal
 * @since Mar 9, 2017
 * @param <T>
 */
public class SecureApplicationResponse<T> extends ApplicationResponse<T>{

	private transient static final long serialVersionUID = -6894684395347053678L;

	private final String token;

	public SecureApplicationResponse(CharSequence token) {
		super();
		this.token = String.class.cast(token);
	}

	public String getToken() {
		return token;
	}
}
