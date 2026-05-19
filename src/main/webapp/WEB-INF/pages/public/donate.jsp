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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/donation.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
<title>Donation-PawsyPaila</title>
</head>
<body>
<%@ include file="header.jsp"%>
	<div class="main-container">
	<div class="card">
		<div class="icon"><img src="${pageContext.request.contextPath}/images/donate/heart.png"></div>
		
		<h1>Donate</h1>
		<p>Your donation saves lives. 100% goes directly to shelter care, vet bills, and adoptive programs.</p>
		    <%-- Success Message --%>
            <c:if test="${not empty success}">
                <p class="msg-success">${success}</p>
            </c:if>

            <%-- Error Message --%>
            <c:if test="${not empty error}">
                <p class="msg-error">${error}</p>
            </c:if>
		<form action="${pageContext.request.contextPath}/donate" method="post">
			<input type="number" name="donationAmount"  placeholder="Amount (Rs.)" class="form-control"  value="${param.donationAmount}" min="1" required>
			<input name="donationDate" placeholder="Date" class="form-control" type="date" value="${param.donationDate}" required>
			<input name="donationPaymentMethod" placeholder="Payment Method" class="form-control" value="${param.donationPaymentMethod}" required>
			<button type="submit" class="Donate-button">Donate</button>
		</form>
		
		</div>
	</div>
	<%@ include file="/WEB-INF/pages/public/footer.jsp" %>
</body>
</html>