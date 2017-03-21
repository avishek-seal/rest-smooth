package io.github.restsmooth.context;

import io.github.restsmooth.async.AsyncRequestProcessor;
import io.github.restsmooth.configs.RestSmoothConfiguration;
import io.github.restsmooth.constants.ContentType;
import io.github.restsmooth.core.Resource;
import io.github.restsmooth.core.ResourceQuery;
import io.github.restsmooth.listeners.RestSmoothAsyncListener;
import io.github.restsmooth.methods.DELETE;
import io.github.restsmooth.methods.GET;
import io.github.restsmooth.methods.POST;
import io.github.restsmooth.methods.PUT;
import io.github.restsmooth.sender.ResponseSender;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.reflections.Reflections;

public class RestSmoothContext implements Serializable{
	private static final long serialVersionUID = 7234557245032842333L;

	private static final RestSmoothConfiguration CONFIGURATION = new RestSmoothConfiguration();
	
	private static final Map<String, Resource<?>> RESOURCES = new HashMap<>();
	
	private static final Map<String, ResponseSender> SENDERS = new HashMap<>();
	
	private final String webApplicationContext;
	
	static{
		final Reflections reflections = new Reflections(CONFIGURATION.getPackageToScan());
		
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(io.github.restsmooh.rules.Resource.class);
		
		classes.forEach(clazz -> {
			Annotation annotation = null;
			
			for(Annotation ano : clazz.getAnnotations()) {
				if(ano.annotationType().equals(io.github.restsmooh.rules.Resource.class)) {
					annotation = ano;
					break;
				}
			}
			
			io.github.restsmooh.rules.Resource resource = io.github.restsmooh.rules.Resource.class.cast(annotation);
			
			try {
				@SuppressWarnings({ "rawtypes", "unchecked" })
				final Resource<?> resourceMeta = new Resource(resource.name(), resource.produces().getValue(), resource.consumes().getValue(), clazz);
				
				RESOURCES.put(resource.name().replace("/", ""), resourceMeta);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		SENDERS.put(ContentType.JSON.getValue(), new ResponseSender() {
			
			@Override
			public void send(HttpServletResponse httpServletResponse, Object object) throws IOException {
				httpServletResponse.setContentType(ContentType.JSON.getValue());
				OBJECTMAPPER.writeValue(httpServletResponse.getWriter(), object);
			}
		});
		
		SENDERS.put(ContentType.XML.getValue(), new ResponseSender() {
			
			@Override
			public void send(HttpServletResponse httpServletResponse, Object object) throws IOException {
				httpServletResponse.setContentType(ContentType.XML.getValue());
				XMLMAPPER.writeValue(httpServletResponse.getWriter(), object);
			}
		});
	}
	
	public RestSmoothContext(String webContext) {
		this.webApplicationContext = webContext;
	}

	public String getWebApplicationContext() {
		return webApplicationContext;
	}
	
	public final void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		service(GET.class, httpServletRequest, httpServletResponse);
	}
	
	public final void post(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		service(POST.class, httpServletRequest, httpServletResponse);
	}
	
	public final void put(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		service(PUT.class, httpServletRequest, httpServletResponse);
	}
	
	public final void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		service(DELETE.class, httpServletRequest, httpServletResponse);
	}
	
	private final void service(Class<?> method, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException{
		final ResourceQuery queryObject = new ResourceQuery(httpServletRequest, webApplicationContext);
		
		final Resource<?> resource = RESOURCES.get(queryObject.getResourceName());
		
		if(resource.isMethodAsync(method, queryObject)) {
			final AsyncContext asyncContext = httpServletRequest.startAsync();
			asyncContext.addListener(new RestSmoothAsyncListener());
	        final ThreadPoolExecutor executor = (ThreadPoolExecutor) httpServletRequest.getServletContext().getAttribute("executor");
	        executor.execute(new AsyncRequestProcessor(resource, method, queryObject, SENDERS.get(resource.getProduces()), asyncContext));
		} else {
			resource.invokeOperation(method, queryObject, httpServletRequest, httpServletResponse, SENDERS.get(resource.getProduces()));
		}
	}
}
