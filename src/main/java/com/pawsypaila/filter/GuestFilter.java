package com.pawsypaila.filter;

import java.io.IOException;

import com.pawsypaila.utils.SessionUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter(urlPatterns = {"/login", "/register", ""})
public class GuestFilter extends HttpFilter {
    
    private static final long serialVersionUID = 1L;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
    	// Cast the generic request/response to HTTP-specific versions
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// Logic: Check if the session exists and contains your login identifier
		// Change "user" to whatever attribute name you set in your LoginServlet
		boolean isLoggedIn = SessionUtil.getAttribute(httpRequest, "user") != null;

        if (isLoggedIn) {
            
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
        } else {
            // User is a guest, let them proceed to login/register/home
            chain.doFilter(request, response);
        }
    }
}