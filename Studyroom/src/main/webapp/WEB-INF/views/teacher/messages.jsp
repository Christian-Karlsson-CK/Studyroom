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
		
		
		
		<link rel="stylesheet" type="text/css" href="CSS/teacher/messages.css"/>
		
		<link href="https://fonts.googleapis.com/css2?family=Cormorant+SC:wght@500&family=Nanum+Gothic:wght@400;800&display=swap" rel="stylesheet">
		<title>Studyroom Messages</title>
	</head>
	
	<body>
		
		<%@ include file="header.jsp" %>
		
		<div class = "contents">
			
			<div class="users_list">
				<h3>Your students:</h3>
				<div class="courses_sections">
					<h4>Math B students</h4>
				</div>
				
				<c:forEach items="${friendList}" var="friendLists">
					
					<a href="TeacherController?action=messages_click_friend&friend=${friendLists.username}&message_date=${friendLists.lastSentMessageDate}&friend_fullname=${friendLists.firstname} ${friendLists.lastname}">
					<div class= "user">
						<div class= "user_row">
						<img class="img" alt="icon" src="RESOURCES/IMAGES/user_icon.png">
						<span class="name" id="name">${friendLists.firstname} ${friendLists.lastname}</span>
						<span class="date" id="date"> <c:if test="${empty friendLists.lastSentMessageDate}">
						
							
							</c:if>
							
							<c:if test="${not empty friendLists.lastSentMessageDate}">
							Sent ${friendLists.lastSentMessageDate}
							
							</c:if>
						</span>
						</div>
						<i class="message"><c:if test="${empty friendLists.lastSentMessage}">
							No messages recieved yet.
							
							</c:if>
							
							<c:if test="${not empty friendLists.lastSentMessage}">
							<c:if test ="${friendLists.newMessagesCount ne 0}"><span class="new_messages"> ${friendLists.newMessagesCount} <img class="message_icon" alt="icon" src="RESOURCES/IMAGES/new_message.png">   </span></c:if> ${friendLists.lastSentMessage}
							
							</c:if></i>
					</div>
					</a>
					
				</c:forEach>
				
				
				<div class="courses_sections">
					<h4>Religion A students</h4>
				</div>
				
				<div class= "user">
					<div class= "user_row">
					<img class="img" alt="icon" src="RESOURCES/IMAGES/user_icon.png">
					<span class="name">Steven Smartson</span>
					<span class="date">Sent 2022-04-10 </span>
					</div>
					<i class="message">Hey I toooolooont understand a thing plsss help me!</i>
				</div>
				
				
								
			</div>
			
			<div class="message_area">
				<div class="user_info">
					<img class="img" alt="icon" src="RESOURCES/IMAGES/user_icon.png">
					<h3>${friendFullname}</h3>
					<span class="date">Last message recieved: <c:out value="${message_date}"/></span>
				</div>	
				
				<div class="message_box_scroll">
					<div class="message_box">
					
						<c:forEach items="${messageList}" var="messageLists">
						<div <c:if test="${messageLists.creatorId eq username}">class="outgoing_message"</c:if>
							<c:if test="${messageLists.creatorId ne username}">class="incomming_message"</c:if>>
							<img class="img" alt="icon" src="RESOURCES/IMAGES/user_icon.png">
							<p>${messageLists.message}</p>
						</div>
						
						</c:forEach>
						
					
					</div>
				</div>
				
				<div class="typing_area">
					<form action="<%= request.getContextPath() %>/TeacherController?action=send_message" method="get" class="typing_form">
						<input type="hidden" name="action" value="send_message">
						
						<textarea class="input" name="input" placeholder="Type message here..."></textarea>
						<input type="submit" class="send" value="Send">
					</form>
				</div>
				
				
				
			</div>
			
			
		
			
			
			
		</div>
	</body>
</html>