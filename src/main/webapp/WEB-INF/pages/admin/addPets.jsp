<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Pet - Pawsy Paila</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminPets.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<div class="flex">
    <!-- Sidebar -->
    <div class="sidebar">
        <%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        
        <!-- Top Header -->
        <div class="top-header">
            <div class="welcome-text">
                <i class="fa-solid fa-paw"></i>
                Welcome Back, ${sessionScope.username}!
            </div>
            <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn-home">Home</a>
        </div>

        <!-- Add Pet Card -->
        <div class="add-pet-card">
            <h2>Add Pet</h2>
            
            <div class="pet-avatar-upload">
                <div class="avatar-circle">
                    <i class="fa-solid fa-user"></i>
                </div>
            </div>

            <form action="${pageContext.request.contextPath}/admin/addPet" method="post">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="petName" required>
                </div>

                <div class="form-group">
                    <label>Pet Type</label>
                    <select name="petType" required>
                        <option value="">Select Pet Type</option>
                        <option value="Dog">Dog</option>
                        <option value="Cat">Cat</option>
                        <option value="Bird">Bird</option>
                        <option value="Rabbit">Rabbit</option>
                        <option value="Fish">Fish</option>
                        <option value="Other">Other</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Breed</label>
                    <input type="text" name="breed" required>
                </div>

                <div class="form-group">
                    <label>Desc</label>
                    <textarea name="petDesc" rows="4" required></textarea>
                </div>

                <button type="submit" class="btn-add-pet">Add Pet</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
</body>
</html>