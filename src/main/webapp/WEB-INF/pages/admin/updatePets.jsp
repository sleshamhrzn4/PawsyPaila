<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Pet - Pawsy Paila</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addPets.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700;800&display=swap" rel="stylesheet">
</head>
<body>

<div class="flex">

    <%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>

    <div class="main-content">

        <div class="top-header">
            <i class="fa-solid fa-paw"></i>
            <h1>Welcome Back, ${sessionScope.username}!</h1>
            <a href="${pageContext.request.contextPath}/adminPets" class="btn-home">Back to Pets</a>
        </div>

        <div class="add-pet-card">
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

			<c:if test="${not empty requestScope.error}">
			    <div style="color:red; margin-bottom:10px;">${requestScope.error}</div>
			</c:if>
            <h2>Edit Pet</h2>

           
            <form action="${pageContext.request.contextPath}/updatePets" method="post" enctype="multipart/form-data">

                <input type="hidden" name="action" value="edit"/>
                <input type="hidden" name="petId" value="${pet.petId}"/>
             
                <input type="hidden" name="existingImage" value="${pet.petImage}"/>

            
                <div class="pet-avatar-upload">
                    <div class="avatar-circle" id="avatarCircle">
                        <c:choose>
                            <c:when test="${not empty pet.petImage}">
                                <img id="preview"
                                     src="${pageContext.request.contextPath}/getImage?name=${pet.petImage}&type=pet"
                                     alt="Pet Image"
                                     style="width:100%;height:100%;border-radius:50%;object-fit:cover;"/>
                            </c:when>
                            <c:otherwise>
                                <i class="fa-solid fa-paw" id="previewIcon"></i>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <label for="petImage" style="cursor:pointer; margin-top:8px; display:block; text-align:center;">
                        <i class="fa-solid fa-upload"></i> Change Image
                    </label>
                    <input type="file" id="petImage" name="petImage" accept="image/*"
                           style="display:none" onchange="previewImage(this)"/>
                </div>

                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="petName" placeholder="Enter pet name" value="${pet.petName}" required>
                </div>

                <div class="form-group">
                    <label>Age</label>
                    <input type="number" name="petAge" placeholder="Enter pet age" min="0" value="${pet.petAge}" required>
                </div>

                <div class="form-group">
                    <label>Pet Type</label>
                    <select name="petType" required>
                        <option value="">Select Pet Type</option>
                        <option value="Dog" ${pet.petType == 'Dog' ? 'selected' : ''}>Dog</option>
                        <option value="Cat" ${pet.petType == 'Cat' ? 'selected' : ''}>Cat</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Gender</label>
                    <select name="petGender" required>
                        <option value="">Select Pet Gender</option>
                        <option value="Male" ${pet.petGender == 'Male' ? 'selected' : ''}>Male</option>
                        <option value="Female" ${pet.petGender == 'Female' ? 'selected' : ''}>Female</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Description</label>
                    <textarea name="petDesc" rows="4" placeholder="Enter pet description" required>${pet.petDesc}</textarea>
                </div>

                <button type="submit" class="btn-add">Update Pet</button>
            </form>
        </div>
    </div>
</div>

<script>
function previewImage(input) {
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function(e) {
            const circle = document.getElementById('avatarCircle');
            // Replace whatever is inside with the new preview image
            circle.innerHTML = '<img id="preview" src="' + e.target.result + '" ' +
                'style="width:100%;height:100%;border-radius:50%;object-fit:cover;" alt="Preview"/>';
        };
        reader.readAsDataURL(input.files[0]);
    }
}
</script>

</body>
</html>