package com.pawsypaila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.pawsypaila.model.PetModel;
import com.pawsypaila.utils.DBconfig;




public class PetDAO {
	 
	public static void addPet(PetModel pet) throws Exception {
	    Connection con = DBconfig.getConnection();
	    String sql = "INSERT INTO pet (petName, petType, Breed, petDesc) VALUES (?, ?, ?, ?)";
	    PreparedStatement pst = con.prepareStatement(sql);
	    pst.setString(1, pet.getPetName());
	    pst.setString(2, pet.getPetType());
	    pst.setString(3, pet.getBreed());
	    pst.setString(4, pet.getPetDesc());
	    
	    pst.executeUpdate();
	    pst.close();
	    con.close();
	}
	
	public List<PetModel> getAllPets() throws Exception{
		List<PetModel> petList = new ArrayList<>();
		String sql = "SELECT * FROM pet";
		
		try (Connection con = DBconfig.getConnection();
	             PreparedStatement pst = con.prepareStatement(sql);
	             ResultSet rs = pst.executeQuery()) {

	            while (rs.next()) {
	                PetModel pet = new PetModel();
	                pet.setPetId(rs.getInt("petId"));
	                pet.setPetName(rs.getString("petName"));
	                pet.setPetType(rs.getString("petType"));
	                pet.setBreed(rs.getString("Breed"));
	                pet.setPetDesc(rs.getString("petDesc"));
	                petList.add(pet);
	            }
	            rs.close();
	    		pst.close();
	    		con.close();
	        }  
		
		
		return petList;
		
	}
	
	public PetModel getPetById(int petId) throws Exception {
		PetModel pet=null;
		String sql = "SELECT * FROM pet WHERE petID = ?";
		
		Connection con = DBconfig.getConnection();
		
		
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, petId);
		
		ResultSet rs = pst.executeQuery();
		
		if (rs.next()) {
	        pet = new PetModel();
	        pet.setPetId(rs.getInt("petId"));
	        pet.setPetName(rs.getString("petName"));
	        pet.setPetType(rs.getString("petType"));
	        pet.setBreed(rs.getString("Breed"));
	        pet.setPetDesc(rs.getString("petDesc"));
	    }
		
		rs.close();
		pst.close();
		con.close();
		return pet;
		
	}
	
	
	public int updatePet(int petId, String petName,String  petType,String Breed, String petDesc)  throws Exception {
	    Connection con = DBconfig.getConnection();
	    String sql = "UPDATE pet SET petName=?, petType=?, Breed=?, petDesc=? WHERE petId=?";
	    PreparedStatement pst = con.prepareStatement(sql);
	    
	    pst.setString(1, petName);
	    pst.setString(2, petType);
	    pst.setString(3, Breed);
	    pst.setString(4, petDesc);
	    pst.setInt(5, petId);
	    
	    int rowsAffected = pst.executeUpdate();
	    
	    pst.close();
	    con.close();
	    return rowsAffected;
	}
	
	
	public void deletePet(int petId) throws Exception{
		Connection con = DBconfig.getConnection();
		String sql = "DELETE FROM Pet WHERE PetID=?";   
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, petId);
		pst.executeUpdate();
		pst.close();
		con.close();

	}
}

