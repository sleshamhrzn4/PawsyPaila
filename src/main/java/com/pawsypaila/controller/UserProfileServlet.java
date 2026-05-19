package com.pawsypaila.controller;

import com.pawsypaila.dao.UserDAO;
import com.pawsypaila.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * __Servlet__ implementation class UserProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userprofile" })
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDAO userDAO = new UserDAO();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		request.getRequestDispatcher("/WEB-INF/pages/user/userProfile.jsp").forward(request, response);
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

		UserModel currentUser = (UserModel) session.getAttribute("user");

		// Read form fields
		String name    = request.getParameter("userName");
		String phone   = request.getParameter("userPhone");
		String address = request.getParameter("userAddress");
		String gender  = request.getParameter("userGender");
		String ageStr  = request.getParameter("userAge");
		

		// Email is read-only — keep from session
		String email    = currentUser.getEmail();
		String password = currentUser.getPassword();
		boolean active  = currentUser.isActive();

		// Safe age parse — fall back to existing value if blank/invalid
		int age = currentUser.getAge();
		if (ageStr != null && !ageStr.trim().isEmpty()) {
			try {
				age = Integer.parseInt(ageStr.trim());
			} catch (NumberFormatException e) {
				// leave age unchanged
			}
		}

		try {
			int rows = userDAO.updateUser(
				currentUser.getUserId(),
				name, phone, email, password,
				address, age, gender, active, 
			);

			if (rows > 0) {
				// Update session so page reflects changes immediately
				currentUser.setFullName(name);
				currentUser.setPhone(phone);
				currentUser.setAddress(address);
				currentUser.setGender(gender);
				currentUser.setAge(age);
				session.setAttribute("user", currentUser);

				request.setAttribute("successMessage", "Profile updated successfully!");
			} else {
				request.setAttribute("errorMessage", "No changes were saved. Please try again.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Something went wrong: " + e.getMessage());
		}

		doGet(request, response);
	}

}