<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Admin Dashboard - Pawsy Paila</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminDonation.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
</head>
<body>
<div class="flex">

    <%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>

    <!-- Main Content -->
    <div class="main-content">

        <!-- Page Header -->
        <div class="page-header">
            <i class="fa-solid fa-paw"></i>
            <h1>Welcome Back, ${sessionScope.username}!</h1>
        </div>
        <div class="card">
            <div class="card-header">
                <h2>Donations</h2>
             </div>
                      <table class="donation-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Payment Method</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
        	<c:when test="${not empty donationList}">
            <c:forEach var="donation" items="${donationList}">
                <tr>
                    <td>
                        <div class="user-cell">
                            <div class="table-avatar">
                                <i class="fa-solid fa-user"></i>
                            </div>
                            ${donation.userName}
                        </div>
                    </td>
                    <td>${donation.donationDate}</td>
                    <td>${donation.donationPaymentMethod}</td>
                    <td>Rs. ${donation.donationAmount}</td>
                </tr>
            </c:forEach>
       		</c:when>
        	<c:otherwise>
            	<tr>
                	<td colspan="4" style="text-align:center; color:#888; padding:30px;">
                    				No donations found.
                	</td>
            	</tr>
        	</c:otherwise>
    	</c:choose>
        </tbody>
       </table>
     </div>
    </div>
</div>
</body>
</html>