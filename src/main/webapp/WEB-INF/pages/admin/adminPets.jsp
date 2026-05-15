<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Pawsy Paila</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminPets.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;600;700;800&family=Nunito:wght@400;600;700&display=swap" rel="stylesheet">
</head>

<body>
<div class="flex">

    <%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>

    <!-- Main Content -->
    <div class="main-content">

        <!-- Page Header -->
        <div class="page-header">
            <i class="fa-solid fa-paw"></i>
            <h1>Welcome Back, ${sessionScope.username}!</h1>
        </div>

        <!-- Flash Messages -->
        <c:if test="${not empty sessionScope.message}">
            <div class="alert alert-success">${sessionScope.message}</div>
            <c:remove var="message" scope="session"/>
        </c:if>

        <c:if test="${not empty sessionScope.error}">
            <div class="alert alert-error">${sessionScope.error}</div>
            <c:remove var="error" scope="session"/>
        </c:if>

        <!-- Manage Pets Card -->
        <div class="card">
            <div class="card-header">
                <h2>Manage Pets</h2>
                <!-- ✅ Fixed: points to AdminPets?action=showAdd -->
                <a href="${pageContext.request.contextPath}/AdminPets?action=showAdd" class="btn-add">
                    <i class="fa-solid fa-plus"></i> Add Pet
                </a>
            </div>

            <c:choose>
                <c:when test="${empty requestScope.pets}">
                    <div class="no-pets">No pets found. Click "Add Pet" to get started.</div>
                </c:when>
                <c:otherwise>
                    <table class="pets-table">
                        <thead>
                            <tr>
                                <th>Pet</th>
                                <th>Type</th>
                                <th>Age</th>
                                <th>Gender</th>
                                <th>Description</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pet" items="${requestScope.pets}">
                                <tr>
                                    <td>
                                        <div class="pet-name-cell">
                                            <div class="pet-avatar">
                                                <i class="fa-solid fa-paw"></i>
                                            </div>
                                            ${pet.petName}
                                        </div>
                                    </td>
                                    <td>${pet.petType}</td>
                                    <td>${pet.petAge}</td>
                                    <td>${pet.petGender}</td>
                                    <td>${pet.petDesc}</td>
                                    <td>
                                        <!-- Edit button -->
                                        <a href="${pageContext.request.contextPath}/AdminPets?action=showEdit&petId=${pet.petId}" 
                                           class="btn-edit">
                                            <i class="fa-solid fa-pen"></i> Edit
                                        </a>

                                        <!-- Delete button -->
                                        <form action="${pageContext.request.contextPath}/AdminPets" 
                                              method="post" style="display:inline;"
                                              onsubmit="return confirm('Delete ${pet.petName}?')">
                                            <input type="hidden" name="action" value="delete"/>
                                            <input type="hidden" name="petId" value="${pet.petId}"/>
                                            <button type="submit" class="btn-delete">
                                                <i class="fa-solid fa-trash"></i> Delete
                                            </button>
                                        </form>
                                    </td>
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