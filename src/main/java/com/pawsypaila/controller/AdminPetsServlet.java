package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.pawsypaila.dao.PetDAO;
import com.pawsypaila.model.PetModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/adminPets" })
public class AdminPetsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
        try {
            

            PetDAO dao = new PetDAO();
            List<PetModel> pets = dao.getAllPets();
            request.setAttribute("pets", pets); 
            request.getRequestDispatcher("/WEB-INF/pages/admin/adminPets.jsp").forward(request, response); 

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Something went wrong.");
            response.sendRedirect(request.getContextPath() + "/AdminPets");
            
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        
        String action = request.getParameter("action");
        try {
            

            if ("delete".equals(action)) {
                int petId = Integer.parseInt(request.getParameter("petId"));
                PetDAO dao = new PetDAO();
                dao.deletePet(petId);
                session.setAttribute("message", "Pet deleted successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Failed to delete: "); 
        }

        response.sendRedirect(request.getContextPath() + "/adminPets");
    }
}