package io.github.restsmooth.configs;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Properties;

public class RestSmoothConfiguration {
	
	@KEY(name = "smooth.resource.package")
	private String packageToScan;
	
	@KEY(name = "smooth.resource.spring")
	private boolean springSupport;
	
	{
		final Properties properties = new Properties();
		
		try(InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("smooth.properties")) {
			properties.load(inputStream);
			
			final Class<RestSmoothConfiguration> configClass = RestSmoothConfiguration.class;
			
			Arrays.asList(configClass.getDeclaredFields()).forEach(field -> {
				field.setAccessible(true);
				
				final Annotation[] annotations = field.getAnnotations();
				
				final KEY key = KEY.class.cast(annotations[0]);
				
				try {
					if(properties.get(key) != null) {
						field.set(this, properties.get(key));
					}
					field.setAccessible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public String getPackageToScan() {
		return packageToScan;
	}
}
