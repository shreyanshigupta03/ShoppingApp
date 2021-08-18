package com.app.customer.dao;

import com.app.exception.BussinessException;
import com.app.model.Customer;

public interface CustomLoginDao {
	public boolean  CheckValidity(String customerEmail,String password) throws BussinessException;

}
