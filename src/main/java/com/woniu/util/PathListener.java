package com.woniu.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class PathListener
 *
 */
public class PathListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public PathListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println(sce.getServletContext().getContextPath());
    	sce.getServletContext().setAttribute("ItemPath",sce.getServletContext().getContextPath());
          
    }
	
}
