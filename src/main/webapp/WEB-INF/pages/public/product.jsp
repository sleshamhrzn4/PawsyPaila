<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
	
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Products - Pawsy Paila</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700;800&family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>


<%@ include file="headerProduct.jsp" %>

  
<div class="products-page">
    <h1 class="page-title">All Products</h1>
    
    <c:if test="${not empty sessionScope.message}">
		    <div class="alert alert-success">
		        ${sessionScope.message}
		    </div>
		   <c:remove var="message" scope="session"/>
		</c:if>


		<c:if test="${not empty sessionScope.error}">
		    <div class="alert alert-error">
		        ${sessionScope.error}
		        <a href="${pageContext.request.contextPath}/login">Login here</a>
		    </div>
		    <c:remove var="error" scope="session"/>
		</c:if>

    <div class="page-layout">

       
        <aside class="sidebar">
            <form method="get" action="${pageContext.request.contextPath}/products" id="filterForm">

                <div class="filter-group">
                    <h4>Sort By</h4>
                    <select name="sortBy" onchange="document.getElementById('filterForm').submit()">
                        <option value="default"   ${sortBy == 'default'   ? 'selected' : ''}>Default</option>
                        <option value="priceLow"  ${sortBy == 'priceLow'  ? 'selected' : ''}>Price: Low to High</option>
                        <option value="priceHigh" ${sortBy == 'priceHigh' ? 'selected' : ''}>Price: High to Low</option>
                        <option value="nameAZ"    ${sortBy == 'nameAZ'    ? 'selected' : ''}>Name A–Z</option>
                    </select>
                </div>

				
                <div class="filter-group">
                    <h4>Price</h4>
                    <div class="price-range">
                        <input type="number" name="minPrice" placeholder="Min"
                               value="${param.minPrice}" min="0">
                        <span>–</span>
                        <input type="number" name="maxPrice" placeholder="Max"
                               value="${param.maxPrice}" min="0">
                    </div>
                    <button type="submit" class="apply-btn">Apply</button>
                </div>

            </form>
        </aside>

      
        <div class="products-grid">
            <c:forEach var="product" items="${products}">
                <div class="product-card">
                
            
                    
                    <div class="product-image">
                        <img src="${pageContext.request.contextPath}/getImage?name=${product.productImage}&type=products"
                             alt="${product.productName}"
                             onerror="this.src='${pageContext.request.contextPath}/getImage?name=default.png&type=products'">
                    </div>
                    <div class="product-info">
                        <h3>${product.productName}</h3>
                        <p class="price">Rs. ${product.productPrice}</p>
                       <form method="post" action="${pageContext.request.contextPath}/cart">
							    <input type="hidden" name="action" value="add">
							    <input type="hidden" name="productId" value="${product.productId}">
							    <input type="hidden" name="quantity" value="1">
							    	<button type="submit" class="add-to-cart">
							        	<i class="fa-solid fa-cart-plus"></i> Add to Cart
							    	</button>
							</form>
                    </div>
                </div>
            </c:forEach>

            <c:if test="${empty products}">
                <div class="empty-state">
                    <i class="fa-solid fa-box-open"></i>
                    <p>No products available yet.</p>
                </div>
            </c:if>
        </div>

    </div>
</div>


   <%@ include file="/WEB-INF/pages/public/footer.jsp" %>

</body>
</html>
