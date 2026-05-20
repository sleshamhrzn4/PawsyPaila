package com.pawsypaila.controller;

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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.pawsypaila.dao.PetDAO;
import com.pawsypaila.model.PetModel;

@WebServlet(asyncSupported = true, urlPatterns = { "/updatePets" })
@MultipartConfig
public class UpdatePetsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int petId = Integer.parseInt(request.getParameter("petId"));

            PetDAO dao = new PetDAO();
            PetModel pet = dao.getPetById(petId);

            if (pet != null) {
                request.setAttribute("pet", pet);
                request.getRequestDispatcher("/WEB-INF/pages/admin/updatePets.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("error", "Pet not found.");
                response.sendRedirect(request.getContextPath() + "/adminPets");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Something went wrong.");
            response.sendRedirect(request.getContextPath() + "/adminPets");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            int petId            = Integer.parseInt(request.getParameter("petId"));
            String petName       = request.getParameter("petName");
            String petAgeStr     = request.getParameter("petAge");
            String petType       = request.getParameter("petType");
            String petGender     = request.getParameter("petGender");
            String petDesc       = request.getParameter("petDesc");
            String existingImage = request.getParameter("existingImage");

            // ── Validation ──────────────────────────────────────────
            if (petName == null || petName.trim().isEmpty()) {
                request.setAttribute("error", "Pet name is required.");
                reloadEditPage(request, response, petId);
                return;
            }
            if (petName.trim().matches(".*\\d.*")) {
                request.setAttribute("error", "Pet name must not contain numbers.");
                reloadEditPage(request, response, petId);
                return;
            }

            int petAge;
            try {
                petAge = Integer.parseInt(petAgeStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Pet age must be a valid number.");
                reloadEditPage(request, response, petId);
                return;
            }
            if (petAge < 0) {
                request.setAttribute("error", "Pet age cannot be negative.");
                reloadEditPage(request, response, petId);
                return;
            }
            if (petAge > 20) {
                request.setAttribute("error", "Pet age cannot be more than 20.");
                reloadEditPage(request, response, petId);
                return;
            }
            // ── End Validation ───────────────────────────────────────

            // ── Image handling ───────────────────────────────────────
            String imageName = existingImage; // default: keep old image

            Part imagePart = request.getPart("petImage");
            if (imagePart != null && imagePart.getSize() > 0) {
                String originalName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
                String extension = originalName.substring(originalName.lastIndexOf('.'));
                imageName = "pet_" + petId + "_" + System.currentTimeMillis() + extension;

                String uploadDir = System.getProperty("user.home") + File.separator
                        + "pawsypaila_uploads" + File.separator + "pets";
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                try (InputStream is = imagePart.getInputStream()) {
                    Files.copy(is, new File(dir, imageName).toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
            // ── End Image handling ───────────────────────────────────

            // ── Build and update pet ─────────────────────────────────
            PetModel pet = new PetModel();
            pet.setPetId(petId);
            pet.setPetName(petName);
            pet.setPetAge(petAge);
            pet.setPetType(petType);
            pet.setPetGender(petGender);
            pet.setPetDesc(petDesc);
            pet.setPetImage(imageName);

            PetDAO dao = new PetDAO();
            dao.updatePet(pet);
            session.setAttribute("message", "Pet updated successfully!");
            response.sendRedirect(request.getContextPath() + "/adminPets"); // ✅ only on success

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Failed to update pet. Please try again.");
            response.sendRedirect(request.getContextPath() + "/adminPets");
        }
    }

    // ✅ Helper method to reload the edit page with the pet data + error message
    private void reloadEditPage(HttpServletRequest request, HttpServletResponse response, int petId)
            throws ServletException, IOException {
        try {
            PetDAO dao = new PetDAO();
            PetModel pet = dao.getPetById(petId);
            request.setAttribute("pet", pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/WEB-INF/pages/admin/updatePets.jsp").forward(request, response);
    }
}