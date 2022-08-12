<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% if(session.getAttribute("first_name") == null){
    response.sendRedirect("login.jsp");
}%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="CSS/teacher/examineStudents.css"/>
		
		<link href="https://fonts.googleapis.com/css2?family=Cormorant+SC:wght@500&family=Nanum+Gothic:wght@400;800&display=swap" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<title>Studyroom Courses</title>
	</head>
	<body>
		
		<%@ include file="header.jsp" %>
		

		
		<div class = "contents">
		
			
			<div class="table_container">
				
				<div class="table_title">
					<h1>Your Students</h1>
				</div>
				
				<table class="table">
					<thead>
						<tr>
							<th>Name</th>
							<th>Username</th>
							<th>Action:</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${studentList}" var="student" varStatus="status">
							
							<form action="<%= request.getContextPath() %>/TeacherController" id="student_form${status.index}" method="get">
								<input type="hidden" name="action" value="select_student"/>
								<input type="hidden" name="student" value="${student.username}"/>
								<input type="hidden" name="student_name" value="${student.firstname} ${student.lastname}"/>
							</form>
							
							
						<tr>
							<td>${student.firstname} ${student.lastname}</td>
							<td>${student.username}</td>
							<td>
								<input type="submit" value="Examine" form="student_form${status.index}" class="buttons">
								
							</td>
							
						</tr>
						
						</c:forEach>
					</tbody>	
				</table>
				
			</div>
			
			<div class="table_container2">
				<c:if test = "${not empty student}">
				<div class="Student_title">
					<h1> ${studentName} Assigned courses:</h1>
				</div>
				
				<table class="table">
					<thead>
						<tr>
							<th>Course</th>
							<th>Completed exercises</th>
							<th>Action:</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${courses}" var="course" varStatus="status">
							
							<form action="<%= request.getContextPath() %>/TeacherController" id="course_form${status.index}" method="get">
								<input type="hidden" name="action" value="select_student_course"/>
								<input type="hidden" name="student" value="${student}"/>
								<input type="hidden" name="student_name" value="${studentName}"/>
								<input type="hidden" name="coursecode" value="${course.coursecode}">
								
							</form>
							<tr>
								<td>${course.course}</td>
								<td>${course.coursecode}</td>
								<td><input type="submit" class="Check" value="Check" form="course_form${status.index}"></td>
							</tr>
						</c:forEach>

					</tbody>	
				</table>
				</c:if>
				<c:if test = "${not empty selectedCourse}">
				<form action="<%= request.getContextPath() %>/TeacherController" method="get" >
				<input type="hidden" name="action" value="save_examinations">
				<input type="hidden" name="coursecoded" value="${selectedCourse.coursecode}">
				<input type="hidden" name="course" value="${selectedCourse.course}">
				<input type="hidden" name="student" value="${student}"/>
				<input type="hidden" name="student_name" value="${studentName}"/>
				<div class="exercise_container">
					
					<h2>Exercises 1</h2>
					<textarea class="exercise" placeholder="You have yet to add and exercise, go to Handle Courses and fill in an exercise." readonly>${course.exercise1}</textarea>
					<h3>Student Answer</h3>
					<textarea class="exercise" placeholder="Student have yet to answer..." readonly>${selectedCourse.studentAnswer1}</textarea>
					<h3>Teacher feedback</h3>
					<textarea class="exercise" placeholder="Type your answer here..." >${selectedCourse.teacherReply1}</textarea>
					
					
					<h2>Exercises 2</h2>
					<textarea class="exercise" placeholder="You have yet to add and exercise, go to Handle Courses and fill in an exercise." readonly>${course.exercise2}</textarea>
					<h3>Student Answer</h3>
					<textarea class="exercise" placeholder="Student have yet to answer..." readonly>${selectedCourse.studentAnswer2}</textarea>
					<h3>Teacher feedback</h3>
					<textarea class="exercise" placeholder="Type your answer here..." >${selectedCourse.teacherReply2}</textarea>
					
					
					<h2>Exercises 3</h2>
					<textarea class="exercise" placeholder="You have yet to add and exercise, go to Handle Courses and fill in an exercise." readonly>${course.exercise3}</textarea>
					<h3>Student Answer</h3>
					<textarea class="exercise" placeholder="Student have yet to answer..." readonly>${selectedCourse.studentAnswer3}</textarea>
					<h3>Teacher feedback</h3>
					<textarea class="exercise" placeholder="Type your answer here..." >${selectedCourse.teacherReply3}</textarea>
					
				</div>
				
				<div class="examination_container">
					<h2>Examination</h2>
					<textarea class="examination" name="examination" placeholder="You have yet to add and Examination test, go to Handle Courses and fill in an examination test.">${course.examination}</textarea>
					<h3>Student Answer</h3>
					<textarea class="exercise" placeholder="Student have yet to answer..." readonly>${selectedCourse.examinationStudentAnswer}</textarea>
					<h3>Teacher feedback</h3>
					<textarea class="exercise" placeholder="Type your answer here..." >${selectedCourse.examinationTeacherReply}</textarea>
				</div>
				<input type="submit" class="send" value="Save Exercises">
				</form>
				</c:if>
			</div>

		</div>
		
		
		
		
	</body>
</html>