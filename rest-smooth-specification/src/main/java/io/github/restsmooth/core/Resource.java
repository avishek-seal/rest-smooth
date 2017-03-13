package io.github.restsmooth.core;

import io.github.restsmooth.exceptions.AmbiguousAnnotationsException;
import io.github.restsmooth.exceptions.AmbiguousPathException;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Resource<T> extends AbstractRegisteredGenerator implements Serializable{

	private static final long serialVersionUID = 441892752356309966L;
	
	private final String resourceName;
	
	private final String produces;
	
	private final String consumes;

	private final Class<T> resourceClass;
	
	private final Map<Class<?>, Map<String, Operation>> operations = new HashMap<>();
	
	private final T resourceInstance;
	
	public Resource(String resourceName, String produces, String consumes, Class<T> resourceClass) throws InstantiationException, IllegalAccessException {
		super();
		this.resourceName = resourceName;
		this.produces = produces;
		this.consumes = consumes;
		this.resourceClass = resourceClass;
		
		resourceInstance = resourceClass.newInstance();
		
		populateOperations(resourceClass);
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getProduces() {
		return produces;
	}

	public String getConsumes() {
		return consumes;
	}

	public Class<T> getResourceClass() {
		return resourceClass;
	}

	public T getResourceInstance() {
		return resourceInstance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((resourceName == null) ? 0 : resourceName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}

		final Resource<?> other = Resource.class.cast(obj);
		
		if (resourceName == null) {
			if (other.resourceName != null){
				return false;
			}
		} else if (!resourceName.equals(other.resourceName)) {
			return false;
		}
		
		return true;
	}
	
	public final Operation getOperation(Class<?> method, String path) {
		return operations.get(method).get(path);
	}
	
	private final void populateOperations(Class<T> resourceClass) {
		Arrays.asList(resourceClass.getMethods()).forEach(method -> {
			Annotation[] annotations = method.getAnnotations();
			
			if(annotations != null && annotations.length > 0) {
				if(annotations.length == 1) {
					final Operation operation = getOperationGenerataor(annotations[0].annotationType()).generate(method, annotations[0]);
					
					Map<String, Operation> map= operations.get(annotations[0].getClass());
					
					if(map == null) {
						map = new HashMap<>();
					}
					
					final Operation oldOperation = map.put(operation.getPath(), operation);
					
					if(oldOperation != null) {
						throw new AmbiguousPathException(resourceClass.getClass().getName(), operation.getPath(), operation.getMethod().getName(), oldOperation.getMethod().getName());
					}
					
					operations.put(annotations[0].annotationType(), map);
				} else {
					throw new AmbiguousAnnotationsException(resourceClass.getClass().getName(), method.getName(), annotations);
				}
			}
		});
	}
}
