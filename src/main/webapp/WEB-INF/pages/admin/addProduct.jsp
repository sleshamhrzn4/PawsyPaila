<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product - Pawsy Paila</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addProduct.css">
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
            <a href="${pageContext.request.contextPath}/adminProduct" class="btn-home">Back to Products</a>
        </div>

        <div class="add-pet-card">
            <h2>Add Product</h2>

            <form action="${pageContext.request.contextPath}/addProduct" method="post" enctype="multipart/form-data">

                <div class="pet-avatar-upload">
                    <label for="productImage" style="cursor:pointer;">
                        <div class="avatar-circle" onclick="document.getElementById('productImage').click()" style="cursor:pointer;">
                        <img id="imagePreview" src="" alt=""
                             style="display:none; width:100%; height:100%; object-fit:cover; border-radius:50%;">
                        <i class="fa-solid fa-box-open" id="imageIcon"></i>
                    </div>
                    </label>
                    <input type="file" id="productImage" name="productImage" accept="image/*" hidden>
                </div>
                
                 <input type="hidden" name="action" value="add"/>

                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="name" placeholder="Enter product name" required>
                </div>

                <div class="form-group">
                    <label>Price (Rs.)</label>
                    <input type="number" name="price" step="0.01" placeholder="Enter price" required>
                </div>

                <div class="form-group">
                    <label>Quantity</label>
                    <input type="number" name="quantity" placeholder="Enter quantity" required>
                </div>

                <div class="form-group">
                    <label>Description</label>
                    <textarea name="description" rows="4" placeholder="Enter product description" required></textarea>
                </div>

                <button type="submit" class="btn-add">Add Product</button>
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
            document.getElementById('imagePreview').style.display = 'block';
            document.getElementById('imageIcon').style.display = 'none';
        };
        reader.readAsDataURL(file);
    }
});
</script>

</body>
</html>