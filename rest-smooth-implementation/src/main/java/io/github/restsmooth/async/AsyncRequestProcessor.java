package io.github.restsmooth.async;

import io.github.restsmooth.context.RestSmoothContext;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AsyncRequestProcessor implements Runnable{

	private final RestSmoothContext restSmoothContext;
	
	private final AsyncContext asyncContext;
	
	private final String HTTP_METHOD;
	
	public AsyncRequestProcessor(String HTTP_METHOD, AsyncContext asyncContext, RestSmoothContext restSmoothContext) {
		this.HTTP_METHOD = HTTP_METHOD;
		this.asyncContext = asyncContext;
		this.restSmoothContext = restSmoothContext;
	}
	@Override
	public void run() {
		try {
			HttpServletRequest httpServletRequest = HttpServletRequest.class.cast(asyncContext.getRequest());
			HttpServletResponse httpServletResponse = HttpServletResponse.class.cast(asyncContext.getResponse());
			
			if(HTTP_METHOD.equals("GET")) {
				this.restSmoothContext.get(httpServletRequest, httpServletResponse);
			} else if(HTTP_METHOD.equals("PUT")) {
				this.restSmoothContext.put(httpServletRequest, httpServletResponse);
			} else if(HTTP_METHOD.equals("POST")) {
				this.restSmoothContext.post(httpServletRequest, httpServletResponse);
			} else if(HTTP_METHOD.equals("DELETE")) {
				this.restSmoothContext.delete(httpServletRequest, httpServletResponse);
			} else if(HTTP_METHOD.equals("OPTIONS")) {
				
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}
