package io.github.restsmooth.core;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ResourceQuery implements Serializable{

	private static final long serialVersionUID = -3892333350521257265L;
	
	private final String resourceName;

	private final String path;
	
	private String subPath;
	
	private boolean subPathPresent;
	
	private String queryParam;
	
	private boolean queryPresent;
	
	private Map<String, String> query;
	
	public ResourceQuery(HttpServletRequest httpServletRequest, String context) {
		final String requestedResource = httpServletRequest.getRequestURI().replace(context, "");
		
		final String[] uriTokens = requestedResource.split("/");
		
		this.resourceName = uriTokens[1];
		
		StringBuilder path = new StringBuilder("");
		
		if(uriTokens.length > 2) {
			for(int index = 2; index < uriTokens.length; index++) {
				path.append(uriTokens[index]);
				
				if(index != uriTokens.length -1) {
					path.append("/");
				}
			}
		}
		
		this.path = path.toString();
		this.queryParam = httpServletRequest.getQueryString();
	}
	
	public Map<String, String> getQuery(){
		if(query == null && queryParam != null && !queryParam.trim().equals("")) {
			query = new HashMap<>();
			
			String[] keyValues = queryParam.trim().split("&");
			
			Arrays.stream(keyValues).forEach(keyValue -> {
				String[] array = keyValue.split("=");
				
				if(array != null && array.length > 0) {
					if(array.length == 2) {
						query.put(array[0], array[1]);
					} else if(array.length == 1) {
						query.put(array[0], "");
					}
				}
			});
		}
		
		return query;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getPath() {
		return path;
	}

	public String getSubPath() {
		return subPath;
	}

	public boolean isSubPathPresent() {
		return subPathPresent;
	}

	public boolean isQueryPresent() {
		return queryPresent;
	}
}
