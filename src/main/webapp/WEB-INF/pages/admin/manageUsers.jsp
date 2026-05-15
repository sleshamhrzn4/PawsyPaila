<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pawsy Paila - Manage Users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageUsers.css">
</head>
<body>

<div class="layout">

    <!-- Sidebar -->
    <aside class="sidebar">
        <div class="logo">
            <span class="logo-paw">🐾</span>
            <div class="logo-text">
                <span class="logo-main">Pawsy</span>
                <span class="logo-sub">पाइला</span>
            </div>
        </div>

        <nav class="nav-menu">
            <a href="${pageContext.request.contextPath}/dashboard" class="nav-item">
                <span class="nav-icon">⊞</span> Dashboard
            </a>
            <a href="${pageContext.request.contextPath}/pets" class="nav-item">
                <span class="nav-icon">🐾</span> Pets
            </a>
            <a href="${pageContext.request.contextPath}/products" class="nav-item">
                <span class="nav-icon">🛒</span> Products
            </a>
            <a href="${pageContext.request.contextPath}/users" class="nav-item active">
                <span class="nav-icon">📋</span> User
            </a>
            <a href="${pageContext.request.contextPath}/donation" class="nav-item">
                <span class="nav-icon">🩷</span> Donation
            </a>
        </nav>

        <div class="sidebar-user">
            <div class="user-avatar">
                <img src="${pageContext.request.contextPath}/images/default-avatar.png"
                     onerror="this.style.display='none'; this.nextElementSibling.style.display='flex'"
                     alt="avatar" />
                <div class="avatar-fallback" style="display:flex;">
                    <span class="avatar-icon">👤</span>
                </div>
            </div>
            <span class="sidebar-username">${sessionScope.loggedInUser.fullName}</span>
        </div>
    </aside>

    <!-- Main Content -->
    <main class="main-content">

        <!-- Welcome Banner -->
        <div class="welcome-banner">
            <span class="welcome-paw">🐾</span>
            <h1>Welcome Back, ${sessionScope.loggedInUser.fullName}!</h1>
        </div>

        <!-- Manage Users Card -->
        <div class="card">
            <h2 class="section-title">Manage Users</h2>

            <!-- Flash Messages -->
            <c:if test="${not empty successMessage}">
                <div class="alert alert-success">${successMessage}</div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-error">${errorMessage}</div>
            </c:if>

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
                                    <td class="user-name-cell">
                                        <div class="user-avatar-sm">
                                            <span class="avatar-icon-sm">👤</span>
                                        </div>
                                        <span>${user.fullName}</span>
                                    </td>
                                    <td>${user.createdDateFormatted}</td>
                                    <td>
                                        <span class="status-badge ${user.active ? 'status-active' : 'status-inactive'}">
                                            ${user.active ? 'Active' : 'Inactive'}
                                        </span>
                                    </td>
                                    <td class="action-cell">
                                        <form action="${pageContext.request.contextPath}/users" method="post" style="display:inline;">
                                            <input type="hidden" name="userId" value="${user.userId}" />
                                            <input type="hidden" name="action" value="activate" />
                                            <button type="submit" class="btn btn-activate">Activate</button>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/users" method="post" style="display:inline;">
                                            <input type="hidden" name="userId" value="${user.userId}" />
                                            <input type="hidden" name="action" value="deactivate" />
                                            <button type="submit" class="btn btn-deactivate">Deactivate</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" class="no-data">No users found.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

    </main>
</div>

</body>
</html>
