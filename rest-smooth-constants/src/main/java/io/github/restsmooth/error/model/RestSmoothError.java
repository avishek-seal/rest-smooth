package io.github.restsmooth.error.model;

import java.io.Serializable;
import java.util.Map;

public class RestSmoothError implements Serializable {

	private static final long serialVersionUID = -2665726510632004961L;

	private Map<String, ErrorModel> errors;

	public ErrorModel getErrorModel(String className) {
		return errors.get(className);
	}

	public Map<String, ErrorModel> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, ErrorModel> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "RestSmoothError [errors=" + errors + "]";
	}
}
