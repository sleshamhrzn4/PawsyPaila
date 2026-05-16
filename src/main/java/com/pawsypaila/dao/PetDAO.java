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
        String sql = "INSERT INTO pet (petName, petAge, petType, petGender, petDesc) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, pet.getPetName());
        pst.setInt(2, pet.getPetAge());
        pst.setString(3, pet.getPetType());
        pst.setString(4, pet.getPetGender());  
        pst.setString(5, pet.getPetDesc());    
        pst.executeUpdate();
        pst.close();
        con.close();
    }

    public List<PetModel> getAllPets() throws Exception {
        List<PetModel> petList = new ArrayList<>();
        String sql = "SELECT * FROM pet";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                PetModel pet = new PetModel();
                pet.setPetId(rs.getInt("petId"));
                pet.setPetName(rs.getString("petName"));
                pet.setPetAge(rs.getInt("petAge"));
                pet.setPetType(rs.getString("petType"));
                pet.setPetGender(rs.getString("petGender")); 
                pet.setPetDesc(rs.getString("petDesc"));
                petList.add(pet);
            }
        }

        return petList;
    }

    public PetModel getPetById(int petId) throws Exception {
        PetModel pet = null;
        String sql = "SELECT * FROM pet WHERE petId = ?";

        Connection con = DBconfig.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, petId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            pet = new PetModel();
            pet.setPetId(rs.getInt("petId"));
            pet.setPetName(rs.getString("petName"));
            pet.setPetAge(rs.getInt("petAge"));
            pet.setPetType(rs.getString("petType"));
            pet.setPetGender(rs.getString("petGender"));
            pet.setPetDesc(rs.getString("petDesc"));
        }

        rs.close();
        pst.close();
        con.close();
        return pet;
    }

    public int updatePet(PetModel pet) throws Exception {
        String sql = "UPDATE pet SET petName=?, petAge=?, petType=?, petGender=?, petDesc=? WHERE petId=?";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, pet.getPetName());
            pst.setInt(2, pet.getPetAge());
            pst.setString(3, pet.getPetType());
            pst.setString(4, pet.getPetGender());
            pst.setString(5, pet.getPetDesc());
            pst.setInt(6, pet.getPetId());

            return pst.executeUpdate();
        }
    }
    public void deletePet(int petId) throws Exception {
        Connection con = DBconfig.getConnection();
        String sql = "DELETE FROM pet WHERE petId=?";  
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, petId);
        pst.executeUpdate();
        pst.close();
        con.close();
    }
}
