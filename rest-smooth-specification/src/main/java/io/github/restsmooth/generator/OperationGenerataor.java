package io.github.restsmooth.generator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import io.github.restsmooth.core.Operation;

@FunctionalInterface
public interface OperationGenerataor {

	Operation generate(Method method, Annotation annotation);
}
