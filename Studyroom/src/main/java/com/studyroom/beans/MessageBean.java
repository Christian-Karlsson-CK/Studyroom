package com.studyroom.beans;

import java.io.Serializable;

/**  
 * 
 * 
 * This bean can hold data about a message.
 * 
 * 
 * **/

public class MessageBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String creatorId;
	private String message;
	private String createDate;
	private String recipientId;
	private boolean isRead;
	
	
	
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
	public String getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	
	
	public boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}
	
}
