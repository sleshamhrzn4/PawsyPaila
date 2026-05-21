/**
 * UserProfileServlet - Handles viewing and updating the logged-in user's profile.
 * Mapped to URL: /userprofile
 *
 * Methods:
 * - doGet()  : Checks if user is logged in via session and forwards to userProfile.jsp.
 *              Redirects to /login if user is not logged in.
 * - doPost() : Handles profile image upload, updates user details via UserDAO.updateUser(),
 *              refreshes session with updated user data and forwards back to userProfile.jsp.
 */
package com.pawsypaila.controller;

import com.pawsypaila.dao.UserDAO;
import com.pawsypaila.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;

/**
 * __Servlet__ implementation class UserProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/userprofile" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize       = 1024 * 1024 * 10,
    maxRequestSize    = 1024 * 1024 * 50
)
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAO();

    public UserProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/pages/user/userProfile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("user") == null) {
	        response.sendRedirect(request.getContextPath() + "/login");
	        return;
	    }
	    UserModel currentUser = (UserModel) session.getAttribute("user");

	    String name     = request.getParameter("userName");
	    String phone    = request.getParameter("userPhone");
	    String address  = request.getParameter("userAddress");
	    String ageStr   = request.getParameter("userAge");
	    String gender   = request.getParameter("userGender");

	    String email      = currentUser.getEmail();
	    String password   = currentUser.getPassword();
	    boolean active    = currentUser.isActive();
	    String profileImg = currentUser.getProfileImg();

	    int age = currentUser.getAge();
	    if (ageStr != null && !ageStr.trim().isEmpty()) {
	        try {
	            age = Integer.parseInt(ageStr.trim());
	        } catch (NumberFormatException e) {
	            
	        }
	    }

	    try {
	        Part filePart = request.getPart("profileImage");
	        if (filePart != null && filePart.getSize() > 0) {
	            String originalFileName = filePart.getSubmittedFileName();
	            String fileName = currentUser.getUserId() + "_" + originalFileName;

	            
	            String baseDir = System.getProperty("user.home") + File.separator + "pawsypaila_uploads";
	            String uploadPath = baseDir + File.separator + "userProfile";

	            File uploadDir = new File(uploadPath);
	            if (!uploadDir.exists()) {
	                uploadDir.mkdirs();
	            }

	            filePart.write(uploadPath + File.separator + fileName);
	            profileImg = fileName;
	            System.out.println("Image saved to: " + uploadPath + File.separator + fileName);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    try {
	        int rows = userDAO.updateUser(
	            currentUser.getUserId(),
	            name, phone, email, password,
	            address, age, gender, active,
	            profileImg
	        );
	        if (rows > 0) {
	            currentUser.setFullName(name);
	            currentUser.setPhone(phone);
	            currentUser.setAddress(address);
	            currentUser.setAge(age);
	            currentUser.setGender(gender);
	            currentUser.setProfileImg(profileImg); 
	            session.setAttribute("user", currentUser);
	            request.setAttribute("successMessage", "Profile updated successfully!");
	        } else {
	            request.setAttribute("errorMessage", "No changes were saved. Please try again.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Something went wrong: " + e.getMessage());
	    }

	    doGet(request, response);
	}
}