<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% if(session.getAttribute("username") == null){
    response.sendRedirect("login.jsp");
}%>

<!DOCTYPE html>
<html>
	<!------------------------------This page lets Admin add or remove courses. ---------------------------------------->
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="CSS/admin/adminCourses.css"/>
		
		<link href="https://fonts.googleapis.com/css2?family=Cormorant+SC:wght@500&family=Nanum+Gothic:wght@400;800&display=swap" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<title>Studyroom Courses</title>
	</head>
	<body>
		
		<%@ include file="header.jsp" %>
		

		
		<div class = "contents">
		
			
			<div class="table_container">
				
				<div class="table_title">
					<h1>Courses</h1>
				</div>
				
					<form action="<%= request.getContextPath() %>/AdminController" id="add_form" method="get">
						<input type="hidden" name="action" value="add_course"/>
					</form>
				
				
				
				
				<table class="table">
					<thead>
						<tr>
							<th>Course code ID</th>
							<th>Name</th>
							<th>Teacher ID</th>
							<th>Start date</th>
							<th>End date</th>
							<th>Pace</th>
							<th>Points</th>
							<th>Actions:</th>
						</tr>
					</thead>
					<tbody>
						
						<tr> 
								
							<td><input type="text" name="course_code_id" form="add_form"/></td>
							<td><input type="text" name="name" form="add_form"/></td>
							<td><input type="text" name="teacher_id" form="add_form"/></td>
							<td><input type="text" name="start_date" form="add_form"/></td>
							<td><input type="text" name="end_date" form="add_form"/></td>
							<td><input type="text" name="pace" form="add_form"/></td>
							<td><input type="text" name="points" form="add_form"/></td>
							<td><input type="submit" name="add_button" value="Add" form="add_form"/></td>
								
						</tr>
						
						<c:forEach items="${courseList}" var="courseLists" varStatus="status">
							
							<form action="<%= request.getContextPath() %>/AdminController" id="edit_form${status.index}" method="get">
								<input type="hidden" name="action" value="edit_course"/>
							</form>
							
							<form action="<%= request.getContextPath() %>/AdminController" id="delete_form${status.index}" method="get">
								<input type="hidden" name="action" value="delete_course"/>	
							</form>
							
						<tr>
							<td><input type="text" value="${courseLists.courseCodeId}" name="course_code_id" id="edit" form="edit_form${status.index}"/></td>
							<td><input type="text" value="${courseLists.name}" name="name" id="edit" form="edit_form${status.index}"></td>
							<td><input type="text" value="${courseLists.teacherId}" name="teacher_id" id="edit" form="edit_form${status.index}"></td>
							<td><input type="text" value="${courseLists.startDate}" name="start_date" id="edit" form="edit_form${status.index}"></td>
							<td><input type="text" value="${courseLists.endDate}" name="end_date" id="edit" form="edit_form${status.index}"></td>
							<td><input type="text" value="${courseLists.pace}" name="pace" id="edit" form="edit_form${status.index}"></td>
							<td><input type="text" value="${courseLists.points}" name="points" id="edit" form="edit_form${status.index}"></td>
							
							<td><input type="hidden" name="course" value="${courseLists.name}" form="delete_form${status.index}"/>
								<input type="hidden" name="course" value="${courseLists.name}" form="edit_form${status.index}"/>
								<div class="buttons">
									<input type="submit" value="Save edit" form="edit_form${status.index}" class="buttons">
									<input type="submit" value="Delete" form="delete_form${status.index}" class="buttons">
								</div>
							</td>
							
						</tr>
						
						</c:forEach>
						
					</tbody>	
				</table>
				
			</div>
			
		</div>
		

	</body>
</html>