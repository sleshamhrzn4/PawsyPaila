<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile - Pawsy</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userProfile.css">
    <link href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;600;700;800&family=Nunito:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
</head>
<body>

<div class="app-container">

   

    <div class="main-content">

        <!-- Top Header -->
        <div class="top-header">
            <div class="header-left">
                <i class="fas fa-paw"></i>
                <h1>Welcome Back, ${user.name}!</h1>
            </div>
            <button class="home-btn"
                onclick="window.location.href='${pageContext.request.contextPath}/dashboard'">
                Home
            </button>
        </div>

        <!-- Profile Card -->
        <div class="profile-wrapper">
            <div class="profile-card">
                <h2>My Profile</h2>

                <!-- Avatar -->
                <div class="avatar-circle">
                    <i class="fas fa-user"></i>
                </div>

                <!-- Success/Error messages -->
                <c:if test="${not empty message}">
                    <p class="success-msg">${message}</p>
                </c:if>
                <c:if test="${not empty error}">
                    <p class="error-msg">${error}</p>
                </c:if>

                <!-- Profile Form -->
                <form action="${pageContext.request.contextPath}/userProfile" method="post">

                    <div class="profile-row">
                        <label>Name</label>
                        <input type="text" name="name" value="${user.name}">
                    </div>

                    <div class="profile-row">
                        <label>Phone</label>
                        <input type="text" name="phone" value="${user.phone}">
                    </div>

                    <div class="profile-row">
                        <label>Email</label>
                        <input type="email" name="email" value="${user.email}">
                    </div>

                    <div class="profile-row">
                        <label>Address</label>
                        <input type="text" name="address" value="${user.address}">
                    </div>

                    <div class="profile-row">
                        <label>Gender</label>
                        <input type="text" name="gender" value="${user.gender}">
                    </div>

                    <div class="profile-row">
                        <label>Age</label>
                        <input type="number" name="age" value="${user.age}">
                    </div>

                    <button type="submit" class="save-btn">Save Changes</button>
                </form>

            </div>
        </div>

    </div>
</div>

</body>
</html>