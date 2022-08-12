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
							
							<form action="<%= request.getContextPath() %>/TeacherController" id="edit_form${status.index}" method="get">
								<input type="hidden" name="action" value="edit_course"/>
								<input type="hidden" name="coursecode" value="${course.courseCodeId}"/>
							</form>
							
							
						<tr>
							<td>${course.name}</td>
							<td>${course.courseCodeId}</td>
							<td><input type="submit" value="Edit" form="edit_form${status.index}" class="buttons"></td>
							
						</tr>
						
						</c:forEach>
					</tbody>	
				</table>
				
			</div>
			
			<div class="table_container2">
				
				<div class="table_title">
					<h1>Selected Course</h1>
				</div>
				
				<c:if test = "${not empty selectedCourse.courseCodeId}">
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
								<td>${selectedCourse.courseCodeId}</td>
								<td>${selectedCourse.name}</td>
								<td>${selectedCourse.startDate}</td>
								<td>${selectedCourse.endDate}</td>
								<td>${selectedCourse.pace}</td>
								<td>${selectedCourse.points}</td>
							</tr>
						

					</tbody>	
				</table>
				
				<form action="<%= request.getContextPath() %>/TeacherController" method="get" >
				<input type="hidden" name="action" value="save_exercises">
				<input type="hidden" name="coursecode" value="${selectedCourse.courseCodeId}">
				<input type="hidden" name="course" value="${selectedCourse.name}">
				<div class="exercise_container">
					
					<h2>Exercises 1</h2>
					<textarea class="exercise" name="exercise1" placeholder="Type exercise 1 here...">${selectedCourse.exercise1}</textarea>
					<h2>Exercises 2</h2>
					<textarea class="exercise" name="exercise2" placeholder="Type exercise 2 here...">${selectedCourse.exercise2}</textarea>
					<h2>Exercises 3</h2>
					<textarea class="exercise" name="exercise3" placeholder="Type exercise 3 here...">${selectedCourse.exercise3}</textarea>
				</div>
				
				<div class="examination_container">
					<h2>Examination</h2>
					<textarea class="examination" name="examination" placeholder="Type examination here...">${selectedCourse.examination}</textarea>
				</div>
				<input type="submit" class="send" value="Save Exercises">
				</form>
				</c:if>
			</div>

		</div>
		
		
		
		
	</body>
</html>