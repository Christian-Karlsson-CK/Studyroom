package com.studyroom.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.studyroom.databaseconnection.DatabaseConnection;

public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String firstname;
	private String lastname;
	private String personalId;
	private String username;
	private String password;
	private String usertype;



	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPersonalId() {
		return personalId;
	}
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}
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
	
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	
	public void getUserData() {
		Connection con = DatabaseConnection.getConnection();
		Statement stmt;
		ResultSet rs;
		
		
		
		if(con != null) {
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT personal_id, first_name, last_name, user_type FROM user WHERE username='" + username + "'");
				rs.next();
				this.setPersonalId(rs.getString("personal_id"));
				this.setFirstname(rs.getString("first_name"));
				this.setLastname(rs.getString("last_name"));
				this.setUsertype(rs.getString("user_type"));
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
	}




}

