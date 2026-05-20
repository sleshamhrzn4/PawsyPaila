package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.Map;

import com.pawsypaila.dao.UserDAO;
import com.pawsypaila.service.RegisterService;
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

        String fullName = request.getParameter("fullName");
        String phone    = request.getParameter("phone");
        String email    = request.getParameter("email");
        String password = request.getParameter("password");
        String address  = request.getParameter("address");
        String gender   = request.getParameter("gender");
        String ageParam = request.getParameter("age");

        // Run backend validation
        RegisterService service = new RegisterService();
        Map<String, String> errors = service.validate(fullName, phone, email, password, address, gender, ageParam);

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            // Send the old values back so user doesn't retype everything
            request.setAttribute("oldFullName", fullName);
            request.setAttribute("oldPhone",    phone);
            request.setAttribute("oldEmail",    email);
            request.setAttribute("oldAddress",  address);
            request.setAttribute("oldAge",      ageParam);
            request.setAttribute("oldGender",   gender);
            request.getRequestDispatcher("/WEB-INF/pages/public/register.jsp")
                   .forward(request, response);
            return;
        }

        // Parse age (already validated above)
        int age = Integer.parseInt(ageParam.trim());
        String hashedPassword = PasswordUtil.getHashPassword(password);

        // Handle profile image
        String imageName = "default.png";
        try {
            Part filePart = request.getPart("profileImage");
            if (filePart != null && filePart.getSize() > 0) {
                if (FileUploadUtil.isImage(filePart)) {
                    String extension = FileUploadUtil.getFileExtension(filePart.getSubmittedFileName());
                    imageName = System.currentTimeMillis() + fullName.trim().replaceAll("\\s+", "")+ extension;
                    FileUploadUtil.saveFile(filePart, UPLOAD_DIR, imageName);
                } else {
                    SessionUtil.setAttribute(request, "error", "Only image files are allowed!", 60);
                    response.sendRedirect(request.getContextPath() + "/register");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Save to DB
        try {
            UserDAO userDAO = new UserDAO();
            userDAO.insertUser(fullName, phone, email, hashedPassword,
                               address, age, gender, false, imageName);
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/public/register.jsp")
                   .forward(request, response);
        }
    }
}