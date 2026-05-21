/**
 * UserDashboardServlet - Loads the logged-in user's dashboard.
 * Mapped to URL: /userdashboard
 *
 * Methods:
 * - doGet()  : Checks if user is logged in via session, fetches their adoption requests
 *              via AdoptionRequestDAO.getRequestsByUserId() and forwards to userDashboard.jsp.
 *              Redirects to /login if user is not logged in.
 * - doPost() : Delegates to doGet().
 */

package com.pawsypaila.controller;

import com.pawsypaila.dao.AdoptionRequestDAO;
import com.pawsypaila.model.AdoptionRequestModel;
import com.pawsypaila.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class UserDashboardServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userdashboard" })
public class UserDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
		    response.sendRedirect(request.getContextPath() + "/login");
		    return;
		}

		UserModel currentUser = (UserModel) session.getAttribute("user");

		try {
		    AdoptionRequestDAO dao = new AdoptionRequestDAO();
		    List<AdoptionRequestModel> requests = dao.getRequestsByUserId(currentUser.getUserId());
		    request.setAttribute("adoptionRequests", requests);
		} catch (Exception e) {
		    e.printStackTrace();
		    request.setAttribute("errorMessage", "Could not load your adoption requests.");
		}

		request.getRequestDispatcher("/WEB-INF/pages/user/userDashboard.jsp")
		       .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}