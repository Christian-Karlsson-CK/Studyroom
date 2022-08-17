package com.studyroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.studyroom.beans.FriendBean;
import com.studyroom.beans.MessageBean;
import com.studyroom.databaseconnection.DatabaseConnection;

public class MessageDAO {
	
public List<FriendBean> getTeacherFriendList(String teacher){
		
		String teacherId = teacher;
		List<FriendBean> friendList = new ArrayList<FriendBean>();
		
		Connection con = DatabaseConnection.getConnection();
		Statement stmtForAssigned_student;
		Statement stmtMessage;
		Statement stmtUser;
		ResultSet rsFromAssigned_student;
		ResultSet rsFromUser;
		ResultSet rsFromMessage;

		
		
		if(con != null) {
			try {
				//Start by getting students from courses that teacher is teaching in.
				stmtForAssigned_student = con.createStatement();
				stmtMessage = con.createStatement();
				stmtUser = con.createStatement();
				
				rsFromAssigned_student = stmtForAssigned_student.executeQuery("SELECT username, course FROM assigned_student WHERE teacher_id='" + teacherId  + "' GROUP BY username");
				//rsFromAssigned_student.next();
				//String test = rsFromAssigned_student.getString("username");
				//System.out.println(test);
				
				//Create friendBean for each student the teacher has. Also add last message sent by student to show in friendlist on messagepage for teacher.
				int newMessageCounter = 0;
				while(rsFromAssigned_student.next()) {
					
					//System.out.println((String)rsFromAssigned_student.getString("username"));
					
					FriendBean friendBean = new FriendBean();
					friendBean.setUsername(rsFromAssigned_student.getString("username"));
					friendBean.setCourse(rsFromAssigned_student.getString("course"));
					
					//System.out.println(friendBean.getUsername());
					
					rsFromUser = stmtUser.executeQuery("SELECT first_name, last_name FROM user WHERE username='" + friendBean.getUsername()  + "' ");
					rsFromUser.next();
					friendBean.setFirstname(rsFromUser.getString("first_name"));
					friendBean.setLastname(rsFromUser.getString("last_name"));
					
					stmtMessage = con.createStatement();
					rsFromMessage = stmtMessage.executeQuery("SELECT message, create_date, is_read FROM message WHERE creator_id='" + friendBean.getUsername() + "' AND recipient_id='" + teacherId  + "' ");
					while(rsFromMessage.next()) {
						friendBean.setLastSentMessage(rsFromMessage.getString("message"));
						friendBean.setLastSentMessageDate(rsFromMessage.getString("create_date"));
						
						if(rsFromMessage.getString("is_read").equals("true")) {
							friendBean.setIsRead(true);
						}
						
						else {
							friendBean.setIsRead(false);
							newMessageCounter++;
						}
						
					}
					friendBean.setNewMessagesCount(newMessageCounter);
					friendList.add(friendBean);
				}
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		return friendList;
	}
	
	
	public List<FriendBean> getStudentFriendList(String student){
		
		String studentId = student;
		List<FriendBean> friendList = new ArrayList<FriendBean>();
		
		Connection con = DatabaseConnection.getConnection();
		Statement stmtForAssigned_student;
		Statement stmtMessage;
		Statement stmtUser;
		ResultSet rsFromAssigned_student;
		ResultSet rsFromUser;
		ResultSet rsFromMessage;
		
		if(con != null) {
			try {
				//Start by getting teachers from courses that student is assigned to.
				stmtForAssigned_student = con.createStatement();
				stmtMessage = con.createStatement();
				stmtUser = con.createStatement();
				
				rsFromAssigned_student = stmtForAssigned_student.executeQuery("SELECT DISTINCT teacher_id, course FROM assigned_student WHERE username='" + studentId  + "' GROUP BY teacher_id");
				
				//Create friendBean for each teacher the student has. Also add last message sent by teacher to show in friendlist on messagepage for student.
				int newMessageCounter = 0;
				while(rsFromAssigned_student.next()) {
					
					System.out.println((String)rsFromAssigned_student.getString("teacher_id"));
					
					FriendBean friendBean = new FriendBean();
					friendBean.setUsername(rsFromAssigned_student.getString("teacher_id"));
					friendBean.setCourse(rsFromAssigned_student.getString("course"));
					
					
					rsFromUser = stmtUser.executeQuery("SELECT first_name, last_name FROM user WHERE username='" + friendBean.getUsername()  + "' ");
					rsFromUser.next();
					friendBean.setFirstname(rsFromUser.getString("first_name"));
					friendBean.setLastname(rsFromUser.getString("last_name"));
					
					stmtMessage = con.createStatement();
					rsFromMessage = stmtMessage.executeQuery("SELECT message, create_date, is_read FROM message WHERE creator_id='" + friendBean.getUsername() + "' AND recipient_id='" + studentId  + "' ");
					while(rsFromMessage.next()) {
						friendBean.setLastSentMessage(rsFromMessage.getString("message"));
						friendBean.setLastSentMessageDate(rsFromMessage.getString("create_date"));
						
						if(rsFromMessage.getString("is_read").equals("true")) {
							friendBean.setIsRead(true);
						}
						
						else {
							friendBean.setIsRead(false);
							newMessageCounter++;
						}
						
					}
					friendBean.setNewMessagesCount(newMessageCounter);
					friendList.add(friendBean);
				}
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		return friendList;
	}
	
	public void setMessagesRead(String user, String recipient){
		
		System.out.println(user);
		System.out.println(recipient);
		Connection con = DatabaseConnection.getConnection();
		Statement stmt;
		
		if(con != null) {
			try {
				stmt = con.createStatement();
				stmt.executeUpdate("UPDATE message SET is_read='true' WHERE creator_id='" + recipient + "' AND recipient_id='" + user +"' ");
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
	}
	
	public List<MessageBean> getMessageList(String user, String recipient){
		
		String username = user;
		String recipientId = recipient;
		
		List<MessageBean> messages = new ArrayList<MessageBean>();
		
		Connection con = DatabaseConnection.getConnection();
		Statement stmt;
		ResultSet rs;
		
		if(con != null) {
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT creator_id, create_date, message, recipient_id, is_read FROM message "
								+ "		WHERE creator_id='" + username + "' AND recipient_id='" + recipientId +"' "
								+ "		UNION "
								+ "		SELECT creator_id, create_date, message, recipient_id, is_read FROM message "
								+ "		WHERE creator_id='" + recipientId + "' AND recipient_id='" + username +"' "
								+ "		ORDER BY create_date");
				
				
				while(rs.next()) {
					MessageBean messageBean = new MessageBean();
					messageBean.setCreatorId(rs.getString("creator_id"));
					messageBean.setMessage(rs.getString("message"));
					//System.out.println(messageBean.getCreatorId());
					messages.add(messageBean);
				}
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		return messages;
	}
	
	public int addMessage(MessageBean message) {
		
		Connection con = DatabaseConnection.getConnection();
		
		String INSERT_MESSAGE_SQL = "INSERT INTO message "+
				" (creator_id, create_date, message, recipient_id, is_read) VALUES " + 
				" (?, ?, ?, ?, ?);";
		
		int result = 0;
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(INSERT_MESSAGE_SQL);
				preparedStatement.setString(1, message.getCreatorId());
				preparedStatement.setString(2, message.getCreateDate());
				preparedStatement.setString(3, message.getMessage());
				preparedStatement.setString(4, message.getRecipientId());
				preparedStatement.setString(5, "false");
				
				result = preparedStatement.executeUpdate();
				
				
				//stmt.executeQuery("INSERT INTO `studyroom`.`message` (`creator_id`, `create_date`, `message`, `recipient_id`, `is_read`) VALUES ('guakr', '2002-09', 'my msessage is this', 'toJEFRE123', 'false'");
				
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return result;
	}


	public int getNewMessagesCount(String username) {
		
		Connection con = DatabaseConnection.getConnection();
		Statement stmt;
		ResultSet rs;
		int newMessageCounter = 0;
		
		if(con != null) {
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT is_read FROM message WHERE recipient_id='" + username +"'");
				
				while(rs.next()) {
					if(rs.getString("is_read")!= null) {
						if(rs.getString("is_read").equals("false")) {
							newMessageCounter++;
						}
					}
					
				}
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		return newMessageCounter;
	}

}
