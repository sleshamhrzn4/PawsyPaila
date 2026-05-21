/**
 * DonationDAO - Handles all database operations for the 'Donation' table.
 *
 * Methods:
 * - insertDonation() : Inserts a new donation record for a user. Returns true if successful.
 * - getAllDonations() : Returns all donations joined with user fullName, ordered by date descending.
 *
 * Uses DBconfig.getConnection() for database connectivity.
 */


package com.pawsypaila.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pawsypaila.model.DonationModel;
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
public List<DonationModel> getAllDonations() {
	List<DonationModel> donationList = new ArrayList<>();
	
	String sql = "SELECT d.donationId, d.donationAmount, d.donationDate, " +
            "d.donationPaymentMethod, u.fullName " +
            "FROM Donation d " +
            "JOIN User u ON d.userId = u.userId " +
            "ORDER BY d.donationDate DESC";
	
	try (Connection con = DBconfig.getConnection();
	         PreparedStatement pst = con.prepareStatement(sql);
	         ResultSet rs = pst.executeQuery()) {
		
		 while (rs.next()) {
	            DonationModel donation = new DonationModel();
	            donation.setDonationId(rs.getInt("donationId"));
	            donation.setDonationAmount(rs.getDouble("donationAmount"));
	            donation.setDonationDate(rs.getDate("donationDate"));
	            donation.setDonationPaymentMethod(rs.getString("donationPaymentMethod"));
	            donation.setUserName(rs.getString("fullName"));
	            donationList.add(donation);
		 		}
					} catch (SQLException e) {
						e.printStackTrace();
					}
			return donationList;
		}
}

