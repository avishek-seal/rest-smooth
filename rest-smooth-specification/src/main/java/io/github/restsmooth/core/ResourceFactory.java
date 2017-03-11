package io.github.restsmooth.core;

import io.github.restsmooth.generator.OperationGenerataor;

import java.util.HashMap;
import java.util.Map;

public interface ResourceFactory {
	
	Map<Class<?>, OperationGenerataor> GENERATORS = new HashMap<>();
	
	default OperationGenerataor getOperationGenerataor(Class<?> annotationClass) {
		return GENERATORS.get(annotationClass);
	}
}
