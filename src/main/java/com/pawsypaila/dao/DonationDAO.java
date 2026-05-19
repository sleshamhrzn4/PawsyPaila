package com.pawsypaila.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;

import com.pawsypaila.utils.DBconfig;



public class DonationDAO {
	 
public boolean insertDonation(int userId, double donationAmount,
     String donationDate, String donationPaymentMethod) {

	 String sql = "INSERT INTO Donation (userId, donationAmount, donationDate, donationPaymentMethod) VALUES (?, ?, ?, ?)";

	 	try (Connection con = DBconfig.getConnection();
	 	PreparedStatement pst = con.prepareStatement(sql)) {

	 		pst.setInt(1, userId);
	 		pst.setDouble(2, donationAmount);
	 		pst.setString(3, donationDate);
	 		pst.setString(4, donationPaymentMethod);

	 		return pst.executeUpdate() > 0;

	 		} catch (Exception e) {
	 			e.printStackTrace();
	 		return false;
	 		}
	}
}
