package com.app.model;

public class Cart {
 private int cartId;
 private int customerId;
 private int productId;
public int getCartId() {
	return cartId;
}
public void setCartId(int cartId) {
	this.cartId = cartId;
}
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}


@Override
public String toString() {
	return "Cart [cartId=" + cartId + ", customerId=" + customerId + ", productId=" + productId + "]";
}
 
}
