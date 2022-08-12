package com.studyroom.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.studyroom.databaseconnection.DatabaseConnection;

public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserType() {
		
		Connection con = DatabaseConnection.getConnection();
		Statement stmt;
		ResultSet rs;
		
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
	
	
	
	public boolean validateUsername() {
		
		Connection con = DatabaseConnection.getConnection();
		Statement stmt;
		ResultSet rs;
		boolean hasUsername = false;
		
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
	
	public boolean validatePassword() {
		
		Connection con = DatabaseConnection.getConnection();
		Statement stmt;
		ResultSet rs;
		boolean isPasswordCorrect = false;
		
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
	

	
	
	
	
	
}
