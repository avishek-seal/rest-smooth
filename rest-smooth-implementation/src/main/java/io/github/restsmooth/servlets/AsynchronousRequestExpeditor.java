package io.github.restsmooth.servlets;

import io.github.restsmooth.async.AsyncRequestProcessor;
import io.github.restsmooth.context.RestSmoothContext;
import io.github.restsmooth.listeners.RestSmoothAsyncListener;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AsynchronousRequestExpeditor
 * 
 * @author Avishek Seal
 * @since Mar 10, 2017
 */
@WebServlet(asyncSupported = true)
public final class AsynchronousRequestExpeditor extends HttpServlet {
	private static final long serialVersionUID = -4431548081796607129L;

	private RestSmoothContext restSmoothContext;
	
    public AsynchronousRequestExpeditor() {
        super();
    }

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
		AsyncContext asyncCtx = request.startAsync();
        asyncCtx.addListener(new RestSmoothAsyncListener());
        ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");
        executor.execute(new AsyncRequestProcessor("GET", asyncCtx, restSmoothContext));
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}

}
