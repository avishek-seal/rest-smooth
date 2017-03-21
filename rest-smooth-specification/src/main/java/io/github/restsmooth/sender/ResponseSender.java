package io.github.restsmooth.sender;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@FunctionalInterface
public interface ResponseSender {

	ObjectMapper OBJECTMAPPER = new ObjectMapper();
	
	XmlMapper XMLMAPPER = new XmlMapper();
	
	void send(HttpServletResponse httpServletResponse, Object object) throws IOException;
}
