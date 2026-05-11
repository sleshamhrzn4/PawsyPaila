<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <link rel="stylesheet" href="css/addProduct.css">
<title>Add Product</title>
</head>
<body>
<div class="form-container">
    <div class="form-box">
        <div class="form-header">
            <h2>Add New Product</h2>
            <a href="manage-products.jsp" class="close-btn">✕</a>
        </div>

        <form action="admin-product" method="post" enctype="multipart/form-data">
            <div class="input-group">
                <label>Item Name</label>
                <input type="text" name="itemName" required>
            </div>

            <div class="input-group">
                <label>Price (Rs.)</label>
                <input type="number" name="price" step="0.01" required>
            </div>

            <div class="input-group">
                <label>Description</label>
                <textarea name="description" rows="5" required></textarea>
            </div>

            <div class="input-group">
                <label>Product Image</label>
                <input type="file" name="productImage" accept="image/*">
            </div>

            <div class="form-actions">
                <a href="manageProducts.jsp" class="cancel-btn">Cancel</a>
                <button type="submit" class="submit-btn">Add Product</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>