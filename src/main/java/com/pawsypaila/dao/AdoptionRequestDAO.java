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
	 
    
    public void insertAdoptionRequest(int userId, int petId,
            String reason, String requestDate) throws Exception {
 
        LocalDate localDate = LocalDate.parse(requestDate);
        Date sqlDate = Date.valueOf(localDate);
 
        Connection con = DBconfig.getConnection();
 
        String sql = "INSERT INTO AdoptionRequest (userId, petId, AdoptionStatus, reason, requestDate) "
                   + "VALUES (?, ?, ?, ?, ?)";
 
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, userId);
        pst.setInt(2, petId);
        pst.setString(3, "Pending");
        pst.setString(4, reason);
        pst.setDate(5, sqlDate);
 
        
        
        int generatedId = -1;
        ResultSet rs = pst.getGeneratedKeys();
        pst.executeUpdate();
        if (rs.next()) {
        	 generatedId = rs.getInt(1);
        }
 
        rs.close();
        pst.close();
        con.close();
    }
 
    // ── GET ALL REQUESTS ─────────────────────────────────────────────────────
    public List<AdoptionRequestModel> getAllRequests() throws Exception {
        List<AdoptionRequestModel> requests = new ArrayList<>();
 
        Connection con = DBconfig.getConnection();
 
        String sql = "SELECT ar.adoptionId, ar.userId, ar.petId, ar.AdoptionStatus, "
                   + "       ar.reason, ar.requestDate, "
                   + "       u.fullName AS userName, p.petName "
                   + "FROM AdoptionRequest ar "
                   + "JOIN user u ON ar.userId = u.userId "
                   + "JOIN Pets p ON ar.petId  = p.petId "
                   + "ORDER BY ar.requestDate DESC";
 
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
 
        while (rs.next()) {
            AdoptionRequestModel m = new AdoptionRequestModel();
            m.setAdoptionId(rs.getInt("adoptionId"));
            m.setUserId(rs.getInt("userId"));
            m.setPetId(rs.getInt("petId"));
            m.setAdoptionStatus(rs.getString("AdoptionStatus"));
            m.setReason(rs.getString("reason"));
            m.setRequestDate(rs.getDate("requestDate"));
            m.setFullName(rs.getString("fullName"));
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
}

