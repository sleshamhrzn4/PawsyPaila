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

        try {
            UserDAO userDAO = new UserDAO();

            // 1. Fetch user from DB by email
            UserModel user = userDAO.getUserByEmail(email);

            // 2. Check if user exists
            if (user == null) {
                System.out.println("User not found!");
                request.setAttribute("errorMessage", "No account found with that email.");
                request.getRequestDispatcher("/WEB-INF/pages/public/login.jsp").forward(request, response);
                return;
            }

            // 3. Check if account is active
            if (!user.isActive()) {
                System.out.println("Account is deactivated!");
                request.setAttribute("errorMessage", "Your account is not yet activated. Please wait for the admin to activate your account.");
                request.getRequestDispatcher("/WEB-INF/pages/public/login.jsp").forward(request, response);
                return;
            }

            // 4. Check password
            boolean passwordMatch = PasswordUtil.checkPassword(password, user.getPassword());

            if (passwordMatch) {
                System.out.println("Login successful!");
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
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

            } else {
                System.out.println("Wrong password!!!");
                request.setAttribute("errorMessage", "Incorrect password. Please try again.");
                request.getRequestDispatcher("/WEB-INF/pages/public/login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "Something went wrong. Please try again later.");
            request.getRequestDispatcher("/WEB-INF/pages/public/login.jsp").forward(request, response);
        }
    }
}