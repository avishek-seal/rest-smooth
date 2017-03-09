package io.github.restsmooth.constants;

public enum ContentType {

	JSON("application/json"), XML("application/xml"), CSV("text/csv");
	
	String value;
	
	private ContentType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
