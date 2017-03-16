package io.github.restsmooth.core;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ResourceQuery implements Serializable{

	private static final long serialVersionUID = -3892333350521257265L;

	private final String path;
	
	private String subPath;
	
	private boolean subPathPresent;
	
	private String queryParam;
	
	private boolean queryPresent;
	
	private Map<String, String> query;
	
	public ResourceQuery(String path, String query) {
		String[] pathTokens = path.split("/");
		
		this.path = pathTokens[0];
		
		if(pathTokens.length > 1) {
			subPathPresent = true;
			subPath = pathTokens[1];
		}
		
		if(query != null && !query.trim().equals("")) {
			this.queryParam = query;
			this.queryPresent = true;
		}
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
