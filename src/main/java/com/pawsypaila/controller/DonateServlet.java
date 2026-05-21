/**
 * DonateServlet - Handles donation form submission.
 * Mapped to URL: /donate
 *
 * Methods:
 * - doGet()  : Forwards the request to donate.jsp.
 * - doPost() : Validates donation details, saves donation via DonationDAO.insertDonation()
 *              and forwards back to donate.jsp with success or error message.
 *
 * Validations:
 * - User must be logged in via session.
 * - All fields must not be empty.
 * - Donation amount must be greater than 499.
 * - Donation date must not be in the past or future.
 */

package com.pawsypaila.controller;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.pawsypaila.dao.DonationDAO;
import com.pawsypaila.model.UserModel;


/**
 * Servlet implementation class DonateServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/donate" })
public class DonateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/pages/public/donate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 HttpSession session = request.getSession(false);
	        if (session == null || session.getAttribute("user") == null) {
	            response.sendRedirect(request.getContextPath() + "/login");
	            return;
	        }
	        
	     UserModel loggedInUser = (UserModel) session.getAttribute("user");
	     int userId = loggedInUser.getUserId();
	     
	     String amountStr     = request.getParameter("donationAmount");
	     String donationDate  = request.getParameter("donationDate");
	     String paymentMethod = request.getParameter("donationPaymentMethod");
	     
	     if (amountStr == null || amountStr.trim().isEmpty() ||
	    	 donationDate == null || donationDate.trim().isEmpty() ||
	         paymentMethod == null || paymentMethod.trim().isEmpty()) {

	         request.setAttribute("error", "All fields are required.");
	         doGet(request, response);
	         return;
	         }
	     
	     double donationAmount;
	        try {
	            donationAmount = Double.parseDouble(amountStr);
	            if (donationAmount <= 499) throw new NumberFormatException();
	        } catch (NumberFormatException e) {
	            request.setAttribute("error", "Please enter a valid donation amount.");
	            doGet(request, response);
	            return;
	        }
	        
	        java.time.LocalDate selectedDate;
	        try {
	            selectedDate = java.time.LocalDate.parse(donationDate);
	            if (selectedDate.isAfter(java.time.LocalDate.now())) {
	                request.setAttribute("error", "Donation date invalid");
	                doGet(request, response);
	                return;
	            }
	        } catch (Exception e) {
	            request.setAttribute("error", "Please enter a valid date.");
	            doGet(request, response);
	            return;
	        }
	        
	        try {
	            selectedDate = java.time.LocalDate.parse(donationDate);
	            if (selectedDate.isBefore(java.time.LocalDate.now())) {
	                request.setAttribute("error", "Donation date invalid");
	                doGet(request, response);
	                return;
	            }
	        } catch (Exception e) {
	            request.setAttribute("error", "Please enter a valid date.");
	            doGet(request, response);
	            return;
	        }
	        
	        DonationDAO dao = new DonationDAO();
	        try {
	            boolean success = dao.insertDonation(userId, donationAmount, donationDate, paymentMethod);

	            if (success) {
	                request.setAttribute("success", "Thank you! Your donation of Rs. " + donationAmount + " was received.");
	            } else {
	                request.setAttribute("error", "Donation failed. Please try again.");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "An unexpected error occurred. Please try again later.");
	        }
		doGet(request, response);
	}

}
