package com.studyroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.studyroom.beans.UserBean;
import com.studyroom.beans.AssignedStudentBean;
import com.studyroom.databaseconnection.DatabaseConnection;

public class UserDAO {
	Connection con = DatabaseConnection.getConnection();
	Statement stmt;
	
	/**  
	 * ******************************************************
	 * 				Admin specific method
	 * 				Adds a new user to database.
	 * ******************************************************
	 * **/
	
	public int addUser(UserBean user) {
		
		con = DatabaseConnection.getConnection();
		
		String INSERT_USER_SQL = "INSERT INTO user (personal_id, first_name, last_name, username, password, user_type) VALUES (?, ?, ?, ?, ?, ?)";
		
		int result = 0;
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(INSERT_USER_SQL);
				preparedStatement.setString(1, user.getPersonalId());
				preparedStatement.setString(2, user.getFirstname());
				preparedStatement.setString(3, user.getLastname());
				preparedStatement.setString(4, user.getUsername());
				preparedStatement.setString(5, user.getPassword());
				preparedStatement.setString(6, user.getUsertype());
				
				result = preparedStatement.executeUpdate();
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return result;
	}
	
	/**  
	 * ******************************************************
	 * 				AdminController specific method
	 * 				Retrieves all users from database.
	 * ******************************************************
	 * **/
	
	public List<UserBean> getUsers() {
	
		con = DatabaseConnection.getConnection();
		
		List<UserBean> users = new ArrayList<UserBean>();
		
		String SELECT_USER_SQL = "SELECT personal_id, first_name, last_name, username, password, user_type FROM user WHERE username != 'admin'";
		
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_SQL);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					UserBean userBean = new UserBean();
					userBean.setPersonalId(resultSet.getString("personal_id"));
					userBean.setFirstname(resultSet.getString("first_name"));
					userBean.setLastname(resultSet.getString("last_name"));
					userBean.setUsername(resultSet.getString("username"));
					userBean.setPassword(resultSet.getString("password"));
					userBean.setUsertype(resultSet.getString("user_type"));
					users.add(userBean);
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return users;
	}
	
	/**  
	 * ******************************************************
	 * 				AdminController specific method
	 * 				Deletes a user from database.
	 * ******************************************************
	 * **/
	
	public int deleteUser(String username) {
		
		con = DatabaseConnection.getConnection();
		
		int result = 0;
		
		
		
		String DELETE_USER_SQL = "DELETE FROM user WHERE username='"+ username + "'";
		
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(DELETE_USER_SQL);
				
				result = preparedStatement.executeUpdate();
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return result;
	}
	
	/**  
	 * ******************************************************
	 * 				AdminController specific method
	 * 				Edits a existing user to database.
	 * ******************************************************
	 * **/
	
	public int editUser(UserBean user, String oldUsername) {
		
		con = DatabaseConnection.getConnection();
		
		int result = 0;
		
		
		String UPDATE_USER_SQL = "UPDATE user SET personal_id=?, first_name=?, last_name=?, username=?, password=?, user_type=?  WHERE username=?";
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(UPDATE_USER_SQL);
				preparedStatement.setString(1, user.getPersonalId());
				preparedStatement.setString(2, user.getFirstname());
				preparedStatement.setString(3, user.getLastname());
				preparedStatement.setString(4, user.getUsername());
				preparedStatement.setString(5, user.getPassword());
				preparedStatement.setString(6, user.getUsertype());
				preparedStatement.setString(7, oldUsername);
				result = preparedStatement.executeUpdate();
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return result;
	}
	
	/**  
	 * ******************************************************
	 * 		AdminController specific method
	 * 		Retrieves  all assigned students from database.
	 * ******************************************************
	 * **/
	
	public List<AssignedStudentBean> getAssignedStudents() {
		
		con = DatabaseConnection.getConnection();
		
		List<AssignedStudentBean> assignedStudents = new ArrayList<AssignedStudentBean>();
		
		//String SELECT_USER_SQL = "SELECT personal_id, first_name, last_name, username FROM user AS t1 WHERE EXISTS (SELECT 1 FROM assigned_student AS t2 WHERE t1.username = t2.username) UNION SELECT username, course_code_id FROM course AS t1 WHERE EXISTS (SELECT 1 FROM user AS t2 WHERE t1.username = t2.username)";
		String SELECT_USER_SQL = "SELECT user.personal_id, user.first_name, user.last_name, user.username, assigned_student.coursecode FROM user INNER JOIN assigned_student ON user.username = assigned_student.username";
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_SQL);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					AssignedStudentBean assignedStudent = new AssignedStudentBean();
					assignedStudent.setPersonalId(resultSet.getString("personal_id"));
					assignedStudent.setFirstname(resultSet.getString("first_name"));
					assignedStudent.setLastname(resultSet.getString("last_name"));
					assignedStudent.setUsername(resultSet.getString("username"));
					assignedStudent.setCoursecode(resultSet.getString("coursecode"));
					assignedStudents.add(assignedStudent);
					
					
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return assignedStudents;
	}
	
	/**  
	 * ******************************************************
	 * 		AdminController specific method
	 * 		Adds student to a specific course in database.
	 * ******************************************************
	 * **/
	
	public int addStudent(AssignedStudentBean student) {
		
		con = DatabaseConnection.getConnection();
		
		String CHECK_IF_USER_EXISTS_SQL = "SELECT username FROM user WHERE username =?";
		String CHECK_IF_ALREADY_EXISTS_SQL = "SELECT coursecode, username FROM assigned_student WHERE coursecode =? AND username =?";
		String INSERT_USER_SQL = "INSERT INTO assigned_student (username, coursecode, course, teacher_id) VALUES (?, ?, ?, ?)";
		
		int result = 0;
		
		if(con != null) {
			try {
				//Checks that assigned student does exist in 'user' table before adding to 'assigned_student' table.
				PreparedStatement preparedStatement = con.prepareStatement(CHECK_IF_USER_EXISTS_SQL);
				preparedStatement.setString(1, student.getUsername());
				ResultSet rs = preparedStatement.executeQuery();
				
				if(rs.next()) {

					preparedStatement = con.prepareStatement(CHECK_IF_ALREADY_EXISTS_SQL);
					preparedStatement.setString(1, student.getCoursecode());
					preparedStatement.setString(2, student.getUsername());
					
					rs = preparedStatement.executeQuery();
					
					if(rs.next()==false) {
						preparedStatement = con.prepareStatement(INSERT_USER_SQL);
						preparedStatement.setString(1, student.getUsername());
						preparedStatement.setString(2, student.getCoursecode());
						preparedStatement.setString(3, student.getCourse());
						preparedStatement.setString(4, student.getTeacherId());

						
						result = preparedStatement.executeUpdate();
					}
				}
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return result;
	}
	
	/**  
	 * ******************************************************
	 * 		AdminController specific method
	 * 		Deletes a student from assigned_student table in database.
	 * ******************************************************
	 * **/
	
	public int deleteStudent(String username, String coursecode) {
		
		con = DatabaseConnection.getConnection();
		
		int result = 0;
		
		
		
		String DELETE_STUDENT_SQL = "DELETE FROM assigned_student WHERE username='"+ username + "' AND coursecode='" + coursecode + "'";
		
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(DELETE_STUDENT_SQL);
				
				result = preparedStatement.executeUpdate();
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return result;
	}
	
	/**  
	 * ******************************************************
	 * 		StudentController and TeacherController method
	 * 		Retrieves a specific users data from database.
	 * ******************************************************
	 * **/
	
	public UserBean getUserData(String username) {
		Connection con = DatabaseConnection.getConnection();
		Statement stmt;
		ResultSet rs;
		
		UserBean userBean = new UserBean();
		userBean.setUsername(username);
		
		if(con != null) {
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT personal_id, first_name, last_name, user_type FROM user WHERE username='" + username + "'");
				rs.next();
				
				userBean.setPersonalId(rs.getString("personal_id"));
				userBean.setFirstname(rs.getString("first_name"));
				userBean.setLastname(rs.getString("last_name"));
				userBean.setUsertype(rs.getString("user_type"));
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		return userBean;
	}
	
	
	
	/**  
	 * ******************************************************
	 * 		TeacherController specific method
	 * 		Retrieves all students that are enrolled to teachers courses from database.
	 * ******************************************************
	 * **/
	
	public List<UserBean> getTeacherStudents(String teacherId) {
		
		con = DatabaseConnection.getConnection();
		
		List<UserBean> users = new ArrayList<UserBean>();
		
		String SELECT_USER_SQL = "SELECT distinct user.username, user.first_name, user.last_name FROM user JOIN assigned_student ON assigned_student.teacher_id = '"+ teacherId + "' AND assigned_student.username = user.username";
		
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_SQL);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					UserBean userBean = new UserBean();
					userBean.setFirstname(resultSet.getString("first_name"));
					userBean.setLastname(resultSet.getString("last_name"));
					userBean.setUsername(resultSet.getString("username"));
					users.add(userBean);
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return users;
	}
}

