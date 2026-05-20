<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart - Pawsy Paila</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>

<%@ include file="header.jsp" %>

<div class="cart-page">
    <div class="cart-card">

        <!-- Cart Icon -->
        <div class="cart-header">
            <i class="fa-solid fa-cart-shopping cart-icon"></i>
        </div>

        <c:choose>
            <c:when test="${empty cart}">
                <div class="empty-cart">
                    <p>Your cart is empty.</p>
                    <a href="${pageContext.request.contextPath}/products" class="btn-shop">
                        Browse Products
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="cart-items">
                    <c:forEach var="entry" items="${cart}">
                        <div class="cart-item">
                            <img src="${pageContext.request.contextPath}/getImage?name=${entry.value.product.productImage}&type=products"
                                 alt="${entry.value.product.productName}"
                                 class="item-img">
                            <div class="item-details">
                                <p class="item-name">${entry.value.product.productName}</p>
                                <p class="item-price">Rs. ${entry.value.product.productPrice}</p>
                            </div>
                            <div class="item-actions">
                                <a href="${pageContext.request.contextPath}/cart?action=remove&productId=${entry.key}"
                                   class="remove-btn"
                                   title="Remove">
                                    <i class="fa-solid fa-trash"></i>
                                </a>
                            </div>
                        </div>
                        <div class="divider"></div>
                    </c:forEach>
                </div>

                <!-- Total -->
                <div class="cart-total">
                    <span class="total-label">Total</span>
                    <span class="total-amount">Rs. ${total}</span>
                </div>

                <!-- Checkout -->
                <div class="cart-footer">
                    <a href="${pageContext.request.contextPath}/checkout" class="btn-checkout">
                        Checkout
                    </a>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>