<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Our Pets - Pawsy Paila</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pets.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700;800&display=swap" rel="stylesheet">
</head>
<body>

<%@ include file="/WEB-INF/pages/public/header.jsp" %>

<!-- Page title -->
<header class="pets-header">
    <h1>Our Pets</h1>
</header>

<!-- Search + Filter controls -->
<div class="pets-controls">

    <form class="pets-search" method="get" action="${pageContext.request.contextPath}/pets" id="searchForm">
        <label for="searchInput">Search:</label>
        <input
            id="searchInput"
            type="text"
            name="search"
            placeholder="Search by name"
            value="${fn:escapeXml(search)}"
            oninput="document.getElementById('searchForm').submit()">
    </form>

    <div class="pets-filter">
        <span class="filter-label">Filter By:</span>
        <div class="filter-options">
            <a href="${pageContext.request.contextPath}/pets"
               class="filter-btn ${empty filter && empty letter ? 'active' : ''}">
                <span class="filter-dot"></span> All
            </a>
            <a href="${pageContext.request.contextPath}/pets?filter=dog"
               class="filter-btn ${filter == 'dog' ? 'active' : ''}">
                <span class="filter-dot"></span> Dogs
            </a>
            <a href="${pageContext.request.contextPath}/pets?filter=cat"
               class="filter-btn ${filter == 'cat' ? 'active' : ''}">
                <span class="filter-dot"></span> Cats
            </a>
        </div>
    </div>

   
   
</div>

<!-- Pet listing -->
<c:choose>

    <c:when test="${empty pets}">
        <div class="empty-state">
            <span class="icon"></span>
            <h2>No pets found</h2>
            <p>
                <c:choose>
                    <c:when test="${not empty search}">
                        No pets match "${fn:escapeXml(search)}". Try a different name!
                    </c:when>
                    <c:when test="${not empty letter}">
                        No pets found with names starting with "${fn:escapeXml(letter)}". Try another letter!
                    </c:when>
                    <c:otherwise>
                        No pets listed right now. Check back soon!
                    </c:otherwise>
                </c:choose>
            </p>
            <a href="${pageContext.request.contextPath}/pets">View all pets →</a>
        </div>
    </c:when>

    <c:otherwise>
        <div class="pets-grid">
            <c:forEach var="pet" items="${pets}">
                <a href="${pageContext.request.contextPath}/petDetail?petId=${pet.petId}" class="pet-card-link">
                    <div class="pet-card">
                        <div class="pet-card__img-wrap">
                            <c:choose>
                                <c:when test="${not empty pet.petImage}">
                                    <img src="${pageContext.request.contextPath}/getImage?name=${fn:escapeXml(pet.petImage)}&amp;type=pet"
                                         alt="${fn:escapeXml(pet.petName)}">
                                </c:when>
                                <c:otherwise>
                                    <span class="pet-placeholder">
                                        <c:choose>
                                            <c:when test="${fn:toLowerCase(pet.petType) == 'cat'}"></c:when>
                                            <c:otherwise></c:otherwise>
                                        </c:choose>
                                    </span>
                                </c:otherwise>
                            </c:choose>
                            <span class="pet-badge">${fn:escapeXml(pet.petType)}</span>
                        </div>

                        <div class="pet-card__body">
                            <h2 class="pet-card__name">${fn:escapeXml(pet.petName)}</h2>
                            <p class="pet-card__desc">
                                <c:choose>
                                    <c:when test="${not empty pet.petDesc}">
                                        ${fn:substring(pet.petDesc, 0, 80)}${fn:length(pet.petDesc) > 80 ? '...' : ''}
                                    </c:when>
                                    <c:otherwise>
                                        Hey there, I'm ${fn:escapeXml(pet.petName)} and I'm looking for a loving home!
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </c:otherwise>

</c:choose>

<%@ include file="/WEB-INF/pages/public/footer.jsp" %>

</body>
</html>