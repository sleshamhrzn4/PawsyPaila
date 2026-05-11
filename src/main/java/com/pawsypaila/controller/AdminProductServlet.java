package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.pawsypaila.dao.ProductDAO;
import com.pawsypaila.model.ProductModel;
import com.pawsypaila.utils.SessionUtil;

/**
 * Servlet implementation class AdminProductServlet
 */
@WebServlet("/adminProduct")   // ← This matches your sidebar link
public class AdminProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String action = request.getParameter("action");
    	
    	if ("showAdd".equals(action)) {
    		request.getRequestDispatcher("/WEB-INF/pages/admin/addProduct.jsp")
            .forward(request, response);
     return;
    	}

        try {
            ProductDAO dao = new ProductDAO();
            List<ProductModel> products = dao.getAllProducts();
            request.setAttribute("products", products);
        } catch (Exception e) {
            e.printStackTrace();
            SessionUtil.setAttribute(request, "error", "Error loading products: " + e.getMessage(), 60);
        }

        request.getRequestDispatcher("/WEB-INF/pages/admin/manageProduct.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            ProductDAO dao = new ProductDAO();

            if ("add".equals(action)) {
                // ==================== ADD PRODUCT ====================
                ProductModel product = new ProductModel();
                product.setProductName(request.getParameter("name"));
                product.setproductPrice(Double.parseDouble(request.getParameter("price")));
                product.setProductQuantity(Integer.parseInt(request.getParameter("quantity")));
                product.setProductDescription(request.getParameter("description"));

                dao.addProduct(   request.getParameter("name"),
                	    Double.parseDouble(request.getParameter("price")),
                	    Integer.parseInt(request.getParameter("quantity")),
                	    request.getParameter("description")
                	);
                SessionUtil.setAttribute(request, "message", "Product added successfully!", 60);

            } else if ("edit".equals(action)) {
                // ==================== EDIT PRODUCT ====================
                int productId = Integer.parseInt(request.getParameter("productId"));
                String name = request.getParameter("name");
                double price = Double.parseDouble(request.getParameter("price"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                String description = request.getParameter("description");

                dao.updateProduct(productId, name, price, quantity, description);
                SessionUtil.setAttribute(request, "message", "Product updated successfully!", 60);

            } else if ("delete".equals(action)) {
                // ==================== DELETE PRODUCT ====================
                int productId = Integer.parseInt(request.getParameter("productId"));
                dao.deleteProduct(productId);
                SessionUtil.setAttribute(request, "message", "Product deleted successfully!", 60);
            }

        } catch (Exception e) {
            e.printStackTrace();
            SessionUtil.setAttribute(request, "error", "Operation failed: " + e.getMessage(), 60);
        }

        // Redirect back to manage products page
        response.sendRedirect(request.getContextPath() + "/adminDashboard");
    }
}