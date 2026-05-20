<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Pawsy Paila</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
</head>
<body>

<div class="container">

    <!-- left panel -->
    <div class="left">
        <div class="paw-pattern"></div>
        <div class="left-content">
            <h1>Welcome to<br>Pawsy Paila!</h1>
            <p>Welcome! Let's continue your journey<br>to find your perfect companion.</p>
        </div>
        <img src="${pageContext.request.contextPath}/images/authentication/login_hero.png" alt="Happy Dog" class="dog-image">
    </div>

    <!-- right panel -->
    <div class="right">
        <div class="top-bar">
            <span>Already Have an Account?</span>
            <a href="${pageContext.request.contextPath}/login">
                <button class="login-btn" onclick="location.href='${pageContext.request.contextPath}/login'">Log In</button>
            </a>
        </div>

        <div class="form-container">
            <h2>Register to adopt, shop, or connect with our community</h2>
            
            <form action="${pageContext.request.contextPath}/register" method="post" enctype="multipart/form-data">
			
			    <div class="profile-upload">
			        <label for="profileImage" class="profile-circle">
			            <img id="imagePreview" src="${pageContext.request.contextPath}/images/default-avatar.png" alt="Profile Preview">
			        </label>
			        <input type="file" id="profileImage" name="profileImage" accept="image/*" onchange="previewFile()" hidden>
			    </div>
			
			    <input type="text" name="fullName" placeholder="Full Name" value="${oldFullName}">
			    <c:if test="${not empty errors.fullName}">
			        <span class="error">${errors.fullName}</span>
			    </c:if>
			
			    <input type="text" name="address" placeholder="Address" value="${oldAddress}">
			    <c:if test="${not empty errors.address}">
			        <span class="error">${errors.address}</span>
			    </c:if>
			
			    <div class="row-group">
			        <select name="gender">
			            <option value="" disabled <c:if test="${empty oldGender}">selected</c:if>>Gender</option>
			            <option value="male"   <c:if test="${oldGender == 'male'}">selected</c:if>>Male</option>
			            <option value="female" <c:if test="${oldGender == 'female'}">selected</c:if>>Female</option>
			            <option value="other"  <c:if test="${oldGender == 'other'}">selected</c:if>>Other</option>
			        </select>
			
			        <input type="number" name="age" placeholder="Age" value="${oldAge}">
			    </div>
			    <c:if test="${not empty errors.gender}">
			        <span class="error">${errors.gender}</span>
			    </c:if>
			    <c:if test="${not empty errors.age}">
			        <span class="error">${errors.age}</span>
			    </c:if>
			
			    <input type="text" name="phone" placeholder="Phone Number" value="${oldPhone}">
			    <c:if test="${not empty errors.phone}">
			        <span class="error">${errors.phone}</span>
			    </c:if>
			
			    <input type="text" name="email" placeholder="Email Address" value="${oldEmail}">
			    <c:if test="${not empty errors.email}">
			        <span class="error">${errors.email}</span>
			    </c:if>
			
			    <input type="password" name="password" placeholder="Password">
			    <c:if test="${not empty errors.password}">
			        <span class="error">${errors.password}</span>
			    </c:if>
			
			    <button type="submit" class="signup-btn">Sign Up</button>
			</form>
            <p class="terms">
                By signing up, you agree to our Terms and Privacy Policy
            </p>
        </div>
    </div>
</div>

<script>
	function previewFile() {
	    const preview = document.getElementById('imagePreview');
	    const file    = document.getElementById('profileImage').files[0];
	    if (!file) return;
	    if (!file.type.startsWith('image/')) {
	        alert('Please select a valid image file.');
	        document.getElementById('profileImage').value = '';
	        return;
	    }
	    const reader = new FileReader();
	    reader.onloadend = function () {
	        preview.src = reader.result;
	    };
	    reader.readAsDataURL(file);
	}
</script>

</body>
</html>
