<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile - Pawsy Paila</title>
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userProfile.css">
</head>
<body>

    <div class="dashboard-layout">
      <%-- <jsp:include page="sidebar.jsp" /> --%>  
        <main class="profile-main-content">
            
            <div class="welcome-top-bar">
                <div class="welcome-text">
                    <span class="paw-icon">🐾</span> Welcome Back, <c:out value="${sessionScope.user.name != null ? sessionScope.user.name : 'User'}"/>!
                </div>
                <a href="${pageContext.request.contextPath}/home" class="home-nav-btn">Home</a>
            </div>

            <div class="profile-card-container">
                <div class="profile-card-header">
                    <h2>My Profile</h2>
                </div>

                <form action="${pageContext.request.contextPath}/profile" method="POST" class="profile-form">
                    
                    <div class="avatar-view-block">
                        <div class="avatar-circle">
                            <svg viewBox="0 0 24 24" class="default-avatar-svg">
                                <path d="M12,2A10,10 0 0,0 2,12A10,10 0 0,0 12,22A10,10 0 0,0 22,12A10,10 0 0,0 12,2M12,4A4,4 0 0,1 16,8A4,4 0 0,1 12,12A4,4 0 0,1 8,8A4,4 0 0,1 12,4M12,14C14.67,14 20,15.33 20,18V20H4V18C4,15.33 9.33,14 12,14Z" />
                            </svg>
                        </div>
                    </div>

                    <div class="form-row-entry">
                        <label for="input-name">Name</label>
                        <input type="text" id="input-name" name="userName" value="<c:out value='${sessionScope.user.name}'/>" required />
                    </div>

                    <div class="form-row-entry">
                        <label for="input-phone">Phone</label>
                        <input type="tel" id="input-phone" name="userPhone" value="<c:out value='${sessionScope.user.phone}'/>" />
                    </div>

                    <div class="form-row-entry">
                        <label for="input-email">Email</label>
                        <input type="email" id="input-email" name="userEmail" value="<c:out value='${sessionScope.user.email}'/>" required readonly class="readonly-field" />
                    </div>

                    <div class="form-row-entry">
                        <label for="input-address">Address</label>
                        <input type="text" id="input-address" name="userAddress" value="<c:out value='${sessionScope.user.address}'/>" />
                    </div>

                    <div class="form-row-entry">
                        <label for="select-gender">Gender</label>
                        <select id="select-gender" name="userGender">
                            <option value="Male" ${sessionScope.user.gender == 'Male' ? 'selected' : ''}>Male</option>
                            <option value="Female" ${sessionScope.user.gender == 'Female' ? 'selected' : ''}>Female</option>
                            <option value="Other" ${sessionScope.user.gender == 'Other' ? 'selected' : ''}>Other</option>
                        </select>
                    </div>

                    <div class="form-row-entry">
                        <label for="input-age">Age</label>
                        <input type="number" id="input-age" name="userAge" value="<c:out value='${sessionScope.user.age}'/>" min="0" max="120" />
                    </div>

                    <div class="form-actions-footer">
                        <button type="submit" class="save-profile-btn">Save Changes</button>
                    </div>
                </form>
            </div>
        </main>
    </div>

</body>
</html>