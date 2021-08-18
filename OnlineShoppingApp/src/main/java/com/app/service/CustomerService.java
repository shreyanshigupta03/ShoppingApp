package com.app.service;

import com.app.exception.BussinessException;

public interface CustomerService {
	public Boolean customerLoginService (String customerEmail,String password) throws BussinessException;
	

}
