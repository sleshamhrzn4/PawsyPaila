<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${fn:escapeXml(pet.petName)}- Pawsy Paila</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/petDetail.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700;800&display=swap"
	rel="stylesheet">
</head>
<body>

	<%@ include file="/WEB-INF/pages/public/header.jsp"%>

	<div class="detail-container">
		<div class="detail-card">

		
			<div class="detail-img-wrap">
				<c:choose>
					<c:when test="${not empty pet.petImage}">
						<img
							src="${pageContext.request.contextPath}/getImage?name=${fn:escapeXml(pet.petImage)}&amp;type=pet"
							alt="${fn:escapeXml(pet.petName)}">
					</c:when>
					<c:otherwise>
						<span class="pet-placeholder"> <c:choose>
								<c:when test="${fn:toLowerCase(pet.petType) == 'cat'}">🐱</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</span>
					</c:otherwise>
				</c:choose>
			</div>

			
			<div class="detail-info">
				<h1 class="detail-name">${fn:escapeXml(pet.petName)}</h1>

				<div class="detail-meta">
					<p>
						<strong>Age:</strong> ${pet.petAge}
					</p>
					<p>
						<strong>Gender:</strong> ${fn:escapeXml(pet.petGender)}
					</p>
				</div>

				<c:if test="${not empty sessionScope.adoptMessage}">
					<div class="adopt-success">
						<i class="fa-solid fa-circle-check"></i>
						${sessionScope.adoptMessage}
					</div>
					<c:remove var="adoptMessage" scope="session" />
				</c:if>

				<form action="${pageContext.request.contextPath}/adopt"
					method="post">
					<input type="hidden" name="petId" value="${pet.petId}" /> <input
						type="hidden" name="petName" value="${fn:escapeXml(pet.petName)}" />
					<button type="submit" class="btn-adopt">Adopt</button>
				</form>

				<div class="detail-desc">
					<h3>Description:</h3>
					<p>
						<c:choose>
							<c:when test="${not empty pet.petDesc}">
                            ${fn:escapeXml(pet.petDesc)}
                        </c:when>
							<c:otherwise>
                            Hey there, I'm ${fn:escapeXml(pet.petName)} and I'm looking for a loving home!
                        </c:otherwise>
						</c:choose>
					</p>
				</div>
			</div>

		</div>
	</div>
	<%@ include file="/WEB-INF/pages/public/footer.jsp" %>
</body>
</html>