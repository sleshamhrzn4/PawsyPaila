<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Products</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
   
</head>
<body>

    <%@ include file="header.jsp" %>

    <div class="products-page">
        <h1 class="page-title">All Products</h1>

        <!-- Filters -->
        <div class="filters">
            <div class="sort-filter">
                <label>Sort By:</label>
                <select>
                    <option>Default</option>
                    <option>Price: Low to High</option>
                    <option>Price: High to Low</option>
                    <option>Name A-Z</option>
                </select>
            </div>
            <div class="sort-filter">
                <label>Filter By:</label>
                <select>
                    <option>All Products</option>
                    <option>Clothing</option>
                    <option>Food</option>
                    <option>Toys</option>
                </select>
            </div>
        </div>

        <!-- Products Grid -->
        <div class="products-grid">
            
            <c:forEach var="product" items="${products}">
                <div class="product-card">
                    <div class="product-image">
                        <img src="${pageContext.request.contextPath}/images/product/${product.image}" 
                             alt="${product.name}"
                             onerror="this.src='${pageContext.request.contextPath}/images/product/default.jpg'">
                    </div>
                    <div class="product-info">
                        <h3>${product.name}</h3>
                        <p class="price">Rs. ${product.price}</p>
                        <button class="add-to-cart">Add to Cart</button>
                    </div>
                </div>
            </c:forEach>

            <!-- Empty State (when no products) -->
            <c:if test="${empty products}">
                <div class="empty-state">
                    <p>No products available yet.</p>
                </div>
            </c:if>

        </div>
    </div>

   

</body>
</html>