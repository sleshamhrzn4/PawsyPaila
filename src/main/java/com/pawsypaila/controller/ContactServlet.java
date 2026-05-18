package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.pawsypaila.dao.ContactDAO;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/contact" })
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/pages/public/contact.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String email   = request.getParameter("email");
	     String message = request.getParameter("message");
	     
	     if (email == null || email.trim().isEmpty() ||
	             message == null || message.trim().isEmpty()) {
	             request.setAttribute("error", "All fields are required.");
	             doGet(request, response);
	             return;
	         }
	     
	        ContactDAO dao = new ContactDAO();
	        try {
	            boolean success = dao.insertContact(email.trim(), message.trim());

	            if (success) {
	                request.setAttribute("success", "Your message has been sent successfully!");
	            } else {
	                request.setAttribute("error", "Failed to send message. Please try again.");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "An unexpected error occurred. Please try again later.");
	        }
		doGet(request, response);
	}

}
