<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<style>
body {
    margin: 0;
    font-family: Poppins, sans-serif;
    background-color: #FDF8F0;
}

.navbar {
    display: flex;
    align-items: center;
    margin-left: 50px;
    margin-right: 50px;
    justify-content: space-between;
    background-color: transparent;
    padding: 12px 24px;
    position: relative;
    border-bottom: none;
}

.logo {
    height: 80px;
    width: 110px;
}

.nav-links {
    display: flex;
    gap: 20px;
}

.nav-links a {
    color:  #222222;
    text-decoration: none;
    font-size: 20px;
    padding: 8px 12px;
    transition: font-weight 0.2s;
}

.nav-links a:hover {
    font-weight: bold;
       color:  #222222;
}

.nav-right {
    display: flex;
    align-items: center;
    gap: 25px;
}

.cart-link {
    position: relative;
    color: #222222;
    font-size: 22px;
    text-decoration: none;
    transition: color 0.2s;
}

.cart-link:hover {
    font-weight: bold;
    color: #222222;
}

.cart-badge {
    position: absolute;
    top: -8px;
    right: -10px;
    background: #14b8a6;
    color: #fff;
    font-size: 11px;
    font-weight: 700;
    width: 18px;
    height: 18px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
}

.profile-icon {
    width: 54px;
    height: 54px;
}

.profile-icon img {
    width: 54px;
    height: 54px;
}
</style>
<header class="navbar">
    <a href="${pageContext.request.contextPath}/home" class="logo">
        <img src="${pageContext.request.contextPath}/images/nav/logo_teal.png" alt="Logo" class="logo">
    </a>
    <nav class="nav-links">
        <a href="${pageContext.request.contextPath}/pets">Pets</a>
        <a href="${pageContext.request.contextPath}/products">Products</a>
        <a href="${pageContext.request.contextPath}/aboutus">About</a>
        <a href="${pageContext.request.contextPath}/donate">Donate</a>
        <a href="${pageContext.request.contextPath}/contact">Contact</a>
    </nav>
     <div class="nav-right">
        <a href="${pageContext.request.contextPath}/cart" class="cart-link">
            <i class="fa-solid fa-cart-shopping"></i>
            <span class="cart-badge" id="cart-count">0</span>
        </a>
        <div class="profile-icon">
            <a href="${pageContext.request.contextPath}/profile">
                <img src="${pageContext.request.contextPath}/images/nav/profile.png" alt="Pfp" class="profile-icon">
            </a>
        </div>
    </div>
</header>