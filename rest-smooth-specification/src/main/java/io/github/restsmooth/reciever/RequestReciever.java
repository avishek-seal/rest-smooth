package io.github.restsmooth.reciever;

import io.github.restsmooth.format.FormatSupprt;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface RequestReciever extends FormatSupprt{

	<T> T retrieveObject(HttpServletRequest httpServletRequest, String consumes, Class<T> clazz) throws Exception;
}
