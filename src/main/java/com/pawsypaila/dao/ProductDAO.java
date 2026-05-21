/**
 * ProductDAO - Handles all database operations for the 'product' table.
 *
 * Methods:
 * - addProduct()       : Inserts a new product into the database.
 * - getAllProducts()    : Returns all products from the database.
 * - getProductById()   : Fetches a single product by productId, returns null if not found.
 * - updateProduct()    : Updates name, price, quantity and description of a product by productId.
 * - deleteProduct()    : Deletes a product from the database by productId.
 * - getLatestProducts(): Returns a limited number of latest products.
 * - mapRow()           : Helper method to map a ResultSet row to a ProductModel object.
 *
 * Uses DBconfig.getConnection() for database connectivity.
 */

package com.pawsypaila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pawsypaila.model.ProductModel;
import com.pawsypaila.utils.DBconfig;

public class ProductDAO {

    public void addProduct(String productName, double productPrice, int productQuantity,
                           String productDescription, String productImage) throws Exception {

        String sql = "INSERT INTO product (productName, productPrice, productQuantity, productDescription, productImage) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, productName);
            pst.setDouble(2, productPrice);
            pst.setInt(3, productQuantity);
            pst.setString(4, productDescription);
            pst.setString(5, productImage);
            pst.executeUpdate();
        }
    }

    public List<ProductModel> getAllProducts() throws Exception {
        List<ProductModel> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                productList.add(mapRow(rs));
            }
        }

        return productList;
    }

    public ProductModel getProductById(int productId) throws Exception {
        String sql = "SELECT * FROM product WHERE productId = ?";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, productId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }

        return null;
    }

    public int updateProduct(int productId, String name, double price,
                             int quantity, String description) throws Exception {

        String sql = "UPDATE product SET productName=?, productPrice=?, productQuantity=?, productDescription=? WHERE productId=?";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, name);
            pst.setDouble(2, price);
            pst.setInt(3, quantity);
            pst.setString(4, description);
            pst.setInt(5, productId);

            return pst.executeUpdate();
        }
    }

    public void deleteProduct(int productId) throws Exception {
        String sql = "DELETE FROM product WHERE productId=?";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, productId);
            pst.executeUpdate();
        }
    }

    public List<ProductModel> getLatestProducts(int limit) throws Exception {
        List<ProductModel> productList = new ArrayList<>();
        String sql = "SELECT * FROM product LIMIT ?";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, limit);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    productList.add(mapRow(rs));
                }
            }
        }

        return productList;
    }

    // Helper to avoid repeating the same mapping logic everywhere
    private ProductModel mapRow(ResultSet rs) throws Exception {
        ProductModel product = new ProductModel();
        product.setProductId(rs.getInt("productId"));
        product.setProductName(rs.getString("productName"));
        product.setProductPrice(rs.getDouble("productPrice"));
        product.setProductQuantity(rs.getInt("productQuantity"));
        product.setProductDescription(rs.getString("productDescription"));
        product.setProductImage(rs.getString("productImage"));
        return product;
    }
}