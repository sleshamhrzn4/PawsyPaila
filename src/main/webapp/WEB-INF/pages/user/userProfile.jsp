<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Profile - Pawsy Paila</title>
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userProfile.css">
    <style>
    .alert-success {
        animation: fadeOut 0.5s ease 3s forwards;
    }
    @keyframes fadeOut {
        to { opacity: 0; visibility: hidden; }
    }

    
    .avatar-circle {
        width: 110px !important;
        height: 110px !important;
        border-radius: 50% !important;
        overflow: hidden !important;
        background-color: #008080 !important;
        display: flex !important;
        align-items: center !important;
        justify-content: center !important;
        flex-shrink: 0 !important;
        cursor: pointer !important;
        position: relative !important;
    }

    .avatar-circle img {
        position: absolute !important;
        top: 0 !important;
        left: 0 !important;
        width: 110px !important;
        height: 110px !important;
        object-fit: cover !important;
        object-position: center !important;
        border-radius: 50% !important;
        display: block !important;
    }
</style>
</head>
<body>

    <div class="dashboard-layout">
    <jsp:include page="userSidebar.jsp" /> 
        <main class="profile-main-content">
            
            <div class="welcome-top-bar">
                <div class="welcome-text">
                    <span class="paw-icon">🐾</span> Welcome Back, <c:out value="${sessionScope.user.fullName != null ? sessionScope.user.fullName : 'User'}"/>!
                </div>
                <a href="${pageContext.request.contextPath}/home" class="home-nav-btn">Home</a>
            </div>

            <div class="profile-card-container">
                <div class="profile-card-header">
                    <h2>My Profile</h2>
                </div>

                <%-- Success / Error messages --%>
                <c:if test="${not empty successMessage}">
                    <div class="alert-success"><c:out value="${successMessage}"/></div>
                </c:if>
                <c:if test="${not empty errorMessage}">
                    <div class="alert-error"><c:out value="${errorMessage}"/></div>
                </c:if>

                <form action="${pageContext.request.contextPath}/userprofile" method="POST"
                      enctype="multipart/form-data" class="profile-form">
                    
                    <div class="avatar-view-block">
                        <div class="avatar-circle" onclick="document.getElementById('profileImage').click()">
                            <c:choose>
                                <c:when test="${not empty sessionScope.user.profileImg}">
                                    <%-- ✅ No inline styles — CSS handles sizing --%>
                                       <img id="imagePreview"
                                         src="${pageContext.request.contextPath}/getImage?name=${sessionScope.user.profileImg}&type=userProfile"
                                         alt="Profile Picture"/>
                                    <i class="fa-solid fa-user" id="imageIcon" style="display:none;"></i>
                                </c:when>
                                <c:otherwise>
                                    <img id="imagePreview" src="" alt="" class="hidden-preview"/>
                                    <i class="fa-solid fa-user" id="imageIcon"></i>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <label for="profileImage" class="change-photo-btn">Change Photo</label>
                        <input type="file" id="profileImage" name="profileImage" accept="image/*" style="display:none;"/>
                    </div>

                    <div class="form-row-entry">
                        <label for="input-name">Name</label>
                        <input type="text" id="input-name" name="userName" value="<c:out value='${sessionScope.user.fullName}'/>" required />
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

    <script>
        document.getElementById('profileImage').addEventListener('change', function() {
            const file = this.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    document.getElementById('imagePreview').src = e.target.result;
                    document.getElementById('imagePreview').style.display = 'block';
                    document.getElementById('imageIcon').style.display = 'none';
                };
                reader.readAsDataURL(file);
            }
        });

        const successMsg = document.querySelector('.alert-success');
        if (successMsg) {
            setTimeout(() => {
                successMsg.style.transition = 'opacity 0.5s ease';
                successMsg.style.opacity = '0';
                setTimeout(() => successMsg.style.display = 'none', 500);
            }, 3000);
        }
    </script>

</body>
</html>