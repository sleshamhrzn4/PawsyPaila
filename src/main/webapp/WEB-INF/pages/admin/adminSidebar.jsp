<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <aside class="sidebar">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/images/adminDashboard/logo.png" class="logo" alt="logo" style="width:150px; height:auto; ">
        </div>
        <nav class="sidebar-nav">
            <a href="${pageContext.request.contextPath}/adminDashboard" class="nav-item">
                <i class="fa-solid fa-table-columns nav-icon" style="font-size: 20px; width: 30px;"></i>Dashboard
            </a>
            <a href="${pageContext.request.contextPath}/adminPets" class="nav-item">
                <i class="fa-solid fa-paw nav-icon" style="font-size: 20px; width: 30px;"></i>Pets
            </a>

            <a href="${pageContext.request.contextPath}/adminProduct" class="nav-item">
                <i class="fa-solid fa-cart-shopping nav-icon" style="font-size: 20px; width: 30px;"></i>Products
            </a>

            <a href="${pageContext.request.contextPath}/manageUsers" class="nav-item">
                <i class="fa-regular fa-clipboard nav-icon" style="font-size: 20px; width: 30px;"></i>Users
            </a>
            
            <a href="${pageContext.request.contextPath}/adminAdoption" class="nav-item">
                <i class="fa-solid fa-hand-holding-heart nav-icon" style="font-size: 20px; width: 30px;"></i>Adoption
            </a>

            <a href="${pageContext.request.contextPath}/adminDonation" class="nav-item">
                <i class="fa-solid fa-heart nav-icon" style="font-size: 20px; width: 30px;"></i>Donation
            </a>
        </nav>
        <div class="sidebar-user">
            <span class="user-icon"><i class="fa-solid fa-circle-user" style="font-size: 24px; color: #17a99e;"></i></span>
            <span>${sessionScope.userName}</span>
        </div>
    </aside>