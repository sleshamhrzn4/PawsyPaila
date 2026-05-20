package com.pawsypaila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.pawsypaila.model.CartModel;
import com.pawsypaila.utils.DBconfig;

public class CartDAO {
	 public int createCart(int userId) throws Exception {
		 
		 Connection con = DBconfig.getConnection();
	      
		 String sql = "INSERT INTO Cart (userId) VALUES (?)";
	        PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        pst.setInt(1, userId);
	        pst.executeUpdate();

	        ResultSet rs = pst.getGeneratedKeys();
	        int cartId = 0;
	        if (rs.next()) cartId = rs.getInt(1);
	        
	        rs.close();
	        pst.close();
	        con.close();
	        return cartId;
	    }
	 public CartModel getCartByUserId(int userId) throws Exception {
	        CartModel cart = null;
	        Connection con = DBconfig.getConnection();

	        String sql = "SELECT * FROM Cart WHERE userId = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setInt(1, userId);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            cart = new CartModel();
	            cart.setCartId(rs.getInt("cartId"));
	            cart.setUserId(rs.getInt("userId"));
	        }

	        rs.close();
	        pst.close();
	        con.close();
	        return cart;
	    }

}

