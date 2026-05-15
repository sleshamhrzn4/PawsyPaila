<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product - Pawsy Admin</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminDashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addProduct.css">
    
    <link href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;600;700;800&family=Nunito:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">
</head>
<body>

    <div class="admin-container">
        
        <!-- Sidebar -->
        <%@ include file="/WEB-INF/pages/admin/adminSidebar.jsp" %>

        <!-- Main Content -->
        <div class="main-content">
            
            <!-- Top Header -->
            <div class="top-header">
                <div class="flex items-center gap-3">
                    <i class="fas fa-paw text-3xl text-teal-600"></i>
                    <h1>Welcome Back, Yunisha!</h1>
                </div>
                <button class="home-btn">Home</button>
            </div>

            <!-- Add Product Form -->
            <div class="add-product-container">
                <div class="add-product-box">
                    <h2>Add Product</h2>
                    	
                    	<form action="${pageContext.request.contextPath}/addProduct" method="post" enctype="multipart/form-data">
                    	
                    	<input type="hidden" name="action" value="add">
                    
						<input type="file" id="productImage" name="productImage" accept="image/*" hidden>

						<!-- Clickable avatar that previews the image -->
						<div class="icon-circle" onclick="document.getElementById('productImage').click()" style="cursor:pointer;">
						    <img id="imagePreview" src="" alt="" style="display:none; width:100%; height:100%; object-fit:cover; border-radius:50%;">
						    <i class="fas fa-user" id="userIcon"></i>
						</div>

                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" name="name" required>
                        </div>

                        <div class="form-group">
                            <label>Price</label>
                            <input type="number" name="price" step="0.01" required>
                        </div>

                        <div class="form-group">
                            <label>Quantity</label>
                            <input type="number" name="quantity" required>
                        </div>

                        <div class="form-group">
                            <label>Desc</label>
                            <textarea name="description" rows="4" required></textarea>
                        </div>

                        <button type="submit" class="add-btn">Add</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script>
    document.getElementById('productImage').addEventListener('change', function() {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                const preview = document.getElementById('imagePreview');
                const icon = document.getElementById('userIcon');

                preview.src = e.target.result;
                preview.style.display = 'block';
                icon.style.display = 'none';
            };
            reader.readAsDataURL(file);
        }
    });
</script>

</body>
</html>