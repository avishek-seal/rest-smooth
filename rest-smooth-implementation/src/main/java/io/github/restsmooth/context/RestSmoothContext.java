package io.github.restsmooth.context;

import io.github.restsmooth.configs.RestSmoothConfiguration;
import io.github.restsmooth.core.Resource;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

public class RestSmoothContext implements Serializable{
	private static final long serialVersionUID = 7234557245032842333L;

	private static final RestSmoothConfiguration CONFIGURATION = new RestSmoothConfiguration();
	
	private static final Map<String, Resource<?>> RESOURCES = new HashMap<>();
	
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
}
