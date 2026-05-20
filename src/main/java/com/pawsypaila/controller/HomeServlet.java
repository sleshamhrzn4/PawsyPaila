package com.pawsypaila.controller;

import com.pawsypaila.dao.PetDAO;
import com.pawsypaila.dao.ProductDAO;
import com.pawsypaila.model.PetModel;
import com.pawsypaila.model.ProductModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
		    PetDAO petDAO = new PetDAO();
		    ProductDAO productDAO = new ProductDAO();

		    List<PetModel> petList = petDAO.getAllPets();
		    List<ProductModel> productList = productDAO.getAllProducts();

		    request.setAttribute("petList", petList);
		    request.setAttribute("itemList", productList);
		} catch (Exception e) {
		    e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/pages/public/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}