package com.app.customer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.customer.dao.CustomLoginDao;
import com.app.dao.dbutil.MysqlDbconnection;
import com.app.exception.BussinessException;
import com.app.model.Customer;
import com.app.model.Product;

public class CustomerLoginDaoImpl implements CustomLoginDao{

	private static Logger log = Logger.getLogger(CustomerLoginDaoImpl.class);
	
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

	@Override
	public List<Product> getProductByName(String productName) throws BussinessException {
		
		//public Product getProductById(String productName) throws BussinessException {
		List<Product> products = new ArrayList<>();
		Product product=null;
			
			try(Connection connection=MysqlDbconnection.getConnection()){
				String sql="Select productName,productPrice from product where productName=?";
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1,productName);
				ResultSet resultSet=preparedStatement.executeQuery();
				if(resultSet!=null) {
					while(resultSet.next()) {
						product=new Product();
						product.setProductName(productName);
						product.setProductPrice(resultSet.getString("productPrice"));
						products.add(product);
							
					}
					
				}else {
					throw new BussinessException("Entered product name "+productName+" doesnt exist");
				}
			} catch (ClassNotFoundException | SQLException e) {
				log.error(e);
				throw new BussinessException("Internal error occured contact sysadmin");
			}
			return products;
		}

	@Override
	public List<Product> getProductByPrice(String productPrice) throws BussinessException {

		List<Product> products = new ArrayList<>();
		Product product=null;
			
			try(Connection connection=MysqlDbconnection.getConnection()){
				String sql="Select productId, productName,productPrice from product where productPrice=?";
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1,productPrice);
				ResultSet resultSet=preparedStatement.executeQuery();
				if(resultSet!=null) {
					while(resultSet.next()) {
						product=new Product();
						product.setProductId(resultSet.getInt("productId"));
						product.setProductPrice(productPrice);
						product.setProductName(resultSet.getString("productName"));
						products.add(product);
							
					}
					
				}else {
					throw new BussinessException("Entered product with  "+productPrice+"  price doesnt exist");
				}
			} catch (ClassNotFoundException | SQLException e) {
				log.error(e);
				throw new BussinessException("Internal error occured contact sysadmin");
			}
			return products;
		
	}

}
