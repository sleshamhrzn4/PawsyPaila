<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Pet - Pawsy Paila</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/adminSidebar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/addPets.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700;800&display=swap"
	rel="stylesheet">
</head>
<body>

	<div class="flex">

		<%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp"%>

		<div class="main-content">

			<div class="top-header">
				<i class="fa-solid fa-paw"></i>
				<h1>Welcome Back, ${sessionScope.username}!</h1>
				<a href="${pageContext.request.contextPath}/adminPets"
					class="btn-home">Back to Pets</a>
			</div>

			<div class="add-pet-card">
				<c:if test="${not empty requestScope.error}">
					<div style="color: red; margin-bottom: 10px;">${requestScope.error}</div>
				</c:if>


				<c:if test="${not empty sessionScope.message}">
					<div style="color: green; margin-bottom: 10px;">${sessionScope.message}</div>
				</c:if>

				<h2>Add Pet</h2>

				<form action="${pageContext.request.contextPath}/addPets"
					method="post" enctype="multipart/form-data">

					<div class="pet-avatar-upload">
						<div class="avatar-circle"
							onclick="document.getElementById('petImage').click()"
							style="cursor: pointer;">
							<img id="imagePreview" src="" alt=""
								style="display: none; width: 100%; height: 100%; object-fit: cover; border-radius: 50%;">
							<i class="fa-solid fa-paw" id="imageIcon"></i>
						</div>
						<input type="file" id="petImage" name="petImage" accept="image/*"
							hidden>
					</div>

					<input type="hidden" name="action" value="add" />

					<div class="form-group">
						<label>Name</label>
						<%-- ✅ Retain value after failed submission --%>
						<input type="text" name="petName" placeholder="Enter pet name"
							value="${param.petName}" required>
					</div>

					<div class="form-group">
						<label>Age</label> <input type="number" name="petAge"
							placeholder="Enter pet age" value="${param.petAge}" min="0"
							max="20" required>
					</div>

					<div class="form-group">
						<label>Pet Type</label> <select name="petType" required>
							<option value="">Select Pet Type</option>
							<option value="Dog" ${param.petType == 'Dog' ? 'selected' : ''}>Dog</option>
							<option value="Cat" ${param.petType == 'Cat' ? 'selected' : ''}>Cat</option>
						</select>
					</div>

					<div class="form-group">
						<label>Gender</label> <select name="petGender" required>
							<option value="">Select Pet Gender</option>
							<option value="Male"
								${param.petGender == 'Male'   ? 'selected' : ''}>Male</option>
							<option value="Female"
								${param.petGender == 'Female' ? 'selected' : ''}>Female</option>
						</select>
					</div>

					<div class="form-group">
						<label>Description</label>
						<textarea name="petDesc" rows="4"
							placeholder="Enter pet description" required>${param.petDesc}</textarea>
					</div>

					<button type="submit" class="btn-add">Add Pet</button>

				</form>

			</div>
		</div>
	</div>

	<script>
		document
				.getElementById('petImage')
				.addEventListener(
						'change',
						function() {
							const file = this.files[0];
							if (file) {
								const reader = new FileReader();
								reader.onload = function(e) {
									document.getElementById('imagePreview').src = e.target.result;
									document.getElementById('imagePreview').style.display = 'block';
									document.getElementById('imageIcon').style.display = 'none';
								};
								reader.readAsDataURL(file);
							}
						});
	</script>

</body>
</html>