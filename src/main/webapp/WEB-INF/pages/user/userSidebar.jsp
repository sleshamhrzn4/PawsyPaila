<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/userSidebar.css">

<aside class="sidebar">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/images/adminDashboard/logo.png"
             class="logo" alt="logo" style="width:150px; height:auto;">
    </div>
    <nav class="sidebar-nav">

        <%-- ✅ Changed from My Profile to Dashboard --%>
        <a href="${pageContext.request.contextPath}/userdashboard" class="nav-item">
            <img src="${pageContext.request.contextPath}/images/adminDashboard/dash.png"
                 alt="dashboard" style="width: 30px; height: auto;">
            Dashboard
        </a>

        <a href="${pageContext.request.contextPath}/logout" class="nav-item">
            <i class="fa-solid fa-right-from-bracket" style="width: 30px; font-size: 1.3rem; color: #e05c5c;"></i>
            <span style="color: #e05c5c;">Logout</span>
        </a>

    </nav>

    <%-- ✅ Made footer clickable — redirects to edit profile --%>
<a href="${pageContext.request.contextPath}/userprofile" class="sidebar-footer">
    <span class="sidebar-avatar">
        ${sessionScope.user.fullName.substring(0,1).toUpperCase()}
    </span>
    <span class="sidebar-user-info">
        <span class="sidebar-user-name">${sessionScope.user.fullName}</span>
        <span class="sidebar-user-role">Member</span>
    </span>
</a>

</aside>