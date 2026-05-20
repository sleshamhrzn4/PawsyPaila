package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.pawsypaila.dao.AdoptionRequestDAO;
import com.pawsypaila.model.AdoptionRequestModel;

/**
 * Servlet implementation class AdminAdoptionServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/adminAdoption" })
public class AdminAdoptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAdoptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private final AdoptionRequestDAO dao = new AdoptionRequestDAO();
    
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<AdoptionRequestModel> requests = dao.getAllRequests();
            req.setAttribute("adoptionList", requests);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("adoptionList", new java.util.ArrayList<>());
            req.setAttribute("errorMessage", "Failed to load adoption requests: " + e.getMessage());
        }
        
        req.getRequestDispatcher("/WEB-INF/pages/admin/adminAdoption.jsp")
           .forward(req, resp);
    }
 
   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            int adoptionId = Integer.parseInt(req.getParameter("adoptionId"));
            String action  = req.getParameter("action");

            if (action.equals("delete")) {
                boolean success = dao.deleteRequest(adoptionId);
                if (success) {
                    session.setAttribute("message", "Request #" + adoptionId + " has been deleted.");
                } else {
                    session.setAttribute("error", "Could not delete request #" + adoptionId + ".");
                }
            } else {
                String newStatus = action.equals("accept") ? "Approved" : "Rejected";
                boolean success = dao.updateStatus(adoptionId, newStatus);
                if (success) {
                    session.setAttribute("message",
                        "Request #" + adoptionId + " has been " + newStatus.toLowerCase() + ".");
                } else {
                    session.setAttribute("error", "Could not update request #" + adoptionId + ".");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Error: " + e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/adminAdoption");
    }
}
