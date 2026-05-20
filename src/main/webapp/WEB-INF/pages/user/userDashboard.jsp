<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Pawsy Paila</title>
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userDashboard.css">
</head>
<body>
<div class="dashboard-layout">
    <jsp:include page="userSidebar.jsp" />
    <main class="profile-main-content">
        <div class="welcome-top-bar">
            <div class="welcome-text">
                <span class="paw-icon">🐾</span> Welcome Back, <c:out value="${sessionScope.user.fullName != null ? sessionScope.user.fullName : 'User'}"/>!
            </div>
            <a href="${pageContext.request.contextPath}/home" class="home-nav-btn">Home</a>
        </div>
        <div class="adoption-card">
            <h2 class="section-title">Adoption Requests</h2>
            <c:choose>
                <c:when test="${empty adoptionRequests}">
                    <div class="empty-state">
                        <i class="fa-solid fa-paw"></i>
                        <p>You haven't made any adoption requests yet.</p>
                        <a href="${pageContext.request.contextPath}/pets" class="home-nav-btn">Browse Pets</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="adoption-table">
                        <thead>
                            <tr>
                                <th>Pet</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="req" items="${adoptionRequests}">
                                <tr>
                                    <td>
                                        <div class="pet-name-cell">
                                            <div class="pet-icon">
                                                <i class="fa-solid fa-paw"></i>
                                            </div>
                                            <span>${req.petName}</span>
                                        </div>
                                    </td>
                                    <td>
                                        <span class="status-badge ${req.adoptionStatus.toLowerCase()}">
                                            ${req.adoptionStatus}
                                        </span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
</div>
</body>
</html>