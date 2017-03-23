package io.github.restsmooh.rules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation makes a resources or a resource method
 * asynchronous. i.e. the request will be processed in a separate thread pool
 * other than http request thread pool.
 * Use this annotation only when a method or a resource with all its methods
 * require enormous amount of time to complete its execution.
 * 
 * @author Avishek Seal
 * @since Mar 23, 2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
public @interface Async {

}
