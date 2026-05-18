package com.pawsypaila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.pawsypaila.utils.DBconfig;

public class ContactDAO {

    public boolean insertContact(String email, String message) {

        String sql = "INSERT INTO Contact (email, message) VALUES (?, ?)";

        try (Connection con = DBconfig.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, email);
            pst.setString(2, message);

            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}