<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<% if(session.getAttribute("first_name") == null){
    response.sendRedirect("login.jsp");
}%>

    
<!DOCTYPE html>
<html>
	<!------------------------------This page serves as a chat to the teachers. ---------------------------------------->
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		
		
		<link rel="stylesheet" type="text/css" href="CSS/student/messages.css"/>
		
		<link href="https://fonts.googleapis.com/css2?family=Cormorant+SC:wght@500&family=Nanum+Gothic:wght@400;800&display=swap" rel="stylesheet">
		<title>Studyroom Messages</title>
	</head>
	
	<body>
		
		<%@ include file="header.jsp" %>
		
		<div class = "contents">
			
			<div class="users_list">
				<p>Your Teachers:</p>
				
				<div class="courses_sections">
				</div>
				
				<c:forEach items="${friendList}" var="friendList">
					
					<a href="StudentController?action=messages_click_friend&friend=${friendList.username}&message_date=${friendList.lastSentMessageDate}&friend_fullname=${friendList.firstname} ${friendList.lastname}">
					<div class= "user">
						<div class= "user_row">
						<img class="img" alt="icon" src="RESOURCES/IMAGES/user_icon.png">
						<span class="name" id="name">${friendList.firstname} ${friendList.lastname}</span>
						<span class="date" id="date"> <c:if test="${empty friendList.lastSentMessageDate}">
						
							
							</c:if>
							
							<c:if test="${not empty friendList.lastSentMessageDate}">
							Sent ${friendList.lastSentMessageDate}
							
							</c:if>
						</span>
						</div>
						<i class="message"><c:if test="${empty friendList.lastSentMessage}">
							No messages recieved yet.
							
							</c:if>
							
							<c:if test="${not empty friendList.lastSentMessage}">
							<c:if test ="${friendList.newMessagesCount ne 0}"><span class="new_messages"> ${friendList.newMessagesCount}  <img class="message_icon" alt="icon" src="RESOURCES/IMAGES/new_message.png"> </span></c:if>  ${friendList.lastSentMessage}
							
							</c:if></i>
					</div>
					</a>
					
				</c:forEach>
				
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
					<form action="<%= request.getContextPath() %>/StudentController?action=send_message" method="get" class="typing_form">
						<input type="hidden" name="action" value="send_message">
						
						<textarea class="input" name="input" placeholder="Type message here..."></textarea>
						<input type="submit" class="send" value="Send">
					</form>
				</div>
				
				
				
			</div>
			
		
			
			
			
		</div>
	</body>
</html>