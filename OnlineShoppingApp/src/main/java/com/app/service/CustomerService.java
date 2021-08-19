package com.app.service;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Product;

import java.util.List;

import com.app.exception.BussinessException;

public interface CustomerService {
	public Boolean customerLoginService (String customerEmail,String password) throws BussinessException;
	public List<Product> getProductByName(String name) throws BussinessException;
	public List<Product> getProductByPrice(String productPrice) throws BussinessException;
	public boolean addProductToCart(String item,String CustomerEmail) throws BussinessException;
	public List<Product> viewCart(String CustomerEmail) throws BussinessException;
	public boolean PlaceOrder(String customerEmail) throws BussinessException;
	public boolean registerCustomer(Customer customer) throws BussinessException;
}
