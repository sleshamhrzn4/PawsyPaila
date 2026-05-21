/**
 * AdminDonationServlet - Loads all donations for the admin view.
 * Mapped to URL: /adminDonation
 *
 * Methods:
 * - doGet()  : Fetches all donations via DonationDAO.getAllDonations()
 *              and forwards to adminDonation.jsp.
 * - doPost() : Delegates to doGet().
 */

package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.pawsypaila.dao.DonationDAO;
import com.pawsypaila.model.DonationModel;

/**
 * Servlet implementation class AdminDonationServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/adminDonation" })
public class AdminDonationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDonationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 DonationDAO dao = new DonationDAO();
	        List<DonationModel> donationList = dao.getAllDonations();
	        request.setAttribute("donationList", donationList);
		
		request.getRequestDispatcher("/WEB-INF/pages/admin/adminDonation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
