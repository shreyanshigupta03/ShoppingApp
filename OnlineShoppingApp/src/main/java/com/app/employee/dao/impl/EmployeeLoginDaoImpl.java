package com.app.employee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.customer.dao.CustomLoginDao;
import com.app.dao.dbutil.MysqlDbconnection;
import com.app.employee.dao.EmployeeLoginDao;
import com.app.exception.BussinessException;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.model.Product;

public class EmployeeLoginDaoImpl implements EmployeeLoginDao {

	private static Logger log = Logger.getLogger(EmployeeLoginDaoImpl.class);

	@Override
	public List<Customer> getCustomerByName(String customer) throws BussinessException {

		List<Customer> customers = new ArrayList<>();
		Customer customerObj = null;
		String temp[] = customer.split(" ");

		try (Connection connection = MysqlDbconnection.getConnection()) {
			String sql = "Select id, customerFirstName, customerLastName,customerEmail from customer where customerFirstName=? and customerLastName=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, temp[0]);
			preparedStatement.setString(2, temp[1]);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					customerObj = new Customer();
					customerObj.setId(resultSet.getInt("id"));
					customerObj.setCustomerFirstName(temp[0]);
					customerObj.setCustomerLastName(temp[1]);
					customerObj.setCustomerEmail(resultSet.getString("customerEmail"));
					customers.add(customerObj);

				}

			} else {
				throw new BussinessException("Entered Customer with Name  " + customer + " doesnt exist");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BussinessException("Internal error occured contact sysadmin");
		}
		return customers;

	}

	@Override
	public List<Customer> getCustomerById(String cutomerId) throws BussinessException {

		List<Customer> customers = new ArrayList<>();
		Customer customerObj = null;

		try (Connection connection = MysqlDbconnection.getConnection()) {
			String sql = "Select id, customerFirstName, customerLastName,customerEmail from customer where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, cutomerId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					customerObj = new Customer();
					customerObj.setId(resultSet.getInt("id"));
					customerObj.setCustomerFirstName(resultSet.getString("customerFirstName"));
					customerObj.setCustomerLastName(resultSet.getString("customerLastName"));
					customerObj.setCustomerEmail(resultSet.getString("customerEmail"));
					customers.add(customerObj);

				}

			} else {
				throw new BussinessException("Entered Customer with Id  " + cutomerId + " doesnt exist");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BussinessException("Internal error occured contact sysadmin");
		}
		return customers;

	}

	@Override
	public List<Customer> getCustomerByEmail(String customerMail) throws BussinessException {

		List<Customer> customers = new ArrayList<>();
		Customer customerObj = null;

		try (Connection connection = MysqlDbconnection.getConnection()) {
			String sql = "Select id, customerFirstName, customerLastName,customerEmail from customer where customerEmail=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customerMail);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					customerObj = new Customer();
					customerObj.setId(resultSet.getInt("id"));
					customerObj.setCustomerFirstName(resultSet.getString("customerFirstName"));
					customerObj.setCustomerLastName(resultSet.getString("customerLastName"));
					customerObj.setCustomerEmail(resultSet.getString("customerEmail"));
					customers.add(customerObj);

				}

			} else {
				throw new BussinessException("Entered Customer with Email ID  " + customerMail + " doesnt exist");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BussinessException("Internal error occured contact sysadmin");
		}
		return customers;
	}

	@Override
	public List<Customer> getCustomerByOrderId(String orderID) throws BussinessException {

		List<Customer> customers = new ArrayList<>();
		Customer customerObj = null;

		try (Connection connection = MysqlDbconnection.getConnection()) {
			String sql = "Select id, customerFirstName, customerLastName,customerEmail from customer join shopping_app.order o on customer.id=o.cartId where o.orderId=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					customerObj = new Customer();
					customerObj.setId(resultSet.getInt("id"));
					customerObj.setCustomerFirstName(resultSet.getString("customerFirstName"));
					customerObj.setCustomerLastName(resultSet.getString("customerLastName"));
					customerObj.setCustomerEmail(resultSet.getString("customerEmail"));
					customers.add(customerObj);

				}

			} else {
				throw new BussinessException("Entered Customer with Order ID  " + orderID + " doesnt exist");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BussinessException("Internal error occured contact sysadmin");
		}
		return customers;
	}

	@Override
	public boolean addProduct(List<Product> products) throws BussinessException {

		boolean result = false;
		try (Connection connection = MysqlDbconnection.getConnection()) {
			String sql = "insert into product(productName,productPrice) values(?,?)";
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql);

			for (Product product : products) {
				preparedStatement1.setString(1, product.getProductName());
				preparedStatement1.setString(2, product.getProductPrice());
				result = preparedStatement1.execute();

			}

			return result;
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BussinessException("Internal error occured contact sysadmin");

		}

	}

	
	@Override
	public List<Order> viewOrders() throws BussinessException {
	List<Order> orders=new ArrayList<>();
	Order order=null;
	try (Connection connection = MysqlDbconnection.getConnection()) {
		String sql = "Select orderId ,orderStatus from shopping_app.order";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			order= new Order();
			order.setStatus(resultSet.getString("orderStatus"));
			order.setOrderId(resultSet.getInt("orderId"));
			orders.add(order);
			
		}
		//preparedStatement.setString(1, order);
	
		return orders;
	} catch (ClassNotFoundException | SQLException e) {
		log.error(e);
		throw new BussinessException("Internal error occured contact sysadmin");

	}


	}
	
	@Override
	public boolean changeOrderStatus(String order) throws BussinessException {
	
		String orderIds[] = order.split(",");
		
		try (Connection connection = MysqlDbconnection.getConnection()) {
		String sql = "UPDATE shopping_app.order SET orderStatus = 'shipped' WHERE (orderId = ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		for (String orderID : orderIds) {
			preparedStatement.setInt(1,Integer.parseInt(orderID));
			preparedStatement.executeUpdate();
		}
		//preparedStatement.setString(1, order);
		return true;
	} catch (ClassNotFoundException | SQLException e) {
		log.error(e);
		throw new BussinessException("Internal error occured contact sysadmin");

	}


	}
	}
