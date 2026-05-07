package com.pawsypaila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.pawsypaila.utils.DBconfig;



public class PetDAO {
	 
	public void insertPet(String firstName, String lastName, String username, String dob,
            String gender, String email, String number, String password, int programId) throws Exception {

		Connection con = DBconfig.getConnection();

	String sql = "INSERT INTO students (first_name, last_name, username, dob, gender, email, number, password, program_id) "
			+ "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, firstName);
		pst.setString(2, lastName);
		pst.setString(3, username);
		pst.setString(4, dob);
		pst.setString(5, gender);
		pst.setString(6, email);
		pst.setString(7, number);
		pst.setString(8, password);
		pst.setInt(9, programId); // <-- must be existing program_id
		
		pst.executeUpdate();
		pst.close();
		con.close();
	}
}

