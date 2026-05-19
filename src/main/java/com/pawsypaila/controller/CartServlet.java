package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import com.pawsypaila.dao.ProductDAO;
import com.pawsypaila.model.ProductModel;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Cart stored in session as Map<productId, CartItem>
    public static class CartItem {
        public ProductModel product;
        public int quantity;

        public CartItem(ProductModel product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
    }

    @SuppressWarnings("unchecked")
    private Map<Integer, CartItem> getCart(HttpSession session) {
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedHashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // Return cart count as plain text (for badge update)
        if ("count".equals(action)) {
            HttpSession session = request.getSession(false);
            int count = 0;
            if (session != null) {
                Map<Integer, CartItem> cart = getCart(session);
                for (CartItem item : cart.values()) {
                    count += item.quantity;
                }
            }
            response.setContentType("text/plain");
            response.getWriter().write(String.valueOf(count));
            return;
        }

        // Remove item
        if ("remove".equals(action)) {
            String productIdStr = request.getParameter("productId");
            if (productIdStr != null) {
                int productId = Integer.parseInt(productIdStr);
                Map<Integer, CartItem> cart = getCart(request.getSession());
                cart.remove(productId);
            }
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        // Show cart page
        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = getCart(session);

        double total = 0;
        for (CartItem item : cart.values()) {
            total += item.product.getProductPrice() * item.quantity;
        }

        request.setAttribute("cart", cart);
        request.setAttribute("total", total);
        request.getRequestDispatcher("/WEB-INF/pages/public/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = getCart(session);

        if ("add".equals(action)) {
            String productIdStr = request.getParameter("productId");
            String quantityStr  = request.getParameter("quantity");

            if (productIdStr != null) {
                try {
                    int productId = Integer.parseInt(productIdStr);
                    int quantity  = (quantityStr != null) ? Integer.parseInt(quantityStr) : 1;

                    if (cart.containsKey(productId)) {
                        // Increment quantity if already in cart
                        cart.get(productId).quantity += quantity;
                    } else {
                        // Fetch product from DB and add
                        ProductDAO dao = new ProductDAO();
                        ProductModel product = dao.getProductById(productId);
                        if (product != null) {
                            cart.put(productId, new CartItem(product, quantity));
                        }
                    }
                    response.setStatus(HttpServletResponse.SC_OK);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
            return;
        }

        // Update quantity
        if ("update".equals(action)) {
            String productIdStr = request.getParameter("productId");
            String quantityStr  = request.getParameter("quantity");
            if (productIdStr != null && quantityStr != null) {
                int productId = Integer.parseInt(productIdStr);
                int quantity  = Integer.parseInt(quantityStr);
                if (quantity <= 0) {
                    cart.remove(productId);
                } else if (cart.containsKey(productId)) {
                    cart.get(productId).quantity = quantity;
                }
            }
            response.sendRedirect(request.getContextPath() + "/cart");
        }
    }
}