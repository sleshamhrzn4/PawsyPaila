package com.pawsypaila.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.pawsypaila.model.CartItemModel;
import com.pawsypaila.utils.DBconfig;


public class CartItemDAO {
    public void addToCart(int cartId, int productId, int cartQuantity) throws Exception {
    	
    	Connection con = DBconfig.getConnection();
        String sql = "INSERT INTO CartItem (productId, cartId, cartQuantity) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, productId);
        pst.setInt(2, cartId);
        pst.setInt(3, cartQuantity);

        pst.executeUpdate();
        pst.close();
        con.close();
    }

    // Get all items in a cart
    public List<CartItemModel> getItemsByCartId(int cartId) throws Exception {
        List<CartItemModel> items = new ArrayList<>();
        Connection con = DBconfig.getConnection();

        String sql = "SELECT * FROM CartItem WHERE cartId = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, cartId);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            CartItemModel item = new CartItemModel();
            item.setCartItemId(rs.getInt("cartItemId"));
            item.setProductId(rs.getInt("productId"));
            item.setCartId(rs.getInt("cartId"));
            item.setCartItemQuantity(rs.getInt("cartQuantity"));
            items.add(item);
        }

        rs.close();
        pst.close();
        con.close();
        return items;
    }

    // Update quantity of an item in cart
    public int updateCartItem(int cartItemId, int cartQuantity) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE CartItem SET cartQuantity = ? WHERE cartItemId = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, cartQuantity);
        pst.setInt(2, cartItemId);

        int rowsAffected = pst.executeUpdate();
        pst.close();
        con.close();
        return rowsAffected;
    }

    // Remove an item from cart
    public int deleteCartItem(int cartItemId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "DELETE FROM CartItem WHERE cartItemId = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, cartItemId);

        int rowsAffected = pst.executeUpdate();
        pst.close();
        con.close();
        return rowsAffected;
    }

}

