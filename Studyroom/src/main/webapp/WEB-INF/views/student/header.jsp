<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<!------------------------------This is the header for all student pages. ---------------------------------------->
	<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="CSS/student/header.css"/>
	<link href="https://fonts.googleapis.com/css2?family=Cormorant+Garamond&family=Cormorant+SC:wght@500&family=Nanum+Gothic:wght@400;800&display=swap" rel="stylesheet">
	</head>
<body>

	<div class="header">
		
			<div class="inner_header">
			
				<div class="logo_container">
				
				<img class = "logo" alt="studyroom" src="RESOURCES/IMAGES/logo.jpg">
				</div>
				
				<ul class="nav">
				<a href="StudentController?action=to_study" ><li>Study</li></a>
				<a href="StudentController?action=to_messages" ><li>Messages</li></a>
				<a href="StudentController?action=do_logout" class="logout"><li>Logout</li></a>
				<a href="StudentController?action=to_home" ><li class="first_name"><%= session.getAttribute("first_name") %></li></a>
					
				</ul>
			
			
		</div>

	</div>
</body>
</html>