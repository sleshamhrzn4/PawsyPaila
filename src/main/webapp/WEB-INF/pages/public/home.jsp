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

    <%-- hero --%>
    <section class="hero">
        <div class="hero-content">
            <h1>Where Every Paw Finds a Home</h1>
            <p>Adopt, Care and Make a Difference</p>
            <a href="pets" class="btn-main">Find Your Companion</a>
        </div>
        <div class="hero-image">
            <img src="${pageContext.request.contextPath}/images/home/home_hero.png" alt="home hero">
        </div>
    </section>

    <%-- about us --%>
    <section class="about-section">
        <h2>About</h2>
        <%-- Logo sits above the three-column row --%>
        <div class="about-logo-top">
            <img src="${pageContext.request.contextPath}/images/nav/logo_teal.png" alt="Pawsy Paila Logo">
        </div>
        <div class="about-content">
            <div class="about-bubble-left">
                At Pawsy Paila, we believe that every pet deserves a loving family
            </div>
            <%-- Center image: swap src to any about/pet image you prefer --%>
            <div class="about-center">
                <img src="${pageContext.request.contextPath}/images/home/about.png" alt="dog">
            </div>
            <div class="about-bubble-right">
                Our platform was created with a mission to connect animals in need
                with caring people, while also making pet care easy, accessible, and enjoyable.
            </div>
        </div>
    </section>

    <%-- pets --%>
    <section class="section-container">
        <div class="section-header">
            <h2>OUR PETS</h2>
            <a href="${pageContext.request.contextPath}/pets" class="view-more">View More</a>
        </div>
        <div class="pets-grid">
            <c:choose>
                <c:when test="${not empty petList}">
                    <c:forEach var="pet" items="${petList}">
                        <div class="pet-card">
                            <div class="pet-circle">
                                <img src="${pageContext.request.contextPath}/getImage?name=${pet.petImage}&type=pets"
                                     alt="${pet.petName}">
                            </div>
                            <h3>${pet.petName}</h3>
                            <p class="clamp-3">${pet.petDesc}</p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No pets available at the moment.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </section>

    <%-- products --%>
    <section class="section-container shop-bg">
        <div class="shop-layout">
            <div class="shop-sidebar">
                <h2>ITEM SHOP</h2>
                <a href="${pageContext.request.contextPath}/products" class="btn-shop">Shop Now</a>
            </div>
            <div class="items-grid">
                <c:choose>
                    <c:when test="${not empty itemList}">
                        <c:forEach var="item" items="${itemList}">
                            <div class="item-card">
                                <div class="item-img-box">
                                    <img src="${pageContext.request.contextPath}/getImage?name=${item.productImage}&type=products"
                                         alt="${item.productName}">
                                </div>
                                <div class="item-info">
                                    <p>${item.productName}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>No products available at the moment.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </section>

    <%-- support --%>
    <section class="support-section">
        <h2>Support Us</h2>
        <div class="support-image">
            <img src="${pageContext.request.contextPath}/images/home/support.png" alt="Support Us">
        </div>
        <a href="${pageContext.request.contextPath}/donate" class="btn-main">Donate Now</a>
        <h3>Every Paw Counts</h3>
        <p>Your donation transforms lives. 100% goes directly to shelter care, vet bills, and adoption programs, with full transparency on every rescue.</p>
    </section>

    <%-- footer --%>
    <jsp:include page="footer.jsp" />

</body>
</html>
