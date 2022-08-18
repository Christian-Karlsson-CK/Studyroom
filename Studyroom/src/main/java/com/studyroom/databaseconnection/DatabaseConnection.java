package com.studyroom.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**  
 * 
 * 
 * This class handles database connection.
 * 
 * 
 * **/

public class DatabaseConnection {
	
	
	
	public static Connection getConnection() {
		
		Connection con = null;
		
		//Try to specify driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		
		catch(Exception ex){
			System.out.println("Exception: " + ex.getMessage());
		   
		}
		
		//Try to get a connection with localserver
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studyroom", "root", "root");
			
		}
		catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
			
		}
		return con;
		
	}
	
	//Disconnect with database
	public static void disconnect(Connection con) {
		try {
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
