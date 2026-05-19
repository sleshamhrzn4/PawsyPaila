<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pawsy Paila - Home</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
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
            <img src="images/home_hero.png" alt="home hero">
        </div>
    </section>

    <section class="section-container">
        <div class="section-header">
            <h2>OUR PETS</h2>
            <a href="pets" class="view-more">View More</a>
        </div>
        <div class="pets-grid">
            <%-- PETS forEach — replace your existing one --%>
            <c:forEach var="pet" items="${petList}">
            <div class="pet-card">
               <div class="pet-circle">
               <img src="${pageContext.request.contextPath}/images/${pet.petImage}" alt="${pet.petName}">
               </div>
        <h3>${pet.petName}</h3>
        <p>${pet.petDesc}</p>
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
                <%-- PRODUCTS forEach — replace your existing one --%>
			<c:forEach var="item" items="${itemList}">
	   			<div class="item-card">
	       		 	<div class="item-img-box">
	            		<img src="${pageContext.request.contextPath}/images/${item.productImage}" alt="${item.productName}">
	        		</div>
	        		<div class="item-info">
	        		<p>${item.productName}</p>
	        		</div>
	    		</div>
			</c:forEach>
            </div>
        </div>
    </section>

</body>
</html>