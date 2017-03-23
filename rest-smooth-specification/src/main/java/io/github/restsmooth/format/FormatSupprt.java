package io.github.restsmooth.format;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public interface FormatSupprt {

	ObjectMapper MAPPER = new ObjectMapper();
	
	XmlMapper XMLMAPPER = new XmlMapper();
}
