<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <aside class="sidebar">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/images/adminDashboard/logo.png" class="logo" alt="logo" style="width:150px; height:auto; ">
        </div>
        <nav class="sidebar-nav">
           <a href="${pageContext.request.contextPath}/adminDashboard" class="nav-item">
        <img src="${pageContext.request.contextPath}/images/adminDashboard/dash.png" 
             alt="dashboard" style="width: 30px; height: auto;">Dashboard</a>
            <a href="${pageContext.request.contextPath}/adminPets" class="nav-item">
        <img src="${pageContext.request.contextPath}/images/adminDashboard/pet.png" 
             alt="pets" style="width: 30px; height: auto;">
        Pets
    </a>

    <a href="${pageContext.request.contextPath}/adminProduct" class="nav-item">
        <img src="${pageContext.request.contextPath}/images/adminDashboard/product.png" 
             alt="items" style="width: 30px; height: auto;">
        Products
    </a>

    <a href="${pageContext.request.contextPath}/manageUsers" class="nav-item">
        <img src="${pageContext.request.contextPath}/images/adminDashboard/application.png" 
             alt="users" style="width: 30px; height: auto;">
        Users
    </a>
    
    <a href="${pageContext.request.contextPath}/adminAdoption" class="nav-item">
    <img src="${pageContext.request.contextPath}/images/adminDashboard/adoption.png"
         alt="adoption" style="width: 30px; height: auto;">
    Adoption
    </a>

   <a href="${pageContext.request.contextPath}/adminDonation" class="nav-item">
    <img src="${pageContext.request.contextPath}/images/adminDashboard/donation.png"
         alt="donation" style="width: 30px; height: auto;">
    Donation
   </a>
        </nav>
        <div class="sidebar-user">
            <span class="user-icon"></span>
            <span>${sessionScope.userName}</span>
        </div>
    </aside>
  
