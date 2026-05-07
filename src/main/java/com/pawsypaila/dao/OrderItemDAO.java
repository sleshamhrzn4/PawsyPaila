package com.pawsypaila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pawsypaila.model.OrderItemModel;
import com.pawsypaila.utils.DBconfig;


public class OrderItemDAO {
	//ADD an item 
    public void insertOrderItem(int orderId, int productId,
            double orderItemPrice, int orderItemQuantity) throws Exception {
    	
    	Connection con = DBconfig.getConnection();
        String sql = "INSERT INTO OrderItem (orderId, productId, orderItemPrice, orderItemQuantity) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, orderId);
        pst.setInt(2, productId);
        pst.setDouble(3, orderItemPrice);
        pst.setInt(4, orderItemQuantity);

        pst.executeUpdate();
        pst.close();
        con.close();
    }

    // Get all items belonging to one order
    public List<OrderItemModel> getItemsByOrderId(int orderId) throws Exception {
        List<OrderItemModel> items = new ArrayList<>();
        Connection con = DBconfig.getConnection();

        String sql = "SELECT * FROM OrderItem WHERE orderId = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, orderId);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            OrderItemModel item = new OrderItemModel();
            item.setOrderItemId(rs.getInt("orderItemId"));
            item.setOrderId(rs.getInt("orderId"));
            item.setProductId(rs.getInt("productId"));
            item.setOrderItemPrice(rs.getDouble("orderItemPrice"));
            item.setOrderItemQuantity(rs.getInt("orderItemQuantity"));
            items.add(item);
        }

        rs.close();
        pst.close();
        con.close();
        return items;
    }

	}
