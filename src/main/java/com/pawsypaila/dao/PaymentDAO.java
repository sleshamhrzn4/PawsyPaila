package com.pawsypaila.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.pawsypaila.model.PaymentModel;
import com.pawsypaila.utils.DBconfig;





public class PaymentDAO {
	 public int insertPayment(String paymentMethod, String paymentStatus, 
			 double totalPayment) throws Exception {
		
		 Connection con =DBconfig.getConnection();
	     
		 String sql = "INSERT INTO Payment (paymentMethod, paymentStatus, totalPayment) VALUES (?, ?, ?)";
	        PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        pst.setString(1, paymentMethod);
	        pst.setString(2, paymentStatus);
	        pst.setDouble(3, totalPayment);
	        pst.executeUpdate();
	        
	        ResultSet rs = pst.getGeneratedKeys();
	        int paymentId = 0;
	        if (rs.next()) paymentId = rs.getInt(1);

	        rs.close();
	        pst.close();
	        con.close();
	        return paymentId;
	    }
	 public PaymentModel getPaymentById(int paymentId) throws Exception {
	        PaymentModel payment = null;
	        Connection con = DBconfig.getConnection();

	        String sql = "SELECT * FROM Payment WHERE paymentId = ?";
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setInt(1, paymentId);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            payment = new PaymentModel();
	            payment.setPaymentId(rs.getInt("paymentId"));
	            payment.setPaymentMethod(rs.getString("paymentMethod"));
	            payment.setPaymentStatus(rs.getString("paymentStatus"));
	            payment.setTotalPaymentAmt(rs.getDouble("totalPaymentAmt"));
	        }

	        rs.close();
	        pst.close();
	        con.close();
	        return payment;
	    }

	    // Update payment status (e.g. pending → completed)
	    public int updatePaymentStatus(int paymentId, String paymentStatus) throws Exception {
	        Connection con = DBconfig.getConnection();
	        String sql = "UPDATE Payment SET paymentStatus = ? WHERE paymentId = ?";
	        PreparedStatement pst = con.prepareStatement(sql);

	        pst.setString(1, paymentStatus);
	        pst.setInt(2, paymentId);

	        int rowsAffected = pst.executeUpdate();
	        pst.close();
	        con.close();
	        return rowsAffected;
	    }

	    }


