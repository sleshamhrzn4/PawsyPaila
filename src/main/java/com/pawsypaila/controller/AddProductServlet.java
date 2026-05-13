package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;


import com.pawsypaila.dao.ProductDAO;
import com.pawsypaila.utils.FileUploadUtil;
import com.pawsypaila.utils.SessionUtil;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/addProduct")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2,
	    maxFileSize = 1024 * 1024 * 10,
	    maxRequestSize = 1024 * 1024 * 50
	)
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static final String UPLOAD_DIR = System.getProperty("user.home") 
			 + File.separator + "webapp_uploads";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 request.getRequestDispatcher("/WEB-INF/pages/admin/addProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Get form data
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");
        String description = request.getParameter("description");

        try {
           

            String imageFileName = null;

            // Handle Image Upload
            Part filePart = request.getPart("productImage");
            if (filePart != null && filePart.getSize() > 0) {
                if (FileUploadUtil.isImage(filePart)) {
                    String extension = FileUploadUtil.getFileExtension(filePart.getSubmittedFileName());
                    imageFileName = System.currentTimeMillis() + extension;   // Unique filename

                    FileUploadUtil.saveFile(filePart, UPLOAD_DIR, imageFileName);
                } else {
                    SessionUtil.setAttribute(request, "error", "Invalid image file type!", 60);
                    response.sendRedirect(request.getContextPath() + "/WEB-INF/pages/admin/addProduct.jsp");
                    return;
                }
            }

            // TODO: Save product to database (You will add this later)
            // ProductDAO productDAO = new ProductDAO();
            // boolean isSaved = productDAO.addProduct(name, price, quantity, description, imageFileName);

            // Success Message
            SessionUtil.setAttribute(request, "message", "Product added successfully!", 60);

        } catch (NumberFormatException e) {
            SessionUtil.setAttribute(request, "error", "Invalid price or quantity format.", 60);
        } catch (Exception e) {
            e.printStackTrace();
            SessionUtil.setAttribute(request, "error", "Error adding product: " + e.getMessage(), 60);
        }

        // Redirect back to products list page
        response.sendRedirect(request.getContextPath() + "/WEB-INF/pages/admin/adminProduct.jsp");
    }
}


