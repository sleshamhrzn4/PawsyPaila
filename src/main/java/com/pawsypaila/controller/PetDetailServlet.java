/**
 * PetDetailServlet - Loads details for a single pet.
 * Mapped to URL: /petDetail
 *
 * Methods:
 * - doGet() : Fetches a pet via PetDAO.getPetById() and forwards to petDetail.jsp.
 *             Redirects to /pets if pet is not found.
 */
package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.pawsypaila.dao.PetDAO;
import com.pawsypaila.model.PetModel;

/**
 * Servlet implementation class PetDetailServlet
 */
@WebServlet("/petDetail")
public class PetDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int petId = Integer.parseInt(request.getParameter("petId"));
            PetDAO dao = new PetDAO();
            PetModel pet = dao.getPetById(petId);

            if (pet == null) {
                response.sendRedirect(request.getContextPath() + "/pets");
                return;
            }

            request.setAttribute("pet", pet);
            request.getRequestDispatcher("/WEB-INF/pages/public/petDetail.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/pets");
        }
    }

}
