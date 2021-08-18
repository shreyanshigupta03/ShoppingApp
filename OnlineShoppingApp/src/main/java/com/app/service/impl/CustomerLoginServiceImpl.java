package com.app.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.app.customer.dao.CustomLoginDao;
import com.app.customer.dao.impl.CustomerLoginDaoImpl;
import com.app.exception.BussinessException;
import com.app.model.Customer;
import com.app.service.CustomerService;

public class CustomerLoginServiceImpl implements CustomerService{

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
			CustomLoginDao customLoginDao = new CustomerLoginDaoImpl();
			return customLoginDao.CheckValidity(customerEmail, password);
			
		}else
		{
			return false;
		}
		
	}
	
	

}
