/**
 * LoginServlet - Handles user authentication.
 * Mapped to URL: /login
 *
 * Methods:
 * - doGet()  : Forwards the request to login.jsp.
 * - doPost() : Validates email and password, authenticates user via
 *              UserDAO.getUserByEmail() and PasswordUtil.checkPassword().
 *              Redirects admin to /adminDashboard and users to /home.
 *
 * Validations:
 * - Email and password must not be empty or exceed max length.
 * - Email must contain '@' and '.'.
 * - Account must be active before login is allowed.
 */
package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.pawsypaila.dao.UserDAO;
import com.pawsypaila.model.UserModel;
import com.pawsypaila.utils.PasswordUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final int MAX_EMAIL_LENGTH    = 255;
    private static final int MAX_PASSWORD_LENGTH = 128;

    public LoginServlet() {
        super();
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/public/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email    = request.getParameter("email");
        String password = request.getParameter("password");

        
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Email is required.");
            doGet(request, response);
            return;
        }
        if (password == null || password.isEmpty()) {
            request.setAttribute("errorMessage", "Password is required.");
            doGet(request, response);
            return;
        }

        email = email.trim();

   
        if (email.length() > MAX_EMAIL_LENGTH) {
            request.setAttribute("errorMessage", "Email address is too long.");
            doGet(request, response);
            return;
        }
        if (password.length() > MAX_PASSWORD_LENGTH) {
            request.setAttribute("errorMessage", "Password is too long.");
            doGet(request, response);
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            request.setAttribute("errorMessage", "Please enter a valid email.");
            doGet(request, response);
            return;
        }

        try {
            UserDAO userDAO = new UserDAO();

            
            UserModel user = userDAO.getUserByEmail(email);

            if (user == null) {
                System.out.println("Login failed ,no account for email: " + email);
                request.setAttribute("errorMessage", "No account found with that email.");
                doGet(request, response);
                return;
            }

     
            if (!user.isActive()) {
                System.out.println("Login failed , account inactive: " + email);
                request.setAttribute("errorMessage",
                        "Your account is not yet activated. Please wait for the admin to activate your account.");
                doGet(request, response);
                return;
            }

       
            boolean passwordMatch = PasswordUtil.checkPassword(password, user.getPassword());

            if (!passwordMatch) {
                System.out.println("Login failed , wrong password for: " + email);
                request.setAttribute("errorMessage", "Incorrect password. Please try again.");
                doGet(request, response);
                return;
            }

  
            System.out.println("Login successful for: " + email);

           
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            
            HttpSession session = request.getSession(true);
            session.setAttribute("user",     user);
            session.setAttribute("username", user.getFullName());

            String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
            if (redirectUrl != null) {
                session.removeAttribute("redirectAfterLogin");
                response.sendRedirect(redirectUrl);
            } else if ("admin".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/adminDashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }

        } catch (Exception e) {
            System.err.println("LoginServlet error: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "Something went wrong. Please try again later.");
            doGet(request, response);
        }
    }
}