package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.pawsypaila.dao.AdoptionRequestDAO;
import com.pawsypaila.dao.PetDAO;
import com.pawsypaila.dao.ProductDAO;
import com.pawsypaila.model.AdoptionRequestModel;
import com.pawsypaila.model.PetModel;
import com.pawsypaila.model.ProductModel;

/**
 * Servlet implementation class AdminDashboardServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/adminDashboard" })
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 try {
	            //Pets 
	            PetDAO petDAO = new PetDAO();
	            List<PetModel> pets = petDAO.getAllPets();
	            request.setAttribute("totalPets", pets.size());
	            request.setAttribute("petList", pets);
	 
	            //Products
	            ProductDAO productDAO = new ProductDAO();
	            List<ProductModel> products = productDAO.getAllProducts();
	            request.setAttribute("totalProducts", products.size());
	            request.setAttribute("productList", products);
	 
	            //Adoption Requests
	            AdoptionRequestDAO adoptionDAO = new AdoptionRequestDAO();
	            List<AdoptionRequestModel> adoptions = adoptionDAO.getAllRequests();
	            request.setAttribute("totalRequests", adoptions.size());
	            request.setAttribute("adoptionList", adoptions);
	 
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("totalPets", 0);
	            request.setAttribute("totalProducts", 0);
	            request.setAttribute("totalRequests", 0);
	            
	            //remove later
	            request.setAttribute("petList", new java.util.ArrayList<>());
	            request.setAttribute("productList", new java.util.ArrayList<>());
	            request.setAttribute("adoptionList", new java.util.ArrayList<>());
	           
	        }
		request.getRequestDispatcher("WEB-INF/pages/admin/adminDashboard.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
