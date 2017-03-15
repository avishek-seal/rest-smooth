package io.github.restsmooth.context;

import io.github.restsmooth.configs.RestSmoothConfiguration;
import io.github.restsmooth.core.Resource;
import io.github.restsmooth.methods.GET;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.reflections.Reflections;

public class RestSmoothContext implements Serializable{
	private static final long serialVersionUID = 7234557245032842333L;

	private static final RestSmoothConfiguration CONFIGURATION = new RestSmoothConfiguration();
	
	private static final Map<String, Resource<?>> RESOURCES = new HashMap<>();
	
	private final String webApplicationContext;
	
	static{
		final Reflections reflections = new Reflections(CONFIGURATION.getPackageToScan());
		
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(io.github.restsmooh.rules.Resource.class);
		
		classes.forEach(clazz -> {
			io.github.restsmooh.rules.Resource resource = io.github.restsmooh.rules.Resource.class.cast(clazz.getAnnotations()[0]);
			
			try {
				@SuppressWarnings({ "rawtypes", "unchecked" })
				final Resource<?> resourceMeta = new Resource(resource.name(), resource.produces().getValue(), resource.consumes().getValue(), clazz);
				
				RESOURCES.put(resource.name(), resourceMeta);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public RestSmoothContext(String webContext) {
		this.webApplicationContext = webContext;
	}

	public String getWebApplicationContext() {
		return webApplicationContext;
	}
	
	public final void get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		String requestedResource = httpServletRequest.getRequestURI().replace(webApplicationContext, "");
		
		System.out.println(RESOURCES.get(requestedResource));
		
		RESOURCES.get(requestedResource).invokeOperation(GET.class, "/", httpServletRequest, httpServletResponse);
	}
	
	public final void post(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	}
	
	public final void put(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	}
	
	public final void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	}
}
