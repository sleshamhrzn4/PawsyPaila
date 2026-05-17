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

        <div class="page-header">
            <i class="fa-solid fa-paw"></i>
            <h1>Welcome Back, ${sessionScope.username}!</h1>
        </div>

        <c:if test="${not empty sessionScope.message}">
            <div class="alert alert-success">${sessionScope.message}</div>
            <c:remove var="message" scope="session"/>
        </c:if>

        <c:if test="${not empty sessionScope.error}">
            <div class="alert alert-error">${sessionScope.error}</div>
            <c:remove var="error" scope="session"/>
        </c:if>

        <div class="card">
            <div class="card-header">
                <h2>Manage Products</h2>
                <a href="${pageContext.request.contextPath}/addProduct" class="btn-add">
                    <i class="fa-solid fa-plus"></i> Add Product
                </a>
            </div>

            <c:choose>
                <c:when test="${empty products}">
                    <div class="no-pets">No products found. Click "Add Product" to get started.</div>
                </c:when>
                <c:otherwise>
                    <table class="pets-table">
                        <thead>
                            <tr>
                                <th>Item Name</th>
                                <th>Price</th>
                                <th>Description</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="product" items="${products}">
                                <tr>
                                    <td>
                                        <div class="pet-name-cell">
                                            <img src="${pageContext.request.contextPath}/images/product/${product.productImage}"
                                                 alt="${product.productName}"
                                                 onerror="this.src='${pageContext.request.contextPath}/images/product/default.jpg'"
                                                 style="width:40px; height:40px; border-radius:8px; object-fit:cover;">
                                            ${product.productName}
                                        </div>
                                    </td>
                                    <td>Rs. ${product.productPrice}</td>
                                    <td>${product.productDescription}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/editProduct?productId=${product.productId}" class="btn-edit">
    									<i class="fa-solid fa-pen"></i> Edit
    									
										</a>
										
										
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>