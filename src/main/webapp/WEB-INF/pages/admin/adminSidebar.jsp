<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminDashboard.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css">
 <link href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;600;700;800&family=Nunito:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body>


    
    <aside class="sidebar">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/images/adminDashboard/logo.png" class="logo" alt="logo" style="width:150px; height:auto; ">
        </div>
        <nav class="sidebar-nav">
           <a href="${pageContext.request.contextPath}/AdminDashboard" class="nav-item">
        <img src="${pageContext.request.contextPath}/images/adminDashboard/dash.png" 
             alt="dashboard" style="width: 30px; height: auto;">
             Dashboard
        </a>
            <a href="${pageContext.request.contextPath}/AdminPets" class="nav-item">
        <img src="${pageContext.request.contextPath}/images/adminDashboard/pet.png" 
             alt="pets" style="width: 30px; height: auto;">
        Pets
    </a>

    <a href="${pageContext.request.contextPath}/AdminItems" class="nav-item">
        <img src="${pageContext.request.contextPath}/images/adminDashboard/product.png" 
             alt="items" style="width: 30px; height: auto;">
        Items
    </a>

    <a href="${pageContext.request.contextPath}/AdminApplications" class="nav-item">
        <img src="${pageContext.request.contextPath}/images/adminDashboard/application.png" 
             alt="applications" style="width: 30px; height: auto;">
        Applications
    </a>

    <a href="${pageContext.request.contextPath}/AdminReports" class="nav-item">
        <img src="${pageContext.request.contextPath}/images/adminDashboard/ 
             alt="reports" style="width: 30px; height: auto;">
        Reports
    </a>
        </nav>
        <div class="sidebar-user">
            <span class="user-icon">👤</span>
            <span>${sessionScope.userName}</span>
        </div>
    </aside>
  
</body>
</html>