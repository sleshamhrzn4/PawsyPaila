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

/**
 * Servlet implementation class AddPetsServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddPets" })
public class AddPetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPetsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("WEB-INF/pages/admin/addPets.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
				HttpSession session = request.getSession();
		

		        try {
		            // Get form data
		            String petName = request.getParameter("petName");
		            String petType = request.getParameter("petType");
		            String breed   = request.getParameter("breed");
		            String petDesc = request.getParameter("petDesc");

		            // Create PetModel object
		            PetModel pet = new PetModel();
		            pet.setPetName(petName);
		            pet.setPetType(petType);
		            pet.setBreed(breed);
		            pet.setPetDesc(petDesc);

		            // Save to database
		            PetDAO.addPet(pet);

		            // Success message
		            session.setAttribute("message", "Pet added successfully!");

		        } catch (Exception e) {
		            e.printStackTrace();
		            session.setAttribute("error", "Failed to add pet. Please try again.");
		        }

		        // Redirect back to Add Pet page
		        response.sendRedirect(request.getContextPath() + "WEB-INF/pages/admin/addPets.jsp");
		    }
		}
		
	
