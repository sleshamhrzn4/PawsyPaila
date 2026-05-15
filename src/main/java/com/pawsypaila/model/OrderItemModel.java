package com.pawsypaila.model;

public class OrderItemModel {
	private int orderItemId;
    private int orderId;
    private int productId;
    private double orderItemPrice;
    private int orderItemQuantity;

    public int getOrderItemId() { return orderItemId; }
    public void setOrderItemId(int orderItemId) { this.orderItemId = orderItemId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public double getOrderItemPrice() { return orderItemPrice; }
    public void setOrderItemPrice(double orderItemPrice) { this.orderItemPrice = orderItemPrice; }

    public int getOrderItemQuantity() { return orderItemQuantity; }
    public void setOrderItemQuantity(int orderItemQuantity) { this.orderItemQuantity = orderItemQuantity; }
}
