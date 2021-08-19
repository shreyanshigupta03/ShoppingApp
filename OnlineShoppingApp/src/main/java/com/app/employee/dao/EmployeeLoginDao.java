package com.app.employee.dao;

import java.util.List;

import com.app.exception.BussinessException;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Product;

public interface EmployeeLoginDao {
	public List<Customer> getCustomerByName(String productname) throws BussinessException;
	public List<Customer> getCustomerById(String productPrice) throws BussinessException;
	public List<Customer> getCustomerByEmail(String productPrice) throws BussinessException;
	public List<Customer> getCustomerByOrderId(String productPrice) throws BussinessException;
	public boolean addProduct(List<Product> products) throws BussinessException;

}
