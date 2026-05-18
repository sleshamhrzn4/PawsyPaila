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
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String description = request.getParameter("description");

            ProductDAO dao = new ProductDAO();
            dao.updateProduct(productId, name, price, quantity, description);

            SessionUtil.setAttribute(request, "message", "Product updated successfully!", 60);
        } catch (Exception e) {
            e.printStackTrace();
            SessionUtil.setAttribute(request, "error", "Error updating product: " + e.getMessage(), 60);
        }
        response.sendRedirect(request.getContextPath() + "/adminProduct");
    }
}
