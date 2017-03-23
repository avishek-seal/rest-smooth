package io.github.restsmooth.async;

import io.github.restsmooth.core.Resource;
import io.github.restsmooth.core.ResourceQuery;
import io.github.restsmooth.error.model.RestSmoothError;
import io.github.restsmooth.reciever.RequestReciever;
import io.github.restsmooth.sender.ResponseSender;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AsyncRequestProcessor implements Runnable{

	private final Resource<?> resource;
	
	private final AsyncContext asyncContext;
	
	private final Class<?> method;
	
	private final ResourceQuery queryObject;
	
	private final RequestReciever requestReciever;
	
	private final ResponseSender responseSender;
	
	private final RestSmoothError restSmoothError;
	
	public AsyncRequestProcessor(Resource<?> resource, Class<?> method, ResourceQuery queryObject, RequestReciever requestReciever, ResponseSender responseSender, RestSmoothError restSmoothError, AsyncContext asyncContext) {
		this.resource = resource;
		this.asyncContext = asyncContext;
		this.method = method;
		this.queryObject = queryObject;
		this.requestReciever = requestReciever;
		this.responseSender = responseSender;
		this.restSmoothError = restSmoothError;
	}
	@Override
	public void run() {
		try {
			HttpServletRequest httpServletRequest = HttpServletRequest.class.cast(asyncContext.getRequest());
			HttpServletResponse httpServletResponse = HttpServletResponse.class.cast(asyncContext.getResponse());
			resource.invokeOperation(method, queryObject, httpServletRequest, httpServletResponse, requestReciever, responseSender, restSmoothError);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
