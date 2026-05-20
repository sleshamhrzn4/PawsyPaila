<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Products</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminProduct.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;600;700;800&family=Nunito:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="flex">

    <%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>

    <div class="main-content">

        <div class="welcome-header">
            <i class="fa-solid fa-paw"></i>
            <h1>Welcome Back, ${sessionScope.username}!</h1>
            <a href="${pageContext.request.contextPath}/adminDashboard" class="home-btn">Home</a>
        </div>

        <div class="card">
            <div class="card-header">
                <h2>Manage Products</h2>
                <a href="${pageContext.request.contextPath}/addProduct" class="btn-add">
                    <i class="fa-solid fa-plus"></i> Add Product
                </a>
            </div>

            <c:if test="${not empty message}">
                <div class="alert alert-success">${message}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-error">${error}</div>
            </c:if>

            <c:choose>
                <c:when test="${empty products}">
                    <div class="no-data">
                        <h3>No products found</h3>
                        <p>Click "Add Product" to get started.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <!-- Scrollable wrapper -->
                    <div class="table-wrapper">
                        <table class="product-table">
                            <thead>
                                <tr>
                                    <th>Item Name</th>
                                    <th>Price</th>
                                    <th>Description</th>
                                    
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="product" items="${products}">
                                    <tr>
                                        <td>
                                            <div class="name-cell">
                                                <img class="product-img"
                                                     src="${pageContext.request.contextPath}/getImage?name=${product.productImage}&type=products"
                                                     alt="${product.productName}" />
                                                <span class="product-name">${product.productName}</span>
                                            </div>
                                        </td>
                                        <td class="price-cell">Rs. ${product.productPrice}</td>
                                        <td class="desc-cell">${product.productDescription}</td>
                                        <td class="action-cell">
                                            <a href="${pageContext.request.contextPath}/editProduct?productId=${product.productId}" class="btn-edit">
                                                <i class="fa-solid fa-pen"></i> Edit
                                            </a>
                                            <a href="${pageContext.request.contextPath}/adminProduct?action=delete&productId=${product.productId}"
											   class="btn-delete"
											   onclick="return confirm('Delete this product?')">
											    <i class="fa-solid fa-trash"></i> Delete
											</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</div>
</body>
</html>