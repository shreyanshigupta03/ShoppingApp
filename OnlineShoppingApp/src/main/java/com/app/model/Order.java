package com.app.model;

public class Order {
	private int cartId;
	private int orderId;
	private int OrderProductId;
	private String status;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderProductId() {
		return OrderProductId;
	}
	public void setOrderProductId(int orderProductId) {
		OrderProductId = orderProductId;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [cartId=" + cartId + ", orderId=" + orderId + ", OrderProductId=" + OrderProductId + ", status="
				+ status + "]";
	}

}
