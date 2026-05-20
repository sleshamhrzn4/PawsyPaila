
package com.pawsypaila.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import com.pawsypaila.model.UserModel;

@WebFilter(urlPatterns = {
    "/adminDashboard", "/addPets", "/addProducts", "/adminAdoption",
    "/adminApplication", "/adminPets", "/adminProduct", "/editProduct",
    "/manageUsers", "/updatePets"  
})
public class AdminAuthFilter implements Filter {  

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        UserModel user = (session != null) ? (UserModel) session.getAttribute("user") : null;

        if (user != null && "admin".equalsIgnoreCase(user.getRole())) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }

    @Override public void init(FilterConfig filterConfig) throws ServletException {}
    @Override public void destroy() {}
}