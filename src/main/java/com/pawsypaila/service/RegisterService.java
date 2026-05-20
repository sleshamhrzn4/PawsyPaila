package com.pawsypaila.service;

import java.util.HashMap;
import java.util.Map;

public class RegisterService {

    public Map<String, String> validate(String fullName, String phone, String email, String password, String address, String gender, String ageParam) {

        Map<String, String> errors = new HashMap<>();

        // Full name
        if (fullName == null || fullName.trim().isEmpty()) {
            errors.put("fullName", "Full name is required.");
        }

        // Address
        if (address == null || address.trim().isEmpty()) {
            errors.put("address", "Address is required.");
        }

        // Gender
        if (gender == null || gender.trim().isEmpty()) {
            errors.put("gender", "Please select a gender.");
        }

        // Age — must be a number and at least 16
        if (ageParam == null || ageParam.trim().isEmpty()) {
            errors.put("age", "Age is required.");
        } else {
            try {
                int age = Integer.parseInt(ageParam.trim());
                if (age < 16) {
                    errors.put("age", "You must be at least 16 years old to register.");
                }
            } catch (NumberFormatException e) {
                errors.put("age", "Please enter a valid age.");
            }
        }

        // Phone — exactly 10 digits
        if (phone == null || phone.trim().isEmpty()) {
            errors.put("phone", "Phone number is required.");
        } else if (!phone.trim().matches("\\d{10}")) {
            errors.put("phone", "Phone number must be exactly 10 digits.");
        }

        // Email — must end with @gmail.com
        if (email == null || email.trim().isEmpty()) {
            errors.put("email", "Email is required.");
        } else if (!email.trim().toLowerCase().endsWith("@gmail.com")) {
            errors.put("email", "Email must be a valid @gmail.com address.");
        }

        // Password
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", "Password is required.");
        } else if (password.length() < 6) {
            errors.put("password", "Password must be at least 6 characters.");
        }

        return errors;
    }
}