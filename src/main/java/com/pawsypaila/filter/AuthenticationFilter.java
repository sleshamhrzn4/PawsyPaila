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

// /home and /logout need login before proceeding, so we used these urls for filtering
@WebFilter(urlPatterns = {"/home", "/logout", "/profile", "/getimage"})
public class AuthenticationFilter extends HttpFilter implements Filter {

    private static final long serialVersionUID = 1L;

    public AuthenticationFilter() {
        super();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        boolean isLoggedIn = SessionUtil.getAttribute(httpRequest, "user") != null;

        if (isLoggedIn) {
            chain.doFilter(request, response);
        } else {
            httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {}

    public void destroy() {}
}