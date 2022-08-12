<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="CSS/teacher/header.css"/>
	<link href="https://fonts.googleapis.com/css2?family=Cormorant+Garamond&family=Cormorant+SC:wght@500&family=Nanum+Gothic:wght@400;800&display=swap" rel="stylesheet">
	</head>
<body>

	<div class="header">
		
			<div class="inner_header">
			
				<div class="logo_container">
				
				<img class = "logo" alt="studyroom" src="RESOURCES/IMAGES/logo.jpg">
				</div>
				
				<ul class="nav">
				<a href="TeacherController?action=to_courses" ><li>Handle Courses</li></a>
				<a href="TeacherController?action=to_examine_students" ><li>Examine Students</li></a>
				<a href="TeacherController?action=to_messages" ><li>Messages</li></a>
				<a href="TeacherController?action=do_logout" class="logout"><li>Logout</li></a>
				<a href="TeacherController?action=to_user_profile" ><li class="first_name"><%= session.getAttribute("first_name") %></li></a>
					
				</ul>
			
			
		</div>

	</div>
</body>
</html>