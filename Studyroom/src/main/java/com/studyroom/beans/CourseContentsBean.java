package com.studyroom.beans;

/**  
 * 
 * 
 * This bean can hold data on student answers and teacher reply on exercise/examination on various courses.
 * 
 * 
 * **/

public class CourseContentsBean {
	private String coursecode;
	private String course;
	private String studentUsername;
	private String teacherUsername;
	private String studentAnswer1;
	private String studentAnswer2;
	private String studentAnswer3;
	private String teacherReply1;
	private String teacherReply2;
	private String teacherReply3;
	private String newStudentAnswer;
	private String newTeacherReply;
	private String examinationStudentAnswer;
	private String examinationTeacherReply;
	
	public String getCoursecode() {
		return coursecode;
	}
	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getStudentAnswer1() {
		return studentAnswer1;
	}
	public void setStudentAnswer1(String studentAnswer1) {
		this.studentAnswer1 = studentAnswer1;
	}
	public String getStudentAnswer2() {
		return studentAnswer2;
	}
	public void setStudentAnswer2(String studentAnswer2) {
		this.studentAnswer2 = studentAnswer2;
	}
	public String getStudentAnswer3() {
		return studentAnswer3;
	}
	public void setStudentAnswer3(String studentAnswer3) {
		this.studentAnswer3 = studentAnswer3;
	}
	public String getTeacherReply1() {
		return teacherReply1;
	}
	public void setTeacherReply1(String teacherReply1) {
		this.teacherReply1 = teacherReply1;
	}
	public String getTeacherReply2() {
		return teacherReply2;
	}
	public void setTeacherReply2(String teacherReply2) {
		this.teacherReply2 = teacherReply2;
	}
	public String getTeacherReply3() {
		return teacherReply3;
	}
	public void setTeacherReply3(String teacherReply3) {
		this.teacherReply3 = teacherReply3;
	}
	
	public String getExaminationTeacherReply() {
		return examinationTeacherReply;
	}
	public void setExaminationTeacherReply(String examinationTeacherReply) {
		this.examinationTeacherReply = examinationTeacherReply;
	}
	public String getExaminationStudentAnswer() {
		return examinationStudentAnswer;
	}
	public void setExaminationStudentAnswer(String examinationStudentAnswer) {
		this.examinationStudentAnswer = examinationStudentAnswer;
	}
	public String getStudentUsername() {
		return studentUsername;
	}
	public void setStudentUsername(String studentUsername) {
		this.studentUsername = studentUsername;
	}
	public String getTeacherUsername() {
		return teacherUsername;
	}
	public void setTeacherUsername(String teacherUsername) {
		this.teacherUsername = teacherUsername;
	}
	public String getNewStudentAnswer() {
		return newStudentAnswer;
	}
	public void setNewStudentAnswer(String newStudentAnswer) {
		this.newStudentAnswer = newStudentAnswer;
	}
	public String getNewTeacherReply() {
		return newTeacherReply;
	}
	public void setNewTeacherReply(String newTeacherReply) {
		this.newTeacherReply = newTeacherReply;
	}


	
}
