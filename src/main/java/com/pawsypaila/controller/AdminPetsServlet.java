package com.pawsypaila.controller;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.pawsypaila.dao.PetDAO;
import com.pawsypaila.model.PetModel;
import com.pawsypaila.utils.SessionUtil; 
/**
 * Servlet implementation class AdminPetsServlet
 */
@WebServlet("/AdminPets")
public class AdminPetsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        try {
        	 PetDAO dao = new PetDAO();
            List<PetModel> pets = dao.getAllPets();
            request.setAttribute("pets", pets);
        } catch (Exception e) {
            e.printStackTrace();
            SessionUtil.setAttribute(request, "error", "Error loading pets: " + e.getMessage(), 60);
        }
 
        request.getRequestDispatcher("/WEB-INF/pages/admin/adminPets.jsp")
               .forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String action = request.getParameter("action");
 
        try {
            PetDAO dao = new PetDAO();
 
            if ("add".equals(action)) {
                PetModel pet = new PetModel();
                pet.setPetName(request.getParameter("petName"));
                pet.setPetAge(Integer.parseInt(request.getParameter("petAge")));
                pet.setPetType(request.getParameter("type"));
                pet.setPetGender(request.getParameter("petGender"));
                pet.setPetDesc(request.getParameter("petDesc"));
                
                PetDAO.addPet(pet);
                SessionUtil.setAttribute(request, "message", "Pet added successfully!", 60);
 
            } else if ("edit".equals(action)) {
                int    petId = Integer.parseInt(request.getParameter("petId"));
                String name  = request.getParameter("petName");
                int    age  = Integer.parseInt(request.getParameter("petAge"));
                String type = request.getParameter("type");
                String gender = request.getParameter("petGender");
                String desc  = request.getParameter("petDesc");
                
                dao.updatePet(petId, name, age, type,gender, desc);
                SessionUtil.setAttribute(request, "message", "Pet updated successfully!", 60);
 
            } else if ("delete".equals(action)) {
                int petId = Integer.parseInt(request.getParameter("petId"));
                dao.deletePet(petId);
                SessionUtil.setAttribute(request, "message", "Pet deleted successfully!", 60);
            }
 
        } catch (Exception e) {
            e.printStackTrace();
            SessionUtil.setAttribute(request, "error", "Operation failed: " + e.getMessage(), 60);
        }
 
        response.sendRedirect(request.getContextPath() + "/AdminPets");
    }
}