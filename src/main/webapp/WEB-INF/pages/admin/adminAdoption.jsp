<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adoption Requests - Pawsy Paila</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/adminAdoption.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/adminSidebar.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;600;700;800&family=Nunito:wght@400;600;700&display=swap"
	rel="stylesheet">
</head>
<body>

	<div class="flex">

		<%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp"%>

		<!-- Main Content -->
		<div class="main-content">

			<!-- Welcome Box -->
			<div class="page-header">
				<i class="fa-solid fa-paw"></i>
				<h1>Welcome Back, ${sessionScope.username}!</h1>
			</div>

			<!-- Flash Messages -->
			<c:if test="${not empty sessionScope.message}">
				<div class="alert alert-success">${sessionScope.message}</div>
				<c:remove var="message" scope="session" />
			</c:if>
			<c:if test="${not empty sessionScope.error}">
				<div class="alert alert-error">${sessionScope.error}</div>
				<c:remove var="error" scope="session" />
			</c:if>

			<!-- Card -->
			<div class="card">
				<div class="card-header">
					<h2>
						<i class="fa-solid fa-heart"></i> Adoption Requests
					</h2>
				</div>

				<c:choose>
					<c:when test="${empty adoptionList}">
						<div class="no-data">No adoption requests found.</div>
					</c:when>
					<c:otherwise>
						<table class="adoption-table">
							<thead>
								<tr>
									<th>User</th>
									<th>Pet</th>
									<th>Status</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="req" items="${adoptionList}">
									<tr>
										<!-- User -->
										<td>
											<div class="user-cell">
												<div class="avatar">
													<i class="fa-solid fa-user"></i>
												</div>
												<span>${req.fullName}</span>
											</div>
										</td>

										<!-- Pet -->
										<td>
											<div class="pet-cell">
												<div class="avatar pet-avatar">
													<i class="fa-solid fa-paw"></i>
												</div>
												<span>${req.petName}</span>
											</div>
										</td>


										<!-- Status Badge -->
										<td><span
											class="status-badge status-${req.adoptionStatus.toLowerCase()}">
												${req.adoptionStatus} </span></td>

										<!-- Actions -->
										<!-- Actions -->
										<td class="action-cell"><c:choose>
												<c:when test="${req.adoptionStatus == 'Pending'}">
													<!-- Accept -->
													<form
														action="${pageContext.request.contextPath}/adminAdoption"
														method="post" style="display: inline;">
														<input type="hidden" name="adoptionId"
															value="${req.adoptionId}" /> <input type="hidden"
															name="action" value="accept" />
														<button type="submit" class="btn-accept">
															<i class="fa-solid fa-check"></i> Accept
														</button>
													</form>
													<!-- Reject -->
													<form
														action="${pageContext.request.contextPath}/adminAdoption"
														method="post" style="display: inline;">
														<input type="hidden" name="adoptionId"
															value="${req.adoptionId}" /> <input type="hidden"
															name="action" value="reject" />
														<button type="submit" class="btn-reject">
															<i class="fa-solid fa-xmark"></i> Reject
														</button>
													</form>
												</c:when>
												<c:otherwise>
													<!-- Delete for resolved requests -->
													<form
														action="${pageContext.request.contextPath}/adminAdoption"
														method="post" style="display: inline;">
														<input type="hidden" name="adoptionId"
															value="${req.adoptionId}" /> <input type="hidden"
															name="action" value="delete" />
														<button type="submit" class="btn-delete">
															<i class="fa-solid fa-trash"></i> Delete
														</button>
													</form>
												</c:otherwise>
											</c:choose></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
			</div>

		</div>
	</div>

</body>
</html>
