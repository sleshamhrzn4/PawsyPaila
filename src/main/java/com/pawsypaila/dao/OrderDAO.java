package com.pawsypaila.dao;


import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.pawsypaila.model.OrderModel;
import com.pawsypaila.utils.DBconfig;


public class OrderDAO {
	 
   
    public int insertOrder(int userId, int paymentId, String orderStatus, String orderDate) throws Exception {
    	LocalDate localDate = LocalDate.parse(orderDate); 
    	Date sqlDate = Date.valueOf(localDate);
        
    	Connection con = DBconfig.getConnection();
        String sql = "INSERT INTO `Order` (userId, paymentId, orderDate, orderStatus) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        pst.setInt(1, userId);
        pst.setInt(2, paymentId);
        pst.setDate(3,  sqlDate);
        pst.setString(4, orderStatus);
        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys();
        int orderId = 0;
        if (rs.next()) orderId = rs.getInt(1);

        rs.close();
        pst.close();
        con.close();
        return orderId;
    }

    // Get all orders for a specific user
    public List<OrderModel> getOrdersByUserId(int userId) throws Exception {
        List<OrderModel> orders = new ArrayList<>();
        Connection con = DBconfig.getConnection();

        String sql = "SELECT * FROM `Order` WHERE userId = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            OrderModel o = new OrderModel();
            o.setOrderId(rs.getInt("orderId"));
            o.setUserId(rs.getInt("userId"));
            o.setPaymentId(rs.getInt("paymentId"));
            o.setOrderDate(rs.getDate("orderDate"));
            o.setOrderStatus(rs.getString("orderStatus"));
            orders.add(o);
        }

        rs.close();
        pst.close();
        con.close();
        return orders;
    }

    // Update order status 
    public int updateOrderStatus(int orderId, String orderStatus) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "UPDATE `Order` SET orderStatus = ? WHERE orderId = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, orderStatus);
        pst.setInt(2, orderId);

        int rowsAffected = pst.executeUpdate();
        pst.close();
        con.close();
        return rowsAffected;
    }
}

