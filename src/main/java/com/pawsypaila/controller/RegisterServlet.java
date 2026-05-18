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



/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet( "/register" )
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2,
	    maxFileSize = 1024 * 1024 * 10,
	    maxRequestSize = 1024 * 1024 * 50
	)
public class RegisterServlet extends HttpServlet {
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/pages/public/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
            
           
	
		
	