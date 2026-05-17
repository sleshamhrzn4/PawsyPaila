<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pawsy Paila - Home</title>
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>

    <jsp:include page="header.jsp" />

    <section class="hero">
        <div class="hero-content">
            <h1>Where Every Paw Finds a Home</h1>
            <p>Adopt, Care and Make a Difference</p>
            <a href="pets" class="btn-main">Find Your Companion</a>
        </div>
        <div class="hero-image">
            <img src="images/hero-pets.png" alt="Happy Pets">
        </div>
    </section>

    <section class="section-container">
        <div class="section-header">
            <h2>OUR PETS</h2>
            <a href="pets" class="view-more">View More</a>
        </div>
        <div class="pets-grid">
            <c:forEach var="pet" items="${petList}">
                <div class="pet-card">
                    <div class="pet-circle">
                        <img src="${pet.imageUrl}" alt="${pet.name}">
                    </div>
                    <h3>${pet.name}</h3>
                    <p>${pet.description}</p>
                </div>
            </c:forEach>
        </div>
    </section>

    <section class="section-container shop-bg">
        <div class="shop-layout">
            <div class="shop-sidebar">
                <h2>ITEM SHOP</h2>
                <a href="shop" class="btn-shop">Shop Now</a>
            </div>
            <div class="items-grid">
                <c:forEach var="item" items="${itemList}">
                    <div class="item-card">
                        <div class="item-img-box">
                            <img src="${item.imageUrl}" alt="${item.name}">
                        </div>
                        <div class="item-info">
                            <p>${item.name}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

</body>
</html>