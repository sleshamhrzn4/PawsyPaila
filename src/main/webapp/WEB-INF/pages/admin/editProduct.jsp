<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product - Pawsy Paila</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editProduct.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;600;700;800&family=Nunito:wght@400;600;700&display=swap" rel="stylesheet">
</head>
<body>
<div class="flex">

    <%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>

    <div class="main-content">

        <div class="top-header">
            <i class="fa-solid fa-paw"></i>
            <h1>Welcome Back, ${sessionScope.username}!</h1>
            <a href="${pageContext.request.contextPath}/adminProduct" class="btn-home">Home</a>
        </div>

        <div class="add-pet-card">
            <h2>Edit Product</h2>

            <div class="pet-avatar-upload">
		    <div class="avatar-circle" onclick="document.getElementById('productImage').click()" style="cursor:pointer;">
		        <img id="imagePreview"
		             src="${pageContext.request.contextPath}/getImage?name=${product.productImage}&type=products"
		             style="width:100%; height:100%; object-fit:cover; border-radius:50%;">
		        <i class="fa-solid fa-box-open" id="imageIcon" style="display:none;"></i>
		    </div>
		    <input type="file" id="productImage" name="productImage" accept="image/*" hidden>
			</div>

            <form action="${pageContext.request.contextPath}/editProduct" method="post" enctype="multipart/form-data">
                <input type="hidden" name="productId" value="${product.productId}">

                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="name" value="${product.productName}" required>
                </div>

                <div class="form-group">
                    <label>Price (Rs.)</label>
                    <input type="number" name="price" step="0.01" value="${product.productPrice}" required>
                </div>

                <div class="form-group">
                    <label>Quantity</label>
                    <input type="number" name="quantity" value="${product.productQuantity}" required>
                </div>

                <div class="form-group">
                    <label>Desc</label>
                    <textarea name="description" rows="4" required>${product.productDescription}</textarea>
                </div>

                <div class="btn-group">
                    <button type="submit" class="btn-save">Save</button>
                   
                </div>
            </form>
        </div>
    </div>
</div>

<script>
document.getElementById('productImage').addEventListener('change', function() {
    const file = this.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('imagePreview').src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
});
</script>
</body>
</html>