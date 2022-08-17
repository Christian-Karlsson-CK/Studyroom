<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<!------------------------------This page is the login page and sends data to the LoginController ---------------------------------------->
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="CSS/login.css"/>
		
		<link href="https://fonts.googleapis.com/css2?family=Cormorant+SC:wght@500&family=Nanum+Gothic:wght@400;800&display=swap" rel="stylesheet">
		<title>Studyroom Login</title>
	</head>
	<body>
		
		<input type="hidden" id="status_holder" value="<%= session.getAttribute("status") %>">
		<input type="hidden" id="username_from_servlet" value="<%= session.getAttribute("username") %>">
		
		
		
		<div>
			<img class = "logo" alt="studyroom" src="RESOURCES/IMAGES/logo.jpg">
		</div>
		
		<div class="form_container">
			<h1>Login</h1>
			
			<form action="<%= request.getContextPath() %>/login" method="post">
	
				<div class="username_row">
					<input id="user_name" type="text" name="username" value="" required>
					<span></span>
					<label>Username</label>
				</div>
				<p class="error_texts" id="error_username"style="visibility: hidden">Username does not exist!</p>
				
				<div class="password_row">
					<input type="password" name="password" required>
					<span></span>
					<label>Password</label>
				</div>
				<p class="error_texts" id="error_password"style="visibility: hidden">Wrong password!</p>
				
				<input type="submit" value="Login">
				
				
				
				
				
				<script type="text/javascript">
					
						
						var status = document.getElementById("status_holder").value;
						
						var username_from_servlet = document.getElementById("username_from_servlet").value;
						  if(username_from_servlet != "null") {
							  document.getElementById("user_name").value= username_from_servlet;
						  }
						
						
						if(status == "wrong_username"){
							document.getElementById("error_username").style="visibility: visible";
						}
						else if(status == "wrong_password"){
							document.getElementById("error_password").style="visibility: visible";
						}
						else{
							
							document.getElementById("error_username").style="visibility: hidden";
							document.getElementById("error_username").style="visibility: hidden";
						}
						
					
				</script>
				
				
			</form>
		
		</div>
		
	</body>
</html>