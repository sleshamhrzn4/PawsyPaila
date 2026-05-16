package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.pawsypaila.dao.PetDAO;
import com.pawsypaila.model.PetModel;



@WebServlet(asyncSupported = true, urlPatterns = { "/UpdatePets" })
public class UpdatePetsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
        	int petId = Integer.parseInt(request.getParameter("petId"));
        	
            PetDAO dao = new PetDAO();
            PetModel pet = dao.getPetById(petId); 

            if (pet != null) {
                request.setAttribute("pet", pet);
                request.getRequestDispatcher("/WEB-INF/pages/admin/updatePets.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("error", "Pet not found.");
                response.sendRedirect(request.getContextPath() + "/AdminPets");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Something went wrong: ");
            
            response.sendRedirect(request.getContextPath() + "/AdminPets");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        try {
            int petId = Integer.parseInt(request.getParameter("petId"));
            String petName = request.getParameter("petName");
            int petAge = Integer.parseInt(request.getParameter("petAge"));
            String petType = request.getParameter("petType");
            String petGender = request.getParameter("petGender");
            String petDesc = request.getParameter("petDesc");

            PetModel pet = new PetModel();
            pet.setPetId(petId);
            pet.setPetName(petName);
            pet.setPetAge(petAge);
            pet.setPetType(petType);
            pet.setPetGender(petGender);
            pet.setPetDesc(petDesc);

            PetDAO dao = new PetDAO();
            dao.updatePet(pet); 

            session.setAttribute("message", "Pet updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Failed to update pet. Please try again.");
        }

        response.sendRedirect(request.getContextPath() + "/AdminPets");
    }
}