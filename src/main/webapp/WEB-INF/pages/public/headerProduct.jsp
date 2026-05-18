<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Navbar</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<style>
body {
    margin: 0;
    font-family: Poppins, sans-serif;
}

/* Navbar */
.navbar {
    display: flex;
    align-items: center;
    margin-left: 50px;
    margin-right: 50px;
    justify-content: space-between;
    background-color: transparent;
    padding: 12px 24px;
    position: relative;
}

/* Logo */
.logo {
    height: 80px;
    width: 110px;
}

/* Navigation links */
.nav-links {
    display: flex;
    gap: 20px;
}

.nav-links a {
    color: #222222;
    text-decoration: none;
    font-size: 20px;
    padding: 8px 12px;
    transition: font-weight 0.2s;
}

/* Hover effect */
.nav-links a:hover {
    font-weight: bold;
}

.profile-icon {
    width: 54px;
    height: 54px;
}

/* Cart icon */
.cart-link {
    position: relative;
    color: #222;
    font-size: 22px;
    text-decoration: none;
    margin-right: 16px;
    transition: color 0.2s;
}

.cart-link:hover {
    color: #14b8a6;
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

.nav-right {
    display: flex;
    align-items: center;
}
</style>
</head>
<body>
<header class="navbar">
    <a href="${pageContext.request.contextPath}/home" class="logo">
        <img src="${pageContext.request.contextPath}/images/nav/logo_teal.png" alt="Logo" class="logo">
    </a>
    <!-- nav bar links -->
    <nav class="nav-links">
        <a href="${pageContext.request.contextPath}/pets">Pets</a>
        <a href="${pageContext.request.contextPath}/products">Products</a>
        <a href="${pageContext.request.contextPath}/about">About</a>
        <a href="${pageContext.request.contextPath}/donate">Donate</a>
        <a href="${pageContext.request.contextPath}/contact">Contact</a>
    </nav>
    <div class="nav-right">
        <!-- Cart icon -->
        <a href="${pageContext.request.contextPath}/cart" class="cart-link">
            <i class="fa-solid fa-cart-shopping"></i>
            <span class="cart-badge" id="cart-count">0</span>
        </a>
        <!-- Profile -->
        <div class="profile-icon">
            <a href="${pageContext.request.contextPath}/profile">
                <img src="${pageContext.request.contextPath}/images/nav/profile.png" alt="Pfp" class="profile-icon">
            </a>
        </div>
    </div>
</header>
</body>
</html>
