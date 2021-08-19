package com.app.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.app.Main;
import com.app.customer.dao.CustomLoginDao;
import com.app.customer.dao.impl.CustomerLoginDaoImpl;
import com.app.exception.BussinessException;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Product;
import com.app.service.CustomerService;

public class CustomerLoginServiceImpl implements CustomerService{
	private static Logger log = Logger.getLogger(CustomerService.class);
	private CustomLoginDao customLoginDao ;
	
	public CustomLoginDao getCustomLoginDao() {
		if(customLoginDao==null) {
			customLoginDao= new CustomerLoginDaoImpl();
		}
		return customLoginDao;
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		public static boolean validate(String emailStr) {
		        Matcher  matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		        return matcher.find();
		}
	
	@Override
	public Boolean customerLoginService(String customerEmail, String password) throws BussinessException {
		// TODO Auto-generated method stub
		if(validate(customerEmail) && password!=null) {
			return getCustomLoginDao().CheckValidity(customerEmail, password);
			
		}else
		{	
			log.error("Email not vaild");
			return false;
		}
		
	}

	@Override
	public List<Product> getProductByName(String name) throws BussinessException {
		if (name!=null) {
			return getCustomLoginDao().getProductByName(name);
		}
		return null;
	}

	@Override
	public List<Product> getProductByPrice(String productPrice) throws BussinessException {
		if (productPrice!=null) {
			return getCustomLoginDao().getProductByPrice(productPrice);
		}
		return null;
	}

	@Override
	public boolean addProductToCart(String item,String CustomerEmail) throws BussinessException {
		if (item!=null) {
			return getCustomLoginDao().addProductToCart(item, CustomerEmail);
		}
		return false;
	}

	@Override
	public List<Product> viewCart(String CustomerEmail) throws BussinessException{
		if (CustomerEmail!=null) {
			return getCustomLoginDao().viewCart(CustomerEmail);
		}
		return null;
	}

	@Override
	public boolean PlaceOrder(String customerEmail) throws BussinessException {
		if (customerEmail!=null) {
			return getCustomLoginDao().PlaceOrder(customerEmail);
		}
		return false;
	
	

	}
}
