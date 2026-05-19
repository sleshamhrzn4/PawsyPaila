<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Footer</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<footer class="site-footer">
    <div class="footer-top">

        <!-- Navigation links -->
        <nav class="footer-nav">
            <a href="${pageContext.request.contextPath}/pets">Adopt</a>
            <a href="${pageContext.request.contextPath}/products">Shop</a>
            <a href="${pageContext.request.contextPath}/donate">Support Us</a>
            <a href="${pageContext.request.contextPath}/about">About Us</a>
        </nav>

        <!-- Center: Logo, tagline, socials -->
        <div class="footer-center">
            <a href="${pageContext.request.contextPath}/home">
                <img
                    src="${pageContext.request.contextPath}/images/footer/logo_black.png"
                    alt="Pawsy Paila Logo"
                    class="footer-logo"
                >
            </a>
            <p class="footer-tagline">
                Connecting stray animals with loving homes and<br>
                building a compassionate community across Nepal
            </p>
            <div class="footer-socials">
                <a href="#" aria-label="Facebook"><i class="fab fa-facebook-f"></i></a>
                <a href="#" aria-label="Instagram"><i class="fab fa-instagram"></i></a>
                <a href="#" aria-label="TikTok"><i class="fab fa-tiktok"></i></a>
                <a href="#" aria-label="Twitter / X"><i class="fab fa-x-twitter"></i></a>
                <a href="#" aria-label="WhatsApp"><i class="fab fa-whatsapp"></i></a>
                <a href="mailto:info@pawsypaila.com.np" aria-label="Email"><i class="far fa-envelope"></i></a>
            </div>
        </div>

        <!-- Contact  -->
        <div class="footer-contact">
            <a href="mailto:info@pawsypaila.com.np">info@pawsypaila.com.np</a>
            <a href="tel:+97701-1234567">+977 01-1234567</a>
            <a href="tel:+9779808417060">+977 9808417060</a>
            <a href="tel:+9779812345678">+977 9812345678</a>
        </div>

    </div>

    <!-- Bottom  -->
    <div class="footer-bottom">
        &copy; Pawsy Paila 2026
    </div>
</footer>

</body>
</html>
