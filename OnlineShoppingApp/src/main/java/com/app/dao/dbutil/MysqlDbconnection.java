package com.app.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDbconnection {
	private static Connection connection;
	private MysqlDbconnection() {
		
	}
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		//step 1-Load Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//step 2 -open connection
		String password="M@nn0304";
		String url="jdbc:mysql://localhost:3306/shopping_app";
		String username="root";
		connection=DriverManager.getConnection(url, username, password);
		return connection;
		
	}
	

}
