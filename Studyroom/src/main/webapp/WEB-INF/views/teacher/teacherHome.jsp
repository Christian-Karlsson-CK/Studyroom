<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% if(session.getAttribute("first_name") == null){
    response.sendRedirect("login.jsp");
}%>

<!DOCTYPE html>
<html>
	<!------------------------------This page is the first page for the teachers after login. ---------------------------------------->
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="CSS/teacher/teacherHome.css"/>
		<link href="https://fonts.googleapis.com/css2?family=Cormorant+Garamond&family=Cormorant+SC:wght@500&family=Nanum+Gothic:wght@400;800&display=swap" rel="stylesheet">
		<title>Studyroom Home</title>
	</head>
	<body>
	
		<%@ include file="header.jsp" %>
		

		
		<div class = "contents">
		
		
			<h1>Welcome <%= session.getAttribute("first_name") %></h1>
			<h3>Here is todays work:	</h3>
			
			<h3>You have ${newStudentAnswerCount} new student exercise/examination answers to examine.</h3>
			<h3>You have ${newStudentMessageCount} new messages.</h3>
			<h3>Have a nice workday!</h3>
		</div>
		
		
		
		
	</body>
</html>