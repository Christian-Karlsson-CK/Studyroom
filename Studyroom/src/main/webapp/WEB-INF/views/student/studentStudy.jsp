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
		<link rel="stylesheet" type="text/css" href="CSS/teacher/handleCourses.css"/>
		
		<link href="https://fonts.googleapis.com/css2?family=Cormorant+SC:wght@500&family=Nanum+Gothic:wght@400;800&display=swap" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<title>Studyroom Courses</title>
	</head>
	<body>
		
		<%@ include file="header.jsp" %>
		

		
		<div class = "contents">
		
			
			<div class="table_container">
				
				<div class="table_title">
					<h1>Your Courses</h1>
				</div>
				
				<table class="table">
					<thead>
						<tr>
							<th>Course</th>
							<th>Code</th>
							<th>Action:</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${courseList}" var="course" varStatus="status">
							
							<form action="<%= request.getContextPath() %>/StudentController" id="course_form${status.index}" method="get">
								<input type="hidden" name="action" value="study_course"/>
								<input type="hidden" name="coursecode" value="${course.coursecode}"/>
							</form>
							
							
						<tr>
							<td>${course.course}</td>
							<td>${course.coursecode}</td>
							<td><input type="submit" value="Study" form="course_form${status.index}" class="buttons"></td>
							
						</tr>
						
						</c:forEach>
					</tbody>	
				</table>
				
			</div>
			
			<div class="table_container2">
				
				<div class="table_title">
					<h1>Selected Course</h1>
				</div>
				
				<c:if test = "${not empty course.courseCodeId}">
				<table class="table">
					<thead>
						<tr>
							<th>Course</th>
							<th>Code</th>
							<th>Start date</th>
							<th>End date</th>
							<th>Pace</th>
							<th>Points</th>
						</tr>
					</thead>
					
					<tbody>
						
							<tr>
								<td>${course.courseCodeId}</td>
								<td>${course.name}</td>
								<td>${course.startDate}</td>
								<td>${course.endDate}</td>
								<td>${course.pace}</td>
								<td>${course.points}</td>
							</tr>
						

					</tbody>	
				</table>
				
				<form action="<%= request.getContextPath() %>/StudentController" method="get" >
				<input type="hidden" name="action" value="save_exercises">
				<input type="hidden" name="coursecode" value="${courseContents.coursecode}">
				<div class="exercise_container">
					
					<h2>Exercises 1</h2>
					<textarea class="exercise" placeholder="Teacher have yet to add an exercise." readonly>${course.exercise1}</textarea>
					<h3>Your Answer</h3>
					<textarea class="exercise" name="student_answer1" placeholder="Write your answer here..." >${courseContents.studentAnswer1}</textarea>
					<h3>Teacher feedback</h3>
					<textarea class="exercise" readonly>${courseContents.teacherReply1}</textarea>
					
					
					<h2>Exercises 2</h2>
					<textarea class="exercise" placeholder="Teacher have yet to add an exercise." readonly>${course.exercise2}</textarea>
					<h3>Your Answer</h3>
					<textarea class="exercise" name="student_answer2" placeholder="Write your answer here...">${courseContents.studentAnswer2}</textarea>
					<h3>Teacher feedback</h3>
					<textarea class="exercise" readonly>${courseContents.teacherReply2} </textarea>
					
					
					<h2>Exercises 3</h2>
					<textarea class="exercise" placeholder="Teacher have yet to add an exercise." readonly>${course.exercise3}</textarea>
					<h3>Your Answer</h3>
					<textarea class="exercise" name="student_answer3" placeholder="Write your answer here...">${courseContents.studentAnswer3}</textarea>
					<h3>Teacher feedback</h3>
					<textarea class="exercise" readonly>${courseContents.teacherReply3}</textarea>
					
				</div>
				
				<div class="examination_container">
					<h2>Examination</h2>
					<textarea class="examination" name="examination" placeholder="Teacher have yet to add an examination." readonly>${course.examination}</textarea>
					<h3>Your Answer</h3>
					<textarea class="exercise" name="student_examination_answer" placeholder="Write your answer here..." >${courseContents.examinationStudentAnswer}</textarea>
					<h3>Teacher feedback</h3>
					<textarea class="exercise"  readonly>${courseContents.examinationTeacherReply}</textarea>
				</div>
				<input type="submit" class="send" value="Save Exercises">
				</form>
				</c:if>
			</div>

		</div>
		
		
		
		
	</body>
</html>