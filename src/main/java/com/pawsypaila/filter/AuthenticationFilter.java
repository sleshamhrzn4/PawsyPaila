package com.pawsypaila.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.pawsypaila.utils.SessionUtil;



/**
 * Servlet Filter implementation class AuthenticationFilter
 */
// /dashboard, /students and /logout need login before proceeding, so we used these url for filtering
@WebFilter(urlPatterns = {"/dashboard", "/students", "/logout", "/profile","/getimage"})
public class AuthenticationFilter extends HttpFilter implements Filter {
       
    private static final long serialVersionUID = 1L;

    public AuthenticationFilter() {
        super();
    }

	public void destroy() {
		// Cleanup resources if necessary
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// Cast the generic request/response to HTTP-specific versions
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// Logic: Check if the session exists and contains your login identifier
		// Change "user" to whatever attribute name you set in your LoginServlet
		boolean isLoggedIn = SessionUtil.getAttribute(httpRequest, "user") != null;

		if (isLoggedIn) {
			// User is logged in, allow the request to proceed to the destination
			// In my case, now go to that servlet which I have called
			chain.doFilter(request, response);
		} else {
			// User is not logged in, redirect to login page
			// Note: Avoid caching the dashboard so the back button doesn't reveal data
			httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// Initialization logic if necessary
	}

}
