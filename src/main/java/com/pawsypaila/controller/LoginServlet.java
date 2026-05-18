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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet (asyncSupported = true, urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
    	request.getRequestDispatcher("/WEB-INF/pages/public/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	String email    = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
            UserDAO userDAO = new UserDAO();

            // 2. Fetch user from DB by email
            UserModel user = userDAO.getUserByEmail(email);

            if (user == null) {
                // No user found with that email
                System.out.println("User not found!");
                response.sendRedirect(request.getContextPath() + "/login?error=1");
                return;
            }

            // 3. Check password using PasswordUtil
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
                // Wrong password — only runs if passwordMatch is false
                System.out.println("Wrong password!!!");
                response.sendRedirect(request.getContextPath() + "/login?error=2");
            }
            

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/login?error=3");
        }
    }
		
}
