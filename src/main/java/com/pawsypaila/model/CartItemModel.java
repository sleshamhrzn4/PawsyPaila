package com.pawsypaila.model;

public class CartItemModel {
	private int cartItemId;
    private int productId;
    private int cartId;
    private int cartItemQuantity;

    public int getCartItemId() { return cartItemId; }
    public void setCartItemId(int cartItemId) { this.cartItemId = cartItemId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }

    public int getCartItemQuantity() { return cartItemQuantity; }
    public void setCartItemQuantity(int cartItemQuantity) { this.cartItemQuantity = cartItemQuantity; }
}

