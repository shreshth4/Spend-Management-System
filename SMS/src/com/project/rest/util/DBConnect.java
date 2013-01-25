/**
 * @author Shreshth Sandilya (shreshth.4@gmail.com)
 */

package com.project.rest.util;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * @author Shreshth Sandilya (shreshth.4@gmail.com)
 */
public class DBConnect {


	public Connection getDbConnection(){
			
		
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "SMSDB";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "admin";
		
		System.out.println("Loading driver....");
		
		try {
			// This will load the MySQL driver
			Class.forName(driver).newInstance();
			
			System.out.println("Connecting to DB....");
			// Setup the connection with the DB
			conn = DriverManager.getConnection(url+dbName,userName,password);
						
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
