<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<style>
body {
    margin: 0;
    font-family: Poppins, sans-serif;
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
    color: #222222;
    text-decoration: none;
    font-size: 20px;
    padding: 8px 12px;
    transition: font-weight 0.2s;
}

.nav-links a:hover {
    font-weight: bold;
}

.profile-icon {
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
    <div class="profile-icon">
        <a href="${pageContext.request.contextPath}/userprofile">
            <img src="${pageContext.request.contextPath}/images/nav/profile.png" alt="Pfp" class="profile-icon">
        </a>
    </div>
</header>