package io.github.restsmooth.configs;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Properties;

public class RestSmoothConfiguration {
	
	@KEY(name = "smooth.resource.package")
	private String packageToScan;
	
	{
		final Properties properties = new Properties();
		
		try(InputStream inputStream = Properties.class.getResourceAsStream("/smooth.properties")) {
			properties.load(inputStream);
			
			Class<RestSmoothConfiguration> configClass = RestSmoothConfiguration.class;
			
			Arrays.asList(configClass.getDeclaredFields()).forEach(field -> {
				field.setAccessible(true);
				
				Annotation[] annotations = field.getAnnotations();
				
				KEY key = KEY.class.cast(annotations[0]);
				
				try {
					field.set(this, properties.get(key));
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
