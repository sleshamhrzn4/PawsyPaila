<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Products</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminDashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminProduct.css">
    
    <link href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;600;700;800&family=Nunito:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
</head>
<body>

    <div class="admin-container">
        
        <!-- Sidebar -->
       <%@include file = "/WEB-INF/pages/admin/adminSidebar.jsp"%>

        <!-- Main Content -->
        <div class="main-content">
            
            <!-- Top Header -->
            <div class="top-header">
                <h1>Welcome Back, Yunisha!</h1>
                <button class="home-btn" onclick="window.location.href='admin/adminDashboard'">Home</button>
            </div>

            <!-- Manage Products Section -->
            <div class="manage-section">
                <div class="section-header">
                    <h2>Manage Products</h2>
                    <a href="${pageContext.request.contextPath}/addProduct" class="add-product-btn">
                        <i class="fas fa-plus"></i> Add Product
                    </a>
                </div>

                <!-- Products Table -->
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Item Name</th>
                                <th>Price</th>
                                <th>Description</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Data will come from database later -->
                            <c:forEach var="product" items="${products}">
                                <tr>
                                    <td>${product.productName}</td>
                                    <td>Rs. ${product.productPrice}</td>
                                    <td>${product.productDescription}</td>
                                    <td>
                                        <a href="#" class="edit-btn">Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>

                            <!-- Empty State -->
                            <c:if test="${empty products}">
                                <tr>
                                    <td colspan="4" class="empty-row">
                                        No products available yet.<br>
                                        Click "Add Product" to add new items.
                                    </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</body>
</html>