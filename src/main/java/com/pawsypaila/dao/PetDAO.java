/**
 * PetDAO - Handles all database operations for the 'pet' table.
 *
 * Methods:
 * - addPet()          : Inserts a new pet into the database using a PetModel object.
 * - getAllPets()       : Returns all pets from the database.
 * - getPetById()      : Fetches a single pet by petId, returns null if not found.
 * - updatePet()       : Updates all fields of a pet using a PetModel object.
 * - deletePet()       : Deletes a pet from the database by petId.
 * - searchPets()      : Returns pets whose name contains the given keyword.
 * - getPetsByLetter() : Returns pets whose name starts with the given letter.
 * - getPetsByType()   : Returns pets matching the given type (case-insensitive).
 * - getLatestPets()   : Returns a limited number of pets for the home page.
 * - mapRow()          : Helper method to map a ResultSet row to a PetModel object.
 *
 * Uses DBconfig.getConnection() for database connectivity.
 */
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
        String sql = "INSERT INTO pet (petName, petAge, petType, petGender, petDesc, petImage) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, pet.getPetName());
        pst.setInt(2, pet.getPetAge());
        pst.setString(3, pet.getPetType());
        pst.setString(4, pet.getPetGender());
        pst.setString(5, pet.getPetDesc());
        pst.setString(6, pet.getPetImage());
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
                petList.add(mapRow(rs));
            }
        }
        return petList;
    }

    public PetModel getPetById(int petId) throws Exception {
        PetModel pet = null;
        String sql = "SELECT * FROM pet WHERE petId = ?";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, petId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                pet = mapRow(rs);
            }

            rs.close();
        }
        return pet;
    }

    public int updatePet(PetModel pet) throws Exception {
        String sql = "UPDATE pet SET petName=?, petAge=?, petType=?, petGender=?, petDesc=?, petImage=? WHERE petId=?";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, pet.getPetName());
            pst.setInt(2, pet.getPetAge());
            pst.setString(3, pet.getPetType());
            pst.setString(4, pet.getPetGender());
            pst.setString(5, pet.getPetDesc());
            pst.setString(6, pet.getPetImage());
            pst.setInt(7, pet.getPetId());

            return pst.executeUpdate();
        }
    }

    public void deletePet(int petId) throws Exception {
        String sql = "DELETE FROM pet WHERE petId=?";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, petId);
            pst.executeUpdate();
        }
    }

    public List<PetModel> searchPets(String keyword) throws Exception {
        List<PetModel> pets = new ArrayList<>();
        String sql = "SELECT * FROM pet WHERE petName LIKE ?";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, "%" + keyword + "%");  
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                pets.add(mapRow(rs));
            }
        }
        return pets;
    }

    public List<PetModel> getPetsByLetter(String letter) throws Exception {
        List<PetModel> pets = new ArrayList<>();
        String sql = "SELECT * FROM pet WHERE petName LIKE ?";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, letter + "%");  
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                pets.add(mapRow(rs));
            }
        }
        return pets;
    }

    public List<PetModel> getPetsByType(String type) throws Exception {
        List<PetModel> pets = new ArrayList<>();
        String sql = "SELECT * FROM pet WHERE LOWER(petType) = LOWER(?)";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, type);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                pets.add(mapRow(rs));
            }
        }
        return pets;
    }

    //  Shared row mapper 
    private PetModel mapRow(ResultSet rs) throws Exception {
        PetModel pet = new PetModel();
        pet.setPetId(rs.getInt("petId"));
        pet.setPetName(rs.getString("petName"));
        pet.setPetAge(rs.getInt("petAge"));
        pet.setPetType(rs.getString("petType"));
        pet.setPetGender(rs.getString("petGender"));
        pet.setPetDesc(rs.getString("petDesc"));
        pet.setPetImage(rs.getString("petImage"));
        return pet;
    }
    
    // getting pets from database to home page
    public List<PetModel> getLatestPets(int limit) throws Exception {
        List<PetModel> petList = new ArrayList<>();
        String sql = "SELECT * FROM pet LIMIT ?";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, limit);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                PetModel pet = new PetModel();
                pet.setPetId(rs.getInt("petId"));
                pet.setPetName(rs.getString("petName"));
                pet.setPetAge(rs.getInt("petAge"));
                pet.setPetType(rs.getString("petType"));
                pet.setPetGender(rs.getString("petGender"));
                pet.setPetDesc(rs.getString("petDesc"));
                pet.setPetImage(rs.getString("petImage"));
                petList.add(pet);
            }
        }
        return petList;
    }
    
}