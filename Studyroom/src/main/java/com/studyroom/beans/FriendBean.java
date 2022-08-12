package com.studyroom.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.studyroom.databaseconnection.DatabaseConnection;

public class FriendBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String firstname;
	private String lastname;
	private String course;
	private String lastSentMessage;
	private String lastSentMessageDate;
	private boolean isRead;
	private int newMessagesCount;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String atCourse) {
		this.course = atCourse;
	}
	
	public String getLastSentMessage() {
		return lastSentMessage;
	}

	public void setLastSentMessage(String lastSentMessage) {
		this.lastSentMessage = lastSentMessage;
	}
	
	public boolean isRead() {
		return isRead;
	}

	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}
	
	public String getLastSentMessageDate() {
		return lastSentMessageDate;
	}
	
	public void setLastSentMessageDate(String lastSentMessageDate) {
		this.lastSentMessageDate = lastSentMessageDate;
	}
	
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

	public int getNewMessagesCount() {
		return newMessagesCount;
	}

	public void setNewMessagesCount(int newMessagesCount) {
		this.newMessagesCount = newMessagesCount;
	}
}
