package com.pawsypaila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pawsypaila.model.ProductModel;
import com.pawsypaila.utils.DBconfig;





public class ProductDAO {
 
    
    public void addProduct(String productName, double productPrice, int productQuantity, String productDescription, String productImage) 
    		throws Exception {
    
    	Connection con = DBconfig.getConnection();
        
    	String sql = "INSERT INTO product (productName, productPrice, productQuantity, productDescription, productImage) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, productName);
        pst.setDouble(2, productPrice);
        pst.setInt(3, productQuantity);
        pst.setString(4,  productDescription);
        pst.setString(5, productImage);
        pst.executeUpdate();
        pst.close();
        con.close();
    }
    
    public List<ProductModel> getAllProducts() throws Exception {
        List<ProductModel> productList = new ArrayList<>();
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM product";

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            ProductModel product = new ProductModel();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setproductPrice(rs.getDouble("productPrice"));
            product.setProductQuantity(rs.getInt("productQuantity"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setProductImage(rs.getString("productImage"));
            productList.add(product);
        }

        rs.close();
        pst.close();
        con.close();
        return productList;
    }

    // ====================== GET PRODUCT BY ID ======================
    public ProductModel getProductById(int productId) throws Exception {
        ProductModel product = null;
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM product WHERE productId = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, productId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            product = new ProductModel();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setproductPrice(rs.getDouble("productPrice"));
            product.setProductQuantity(rs.getInt("productQuantity"));
            product.setProductDescription(rs.getString("productDescription"));
            product.setProductImage(rs.getString("productImage"));
        }

        rs.close();
        pst.close();
        con.close();
        return product;
    }

    // ====================== UPDATE PRODUCT ======================
    public int updateProduct(int productId, String name, double price, int quantity, String description) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE product SET productName=?, productPrice=?, productQuantity=?, productDescription=? WHERE productId=?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, name);
        pst.setDouble(2, price);
        pst.setInt(3, quantity);
        pst.setString(4, description);
        pst.setInt(5, productId);

        int rowsAffected = pst.executeUpdate();

        pst.close();
        con.close();
        return rowsAffected;
    }

    // ====================== DELETE PRODUCT ======================
    public void deleteProduct(int productId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "DELETE FROM product WHERE productId=?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, productId);
        pst.executeUpdate();

        pst.close();
        con.close();
    }
}
