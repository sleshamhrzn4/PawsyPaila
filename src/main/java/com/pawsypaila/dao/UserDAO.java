package com.pawsypaila.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pawsypaila.model.UserModel;
import com.pawsypaila.utils.DBconfig;






public class UserDAO {

    public void insertUser(String fullName, String phone, String email, String password) throws Exception {

        Connection con = DBconfig.getConnection();

        String sql = "INSERT INTO user (fullName, phone, email, password ) "
                   + "VALUES (?,?,?,?)";

        PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, fullName);
        pst.setString(2, phone);
        pst.setString(3, email);
        pst.setString(4, password);
        
        pst.executeUpdate();
        
        int generatedId = -1;
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            generatedId = rs.getInt(1);
        }
        pst.executeUpdate();
        pst.close();
        con.close();
        
        
    }
    public List<UserModel> getAllUsers() throws Exception {
        List<UserModel> users = new ArrayList<>();
        Connection con = DBconfig.getConnection();
        
        String sql = "SELECT * FROM users";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            UserModel u = new UserModel();
            // Mapping student_id
            u.setUserId(rs.getInt(1));
            u.setUserName(rs.getString("fullName"));
            u.setPhone(rs.getString("phone"));
            u.setemail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            
            users.add(u);
        }
        
        rs.close();
        pst.close();
        con.close();
        return users;
    }

    public UserModel getUserByEmail(String email) throws Exception {
        UserModel user = null;
        Connection con = DBconfig.getConnection();
 
        String sql = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, email);
 
        ResultSet rs = pst.executeQuery();
 
        if (rs.next()) {
            user = new UserModel();
            user.setUserId(rs.getInt("userId"));
            user.setUserName(rs.getString("fullName"));
            user.setPhone(rs.getString("phone"));
            user.setemail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
        }
 
        rs.close();
        pst.close();
        con.close();
        return user;
    }
 
    
    // Create a updateUser method which takes fullname, email, password and phone as input and return rowsAffected
    public int updateStudent (int userID, String fullName, String phone, String email, String password) throws Exception {
    	
    
     
     Connection con = DBconfig.getConnection();
     
     String sql = "UPDATE students SET fullName =?, phone =?, email =?, password = ? where student_id=?";
     
     PreparedStatement pst = con.prepareStatement(sql);
     
     pst.setString(1, fullName);
     pst.setString(2, phone);
     pst.setString(3, email);
     pst.setString(4, password);
    
    
     
     
     int rowsAffected = pst.executeUpdate();
     
     pst.close();
    con.close();
    
    return rowsAffected;
    }
}
