package io.github.restsmooth.core;

import io.github.restsmooth.methods.CONNECT;
import io.github.restsmooth.methods.DELETE;
import io.github.restsmooth.methods.GET;
import io.github.restsmooth.methods.HEAD;
import io.github.restsmooth.methods.OPTIONS;
import io.github.restsmooth.methods.POST;
import io.github.restsmooth.methods.PUT;
import io.github.restsmooth.methods.TRACE;

import java.util.Date;

@SuppressWarnings("deprecation")
public abstract class AbstractRegisteredGenerator implements ResourceFactory{
	
	static{
		GENERATORS.put(CONNECT.class, (method, annotation) -> {
			final Operation operation = new Operation(method);
			
			CONNECT connect = CONNECT.class.cast(annotation);
			
			operation.setPath(connect.path());
			
			return operation;
		});
		
		GENERATORS.put(DELETE.class, (method, annotation) -> {
			final Operation operation = new Operation(method);
			
			DELETE delete = DELETE.class.cast(annotation);
			
			operation.setPath(delete.path());
			
			return operation;
		});
		
		GENERATORS.put(GET.class, (method, annotation) -> {
			final Operation operation = new Operation(method);
			
			GET get = GET.class.cast(annotation);
			
			operation.setPath(get.path());
			operation.setCached(get.cached());
			
			if(!get.expiresOn().equals("")) {
				operation.setExpiresOn(new Date(get.expiresOn()).toGMTString());
			}
			
			return operation;
		});
		
		GENERATORS.put(HEAD.class, (method, annotation) -> {
			final Operation operation = new Operation(method);
			
			HEAD head = HEAD.class.cast(annotation);
			
			operation.setPath(head.path());
			
			return operation;
		});
		
		GENERATORS.put(OPTIONS.class, (method, annotation) -> {
			final Operation operation = new Operation(method);
			
			OPTIONS options = OPTIONS.class.cast(annotation);
			
			operation.setPath(options.path());
			
			return operation;
		});
		
		GENERATORS.put(POST.class, (method, annotation) -> {
			final Operation operation = new Operation(method);
			
			POST post = POST.class.cast(annotation);
			
			operation.setPath(post.path());
			
			return operation;
		});
		
		GENERATORS.put(PUT.class, (method, annotation) -> {
			final Operation operation = new Operation(method);
			
			PUT put = PUT.class.cast(annotation);
			
			operation.setPath(put.path());
			
			return operation;
		});
		
		GENERATORS.put(TRACE.class, (method, annotation) -> {
			final Operation operation = new Operation(method);
			
			TRACE trace = TRACE.class.cast(annotation);
			
			operation.setPath(trace.path());
			
			return operation;
		});
	}
}
