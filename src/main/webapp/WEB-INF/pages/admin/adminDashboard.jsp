<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

    <title>Admin Dashboard - Pawsy Paila</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminDashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css">
    <link href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;600;700;800&family=Nunito:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body>


<div class="dashboard-wrapper">

    <%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>

    
   <main class="main-content">
      
        <div class="welcome-header">
            <i class="fa-solid fa-paw"></i>
            <h1>Welcome Back, ${sessionScope.username}!</h1>
        </div>

       
        <div class="stats-grid">
            <div class="stat-card">
                <p class="stat-label">Pets Listed</p>
                <p class="stat-value">${totalPets}</p>
            </div>
            <div class="stat-card">
                <p class="stat-label">Adopt Requests</p>
                <p class="stat-value">${totalRequests}</p>
            </div>
            <div class="stat-card">
                <p class="stat-label">Products Listed</p>
                <p class="stat-value">${totalProducts}</p>
            </div>
          </div>

     
        <div class="top-row">

           
            <div class="table-card">
            
                <h3>Adoption Requests</h3>
                <a href="${pageContext.request.contextPath}/adminAdoption">
                    </a>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Status</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ar" items="${adoptionList}">
                            <tr>
                                <td><span class="user-icon-sm">👤</span>${ar.fullName}</td>
                                <td>${ar.adoptionStatus}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/adminAdoption">
                                        <button class="btn-view">View</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            
            <div class="table-card">
                <div class="manage-header">
                    <h3>Manage Pets</h3>
                    <a href="${pageContext.request.contextPath}/adminPets">
                        <button class="btn-add">Add Pet</button>
                    </a>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>Pet</th>
                            <th>Age</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pet" items="${petList}">
                            <tr>
                                <td><span class="user-icon-sm">👤</span>${pet.petName}</td>
                                <td>${pet.petAge}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/updatePet?id=${pet.petId}">
                                        <button class="btn-edit">Edit</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
        

       
        <div class="table-card">
            <div class="manage-header">
                <h3>Manage Products</h3>
                <a href="${pageContext.request.contextPath}/adminProduct">
                    <button class="btn-add">Add Product</button>
                </a>
            </div>
            <table class="product-table">
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Price</th>
                        <th>Description</th>
                       
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${productList}">
                        <tr>
                            <td>
                                <div class="name-cell">
                                    <img class="product-thumb"
                                        src="${pageContext.request.contextPath}/getImage?name=${product.productImage}&type=products"
                                                     alt="${product.productName}" />
                                                <span class="product-name">${product.productName}</span>
                                            </div>
                            </td>
                            </td>
                                        <td class="price-cell">Rs. ${product.productPrice}</td>
                                        <td class="desc-cell">${product.productDescription}</td>
                                        <td class="action-cell">
                                            <a href="${pageContext.request.contextPath}/editProduct?productId=${product.productId}" class="btn-edit">
                                                <i class="fa-solid fa-pen"></i> Edit
                                            </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </main>
</div>
</body>
</html>