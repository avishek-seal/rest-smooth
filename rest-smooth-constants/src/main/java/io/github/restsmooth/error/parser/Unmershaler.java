package io.github.restsmooth.error.parser;

import io.github.restsmooth.error.model.RestSmoothError;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Unmershaler {
	
	private static final String ERROR_FILE = "smooth-error-mapper.json";
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public static RestSmoothError getRestSmoothError() {
		try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(ERROR_FILE)) {
			return MAPPER.readValue(inputStream, RestSmoothError.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
