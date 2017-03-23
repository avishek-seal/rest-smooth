package io.github.restsmooth.error.model;

import java.io.Serializable;

public class ErrorModel implements Serializable{

	private static final long serialVersionUID = 8515991932479067748L;
	
	private int code;
	
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
