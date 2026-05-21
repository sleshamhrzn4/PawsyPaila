package com.pawsypaila.controller;
/**
 * GetImageServlet - Serves uploaded images from the server file system.
 * Mapped to URL: /getImage
 *
 * Methods:
 * - doGet() : Reads 'name' and 'type' parameters to locate and stream the image file.
 *             Supports types: 'pets', 'products', and 'userProfile'.
 *             Falls back to default.png if the requested image is not found.
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/getImage")
public class GetImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String imageName = request.getParameter("name");
        String type      = request.getParameter("type"); // "pets" or "products"

        if (imageName == null || imageName.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Image name is required");
            return;
        }

        // Same base directory as where you save uploads
        String baseDir = System.getProperty("user.home") + File.separator + "pawsypaila_uploads";

        String folder;
        if ("products".equalsIgnoreCase(type)) {
            folder = "products";
        } else if ("userProfile".equalsIgnoreCase(type)) {
            folder = "userProfile";
        } else {
            folder = "pets";
        }

        File imageFile = new File(baseDir + File.separator + folder, imageName);

        // Fallback to default.png if image not found
        if (!imageFile.exists()) {
            imageFile = new File(baseDir + File.separator + folder, "default.png");
        }

        if (!imageFile.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
            return;
        }

        String contentType = getServletContext().getMimeType(imageFile.getName());
        if (contentType == null) contentType = "image/png";

        response.setContentType(contentType);
        Files.copy(imageFile.toPath(), response.getOutputStream());
    }
}