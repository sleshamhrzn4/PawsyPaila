package com.pawsypaila.model;

public class UserModel {
    // userId, fullName, phone, email, password, address, age, gender
    private int userId;
    private String fullName;
    private String phone;
    private String email;
    private String password;
    private String address;
    private int age;
    private String gender;

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserName() { return fullName; }
    public void setUserName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public void setemail(String email) { this.email = email; } // backward-compat alias

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}
