package com.app.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.app.Main;
import com.app.customer.dao.CustomLoginDao;
import com.app.customer.dao.impl.CustomerLoginDaoImpl;
import com.app.employee.dao.EmployeeLoginDao;
import com.app.employee.dao.impl.EmployeeLoginDaoImpl;
import com.app.exception.BussinessException;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Product;
import com.app.service.CustomerService;
import com.app.service.EmployeeService;

public class EmployeeLoginServiceImpl implements EmployeeService{
	private static Logger log = Logger.getLogger(CustomerService.class);
	private EmployeeLoginDaoImpl empLoginDao ;
	
	public EmployeeLoginDaoImpl getEmployeeLoginDao() {
		if(empLoginDao==null) {
			empLoginDao= new EmployeeLoginDaoImpl();
		}
		return empLoginDao;
	}

	@Override
	public List<Customer> getCustomerByName(String customer) throws BussinessException {
		if (customer!=null) {
			return getEmployeeLoginDao().getCustomerByName(customer);
		}
		return null;
	}

	@Override
	public List<Customer> getCustomerById(String customerId) throws BussinessException {
		if (customerId!=null) {
			return getEmployeeLoginDao().getCustomerById(customerId);
		}
		return null;
	}

	@Override
	public List<Customer> getCustomerByEmail(String email) throws BussinessException {
		if (email!=null) {
			return getEmployeeLoginDao().getCustomerByEmail(email);
		}
		return null;
	}

	@Override
	public List<Customer> getCustomerByOrderId(String orderId) throws BussinessException {
		if (orderId!=null) {
			return getEmployeeLoginDao().getCustomerByOrderId(orderId);
		}
		return null;
	}

	@Override
	public boolean addProduct(List<Product> products) throws BussinessException {
		if (products!=null) {
			getEmployeeLoginDao().addProduct(products);
		}
		return false;
	}

}
