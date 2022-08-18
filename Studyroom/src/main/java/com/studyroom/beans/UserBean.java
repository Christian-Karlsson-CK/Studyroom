package com.studyroom.beans;

import java.io.Serializable;

/**  
 * 
 * 
 * This bean can hold data about a currently signed in user.
 * 
 * 
 * **/

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

}

