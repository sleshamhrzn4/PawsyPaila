<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    
    <c:if test="${not empty successMessage}">
            <div class="alert-success" style="background-color: #d4edda; color: #155724; padding: 15px; border-radius: 5px; margin-bottom: 20px; text-align: center; font-family: 'Poppins', sans-serif; font-weight: 500; border: 1px solid #c3e6cb;">
                <i class="fa-solid fa-circle-check" style="margin-right: 5px;"></i> ${successMessage}
            </div>
        </c:if>

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
                            <div class="quantity-control">
                                <a href="${pageContext.request.contextPath}/cart?action=decrease&productId=${entry.key}"
                                   class="qty-btn">−</a>
                                <span class="qty-num">${entry.value.quantity}</span>
                                <a href="${pageContext.request.contextPath}/cart?action=increase&productId=${entry.key}"
                                   class="qty-btn">+</a>
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
                    <form action="${pageContext.request.contextPath}/cart" method="POST" style="width: 100%;">
                        <input type="hidden" name="action" value="checkout">
                        <button type="submit" class="btn-checkout" style="width: 100%; display: block; text-align: center; border: none; cursor: pointer; text-decoration: none;">
                            Checkout
                        </button>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>