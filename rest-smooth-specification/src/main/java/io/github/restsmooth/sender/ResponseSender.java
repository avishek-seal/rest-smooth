package io.github.restsmooth.sender;

import io.github.restsmooth.format.FormatSupprt;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface ResponseSender extends FormatSupprt{
	
	void send(HttpServletResponse httpServletResponse, Object object, AsyncContext asyncContext) throws IOException;
}
