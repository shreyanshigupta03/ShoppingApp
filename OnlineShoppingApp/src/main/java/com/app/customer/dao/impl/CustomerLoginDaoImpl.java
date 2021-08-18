package com.app.customer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.customer.dao.CustomLoginDao;
import com.app.dao.dbutil.MysqlDbconnection;
import com.app.exception.BussinessException;
import com.app.model.Customer;

public class CustomerLoginDaoImpl implements CustomLoginDao{

	public boolean CheckValidity(String customerEmail, String customerPassword) throws BussinessException {
		//Customer customer=new Customer();
		//StringBuilder sb=new 	StringBuilder();
		boolean result=false;
		
		try(Connection connection=MysqlDbconnection.getConnection()){
			String sql="select 1 from customer where customerEmail=? and customerPassword=? ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, customerEmail);
			preparedStatement.setString(2, customerPassword);
			ResultSet resultSet=preparedStatement.executeQuery();
			System.out.println();
			if(resultSet.first()) {
				result= true;
				System.out.println("Login Sucessful");
			}
			else 
				throw new BussinessException("Enter vaild user name or password");
			
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			throw new BussinessException("Internal Error occured");
			
		}
		return result;
		
	}

}
