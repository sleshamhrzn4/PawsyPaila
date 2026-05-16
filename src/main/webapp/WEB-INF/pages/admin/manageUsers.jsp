<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
    
<!DOCTYPE html>
<html>
<head>


    <meta charset="UTF-8">
    <title>Admin Dashboard - Pawsy Paila</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageUsers.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;600;700;800&family=Nunito:wght@400;600;700&display=swap" rel="stylesheet">
    
</head>
<body>



<div class="flex">

    
    <%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>

    <!-- Main Content -->
    <div class="main-content">
		    <div class="welcome-box">
		   <img src="${pageContext.request.contextPath}/images/adminDashboard/pet.png" 
             alt="pets" style="width: 40px; height: auto;">
		    <h1>Welcome Back, Yunisha!</h1>
		</div>

       

        <!-- Card -->
        <div class="card">
        
         <!-- Page Header -->
        <div class="page-header">
            <i class="fas fa-users"></i>
            <h1>Manage Users</h1>
        </div>
            <div class="card-header">
                <h2>All Users</h2>
            </div>

            <!-- Flash Messages -->
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success">${successMessage}</div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-error">${errorMessage}</div>
            </c:if>

            <!-- Table -->
            <table class="users-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty userList}">
                            <c:forEach var="user" items="${userList}">
                                <tr>
                                    <td>
                                        <div class="user-name-cell">
                                            <div class="user-avatar">
                                                <i class="fas fa-user"></i>
                                            </div>
                                            <span>${user.fullName}</span>
                                        </div>
                                    </td>
                                    <td>${user.email}</td>
                                    <td>
                                        <span class="${user.active ? 'status-active' : 'status-inactive'}">
                                            ${user.active ? 'Active' : 'Inactive'}
                                        </span>
                                    </td>
                                    <td class="action-cell">
                                        <form action="${pageContext.request.contextPath}/ManageUsers" method="post" style="display:inline;">
                                            <input type="hidden" name="userId" value="${user.userId}" />
                                            <input type="hidden" name="action" value="activate" />
                                            <button type="submit" class="btn-activate">Activate</button>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/ManageUsers" method="post" style="display:inline;">
                                            <input type="hidden" name="userId" value="${user.userId}" />
                                            <input type="hidden" name="action" value="deactivate" />
                                            <button type="submit" class="btn-deactivate">Deactivate</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" class="no-users">No users found.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>

        </div>
    </div>
</div>

</body>
</html>
