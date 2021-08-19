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
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Order;
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
				String sql="Select productId,productName,productPrice from product where productName=?";
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1,productName);
				ResultSet resultSet=preparedStatement.executeQuery();
				if(resultSet!=null) {
					while(resultSet.next()) {
						product=new Product();
						product.setProductId(resultSet.getInt("productId"));
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

	@Override
	public boolean addProductToCart(String item,String CustomerEmail) throws BussinessException {

		List<Cart> cart = new ArrayList<>();
		boolean result=false;
		Cart cart1=null;
		try(Connection connection=MysqlDbconnection.getConnection()){
			//String pids=null;
			String sq="select id from customer where customerEmail=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sq);
			preparedStatement.setString(1,CustomerEmail);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			String sql="insert into cart(cartId,id,productId) values(?,?,?)";
			PreparedStatement preparedStatement1=connection.prepareStatement(sql);
			resultSet.next();
			int id=resultSet.getInt("id");
			
			String pids[]=item.split(",");
			for (String pid : pids) {
				preparedStatement1.setInt(1,id);
				preparedStatement1.setInt(2,id);
				preparedStatement1.setInt(3,Integer.parseInt(pid));
				 result=preparedStatement1.execute();
					
			}
			
			
		return result;
	}catch (ClassNotFoundException | SQLException e) {
		log.error(e);
		throw new BussinessException("Internal error occured contact sysadmin");

}
}

	@Override
	public List<Product> viewCart(String CustomerEmail) throws BussinessException {
		//public Product getProductById(String productName) throws BussinessException {
				List<Product> products = new ArrayList<>();
				Product product=null;
					
					try(Connection connection=MysqlDbconnection.getConnection()){
						String sq="select id from customer where customerEmail=?";
						PreparedStatement preparedStatement=connection.prepareStatement(sq);
						preparedStatement.setString(1,CustomerEmail);
						ResultSet resultSet=preparedStatement.executeQuery();
						resultSet.next();
						String sql="select productName,productPrice "
								+ "from cart join product "
								+ "on cart.productId=product.productId "
								+ "where cart.id=?";
						PreparedStatement preparedStatement1=connection.prepareStatement(sql);
						preparedStatement1.setInt(1, resultSet.getInt("id"));
						//preparedStatement1.setInt(2, resultSet.getInt("productId"));
						ResultSet resultSet1=preparedStatement1.executeQuery();
						
							while(resultSet1.next()) {
								
								 product=new Product();
								 product.setProductName(resultSet1.getString("productName"));
								 product.setProductPrice(resultSet1.getString("productPrice"));
								 products.add(product);
								 			
							
						}
					} catch (ClassNotFoundException | SQLException e) {
						log.error(e);
						throw new BussinessException("Internal error occured contact sysadmin");
					}
					return products;
				}

	@Override
	public boolean PlaceOrder(String customerEmail) throws BussinessException {
		//List<Order> order=new ArrayList<>();
		try(Connection connection=MysqlDbconnection.getConnection()){
			String sq="select id from customer where customerEmail=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sq);
			preparedStatement.setString(1,customerEmail);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			int id=resultSet.getInt("id");
			
			String s="select productId from cart where cartId=?";
			PreparedStatement preparedStatement0=connection.prepareStatement(s);
			preparedStatement0.setInt(1,id);
			ResultSet resultSet1=preparedStatement0.executeQuery();
			
			String sql="insert into shopping_app.order(orderProductId, cartId) values (?, ?)";
			PreparedStatement preparedStatement1=connection.prepareStatement(sql);
			//resultSet.next();
			
			while(resultSet1.next()){
				preparedStatement1.setInt(1,resultSet1.getInt("productId"));
				preparedStatement1.setInt(2,id);
				preparedStatement1.execute();
				System.out.println("");
				
			}
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
		
		return false;
	}
	}

