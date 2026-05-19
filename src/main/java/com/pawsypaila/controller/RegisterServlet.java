package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

import com.pawsypaila.dao.UserDAO;
import com.pawsypaila.utils.FileUploadUtil;
import com.pawsypaila.utils.PasswordUtil;
import com.pawsypaila.utils.SessionUtil;

@WebServlet("/register")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,   // 2 MB
    maxFileSize       = 1024 * 1024 * 10,  // 10 MB
    maxRequestSize    = 1024 * 1024 * 50   // 50 MB
)
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR = System.getProperty("user.home")
            + java.io.File.separator + "pawsypaila_uploads"
            + java.io.File.separator + "userProfile";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/pages/public/register.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("=== doPost called — form submitted ===");

        // --- 1. Read and print all text fields ---
        String fullName = request.getParameter("fullName");
        String phone    = request.getParameter("phone");
        String email    = request.getParameter("email");
        String password = request.getParameter("password");
        String address  = request.getParameter("address");
        String gender   = request.getParameter("gender");
        String ageParam = request.getParameter("age");

        

        // --- 2. Null check before proceeding ---
        if (fullName == null || email == null || password == null || ageParam == null) {
           
            request.setAttribute("errorMessage", "All fields are required. Please fill the form completely.");
            request.getRequestDispatcher("/WEB-INF/pages/public/register.jsp")
                   .forward(request, response);
            return;
        }

        // --- 3. Parse age safely ---
        int age = 0;
        try {
            age = Integer.parseInt(ageParam);
            System.out.println("age (parsed): " + age);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: age is not a valid number — value was: " + ageParam);
            request.setAttribute("errorMessage", "Please enter a valid age.");
            request.getRequestDispatcher("/WEB-INF/pages/public/register.jsp")
                   .forward(request, response);
            return;
        }

        // --- 4. Hash password ---
        String hashedPassword = PasswordUtil.getHashPassword(password);
        System.out.println("Password hashed: " + (hashedPassword != null ? "yes" : "NULL — PasswordUtil failed"));

        // --- 5. Handle profile image ---
        String imageName = "default.png";

        try {
            Part filePart = request.getPart("profileImage");
            System.out.println("filePart      : " + filePart);
            System.out.println("filePart size : " + (filePart != null ? filePart.getSize() : "null"));

            if (filePart != null && filePart.getSize() > 0) {
                System.out.println("Is image: " + FileUploadUtil.isImage(filePart));
                if (FileUploadUtil.isImage(filePart)) {
                    String extension = FileUploadUtil.getFileExtension(filePart.getSubmittedFileName());
                    imageName = System.currentTimeMillis()
                            + fullName.trim().replaceAll("\\s+", "")
                            + extension;
                    System.out.println("Saving image as : " + imageName);
                    System.out.println("Upload dir      : " + UPLOAD_DIR);
                    FileUploadUtil.saveFile(filePart, UPLOAD_DIR, imageName);
                    System.out.println("Image saved successfully.");
                } else {
                    
                    SessionUtil.setAttribute(request, "error", "Only image files are allowed!", 60);
                    response.sendRedirect(request.getContextPath() + "/register");
                    return;
                }
            } else {
                System.out.println("No image uploaded — using default.png");
            }
        } catch (Exception e) {
            System.out.println("ERROR during file upload: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Final imageName: " + imageName);

        // --- 6. Save user to DB ---
        try {
            System.out.println("Attempting DB insert...");
            UserDAO userDAO = new UserDAO();
            userDAO.insertUser(fullName, phone, email, hashedPassword,
                               address, age, gender, false, imageName);
            System.out.println("=== User inserted successfully! ===");
            response.sendRedirect(request.getContextPath() + "/login");

        } catch (Exception e) {
            System.out.println("ERROR: DB insert failed — " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/public/register.jsp")
                   .forward(request, response);
        }
    }
}