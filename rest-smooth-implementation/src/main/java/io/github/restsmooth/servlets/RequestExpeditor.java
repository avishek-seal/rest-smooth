package io.github.restsmooth.servlets;

import io.github.restsmooth.context.RestSmoothContext;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Central expeditor for HTTP request handlers/resource. Expeditors to
 * registered resource for processing a Http request, providing convenient
 * mapping and exception handling facilities. This servlet is very flexible: It
 * can be used with just about any workflow.
 * 
 * @author Avishek Seal
 * @since Mar 10, 2017
 *
 */
public final class RequestExpeditor extends HttpServlet {

	private static final long serialVersionUID = 286079290995859914L;
	
	private RestSmoothContext restSmoothContext;

	/**
	 * 
	 * @author Avishek Seal
	 * @since Mar 10, 2017
	 * @return @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 * @param config
	 * @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException {
		restSmoothContext = new RestSmoothContext(config.getServletContext().getContextPath());
	}

	/**
	 * 
	 * @author Avishek Seal
	 * @since Mar 10, 2017
	 * @return @see javax.servlet.GenericServlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 * @author Avishek Seal
	 * @since Mar 10, 2017
	 * @return @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		restSmoothContext.get(request, response);
	}

	/**
	 * 
	 * @author Avishek Seal
	 * @since Mar 10, 2017
	 * @return @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		restSmoothContext.post(request, response);
	}

	/**
	 * 
	 * @author Avishek Seal
	 * @since Mar 10, 2017
	 * @return @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		restSmoothContext.put(request, response);
	}

	/**
	 * 
	 * @author Avishek Seal
	 * @since Mar 10, 2017
	 * @return @see javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		restSmoothContext.delete(request, response);
	}

	/**
	 * 
	 * @author Avishek Seal
	 * @since Mar 10, 2017
	 * @return @see javax.servlet.http.HttpServlet#doHead(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		restSmoothContext.head(request, response);
	}

	/**
	 * 
	 * @author Avishek Seal
	 * @since Mar 10, 2017
	 * @return @see javax.servlet.http.HttpServlet#doOptions(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		restSmoothContext.options(request, response);
	}

	/**
	 * 
	 * @author Avishek Seal
	 * @since Mar 10, 2017
	 * @return @see javax.servlet.http.HttpServlet#doTrace(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		restSmoothContext.trace(request, response);
	}
}
