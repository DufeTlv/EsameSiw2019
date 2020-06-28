package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class CacheFilter
 */
@WebFilter("*.jsp")
public class CacheFilter implements Filter {

    public CacheFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        
        //HttpSession session = request.getSession(false);      
		/*System.out.println(session.getAttribute("username"));
        if (session == null || session.getAttribute("username") == null) {
        	response.sendRedirect("index.html"); // No logged-in user found, so redirect to login page.
        } else {
	    	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	    	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	    	response.setDateHeader("Expires", 0);
	    	// pass the request along the filter chain
	        chain.doFilter(req, resp);  
        }*/
        
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    	response.setDateHeader("Expires", 0);
    	//setDateHeader("Last-Modified", (new Date()).getTime() );
    	
    	// pass the request along the filter chain
        chain.doFilter(req, resp); 
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
