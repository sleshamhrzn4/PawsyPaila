package com.pawsypaila.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.pawsypaila.dao.ProductDAO;
import com.pawsypaila.model.ProductModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/products" })
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sortBy   = request.getParameter("sortBy");
        String filterBy = request.getParameter("filterBy");
        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");

        try {
            ProductDAO dao = new ProductDAO();
            List<ProductModel> products = dao.getAllProducts();

            //FILTER BY CATEGORY
            if (filterBy != null && !filterBy.isEmpty() && !filterBy.equalsIgnoreCase("All")) {
                products = products.stream()
                    .filter(p -> p.getProductName() != null
                              && p.getProductName().toLowerCase().contains(filterBy.toLowerCase()))
                    .collect(Collectors.toList());
            }

            //FILTER BY PRICE RANGE
            if (minPriceStr != null && !minPriceStr.isEmpty()) {
                try {
                    double minPrice = Double.parseDouble(minPriceStr);
                    products = products.stream()
                        .filter(p -> p.getProductPrice() >= minPrice)
                        .collect(Collectors.toList());
                } catch (NumberFormatException ignored) {}
            }

            if (maxPriceStr != null && !maxPriceStr.isEmpty()) {
                try {
                    double maxPrice = Double.parseDouble(maxPriceStr);
                    products = products.stream()
                        .filter(p -> p.getProductPrice() <= maxPrice)
                        .collect(Collectors.toList());
                } catch (NumberFormatException ignored) {}
            }

            // SORT
            if (sortBy != null) {
                switch (sortBy) {
                    case "priceLow":
                        products.sort(Comparator.comparingDouble(ProductModel::getProductPrice));
                        break;
                    case "priceHigh":
                        products.sort(Comparator.comparingDouble(ProductModel::getProductPrice).reversed());
                        break;
                    case "nameAZ":
                        products.sort(Comparator.comparing(ProductModel::getProductName,
                                      String.CASE_INSENSITIVE_ORDER));
                        break;
                    default:
                        break;
                }
            }

            request.setAttribute("products", products);
            request.setAttribute("sortBy",   sortBy   != null ? sortBy   : "default");
            request.setAttribute("filterBy", filterBy != null ? filterBy : "All");

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/pages/public/product.jsp")
               .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}