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
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get text files
		String fullName = request.getParameter("fullName");
		String phone    = request.getParameter("phone");
		String email    = request.getParameter("email");
		String password = request.getParameter("password");
		String address  = request.getParameter("address");
		String gender   = request.getParameter("gender");
		int    age      = Integer.parseInt(request.getParameter("age"));
	    
	    String hashedPassword = PasswordUtil.getHashPassword(password);
	    
	    System.out.println("fullName: " + fullName);
	    System.out.println("phone:    " + phone);
	    System.out.println("email:    " + email);
	    System.out.println("password: " + password);

	    Part filePart = request.getPart("profileImage");
	    
	    //Handle image upload
	    if (filePart != null && filePart.getSize() > 0) {

            // Get original filename e.g. "myphoto.png"
            String originalFileName = filePart.getSubmittedFileName();

            // Make it unique using username e.g. "john_myphoto.png"
            String fileName = fullName + "_" + originalFileName;

            // Find the real path of webapp/images/ on the server
            String uploadPath = "C:\\Users\\Yunisha Basnet\\eclipse-workspace\\"
                    + "pawsypaila\\src\\main\\webapp\\images";

            System.out.println("Saving to:" + uploadPath);   
            
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();  
            }

            filePart.write(uploadPath + File.separator + fileName);
            System.out.println("File saved: " + fileName);
        }

	    	
            try {
                UserDAO userDAO = new UserDAO();
                userDAO.insertUser(fullName, phone, email, hashedPassword, address, age, gender, false);
                System.out.println("User saved with hashed password");

                
                request.getRequestDispatcher("/WEB-INF/pages/public/register")
 	           .forward(request, response);
                return;

            } catch (Exception e) {
            	 request.setAttribute("errorMessage", "Registration failed. Please try again.");
            	 response.sendRedirect(request.getContextPath() + "/login");
            	    return;
            }
	}
}