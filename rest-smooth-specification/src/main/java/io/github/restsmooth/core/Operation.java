package io.github.restsmooth.core;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Operation implements Serializable{

	private static final long serialVersionUID = 9009741357079794433L;

	private String path;
	
	private boolean cached;
	
	private String expiresOn;
	
	private final Method method;
	
	private final List<Argument> arguments = new LinkedList<>();

	private final Class<?> returnType;
	
	public Operation(Method method) {
		super();
		this.method = method;
		this.returnType = method.getReturnType();
		
		Arrays.stream(method.getParameters()).forEach(parameter -> {
			final Argument argument = new Argument(parameter.getType());
			
			Annotation[] annotations = parameter.getAnnotations();
			
			if(annotations != null && annotations.length > 0) {
				argument.setAnnotation(annotations[0]);
			}
			
			this.arguments.add(argument);
		});
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}



	public boolean isCached() {
		return cached;
	}

	public void setCached(boolean cached) {
		this.cached = cached;
	}

	public String getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(String expiresOn) {
		this.expiresOn = expiresOn;
	}

	public Method getMethod() {
		return method;
	}

	public List<Argument> getArguments() {
		return arguments;
	}

	public Class<?> getReturnType() {
		return returnType;
	}
}
