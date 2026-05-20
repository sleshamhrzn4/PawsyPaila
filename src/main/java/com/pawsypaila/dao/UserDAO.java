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

    //INSERT
    public void insertUser(String fullName, String phone, String email,
                           String password, String address, int age,
                           String gender, boolean active, String profileImg)
            throws Exception {

        Connection con = DBconfig.getConnection();

        String sql = "INSERT INTO user (fullName, phone, email, password, address, age, gender, active, profileImg) "
                   + "VALUES (?,?,?,?,?,?,?,?,?)";

        PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, fullName);
        pst.setString(2, phone);
        pst.setString(3, email);
        pst.setString(4, password);
        pst.setString(5, address);
        pst.setInt(6, age);
        pst.setString(7, gender);
        pst.setBoolean(8, active);
        pst.setString(9, profileImg);

        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            System.out.println("New user inserted with ID: " + rs.getInt(1));
        }
        rs.close();

        pst.close();
        con.close();
    }

    //SELECT ALL
    public List<UserModel> getAllUsers() throws Exception {
        List<UserModel> users = new ArrayList<>();
        Connection con = DBconfig.getConnection();

        String sql = "SELECT * FROM user";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            UserModel u = new UserModel();
            u.setUserId(rs.getInt("userId"));
            u.setFullName(rs.getString("fullName"));
            u.setPhone(rs.getString("phone"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setAddress(rs.getString("address"));
            u.setAge(rs.getInt("age"));
            u.setGender(rs.getString("gender"));
            u.setActive(rs.getBoolean("active"));
            u.setRole(rs.getString("role"));
            u.setProfileImg(rs.getString("profileImg"));
            users.add(u);
        }

        rs.close();
        pst.close();
        con.close();
        return users;
    }

    //SELECT BY EMAIL
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
            user.setFullName(rs.getString("fullName"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setAddress(rs.getString("address"));
            user.setAge(rs.getInt("age"));
            user.setGender(rs.getString("gender"));
            user.setActive(rs.getBoolean("active"));
            user.setRole(rs.getString("role"));
            user.setProfileImg(rs.getString("profileImg"));
        }

        rs.close();
        pst.close();
        con.close();
        return user;
    }
    

    //ACTIVATE / DEACTIVATE
    public boolean setUserStatus(int userId, boolean active) throws Exception {
        Connection con = DBconfig.getConnection();

        String sql = "UPDATE user SET active = ? WHERE userId = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setBoolean(1, active);
        pst.setInt(2, userId);

        boolean success = pst.executeUpdate() == 1;
        pst.close();
        con.close();
        return success;
    }

<<<<<<< HEAD
    //UPDATE
=======
   
>>>>>>> origin/swornima
    public int updateUser(int userId, String fullName, String phone, String email,
                          String password, String address, int age, String gender,
                          boolean active, String profileImg) throws Exception {

        Connection con = DBconfig.getConnection();

        String sql = "UPDATE user SET fullName=?, phone=?, email=?, password=?, "
                   + "address=?, age=?, gender=?, active=?, profileImg=? WHERE userId=?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, fullName);
        pst.setString(2, phone);
        pst.setString(3, email);
        pst.setString(4, password);
        pst.setString(5, address);
        pst.setInt(6, age);
        pst.setString(7, gender);
        pst.setBoolean(8, active);
        pst.setString(9, profileImg);
        pst.setInt(10, userId);

        int rowsAffected = pst.executeUpdate();
        pst.close();
        con.close();
        return rowsAffected;
    }
}