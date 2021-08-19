package com.app.customer.dao;

import java.util.List;

import com.app.exception.BussinessException;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Product;

public interface CustomLoginDao {
	public boolean  CheckValidity(String customerEmail,String password) throws BussinessException;
	public List<Product> getProductByName(String productname) throws BussinessException;
	public List<Product> getProductByPrice(String productPrice) throws BussinessException;
	public boolean addProductToCart(String item,String CustomerEmail) throws BussinessException;
	public List<Product> viewCart(String CustomerEmail) throws BussinessException;
	public boolean PlaceOrder(String customerEmail) throws BussinessException;

}
