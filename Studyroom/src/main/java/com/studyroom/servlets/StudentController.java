package com.studyroom.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.studyroom.beans.AssignedStudentBean;
import com.studyroom.beans.CourseBean;
import com.studyroom.beans.CourseContentsBean;
import com.studyroom.beans.FriendBean;
import com.studyroom.beans.MessageBean;
import com.studyroom.beans.UserBean;
import com.studyroom.dao.CourseDAO;
import com.studyroom.dao.MessageDAO;
import com.studyroom.dao.UserDAO;

/**  
 * 
 * 
 * This servlet handles all student requests from all the student pages.
 * It forwards data to either the CourseDAO, UserDAO or the MessageDAO.
 * 
 * 
 * **/
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private UserBean userBean;
	private RequestDispatcher dispatcher;
	private String friend;
	private CourseDAO courseDAO = new CourseDAO();
	private UserDAO userDAO = new UserDAO();
	private MessageDAO messageDAO = new MessageDAO();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String action = request.getParameter("action");
    	String username = (String) session.getAttribute("username");
    	
    	/** 
		 *  The switch statement changes the view to the correct page if a link in the header has been clicked. It also handles all button presses from the student pages.
		 * **/
    	
    	switch(action) {
    		
    		//If 'Study' has been pressed in header.
    		case "to_study":
    			
    			//Gets all courses that the student is assigned to.
    			List<AssignedStudentBean> courseList = courseDAO.getStudentCourses(username);
    			request.setAttribute("courseList", courseList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentStudy.jsp");
    			break;
    		
    		//If the 'study' button has been pressed on the Study page for a course.
    		case "study_course":
    			
    			//Determine which course the user wants to study.
    			String coursecode = request.getParameter("coursecode");
    			
    			//Get course data
    			CourseBean course = courseDAO.getCourse(coursecode);
    			request.setAttribute("course", course);
    			
    			//Refresh page with course contents like exercises and examination.
    			CourseContentsBean courseContents = courseDAO.getCourseContents(username, coursecode);
    			request.setAttribute("courseContents", courseContents);
    			
    			
    			courseList = courseDAO.getStudentCourses(username);
    			request.setAttribute("courseList", courseList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentStudy.jsp");
    			break;
    			
    		//If the 'save exercises' button has been pressed on the studentStudy.jsp.
    		case "save_exercises":
    			
    			//Find out for what course the exercises belong to.
    			coursecode = request.getParameter("coursecode");
    			
    			//Get all the exercise/examination answers from student and save it to database using CourseDAO.
    			CourseContentsBean courseContentsBean = new CourseContentsBean();
    			courseContentsBean.setCoursecode(request.getParameter("coursecode"));
    			courseContentsBean.setStudentAnswer1(request.getParameter("student_answer1"));
    			courseContentsBean.setStudentAnswer2(request.getParameter("student_answer2"));
    			courseContentsBean.setStudentAnswer3(request.getParameter("student_answer3"));
    			courseContentsBean.setExaminationStudentAnswer(request.getParameter("student_examination_answer"));
    			courseContentsBean.setCoursecode(coursecode);
    			courseDAO.saveStudentExercises(courseContentsBean, username);
    			
    			//Refresh page.
    			course = courseDAO.getCourse(coursecode);
    			request.setAttribute("course", course);
    			
    			courseContents = courseDAO.getCourseContents(username, coursecode);
    			request.setAttribute("courseContents", courseContents);
    			
    			
    			courseList = courseDAO.getStudentCourses(username);
    			request.setAttribute("courseList", courseList);
    			
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentStudy.jsp");
    			break;
    			
    		//If 'messages' has been pressed in header.	
    		case "to_messages":

    			//Get all the friends(teachers) for the user.
    			List<FriendBean> friendList = messageDAO.getStudentFriendList((String)session.getAttribute("username"));
    			request.setAttribute("friendList", friendList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentMessages.jsp");
    			break;
    			
    		//If a friend link has been clicked on the friendlist on studentStudy.jsp.			
    		case "messages_click_friend":
    			
    			//Get friendlist and fill messagearea
    			//If there are new messages from the friend mark them as read in database.
    			messageDAO.setMessagesRead((String)session.getAttribute("username"), (String)request.getParameter("friend"));
    			
    			//Refresh friendlist.
    			friendList = messageDAO.getStudentFriendList((String)session.getAttribute("username"));
    			request.setAttribute("friendList", friendList);
    			
    			//Get the clicked friend.
    			friend = request.getParameter("friend");
    			session.setAttribute("friend", friend);
    			//request.setAttribute("friend", friend);
    			String friendFullname = request.getParameter("friend_fullname");
    			String message_date = request.getParameter("message_date");
    			//request.setAttribute("friendFullname", friendFullname);
    			//request.setAttribute("message_date", message_date);
    			session.setAttribute("friendFullname", friendFullname);
    			session.setAttribute("message_date", message_date);
    			
    			
    			
    			//Get all messages.
    			List<MessageBean> messageList = messageDAO.getMessageList((String)session.getAttribute("username"), friend);
    			request.setAttribute("messageList", messageList);
    			
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentMessages.jsp");
    			break;
    			
    		//if button 'send' has been pressed on studentMessages.jsp
    		case "send_message":
    			//save the written message to database.
    			MessageBean inputMessage = new MessageBean();
    			
    			inputMessage.setMessage(request.getParameter("input"));
    			inputMessage.setCreatorId((String) session.getAttribute("username"));
    			inputMessage.setRecipientId((String) session.getAttribute("friend"));
    			inputMessage.setIsRead(false);
    			
    			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    			LocalDateTime now = LocalDateTime.now();
    			System.out.println(dtf.format(now)); 
    			
    			String dateTime = dtf.format(now);
    			inputMessage.setCreateDate(dateTime);
    			
    			messageDAO.addMessage(inputMessage);
    			
    			//Refresh page
    			friendList = messageDAO.getStudentFriendList((String)session.getAttribute("username"));
    			request.setAttribute("friendList", friendList);
    			
    			
    			
    			
    			messageList = messageDAO.getMessageList((String)session.getAttribute("username"), friend);
    			request.setAttribute("messageList", messageList);
    			
    			
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentMessages.jsp");
    			break;
    			
    		//If 'logout' has been pressed in header.	
    		case "do_logout":
    			session.invalidate();
    			dispatcher = request.getRequestDispatcher("login.jsp");
    			break;
    			
    		//If the users name has been pressed in header.
    		case "to_user_profile":
    			
    			//Count exercises/examination that teacher has done a reply to.
    			int newTeacherReplyCount = courseDAO.getNewTeacherReplyCount(username);
    			Integer wrapper = newTeacherReplyCount;
    			String answerCount = wrapper.toString();
    			request.setAttribute("newTeacherReplyCount", answerCount);
    			
    			//Count unread messages to the logged in user.
    			int newTeacherMessageCount = messageDAO.getNewMessagesCount(username);
    			request.setAttribute("newStudentMessageCount", newTeacherMessageCount);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentHome.jsp");
    			break;
    		
    		//Should never reach the default but just in case of a bug.
    		default:
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentHome.jsp"); 
    			break;
    	}
    		dispatcher.forward(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//After successfull login as student set username as a session attribute.
		session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		//Get logged in user data.
		userBean = userDAO.getUserData(username);
		
		//This is used to set the name in the header.
		session.setAttribute("first_name", userBean.getFirstname());
		
		
		//Count exercises/examination that teacher has done a reply to.
		int newTeacherReplyCount = courseDAO.getNewTeacherReplyCount(username);
		Integer wrapper = newTeacherReplyCount;
		String answerCount = wrapper.toString();
		request.setAttribute("newTeacherReplyCount", answerCount);
		
		//Count unread messages to the logged in user.
		int newTeacherMessageCount = messageDAO.getNewMessagesCount(username);
		request.setAttribute("newTeacherMessageCount", newTeacherMessageCount);
		
		
		dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentHome.jsp"); 	
		dispatcher.forward(request, response);
	}

}
