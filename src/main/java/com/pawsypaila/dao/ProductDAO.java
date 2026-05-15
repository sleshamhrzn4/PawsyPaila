package com.pawsypaila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.pawsypaila.utils.DBconfig;





public class ProductDAO {
 
    
    public void insertProduct(String productName, double price, String productDescription) throws Exception {
    
    	Connection con = DBconfig.getConnection();
        
    	String sql = "INSERT INTO Product (productName, price, productDescription) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, productName);
        pst.setDouble(2, price);
        pst.setString(3, productDescription);
        pst.executeUpdate();
        pst.close();
        con.close();
    }
}
