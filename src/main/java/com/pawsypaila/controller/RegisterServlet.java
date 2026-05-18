package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

import com.pawsypaila.dao.UserDAO;
import com.pawsypaila.utils.PasswordUtil;

@WebServlet("/register")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,   // 2 MB buffer in memory before writing to disk
    maxFileSize       = 1024 * 1024 * 10,  // 10 MB max size
    maxRequestSize    = 1024 * 1024 * 50   // 50 MB max total request
)
public class RegisterServlet extends HttpServlet {

    private static final String UPLOAD_DIR =
        System.getProperty("user.home") + File.separator + "webapp_uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/public/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // collecting all form fields
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String ageStr = request.getParameter("age");
        int age = Integer.parseInt(ageStr);
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // hash the password with bcrypt
        String hashedPassword = PasswordUtil.getHashPassword(password);

        // handle profile image upload by validating img and saving as username.extension adn saving in disk
        String savedFileName = "default-avatar.png";
        Part filePart = request.getPart("profileImage");
        if (filePart != null && filePart.getSize() > 0) {
            String contentType = filePart.getContentType();
            if (contentType != null && contentType.startsWith("image/")) {
                String originalFileName = filePart.getSubmittedFileName();
                String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String fileName = fullName + extension;
                File uploadDirFile = new File(UPLOAD_DIR);
                if (!uploadDirFile.exists()) uploadDirFile.mkdirs();
                filePart.write(UPLOAD_DIR + File.separator + fileName);
                savedFileName = fileName;
            }
        }

        try {
            UserDAO userDAO = new UserDAO();
            userDAO.insertUser(fullName, phone, email, hashedPassword, address, age, gender);
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (Exception e) { //if any error occures
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/register");
        }
    }
}