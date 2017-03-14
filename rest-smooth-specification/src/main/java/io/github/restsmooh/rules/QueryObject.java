package io.github.restsmooh.rules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to construct an object of class
 * having the similar key(s) of the the query parameters by the same.
 * 
 *  i.e. let the request url is employee?nameContains=A&maxSalary=12000&minSalary=5000.
 *  The class have to be like 
 *  class EmployeeQuery {
 *  	private String nameContains;
 *  	private String/Number maxSalary;
 *  	private String/Number minSalary;
 *  
 *  	//getters/setters
 *  }
 *  
 *  this url query parameters will be mapped to the object of the EmployeeQuery class's object
 *  and will be pointed to the reference over which the following annotation will be applied.
 *  
 * @author Avishek Seal
 * @since Mar 9, 2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface QueryObject {

}
