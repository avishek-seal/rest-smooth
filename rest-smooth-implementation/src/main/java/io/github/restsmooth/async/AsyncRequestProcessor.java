package io.github.restsmooth.async;

import io.github.restsmooth.core.Resource;
import io.github.restsmooth.core.ResourceQuery;
import io.github.restsmooth.sender.ResponseSender;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AsyncRequestProcessor implements Runnable{

	private final Resource<?> resource;
	
	private final AsyncContext asyncContext;
	
	private final Class<?> method;
	
	private final ResourceQuery queryObject;
	
	private final ResponseSender responseSender;
	
	public AsyncRequestProcessor(Resource<?> resource, Class<?> method, ResourceQuery queryObject, ResponseSender responseSender, AsyncContext asyncContext) {
		this.resource = resource;
		this.asyncContext = asyncContext;
		this.method = method;
		this.queryObject = queryObject;
		this.responseSender = responseSender;
	}
	@Override
	public void run() {
		try {
			HttpServletRequest httpServletRequest = HttpServletRequest.class.cast(asyncContext.getRequest());
			HttpServletResponse httpServletResponse = HttpServletResponse.class.cast(asyncContext.getResponse());
			resource.invokeOperation(method, queryObject, httpServletRequest, httpServletResponse, responseSender);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
