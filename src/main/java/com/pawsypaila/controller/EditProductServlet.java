/**
 * EditProductServlet - Handles editing an existing product by the admin.
 * Mapped to URL: /editProduct
 *
 * Methods:
 * - doGet()  : Fetches product details via ProductDAO.getProductById() and forwards to editProduct.jsp.
 * - doPost() : Validates product fields, updates product via ProductDAO.updateProduct()
 *              and redirects to /adminProduct on success.
 *
 * Validations:
 * - Name, price, quantity and description must not be empty.
 * - Price and quantity must not be negative.
 */
package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.pawsypaila.dao.ProductDAO;
import com.pawsypaila.model.ProductModel;
import com.pawsypaila.utils.SessionUtil;

@WebServlet("/editProduct")
@MultipartConfig
public class EditProductServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            ProductDAO dao = new ProductDAO();
            ProductModel product = dao.getProductById(productId);
            request.setAttribute("product", product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/pages/admin/editProduct.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	int productId = -1; 
        try {
            productId = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("name");
            String priceStr = request.getParameter("price");
            String quantityStr = request.getParameter("quantity");
            String description = request.getParameter("description");

            if (name == null || name.trim().isEmpty()) {
                SessionUtil.setAttribute(request, "error", "Product name is required!", 60);
                response.sendRedirect(request.getContextPath() + "/editProduct?productId=" + productId);
                return;
            }
            if (priceStr == null || priceStr.trim().isEmpty()) {
                SessionUtil.setAttribute(request, "error", "Price is required!", 60);
                response.sendRedirect(request.getContextPath() + "/editProduct?productId=" + productId);
                return;
            }
            if (quantityStr == null || quantityStr.trim().isEmpty()) {
                SessionUtil.setAttribute(request, "error", "Quantity is required!", 60);
                response.sendRedirect(request.getContextPath() + "/editProduct?productId=" + productId);
                return;
            }
            if (description == null || description.trim().isEmpty()) {
                SessionUtil.setAttribute(request, "error", "Description is required!", 60);
                response.sendRedirect(request.getContextPath() + "/editProduct?productId=" + productId);
                return;
            }

            double price = Double.parseDouble(priceStr);
            int quantity = Integer.parseInt(quantityStr);
            
            if (price < 0) {
                SessionUtil.setAttribute(request, "error", "Price cannot be negative", 60);
                response.sendRedirect(request.getContextPath() + "/editProduct?productId=" + productId);
                return;
            }
            if (quantity < 0) {
                SessionUtil.setAttribute(request, "error", "Quantity cannot be negative", 60);
                response.sendRedirect(request.getContextPath() + "/editProduct?productId=" + productId);
                return;
            }

            //Database Update Action
            ProductDAO dao = new ProductDAO();
            dao.updateProduct(productId, name, price, quantity, description);


            SessionUtil.setAttribute(request, "message", "Product updated successfully!", 60);
            response.sendRedirect(request.getContextPath() + "/adminProduct");
            return;

        } catch (NumberFormatException e) {
            SessionUtil.setAttribute(request, "error", "Invalid price or quantity formatting!", 60);
            response.sendRedirect(request.getContextPath() + "/editProduct?productId=" + productId);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            SessionUtil.setAttribute(request, "error", "Error updating product: " + e.getMessage(), 60);
            response.sendRedirect(request.getContextPath() + "/adminProduct");
            return;
        }
    }
}