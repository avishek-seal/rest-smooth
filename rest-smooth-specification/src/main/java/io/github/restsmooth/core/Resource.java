package io.github.restsmooth.core;

import io.github.restsmooh.rules.Async;
import io.github.restsmooh.rules.PathVariable;
import io.github.restsmooh.rules.Payload;
import io.github.restsmooh.rules.QueryObject;
import io.github.restsmooth.exceptions.AmbiguousAnnotationsException;
import io.github.restsmooth.exceptions.AmbiguousPathException;
import io.github.restsmooth.response.ApplicationResponse;
import io.github.restsmooth.sender.ResponseSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * @author Avishek Seal
 * @since Mar 13, 2017
 * @param <T>
 */
public final class Resource<T> extends AbstractRegisteredGenerator implements Serializable{

	private static final long serialVersionUID = 441892752356309966L;
	
	private final String resourceName;
	
	private final String produces;
	
	private final String consumes;

	private final Class<T> resourceClass;
	
	private final boolean async;
	
	private final Map<Class<?>, Map<String, Operation>> operations = new HashMap<>();
	
	private final T resourceInstance;
	
	/**
	 * 
	 * @param resourceName
	 * @param produces
	 * @param consumes
	 * @param resourceClass
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Resource(String resourceName, String produces, String consumes, Class<T> resourceClass) throws InstantiationException, IllegalAccessException {
		super();
		this.resourceName = resourceName.replace("/", "");
		this.produces = produces;
		this.consumes = consumes;
		this.resourceClass = resourceClass;
		
		Annotation[] annotations = resourceClass.getAnnotations();
		
		if(annotations.length == 2) {
			if(annotations[0].annotationType().equals(Async.class) || annotations[1].annotationType().equals(Async.class)) {
				this.async = true;
			} else {
				this.async = false;
			}
		} else {
			this.async = false;
		}
		
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
	
	/**
	 * 
	 * @param method
	 * @param path
	 * @return
	 */
	public final Operation getOperation(Class<?> method, String path) {
		return operations.get(method).get(path);
	}
	
	/**
	 * this method is used to invoke the appropriate resource method
	 * to perform the required operation
	 * 
	 * @param method
	 * @param path
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException 
	 */
	public final void invokeOperation(Class<?> method, ResourceQuery queryObject, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ResponseSender responseSender) throws IOException{
		final ApplicationResponse<Object> applicationResponse = new ApplicationResponse<>();
		
		try{
			final Map<String, Operation> map = operations.get(method);
			
			if(map == null) {
				applicationResponse.setCode(403);
				applicationResponse.setMessage("This mehod is not supported");
				applicationResponse.setSuccess(false);
			} else {
				final Operation operation = map.get(queryObject.getPath());
				
				if(operation == null) {
					applicationResponse.setCode(404);
					applicationResponse.setMessage("Requested path is not available");
					applicationResponse.setSuccess(false);
				} else {
					final List<Argument> arguments = operation.getArguments();
					
					Object[] objects = null;
					
					if(arguments != null && !arguments.isEmpty()) {
						objects = new Object[arguments.size()];
						
						int index = 0;
						
						for(Argument argument : operation.getArguments()) {
							if(argument.getAnnotation() == null) {
								if(argument.getType().equals(HttpServletRequest.class)) {
									objects[index] = httpServletRequest;
								} else if(argument.getType().equals(HttpServletResponse.class)) {
									objects[index] = httpServletResponse;
								} else if(argument.getType().equals(HttpSession.class)) {
									objects[index] = httpServletRequest.getSession(false);
								} else {
									objects[index] = null;
								}
							} else {
								if(argument.getAnnotation().annotationType().equals(Payload.class)) {
									final StringBuilder jasonBuff = new StringBuilder();
								     
									String line = null;

									try (BufferedReader reader = httpServletRequest.getReader()){
								        while ((line = reader.readLine()) != null) {
								        	jasonBuff.append(line);
								        }
								     }
									
									objects[index] = MAPPER.readValue(jasonBuff.toString(), argument.getType());
								} else if(argument.getAnnotation().getClass().equals(PathVariable.class)) {
									if(queryObject.isSubPathPresent()) {
										objects[index] = queryObject.getSubPath();
									} else {
										objects[index] = null;
									}
								} else if(argument.getAnnotation().getClass().equals(QueryObject.class)) {
									ObjectMapper mapper = new ObjectMapper();
									
									objects[index] = mapper.convertValue(queryObject.getQuery(), argument.getType());
								}
							}
							
							index++;
						}
					} else {
						objects = new Object[0];
					}
					
					Object returnedObject = operation.getMethod().invoke(this.resourceInstance, objects); //invoking the method of this resource instance
					
					applicationResponse.setCode(200);
					applicationResponse.setMessage("Success");
					applicationResponse.setSuccess(true);
					
					if(!(returnedObject instanceof Void)) {
						applicationResponse.setData(returnedObject);
					}
				}
			}
		} catch(JsonParseException jsonParseException) {
			applicationResponse.setCode(400);
			applicationResponse.setMessage(jsonParseException.getMessage());
			applicationResponse.setSuccess(false);
		} catch(JsonMappingException jsonMappingException) {
			applicationResponse.setCode(400);
			applicationResponse.setMessage(jsonMappingException.getMessage());
			applicationResponse.setSuccess(false);
		} catch (Exception e) {
			e.printStackTrace();
			applicationResponse.setCode(403);
			applicationResponse.setMessage("This mehod is not supported");
			applicationResponse.setSuccess(false);
		} catch (Error e) {
			applicationResponse.setCode(500);
			applicationResponse.setMessage("Internal Server Error");
			applicationResponse.setSuccess(false);
		}
		
		responseSender.send(httpServletResponse, applicationResponse);
	}
	
	private final void populateOperations(Class<T> resourceClass) {
		Arrays.asList(resourceClass.getMethods()).forEach(method -> {
			Annotation[] annotations = method.getAnnotations();
			
			if(annotations != null && annotations.length > 0) {
				if(annotations.length == 1) {
					final Operation operation = getOperationGenerataor(annotations[0].annotationType()).generate(method, annotations[0]);
					
					if(this.async) {
						operation.setAsync(this.async);
					} else {
						Annotation[] methodAnnotations = method.getAnnotations();
						
						if(methodAnnotations.length == 2 && (methodAnnotations[0].annotationType().equals(Async.class) || methodAnnotations[1].annotationType().equals(Async.class))) {
							operation.setAsync(true);
						}
					}
					
					Map<String, Operation> map= operations.get(annotations[0].annotationType());
					
					if(map == null) {
						map = new HashMap<>();
					}
					
					final Operation oldOperation = map.put(operation.getPath().replace("/", ""), operation);
					
					if(oldOperation != null) {
						throw new AmbiguousPathException(resourceInstance.getClass().getName(), operation.getPath(), operation.getMethod().getName(), oldOperation.getMethod().getName());
					}
					
					operations.put(annotations[0].annotationType(), map);
				} else {
					throw new AmbiguousAnnotationsException(resourceClass.getClass().getName(), method.getName(), annotations);
				}
			}
		});
	}
}
