package com.pawsypaila.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import com.pawsypaila.utils.DBconfig;

public class AdoptionRequestDAO {
	 
    
    public void insertAdoptionRequest(int userId, int petId, 
    		String reason, String requestDate) throws Exception {
    	LocalDate localDate = LocalDate.parse(requestDate); 
    	Date sqlDate = Date.valueOf(localDate);
        
    	
    	Connection con = DBconfig.getConnection();
        String sql = "INSERT INTO AdoptionRequest (userId, petId, AdoptionStatus, reason, requestDate) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        pst.setInt(1, userId);
        pst.setInt(2, petId);
        pst.setString(3, "Pending");   // default status when first submitted
        pst.setString(4, reason);
        pst.setDate(5,sqlDate);

        pst.executeUpdate();
        pst.close();
        con.close();
    }
}
