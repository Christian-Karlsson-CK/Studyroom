package com.studyroom.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.studyroom.beans.LoginBean;
import com.studyroom.databaseconnection.DatabaseConnection;

/**  
 * 
 * 
 * This DAO handles all user login validation.
 * 
 * 
 * **/

public class LoginDAO {
	
	public boolean validateUsername(LoginBean loginBean) {
		
		Connection con = DatabaseConnection.getConnection();
		Statement stmt;
		ResultSet rs;
		boolean hasUsername = false;
		
		String username = loginBean.getUsername();
		
		if(con != null) {
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT username FROM user WHERE username='" + username + "'");
				if (rs.next()) {
					hasUsername = true;
				}
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		DatabaseConnection.disconnect(con);
		
		return hasUsername;
	}
	
	public boolean validatePassword(LoginBean loginBean) {
		
		Connection con = DatabaseConnection.getConnection();
		Statement stmt;
		ResultSet rs;
		boolean isPasswordCorrect = false;
		
		String username = loginBean.getUsername();
		String password = loginBean.getPassword();
		
		if(con != null) {
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT username FROM user WHERE username='" + username + "' AND password='" + password + "'");
				if (rs.next()) {
					isPasswordCorrect = true;
				}
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		return isPasswordCorrect;
	}
	
	public String getUserType(LoginBean loginBean) {
		
		Connection con = DatabaseConnection.getConnection();
		Statement stmt;
		ResultSet rs;
		
		String username = loginBean.getUsername();
		
		String userType = null;
		
		if(con != null) {
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT user_type FROM user WHERE username='" + username + "'");
				rs.next();
				userType = rs.getString("user_type");
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		return userType;
	}
	
}
