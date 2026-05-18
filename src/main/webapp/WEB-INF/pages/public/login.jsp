<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
<title>Pawsy Paila</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
	<div class="container">

		<!-- LEFT SIDE -->
		<div class="left">
			<div class="overlay"></div>

			<div class="left-content">
				<h1>
					Welcome to<br> Pawsy Paila!
				</h1>

				<p>
					Welcome Back! Let's<br> continue your journey to find<br>
					your perfect companion.
				</p>
			</div>

			<img
				src="${pageContext.request.contextPath}/images/authentication/login_hero.png"
				class="cat" alt="cat">
		</div>


		<!-- RIGHT SIDE -->
		<div class="right">

			<div class="top-bar">
				<span>Do Not Have an Account?</span> <a
					href="${pageContext.request.contextPath}/register">
					<button class="login-btn"
						onclick="location.href='${pageContext.request.contextPath}/register'">Sign
						Up</button>
				</a>

			</div>
			<div class="form-container">
				<h4>Log In to adopt, shop, or connect with our community</h4>

				<c:if test="${param.error == 1}">
					<div class="error-msg">No account found with that email.</div>
				</c:if>
				<c:if test="${param.error == 2}">
					<div class="error-msg">Wrong password. Please try again.</div>
				</c:if>
				<c:if test="${param.error == 3}">
					<div class="error-msg">Something went wrong. Please try
						again.</div>
				</c:if>

				<form action="${pageContext.request.contextPath}/login"
					method="post">

					<input type="email" name="email" placeholder="Email Address"
						required> <input type="password" name="password"
						placeholder="Password" required>

					<button type="submit" class="login-btn">Log In</button>

				</form>
				<p class="terms">By logging in, you agree to our Terms & Privacy
					Policy</p>
			</div>

		</div>

	</div>
</body>
</html>