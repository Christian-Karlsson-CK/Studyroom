package com.studyroom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.studyroom.beans.AssignedStudentBean;
import com.studyroom.beans.CourseBean;
import com.studyroom.beans.CourseContentsBean;
import com.studyroom.databaseconnection.DatabaseConnection;

public class CourseDAO {
	
	Connection con = DatabaseConnection.getConnection();
	Statement stmt;
	
	public int addCourse(CourseBean course) {
		
		con = DatabaseConnection.getConnection();
		
		String INSERT_COURSE_SQL = "INSERT INTO course (course_code_id, name, teacher_id, start_date, end_date, pace, points) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		int result = 0;
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(INSERT_COURSE_SQL);
				preparedStatement.setString(1, course.getCourseCodeId());
				preparedStatement.setString(2, course.getName());
				preparedStatement.setString(3, course.getTeacherId());
				preparedStatement.setString(4, course.getStartDate());
				preparedStatement.setString(5, course.getEndDate());
				preparedStatement.setString(6, course.getPace());
				preparedStatement.setString(7, course.getPoints());
				
				result = preparedStatement.executeUpdate();

			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return result;
	}

	public List<CourseBean> getCourses() {
	
		con = DatabaseConnection.getConnection();
		
		List<CourseBean> courses = new ArrayList<CourseBean>();
		
		String SELECT_COURSE_SQL = "SELECT * FROM course";
		
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_COURSE_SQL);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					CourseBean courseBean = new CourseBean();
					courseBean.setCourseCodeId(resultSet.getString("course_code_id"));
					courseBean.setName(resultSet.getString("name"));
					courseBean.setTeacherId(resultSet.getString("teacher_id"));
					courseBean.setStartDate(resultSet.getString("start_date"));
					courseBean.setEndDate(resultSet.getString("end_date"));
					courseBean.setPace(resultSet.getString("pace"));
					courseBean.setPoints(resultSet.getString("points"));
					courses.add(courseBean);
					
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return courses;
	}
	
	public int deleteCourse(String course) {
		
		con = DatabaseConnection.getConnection();
		
		int result = 0;
		
		
		
		String DELETE_COURSE_SQL = "DELETE FROM course WHERE name='"+ course + "'";
		
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(DELETE_COURSE_SQL);
				
				result = preparedStatement.executeUpdate();
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return result;
	}
	
public int editCourse(CourseBean course, String oldCourseName) {
		
		con = DatabaseConnection.getConnection();
		
		int result = 0;
		
		
		String UPDATE_COURSE_SQL = "UPDATE course SET course_code_id=?, name=?, teacher_id=?, start_date=?, end_date=?, pace=?, points=? WHERE name=?";
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(UPDATE_COURSE_SQL);
				preparedStatement.setString(1, course.getCourseCodeId());
				preparedStatement.setString(2, course.getName());
				preparedStatement.setString(3, course.getTeacherId());
				preparedStatement.setString(4, course.getStartDate());
				preparedStatement.setString(5, course.getEndDate());
				preparedStatement.setString(6, course.getPace());
				preparedStatement.setString(7, course.getPoints());
				preparedStatement.setString(8, oldCourseName);
				result = preparedStatement.executeUpdate();
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return result;
	}

public int addInitialCourseContents(CourseContentsBean courseContents) {
	con = DatabaseConnection.getConnection();
	
	String CHECK_IF_USER_EXISTS_SQL = "SELECT username FROM user WHERE username =?";
	String CHECK_IF_ALREADY_EXISTS_SQL = "SELECT coursecode, student_username FROM course_contents WHERE coursecode =? AND student_username =?";
	String INSERT_COURSE_SQL = "INSERT INTO course_contents(coursecode, course, student_username, teacher_username, new_student_answer, new_teacher_reply) VALUES (?, ?, ?, ?, ?, ?)";
	int result = 0;
	
	if(con != null) {
		try {
			
			//Checks that student does exist in 'user' table before adding to 'course_contents' table.
			PreparedStatement preparedStatement = con.prepareStatement(CHECK_IF_USER_EXISTS_SQL);
			preparedStatement.setString(1, courseContents.getStudentUsername());
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				//System.out.println("user exist");
				preparedStatement = con.prepareStatement(CHECK_IF_ALREADY_EXISTS_SQL);
				preparedStatement.setString(1, courseContents.getCoursecode());
				preparedStatement.setString(2, courseContents.getStudentUsername());
				
				rs = preparedStatement.executeQuery();
				boolean hasNext = rs.next();
				System.out.println(hasNext);
				if(hasNext==false) {
					//System.out.println("false in if");
					preparedStatement = con.prepareStatement(INSERT_COURSE_SQL);
					preparedStatement.setString(1, courseContents.getCoursecode());
					preparedStatement.setString(2, courseContents.getCourse());
					preparedStatement.setString(3, courseContents.getStudentUsername());
					preparedStatement.setString(4, courseContents.getTeacherUsername());
					preparedStatement.setString(5, "false");
					preparedStatement.setString(6, "false");
	
					
					
					result = preparedStatement.executeUpdate();
				}
				else {
				 System.out.println("true else");
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


	public CourseBean getCourse(String coursecode) {
		con = DatabaseConnection.getConnection();
		
		CourseBean courseBean = new CourseBean();
		
		String SELECT_COURSE_SQL = "SELECT * FROM course WHERE course_code_id='" + coursecode + "'";
		
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_COURSE_SQL);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
				resultSet.next();
				courseBean.setCourseCodeId(resultSet.getString("course_code_id"));
				courseBean.setName(resultSet.getString("name"));
				courseBean.setTeacherId(resultSet.getString("teacher_id"));
				courseBean.setStartDate(resultSet.getString("start_date"));
				courseBean.setEndDate(resultSet.getString("end_date"));
				courseBean.setPace(resultSet.getString("pace"));
				courseBean.setPoints(resultSet.getString("points"));
				courseBean.setExercise1(resultSet.getString("exercise1"));
				courseBean.setExercise2(resultSet.getString("exercise2"));
				courseBean.setExercise3(resultSet.getString("exercise3"));
				courseBean.setExamination(resultSet.getString("examination"));
				System.out.println(resultSet.getString("course_code_id"));
				
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		DatabaseConnection.disconnect(con);
	
	
		return courseBean;
	
	}

	public List<CourseBean> getTeacherCourses(String username) {
		con = DatabaseConnection.getConnection();
		
		
		List<CourseBean> courses = new ArrayList<CourseBean>();
		
		String SELECT_COURSES_SQL = "SELECT * FROM course WHERE teacher_id='" + username + "'";
		
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_COURSES_SQL);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					CourseBean courseBean = new CourseBean();
					courseBean.setCourseCodeId(resultSet.getString("course_code_id"));
					courseBean.setName(resultSet.getString("name"));
					courseBean.setTeacherId(resultSet.getString("teacher_id"));
					courseBean.setStartDate(resultSet.getString("start_date"));
					courseBean.setEndDate(resultSet.getString("end_date"));
					courseBean.setPace(resultSet.getString("pace"));
					courseBean.setPoints(resultSet.getString("points"));
					courses.add(courseBean);
					
				}
				
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		DatabaseConnection.disconnect(con);
	
	
		return courses;
	
	}

public CourseContentsBean getCourseContents(String studentUsername, String coursecode) {
	con = DatabaseConnection.getConnection();
	
	CourseContentsBean courseContentsBean = new CourseContentsBean();
	
	String SELECT_COURSE_CONTENTS_SQL = "SELECT * FROM course_contents WHERE student_username='" + studentUsername + "' AND coursecode='" + coursecode + "'";
	
	
	if(con != null) {
		try {
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_COURSE_CONTENTS_SQL);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				courseContentsBean.setCoursecode(resultSet.getString("coursecode"));
				courseContentsBean.setCourse(resultSet.getString("course"));
				courseContentsBean.setStudentAnswer1(resultSet.getString("student_answer1"));
				courseContentsBean.setStudentAnswer2(resultSet.getString("student_answer2"));
				courseContentsBean.setStudentAnswer3(resultSet.getString("student_answer3"));
				courseContentsBean.setTeacherReply1(resultSet.getString("teacher_reply1"));
				courseContentsBean.setTeacherReply2(resultSet.getString("teacher_reply2"));
				courseContentsBean.setTeacherReply3(resultSet.getString("teacher_reply3"));
				courseContentsBean.setExaminationStudentAnswer(resultSet.getString("examination_student_answer"));
				courseContentsBean.setExaminationTeacherReply(resultSet.getString("examination_teacher_reply"));
			}
			
			
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	DatabaseConnection.disconnect(con);


	return courseContentsBean;
	
}

	public int saveExercises(CourseBean course) {
		con = DatabaseConnection.getConnection();
		
		
		
		String INSERT_COURSE_SQL = "UPDATE course SET exercise1=?, exercise2=?, exercise3=?, examination=? WHERE course_code_id=?";
		int result = 0;
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(INSERT_COURSE_SQL);
				preparedStatement.setString(1, course.getExercise1());
				preparedStatement.setString(2, course.getExercise2());
				preparedStatement.setString(3, course.getExercise3());
				preparedStatement.setString(4, course.getExamination());
				preparedStatement.setString(5, course.getCourseCodeId());
				System.out.println(course.getCourseCodeId());
				
				result = preparedStatement.executeUpdate();
	
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return result;
	}
	
	
	public List<AssignedStudentBean> getStudentCourses(String studentUsername, String teacherUsername) {
		con = DatabaseConnection.getConnection();
		
		
		List<AssignedStudentBean> courses = new ArrayList<AssignedStudentBean>();
		
		String SELECT_COURSES_SQL = "SELECT * FROM assigned_student WHERE username='" + studentUsername + "' AND teacher_id='" + teacherUsername +  "'";
		
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_COURSES_SQL);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					AssignedStudentBean assignedStudentBean = new AssignedStudentBean();
					assignedStudentBean.setCoursecode(resultSet.getString("coursecode"));
					assignedStudentBean.setCourse(resultSet.getString("course"));
					assignedStudentBean.setTeacherId(resultSet.getString("teacher_id"));
					courses.add(assignedStudentBean);
					
				}
				
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		DatabaseConnection.disconnect(con);
	
	
		return courses;
	
	}
	
	/******************* 										   ******************/
	/*******************              STUDENT SPECIFIC METHODS     ******************/
	/******************* 										   ******************/
	
	public List<AssignedStudentBean> getStudentCourses(String studentUsername) {
		con = DatabaseConnection.getConnection();
		
		
		List<AssignedStudentBean> courses = new ArrayList<AssignedStudentBean>();
		
		String SELECT_COURSES_SQL = "SELECT * FROM assigned_student WHERE username='" + studentUsername + "'";
		
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_COURSES_SQL);
				
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					AssignedStudentBean assignedStudentBean = new AssignedStudentBean();
					assignedStudentBean.setCoursecode(resultSet.getString("coursecode"));
					assignedStudentBean.setCourse(resultSet.getString("course"));
					assignedStudentBean.setTeacherId(resultSet.getString("teacher_id"));
					courses.add(assignedStudentBean);
					
				}
				
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		DatabaseConnection.disconnect(con);
	
	
		return courses;
	
	}
	
	public int saveStudentExercises(CourseContentsBean courseContents, String username) {
		con = DatabaseConnection.getConnection();
		
		
		
		String INSERT_COURSE_SQL = "UPDATE course_contents SET student_answer1=?, student_answer2=?, student_answer3=?, examination_student_answer=?, new_student_answer=? WHERE coursecode=? AND student_username=?";
		int result = 0;
		
		if(con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(INSERT_COURSE_SQL);
				preparedStatement.setString(1, courseContents.getStudentAnswer1());
				preparedStatement.setString(2, courseContents.getStudentAnswer2());
				preparedStatement.setString(3, courseContents.getStudentAnswer3());
				preparedStatement.setString(4, courseContents.getExaminationStudentAnswer());
				preparedStatement.setString(5, "true");
				preparedStatement.setString(6, courseContents.getCoursecode());
				preparedStatement.setString(7, username);
				
				result = preparedStatement.executeUpdate();
	
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DatabaseConnection.disconnect(con);
		
		
		return result;
	}
	
	
}
	

