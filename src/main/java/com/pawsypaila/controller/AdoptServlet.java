package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import com.pawsypaila.dao.AdoptionRequestDAO;
import com.pawsypaila.model.AdoptionRequestModel;
import com.pawsypaila.model.UserModel;

@WebServlet("/adopt")
public class AdoptServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");
        String petId = request.getParameter("petId");
        String petName = request.getParameter("petName");

        // Not logged in — save intended destination and redirect to login
        if (user == null) {
            session.setAttribute("redirectAfterLogin",
                request.getContextPath() + "/petDetail?petId=" + petId);
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Logged in — save adoption request to DB
        try {
            AdoptionRequestDAO dao = new AdoptionRequestDAO();
            AdoptionRequestModel adoptionRequest = new AdoptionRequestModel();
            adoptionRequest.setPetId(Integer.parseInt(petId));
            adoptionRequest.setUserId(user.getUserId()); // adjust to your actual getter
            adoptionRequest.setAdoptionStatus("Pending");
            dao.insertAdoptionRequest(adoptionRequest);
            session.setAttribute("adoptMessage",
                "Adoption request for " + petName + " sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("adoptMessage", "Failed to send adoption request. Try again.");
        }

        response.sendRedirect(request.getContextPath() + "/petDetail?petId=" + petId);
    }
}