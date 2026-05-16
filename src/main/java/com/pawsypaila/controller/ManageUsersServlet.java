package com.pawsypaila.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import com.pawsypaila.dao.UserDAO;
import com.pawsypaila.model.UserModel;


/**
 * Servlet implementation class ManageUsersServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ManageUsers" })
public class ManageUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    
    public ManageUsersServlet() {
       
    } 
        

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session !=null && session.getAttribute("successMessage") !=null) {
			request.setAttribute("successMessage",session.getAttribute("successMessage"));
			session.removeAttribute("successMessage");
		}
		
		if (session != null && session.getAttribute("errorMessage") != null) {
            request.setAttribute("errorMessage", session.getAttribute("errorMessage"));
            session.removeAttribute("errorMessage");
        }
		
		 try {
			 UserDAO userDAO = new UserDAO();
	            List<UserModel> userList = userDAO.getAllUsers();
	            request.setAttribute("userList", userList);
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "Failed to load users: " + e.getMessage());
	        }
		
		 request.getRequestDispatcher("/WEB-INF/pages/admin/manageUsers.jsp").forward(request, response);
		
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String userIdParam = request.getParameter("userId");
        String action      = request.getParameter("action");
        HttpSession session = request.getSession();
 
        if (userIdParam == null || action == null) {
            session.setAttribute("errorMessage", "Invalid request.");
            response.sendRedirect(request.getContextPath() + "/ManageUsers");
            return;
        }
 
        try {
            int userId = Integer.parseInt(userIdParam);
            boolean activate = "activate".equalsIgnoreCase(action);
            UserDAO userDAO = new UserDAO();
            boolean success  = userDAO.setUserStatus(userId, activate);
 
            if (success) {
                session.setAttribute("successMessage",
                        "User " + (activate ? "activated" : "deactivated") + " successfully.");
            } else {
                session.setAttribute("errorMessage", "Could not update user status.");
            }
 
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "Invalid user ID.");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error: " + e.getMessage());
        }
 
       
        response.sendRedirect(request.getContextPath() + "/ManageUsers");
    }

}
