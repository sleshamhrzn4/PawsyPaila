package com.pawsypaila.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.pawsypaila.model.AdoptionRequestModel;
import com.pawsypaila.utils.DBconfig;



public class AdoptionRequestDAO {
	 
    
	public void insertAdoptionRequest(AdoptionRequestModel model) throws Exception {
	    Connection con = DBconfig.getConnection();

	    String sql = "INSERT INTO AdoptionRequest (userId, petId, AdoptionStatus) VALUES (?, ?, ?)";

	    PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    pst.setInt(1, model.getUserId());
	    pst.setInt(2, model.getPetId());
	    pst.setString(3, "Pending");

	    pst.executeUpdate();

	    ResultSet rs = pst.getGeneratedKeys();
	    if (rs.next()) {
	        model.setAdoptionId(rs.getInt(1)); 
	    }

	    rs.close();
	    pst.close();
	    con.close();
	}
 
  
    public List<AdoptionRequestModel> getAllRequests() throws Exception {
        List<AdoptionRequestModel> requests = new ArrayList<>();

        Connection con = DBconfig.getConnection();

        String sql = "SELECT ar.adoptionId, ar.userId, ar.petId, ar.AdoptionStatus, "
                   + "       u.fullName AS userName, p.petName "
                   + "FROM AdoptionRequest ar "
                   + "JOIN user u ON ar.userId = u.userId "
                   + "JOIN pet p ON ar.petId = p.petId";

        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            AdoptionRequestModel m = new AdoptionRequestModel();
            m.setAdoptionId(rs.getInt("adoptionId"));
            m.setUserId(rs.getInt("userId"));
            m.setPetId(rs.getInt("petId"));
            m.setAdoptionStatus(rs.getString("AdoptionStatus"));
            m.setFullName(rs.getString("userName")); 
            m.setPetName(rs.getString("petName"));
            requests.add(m);
        }

        rs.close();
        pst.close();
        con.close();
        return requests;
    }
 
   
    public boolean updateStatus(int adoptionId, String status) throws Exception {
        Connection con = DBconfig.getConnection();

        String sql = "UPDATE AdoptionRequest SET AdoptionStatus = ? WHERE adoptionId = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, status);
        pst.setInt(2, adoptionId);

        boolean success = pst.executeUpdate() == 1;

        pst.close();
        con.close();
        return success;
    }
    
    public boolean deleteRequest(int adoptionId) throws Exception {
        String sql = "DELETE FROM adoptionrequest WHERE adoptionId = ?";
        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, adoptionId);
            return pst.executeUpdate() > 0;
        }
    }

    public List<AdoptionRequestModel> getRequestsByUserId(int userId) {
        List<AdoptionRequestModel> requests = new ArrayList<>();
        Connection con = null;
        
        try {
            con = DBconfig.getConnection();

            String sql = "SELECT ar.adoptionId, ar.petId, ar.AdoptionStatus, p.petName " +
                         "FROM adoptionrequest ar " +
                         "JOIN pet p ON ar.petId = p.petId " +
                         "WHERE ar.userId = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, userId);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                AdoptionRequestModel req = new AdoptionRequestModel();
                req.setAdoptionId(rs.getInt("adoptionId"));
                req.setPetId(rs.getInt("petId"));
                req.setPetName(rs.getString("petName"));
                req.setAdoptionStatus(rs.getString("AdoptionStatus"));
                requests.add(req);
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return requests;
    }

    
}

