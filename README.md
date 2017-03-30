# rest-smooth

- This is a library project for developing RESTful API in Java.

- While working with REST API in Java, Developers often come across that no framework strongly follow the RESTful Architecture Design.

- This library strongly follows RESTful Architecture principle like :

    -- All API(s) will be termed as resource.

    -- it will follow HTTP Methods, i.e. GET, PUT, POST, DELETE etc. to operate all CRUD operation.

    -- will return Representational State after each method call. developer will have to focus more on calling appropriate business for a 
    specific request.

    -- Application Error and Server error are two different scope. Say, while processing a request if any error/exception occurs in 
    application due to inconsistent logic or invalid data etc then the HTTP Status code will still remain 200 OK, but the representational 
    state, i.e. the response message will contain the error message and the error code of the specific error in the application

    -- But any server issues like if any resource actually not present in application will set the response status to 404.

- Some Important Feature :

    -- There will be both Synchronous and Asynchronous communication presented.
    
    -- There will be both secure and insecure stateless communication support provided.

- Install Rest Smooth Process :
	
		1. Clone this project and run mvn install
		2. Create your own project
		3. add this dependencies to your pom.xml file
		
		<dependency>
			<groupId>io.github.avishek-seal</groupId>
			<artifactId>rest-smooth-implementation</artifactId>
			<version>0.0.1</version>
		</dependency>
		
		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-mapper-asl</artifactId>
			<version>jackson.json.latest.version</version>
		</dependency>
		
		<dependency>
			<groupId>com.fasterxml.jackson.dataformat</groupId>
			<artifactId>jackson-dataformat-xml</artifactId>
			<version>jackson.xml.latest.version</version>
		</dependency>

		4. add this servlet information to your web.xml file
		
		<servlet>
			<servlet-name>servlet-name</servlet-name>
			<servlet-class>io.github.restsmooth.servlets.RequestExpeditor</servlet-class>
			<load-on-startup>1</load-on-startup>
			<async-supported>true</async-supported>
		</servlet>
	
		<servlet-mapping>
			<servlet-name>servlet-name</servlet-name>
			<url-pattern>/</url-pattern>
		</servlet-mapping>
		
		5. Start coding REST API(s).
		
		6. add a properties file named 'smooth.properties' to your project's class path resource. put the below properties in that file:
		
			smooth.resource.package = com.test.resource
		
		7. If you want to map specific exceptions to a specific code and message (for binding that to the response string),
		add smooth-error-mapper.json file to the project class path resource. the file content will look like :
		
			{
				"errors" : {
					"org.codehaus.jackson.JsonParseException" : {
						"code" : "400",
						"message" : "Bad Request"
					},
					
					"org.codehaus.jackson.map.JsonMappingException" : {
						"code" : "400",
						"message" : "Bad Request"
					},
					
					"com.fasterxml.jackson.core.JsonParseException" : {
						"code" : "400",
						"message" : "Bad Request"
					},
					
					"com.test.exception.CustomException" : {
						"code" : "105",
						"message" : "This is a custom error"
					}
				}
			}
		
		8. Sample Code :
		
		package com.test.resources;

		import io.github.restsmooh.rules.Async;
		import io.github.restsmooh.rules.Payload;
		import io.github.restsmooh.rules.Resource;
		import io.github.restsmooth.methods.DELETE;
		import io.github.restsmooth.methods.GET;
		import io.github.restsmooth.methods.POST;
		import io.github.restsmooth.methods.PUT;
		
		import javax.servlet.http.HttpServletRequest;
		import javax.servlet.http.HttpServletResponse;
		
		import com.test.exception.CustomException;
		import com.test.model.Employee;
		
		@Resource(name="/employee")
		public class EmployeeResource {
		
			@Async // <-- using this annotation will make the method to be executed asynchronously, i.e. separate thread pool
			@GET   // will be assigned to execute this process other than Http Servlet thread pool
			public Object getEmployee(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
				
				for(int i = 0; i < 10; i++) {
					try {
						Thread.sleep(500);
						System.out.println("Processing " + Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				return new Employee(2, "Avishek Seal", 10000.0);
			}
			
			@GET(path="/all")
			public Object getEmployees(){
				System.out.println("2 GET INVOKED");
				return null;
			}
			
			@PUT
			public void createEmployee(@Payload Employee employee) throws CustomException {
				System.out.println("CREEAT " + employee);
				throw new CustomException();
			}
			
			@POST
			public void updateEmployee(@Payload Employee employee) {
				System.out.println("UPDATE " + employee);
			}
			
			@DELETE
			public void deleteEmployee(@Payload Employee employee) {
				System.out.println("DELETE " + employee);
			}
		}
				
