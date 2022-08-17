<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% if(session.getAttribute("username") == null){
    response.sendRedirect("login.jsp");
}%>

<!DOCTYPE html>
<html>
	<!------------------------------This page lets Admin add, edit or remove users. ---------------------------------------->
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" type="text/css" href="CSS/admin/adminUsers.css"/>
		
		<link href="https://fonts.googleapis.com/css2?family=Cormorant+SC:wght@500&family=Nanum+Gothic:wght@400;800&display=swap" rel="stylesheet">
		<title>Studyroom Users</title>
	</head>
	
	
	<body>
		<%@ include file="header.jsp" %>
		

		
		<div class = "contents">
			
			<div class="table_container">
				
				<div class="table_title">
					<h1>Users</h1>
				</div>
				
				<form action="<%= request.getContextPath() %>/AdminController" id="add_form" method="get">
					<input type="hidden" name="action" value="add_user"/>
				</form>
				
				
				
				
				<table class="table">
					<thead>
						<tr>
							<th>Personal ID</th>
							<th>Firstname</th>
							<th>Lastname</th>
							<th>Username</th>
							<th>Password</th>
							<th>Usertype</th>
							<th>Actions:</th>
						</tr>
					</thead>
					<tbody>
						
						<tr>
								
							<td><input type="text" name="personal_id" form="add_form"/></td>
							<td><input type="text" name="firstname" form="add_form"/></td>
							<td><input type="text" name="lastname" form="add_form"/></td>
							<td><input type="text" name="username" form="add_form"/></td>
							<td><input type="text" name="password" form="add_form"/></td>
							<td><select name="usertype" form="add_form" id="edit">
							<option value="" selected disabled hidden>Choose</option>
						    <option value="student">student</option>
						    <option value="teacher">teacher</option>
						  	</select></td>
							<td><input type="submit" name="add_button" value="Add" form="add_form"/></td>
								
						</tr>
						
						<c:forEach items="${userList}" var="userLists" varStatus="status">
						<form action="<%= request.getContextPath() %>/AdminController" id="delete_form${status.index}" method="get">
							<input type="hidden" name="action" value="delete_user"/>	
						</form>
				
						<form action="<%= request.getContextPath() %>/AdminController" id="edit_form${status.index}" method="get">
							<input type="hidden" name="action" value="edit_user"/>	
						</form>
						<tr>
							<td><input type="text" value="${userLists.personalId}" name="personal_id" id="edit" form="edit_form${status.index}"/></td>
							<td><input type="text" value="${userLists.firstname}" name="firstname" id="edit" form="edit_form${status.index}"></td>
							<td><input type="text" value="${userLists.lastname}" name="lastname" id="edit" form="edit_form${status.index}"></td>
							<td><input type="text" value="${userLists.username}" name="username" id="edit" form="edit_form${status.index}"></td>
							<td><input type="text" value="${userLists.password}" name="password" id="edit" form="edit_form${status.index}"></td>
							<td><select name="usertype" form="edit_form${status.index}" id="edit">
								<option value="" selected disabled hidden>${userLists.usertype}</option>
							    <option value="student">student</option>
							    <option value="teacher">teacher</option>
							  	</select>
							</td>
							<!-- <td><input type="text" value="${userLists.usertype}" name="usertype" id="edit" form="edit_form"></td>
							-->
							<td><input type="hidden" name="user" value="${userLists.username}" form="delete_form${status.index}"/>
								<input type="hidden" name="user" value="${userLists.username}" form="edit_form${status.index}"/>
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