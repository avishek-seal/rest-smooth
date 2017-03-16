package io.github.restsmooth.listeners;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RestSmoothContextListener implements ServletContextListener{

	private static final String EXECUTOR_NAME = "executor";
	private static final int POOL_SIZE = 100;
	private static final int MAX_POOL_SIZE = 200;
	private static final long KEEP_ALIVE_MILI_SECONDS = 50000L;
	
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		final ThreadPoolExecutor executor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_MILI_SECONDS, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
        servletContextEvent.getServletContext().setAttribute(EXECUTOR_NAME, executor);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) servletContextEvent.getServletContext().getAttribute(EXECUTOR_NAME);
        executor.shutdown();
	}

}
