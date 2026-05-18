package com.pawsypaila.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;

import com.pawsypaila.dao.PetDAO;
import com.pawsypaila.model.PetModel;
import com.pawsypaila.utils.FileUploadUtil;
import com.pawsypaila.utils.SessionUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/addPets" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)

public class AddPetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIR = System.getProperty("user.home") + java.io.File.separator
			+ "pawsypaila_uploads" + java.io.File.separator + "pets";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/pages/admin/addPets.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
			// Get form data
			String petName = request.getParameter("petName");
			int petAge = Integer.parseInt(request.getParameter("petAge"));
			String petType = request.getParameter("petType");
			String petGender = request.getParameter("petGender");
			String petDesc = request.getParameter("petDesc");
			
			
			String imageName = "default.png";
			Part filePart = request.getPart("petImage");
			System.out.println("File part: " + filePart);
			System.out.println("File size: " + (filePart != null ? filePart.getSize() : "null"));
			System.out.println("Is image: " + (filePart != null ? FileUploadUtil.isImage(filePart) : "null"));
			System.out.println("Image name to save: " + imageName);

			if (filePart != null && filePart.getSize() > 0) {
				if (FileUploadUtil.isImage(filePart)) {

					String extension = FileUploadUtil.getFileExtension(filePart.getSubmittedFileName());

					// unique filename
					imageName = System.currentTimeMillis() + "" + petName.trim().replaceAll("\\s+", "") + extension;
					// saving to into folder
					FileUploadUtil.saveFile(filePart, UPLOAD_DIR, imageName);

				} else {
					SessionUtil.setAttribute(request, "error", "Only image files are allowed!", 60);
					response.sendRedirect(request.getContextPath() + "/addPets");
					return;
				}
			}

			// Create PetModel object
			PetModel pet = new PetModel();
			pet.setPetName(petName);
			pet.setPetAge(petAge);
			pet.setPetType(petType);
			pet.setPetGender(petGender);
			pet.setPetDesc(petDesc);
			pet.setPetImage(imageName);

			
			PetDAO.addPet(pet);

			// Success message
			session.setAttribute("message", "Pet added successfully!");

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", "Failed to add pet. Please try again.");
		}

		response.sendRedirect(request.getContextPath() + "/adminPets");

	}
}
