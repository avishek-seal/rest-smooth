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
	
	public ResourceQuery(String path) {
		if(path != null && !path.trim().equals("")) {
			final String[] splitPath = path.split("/");
			
			if(splitPath != null && splitPath.length > 0) {
				this.path = "/";
				
				if(splitPath.length == 2) {
					String[] spliQuery = splitPath[1].split("?");
					
					this.subPath = spliQuery[0];
					
					subPathPresent = true;
					
					if(spliQuery.length == 2) {
						this.queryParam = spliQuery[1];
						
						queryPresent = true;
					} else {
						//TODO invalid query 
					}
				} else {
					//TODO throw invalid requested url
				}
			} else {
				this.path = path;
			}
		} else {
			this.path = "/";
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
