package com.pawsypaila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.pawsypaila.utils.DBconfig;



public class DonationDAO {
	 
   
    public void insertDonation(int userId, double donationAmount,
                               String donationDate, String donationPaymentMethod) throws Exception {
    	
    	Connection con = DBconfig.getConnection();
        
    	String sql = "INSERT INTO Donation (userId, donationAmount, donationDate, donationPaymentMethod) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);
        pst.setDouble(2, donationAmount);
        pst.setString(3, donationDate);        // pass as "YYYY-MM-DD"
        pst.setString(4, donationPaymentMethod);
        pst.executeUpdate();
        pst.close();
        con.close();
    }
}
