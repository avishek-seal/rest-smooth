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

