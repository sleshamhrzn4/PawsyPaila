package com.pawsypaila.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.pawsypaila.dao.PetDAO;
import com.pawsypaila.model.PetModel;


@WebServlet("/pets")
public class PetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
		    protected void doGet(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		 
		    	String search = request.getParameter("search");
		    	String filter = request.getParameter("filter");
		    	 

		    	if (search == null) {search = "";
		    	 if (filter == null) filter = "";

		    	PetDAO petDAO = new PetDAO();
		    	List<PetModel> pets = new ArrayList<>(); 
	           

		        try {
		            if (!search.isEmpty()) {
		                // search by name
		                pets = petDAO.searchPets(search);
		            } else if (!filter.isEmpty()) {
		                
		                pets = petDAO.getPetsByType(filter);
		            } else {
		                // show all
		                pets = petDAO.getAllPets();
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        
		        request.setAttribute("pets", pets);
		        request.setAttribute("search", search);
		        request.setAttribute("filter", filter);

		        request.getRequestDispatcher("/WEB-INF/pages/public/pets.jsp")
		               .forward(request, response);
		    	}
		    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
