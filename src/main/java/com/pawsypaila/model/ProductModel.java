package com.pawsypaila.model;

public class ProductModel {
	private int productId;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private String productDescription;

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getproductPrice() { return productPrice; }
    public void setproductPrice(double productPrice) { this.productPrice = productPrice; }
    
    public int getProductQuantity() { return productQuantity; }
    public void setProductQuantity(int productQuantity) { this.productQuantity = productQuantity; }

    public String getProductDescription() { return productDescription; }
    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }
}


