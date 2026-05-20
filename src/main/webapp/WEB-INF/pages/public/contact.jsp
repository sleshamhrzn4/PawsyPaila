<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/contact.css">
<title>Contact Us-PawsyPaila</title>
</head>
<body>
<%@ include file="header.jsp"%>
<main class="contact-section">
        <div class="contact-card-container">

            <section class="contact-card">
                <div class="card-header">
                    <h1>Contact</h1>
                    <img src="${pageContext.request.contextPath}/images/contact/logo_black.png" class="inline-logo" alt="Pawsy Paila">
                </div>
                
                <%-- Success Message --%>
                <c:if test="${not empty success}">
                    <p class="msg-success">${success}</p>
                </c:if>

                <%-- Error Message --%>
                <c:if test="${not empty error}">
                    <p class="msg-error">${error}</p>
                </c:if>

                <form action="contact" method="post" class="contact-form">
                    <input type="text" name="email" placeholder="Email" class="input-field" value="${emailValue}">
                    
                    <textarea name="message" placeholder="Write Us a Message" class="textarea-field">${messageValue}</textarea>
                    
                    <button type="submit" class="send-btn">Send</button>
                </form>
            </section>
            <%-- RIGHT CARD --%>
            <section class="info-card">
            	<h2>Get In Touch</h2>
            	<p class="info-subtitle">We would love to hear from you. Reach us through any of the following.</p>
            	<div class="info-item">
            		<div>
                        <p class="info-label">Phone</p>
                        <p class="info-value">+977-01-0000000</p>
                        <p class="info-value">+977-9800000000</p>
                    </div>
            	</div>
            	<div class="info-item">
            		<div>
                        <p class="info-label">Email</p>
                        <p class="info-value">info@pawsypaila.com.np</p>
                    </div>
            	</div>
            	<div class="info-item">
            		<div>
                        <p class="info-label">Address</p>
                        <p class="info-value">Kathmandu, Nepal</p>
                    </div>
            	</div>
            	<div class="social-links">
                    <p class="info-label">Follow Us On</p>
                    <div class="social-icons">
            			<a href="#" aria-label="Facebook"><i class="fab fa-facebook-f"></i></a>
            			<a href="#" aria-label="Instagram"><i class="fab fa-instagram"></i></a>
            			<a href="#" aria-label="Twitter / X"><i class="fab fa-x-twitter"></i></a>
        			</div>
                </div>
            </section>
        </div>
    </main>
</body>
</html>