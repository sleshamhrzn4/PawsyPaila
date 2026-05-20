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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/contact.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
<title>Contact Us-Pawsy Paila</title>
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
                    <input type="email" name="email" placeholder="Email" class="input-field" value="${param.email}" required >
                    
                    <textarea name="message" placeholder="Write Us a Message" class="textarea-field" required>${param.message}</textarea>
                    
                    <button type="submit" class="send-btn">Send</button>
                </form>
            </section>
            
        </div>
    </main>
    <%@ include file="/WEB-INF/pages/public/footer.jsp" %>
</body>
</html>