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

  

<!-- ── PAGE ── -->
<div class="products-page">
    <h1 class="page-title">All Products</h1>

    <div class="page-layout">

        <!-- LEFT SIDEBAR FILTERS -->
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
                    <h4>Filter By</h4>
                    <select name="filterBy" onchange="document.getElementById('filterForm').submit()">
                        <option value="All"       ${filterBy == 'All'       ? 'selected' : ''}>All Products</option>
                        <option value="Food"      ${filterBy == 'Food'      ? 'selected' : ''}>Food</option>
                        <option value="Toy"       ${filterBy == 'Toy'       ? 'selected' : ''}>Toys</option>
                        <option value="Accessory" ${filterBy == 'Accessory' ? 'selected' : ''}>Accessories</option>
                        <option value="Medicine"  ${filterBy == 'Medicine'  ? 'selected' : ''}>Medicine</option>
                        <option value="Grooming"  ${filterBy == 'Grooming'  ? 'selected' : ''}>Grooming</option>
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

        <!-- PRODUCTS GRID -->
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
                        <button class="add-to-cart"
                                onclick="addToCart(${product.productId}, '${product.productName}')">
                            <i class="fa-solid fa-cart-plus"></i> Add to Cart
                        </button>
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



<script>
function addToCart(productId, productName) {
    fetch('${pageContext.request.contextPath}/cart', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: 'action=add&productId=' + productId + '&quantity=1'
    })
    .then(res => {
        if (res.ok) {
            showToast(productName + ' added to cart!');
            updateCartCount();
        } else {
            showToast('Please log in to add items to cart.', true);
        }
    })
    .catch(() => showToast('Something went wrong.', true));
}

function updateCartCount() {
    fetch('${pageContext.request.contextPath}/cart?action=count')
    .then(res => res.text())
    .then(count => {
        const badge = document.getElementById('cart-count');
        if (badge) badge.textContent = count;
    }).catch(() => {});
}

function showToast(msg, isError = false) {
    const toast = document.createElement('div');
    toast.className = 'toast' + (isError ? ' toast-error' : '');
    toast.textContent = msg;
    document.body.appendChild(toast);
    setTimeout(() => toast.classList.add('show'), 10);
    setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => toast.remove(), 400);
    }, 2500);
}

updateCartCount();
</script>
</body>
</html>
