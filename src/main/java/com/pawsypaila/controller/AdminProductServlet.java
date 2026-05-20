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

@WebServlet("/adminProduct")
public class AdminProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("showAdd".equals(action)) {
            request.getRequestDispatcher("/WEB-INF/pages/admin/addProduct.jsp")
                   .forward(request, response);
            return;
        }

        if ("delete".equals(action)) {
            try {
                int productId = Integer.parseInt(request.getParameter("productId"));
                ProductDAO dao = new ProductDAO();
                dao.deleteProduct(productId);
            } catch (Exception e) {
                e.printStackTrace();
                // Pass error message via redirect parameter or session if needed
            }
            response.sendRedirect(request.getContextPath() + "/adminProduct");
            return;
        }

        // Default: load and display all products
        try {
            ProductDAO dao = new ProductDAO();
            List<ProductModel> products = dao.getAllProducts();
            request.setAttribute("products", products);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error loading products: " + e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/pages/admin/adminProduct.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            ProductDAO dao = new ProductDAO();

            if ("edit".equals(action)) {
                int productId = Integer.parseInt(request.getParameter("productId"));
                dao.updateProduct(
                    productId,
                    request.getParameter("name"),
                    Double.parseDouble(request.getParameter("price")),
                    Integer.parseInt(request.getParameter("quantity")),
                    request.getParameter("description")
                );
            } else if ("delete".equals(action)) {
                int productId = Integer.parseInt(request.getParameter("productId"));
                dao.deleteProduct(productId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Operation failed: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/adminProduct");
    }
}