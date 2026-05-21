/**
 * AddPetsServlet - Handles adding a new pet by the admin.
 * Mapped to URL: /addPets
 *
 * Methods:
 * - doGet()  : Forwards the request to addPets.jsp.
 * - doPost() : Validates pet details, handles image upload via FileUploadUtil,
 *              saves the pet using PetDAO.addPet(), and redirects to /adminPets.
 *
 * Validations:
 * - Pet name must not be empty or contain numbers.
 * - Pet age must be a valid number between 0 and 20.
 * - Uploaded file must be a valid image.
 */

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
            String petName   = request.getParameter("petName");
            String petAgeStr = request.getParameter("petAge");
            String petType   = request.getParameter("petType");
            String petGender = request.getParameter("petGender");
            String petDesc   = request.getParameter("petDesc");

            // ── Validation 
            if (petName == null || petName.trim().isEmpty()) {
                request.setAttribute("error", "Pet name is required.");
                request.getRequestDispatcher("WEB-INF/pages/admin/addPets.jsp").forward(request, response);
                return;
            }
            if (petName.trim().matches(".*\\d.*")) {
                request.setAttribute("error", "Pet name must not contain numbers.");
                request.getRequestDispatcher("WEB-INF/pages/admin/addPets.jsp").forward(request, response);
                return;
            }

            int petAge;
            try {
                petAge = Integer.parseInt(petAgeStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Pet age must be a valid number.");
                request.getRequestDispatcher("WEB-INF/pages/admin/addPets.jsp").forward(request, response);
                return;
            }
            if (petAge < 0) {
                request.setAttribute("error", "Pet age cannot be negative.");
                request.getRequestDispatcher("WEB-INF/pages/admin/addPets.jsp").forward(request, response);
                return;
            }
            if (petAge > 20) {
                request.setAttribute("error", "Pet age cannot be more than 20.");
                request.getRequestDispatcher("WEB-INF/pages/admin/addPets.jsp").forward(request, response);
                return;
            }
           

            // Image handling
            String imageName = "default.png";
            Part filePart = request.getPart("petImage");
            if (filePart != null && filePart.getSize() > 0) {
                if (FileUploadUtil.isImage(filePart)) {
                    String extension = FileUploadUtil.getFileExtension(filePart.getSubmittedFileName());
                    imageName = System.currentTimeMillis() + "" + petName.trim().replaceAll("\\s+", "") + extension;
                    FileUploadUtil.saveFile(filePart, UPLOAD_DIR, imageName);
                } else {
                    request.setAttribute("error", "Only image files are allowed!");
                    request.getRequestDispatcher("WEB-INF/pages/admin/addPets.jsp").forward(request, response);
                    return;
                }
            }

            // Build and save pet
            PetModel pet = new PetModel();
            pet.setPetName(petName);
            pet.setPetAge(petAge);
            pet.setPetType(petType);
            pet.setPetGender(petGender);
            pet.setPetDesc(petDesc);
            pet.setPetImage(imageName);

            PetDAO.addPet(pet);
            session.setAttribute("message", "Pet added successfully!");
            response.sendRedirect(request.getContextPath() + "/adminPets"); 

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to add pet. Please try again.");
            request.getRequestDispatcher("WEB-INF/pages/admin/addPets.jsp").forward(request, response);
        }
    }
}