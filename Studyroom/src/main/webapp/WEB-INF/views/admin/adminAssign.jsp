<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% if(session.getAttribute("username") == null){
    response.sendRedirect("login.jsp");
}%>
    
<!DOCTYPE html>
<html>

<!------------------------------This page lets Admin assign students to various courses. ---------------------------------------->
	
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="CSS/admin/adminAssign.css"/>
		
		<link href="https://fonts.googleapis.com/css2?family=Cormorant+SC:wght@500&family=Nanum+Gothic:wght@400;800&display=swap" rel="stylesheet">
		<title>Studyroom Assign Students</title>
	</head>
	
	<body>
		
		<%@ include file="header.jsp" %>
		
		<div class = "contents">
		
			
			<div class="table_container">
				
				<div class="table_title">
					<h1>Assign Students</h1>
				</div>
				
			<!-------------------------------------------------------------------------------------------------------------------------->
			<!-----This area bellow displays all courses in a seperate table with all enrolled student after each respective course.---->
			<!------------------------------‐--‐---------------------------------------------------------------------------------------->
				
				
				<c:forEach items="${courseList}" var="courseLists" varStatus="status">
					
					<form action="<%= request.getContextPath() %>/AdminController" id="add_form${status.index}" method="get">
						<input type="hidden" name="action" value="add_student"/>
						<input type="hidden" name="coursecode" value="${courseLists.courseCodeId}"/>
						<input type="hidden" name="course" value="${courseLists.name}"/>
						<input type="hidden" name="teacher_id" value="${courseLists.teacherId}"/>
					</form>
				
					<h1 class="course_h1">${courseLists.name}  /  ${courseLists.courseCodeId}</h1>
					<table class="table">
						<thead>
							<tr>
								<th>Personal ID</th>
								<th>Firstname</th>
								<th>Lastname</th>
								<th>Username</th>
								<th>Actions:</th>
							</tr>
						</thead>
						<tbody>
							<tr> 	
								<td></td>
								<td></td>
								<td></td>
								<td><input type="text" name="username" form="add_form${status.index}"/></td>
								<td><input type="submit" name="add_button" value="Add Student" form="add_form${status.index}"/></td>
							</tr>
							
							<c:forEach items="${assignedStudentList}" var="assignedStudent" varStatus="status">
								<c:if test="${assignedStudent.coursecode eq courseLists.courseCodeId}">
         							<form action="<%= request.getContextPath() %>/AdminController" id="delete_form${status.index}" method="get">
										<input type="hidden" name="action" value="delete_student"/>
										<input type="hidden" name="coursecode" value="${assignedStudent.coursecode}"/>
									</form>
								
									<tr>
										<td>${assignedStudent.personalId}</td>
										<td>${assignedStudent.firstname}</td>
										<td>${assignedStudent.lastname}</td>
										<td>${assignedStudent.username}</td>
										<td><input type="hidden" name="username" value="${assignedStudent.username}" form="delete_form${status.index}"/>
											<div class="buttons">
												<input type="submit" value="Delete" form="delete_form${status.index}" class="buttons">
											</div>

										</td>
									
								</tr>
								</c:if>
							</c:forEach>
     						
								
							
						</tbody>
					</table>
				</c:forEach>
			</div>
			
		</div>

	</body>
</html>