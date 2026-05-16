<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Pet - __Pawsy__ __Paila__</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addPets.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<div class="flex">

    <%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>

    <div class="main-content">

        <div class="top-header">
            <i class="fa-solid fa-paw"></i>
            <h1>Welcome Back, ${sessionScope.username}!</h1>
            <a href="${pageContext.request.contextPath}/AdminPets" class="btn-home">Back to Pets</a>
        </div>

        <div class="add-pet-card">
            <h2>Edit Pet</h2>

            <div class="pet-avatar-upload">
                <div class="avatar-circle">
                    <i class="fa-solid fa-paw"></i>
                </div>
            </div>

            <form action="${pageContext.request.contextPath}/UpdatePets" method="post">

                <%-- Change action to "edit" and pass the pet ID --%>
                <input type="hidden" name="action" value="edit"/>
                <input type="hidden" name="petId" value="${pet.petId}"/>

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

</body>
</html>