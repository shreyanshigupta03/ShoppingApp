package com.app.service;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.model.Product;

import java.util.List;

import com.app.exception.BussinessException;

public interface EmployeeService {
	public List<Customer> getCustomerByName(String productname) throws BussinessException;
	public List<Customer> getCustomerById(String productPrice) throws BussinessException;
	public List<Customer> getCustomerByEmail(String productPrice) throws BussinessException;
	public List<Customer> getCustomerByOrderId(String productPrice) throws BussinessException;
	public boolean addProduct(List<Product> products) throws BussinessException;
	public boolean changeOrderStatus(String customerEmail)throws BussinessException;
	List<Order> viewOrders() throws BussinessException;


}
