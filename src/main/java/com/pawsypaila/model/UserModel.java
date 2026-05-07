package com.pawsypaila.model;

public class UserModel {
 	private int userId;
    private String fullName;
    private String phone;
    private String email;
    private String password;
    

    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserName() { return fullName; }
    public void setUserName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getemail() { return email; }
    public void setemail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; 
    }

   
}



