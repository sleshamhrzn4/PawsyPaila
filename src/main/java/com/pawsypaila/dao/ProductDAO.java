package com.pawsypaila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pawsypaila.model.ProductModel;
import com.pawsypaila.utils.DBconfig;





public class ProductDAO {
 
    
    public void addProduct(String productName, double price, int productQuantity, String productDescription) 
    		throws Exception {
    
    	Connection con = DBconfig.getConnection();
        
    	String sql = "INSERT INTO Product (productName, price, productQuantity, productDescription) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, productName);
        pst.setDouble(2, price);
        pst.setInt(3, productQuantity);
        pst.setString(4,  productDescription);
        pst.executeUpdate();
        pst.close();
        con.close();
    }
    
    public List<ProductModel> getAllProducts() throws Exception {
        List<ProductModel> productList = new ArrayList<>();
        Connection con = DBconfig.getConnection();
        String sql = "SELECT * FROM Product";

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            ProductModel product = new ProductModel();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setproductPrice(rs.getDouble("price"));
            product.setProductQuantity(rs.getInt("productQuantity"));
            product.setProductDescription(rs.getString("productDescription"));
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
        String sql = "SELECT * FROM Product WHERE productId = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, productId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            product = new ProductModel();
            product.setProductId(rs.getInt("productId"));
            product.setProductName(rs.getString("productName"));
            product.setproductPrice(rs.getDouble("price"));
            product.setProductQuantity(rs.getInt("productQuantity"));
            product.setProductDescription(rs.getString("productDescription"));
        }

        rs.close();
        pst.close();
        con.close();
        return product;
    }

    // ====================== UPDATE PRODUCT ======================
    public int updateProduct(int productId, String name, double price, int quantity, String description) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE Product SET productName=?, price=?, productQuantity=?, productDescription=? WHERE productId=?";

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
        String sql = "DELETE FROM Product WHERE productId=?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, productId);
        pst.executeUpdate();

        pst.close();
        con.close();
    }
}
