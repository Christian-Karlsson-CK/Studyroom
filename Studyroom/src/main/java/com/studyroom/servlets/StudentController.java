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
 * Servlet implementation class StudentController
 */
@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private UserBean userBean;
	private RequestDispatcher dispatcher;
	private FriendBean friendBean;
	private String friend;
	private CourseDAO courseDAO;
	private CourseBean course;
	private UserDAO userDAO;
	
       
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
    	CourseDAO courseDAO = new CourseDAO();
    	String username = (String) session.getAttribute("username");
    	MessageDAO messageDAO = new MessageDAO();
    	
    	switch(action) {
    				
    		case "to_study":
    			
    			
    			List<AssignedStudentBean> courseList = courseDAO.getStudentCourses(username);
    			request.setAttribute("courseList", courseList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentStudy.jsp");
    			break;
    			
    		case "study_course":
    			
    			String coursecode = request.getParameter("coursecode");
    			
    			CourseBean course = courseDAO.getCourse(coursecode);
    			request.setAttribute("course", course);
    			
    			CourseContentsBean courseContents = courseDAO.getCourseContents(username, coursecode);
    			request.setAttribute("courseContents", courseContents);
    			
    			
    			courseList = courseDAO.getStudentCourses(username);
    			request.setAttribute("courseList", courseList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentStudy.jsp");
    			break;
    			
    		
    		case "save_exercises":
    			
    			coursecode = request.getParameter("coursecode");
    			
    			CourseContentsBean courseContentsBean = new CourseContentsBean();
    			courseContentsBean.setCoursecode(request.getParameter("coursecode"));
    			courseContentsBean.setStudentAnswer1(request.getParameter("student_answer1"));
    			courseContentsBean.setStudentAnswer2(request.getParameter("student_answer2"));
    			courseContentsBean.setStudentAnswer3(request.getParameter("student_answer3"));
    			courseContentsBean.setExaminationStudentAnswer(request.getParameter("student_examination_answer"));
    			
    			courseContentsBean.setCoursecode(coursecode);
    			courseDAO.saveStudentExercises(courseContentsBean, username);

    			course = courseDAO.getCourse(coursecode);
    			request.setAttribute("course", course);
    			
    			courseContents = courseDAO.getCourseContents(username, coursecode);
    			request.setAttribute("courseContents", courseContents);
    			
    			
    			courseList = courseDAO.getStudentCourses(username);
    			request.setAttribute("courseList", courseList);
    			
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentStudy.jsp");
    			break;
    			
    			
    		case "to_messages":
    			
    			
    			//before dispatching to message page get friendlist of user
    			
    			
    			List<FriendBean> friendList = messageDAO.getStudentFriendList((String)session.getAttribute("username"));
    			request.setAttribute("friendList", friendList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentMessages.jsp");
    			break;
    			
    		case "messages_click_friend":
    			//before dispatching to message page get friendlist and fill messagearea
    			
    			messageDAO.setMessagesRead((String)session.getAttribute("username"), (String)request.getParameter("friend"));
    			
    			friendList = messageDAO.getStudentFriendList((String)session.getAttribute("username"));
    			request.setAttribute("friendList", friendList);
    			
    			friend = request.getParameter("friend");
    			session.setAttribute("friend", friend);
    			request.setAttribute("friend", friend);
    			String friendFullname = request.getParameter("friend_fullname");
    			String message_date = request.getParameter("message_date");
    			request.setAttribute("friendFullname", friendFullname);
    			request.setAttribute("message_date", message_date);
    			session.setAttribute("friendFullname", friendFullname);
    			session.setAttribute("message_date", message_date);
    			
    			
    			
    			
    			List<MessageBean> messageList = messageDAO.getMessageList((String)session.getAttribute("username"), friend);
    			request.setAttribute("messageList", messageList);
    			
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentMessages.jsp");
    			break;
    			
    		case "send_message":
    			MessageBean inputMessage = new MessageBean();
    			
    			System.out.println(request.getParameter(username));
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
    			
    			friendList = messageDAO.getStudentFriendList((String)session.getAttribute("username"));
    			request.setAttribute("friendList", friendList);
    			
    			
    			
    			
    			messageList = messageDAO.getMessageList((String)session.getAttribute("username"), friend);
    			request.setAttribute("messageList", messageList);
    			
    			
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentMessages.jsp");
    			break;
    			
    		case "do_logout":
    			session.invalidate();
    			dispatcher = request.getRequestDispatcher("login.jsp");
    			break;
    		
    		case "to_user_profile":
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentHome.jsp");
    			break;
    			
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

		//System.out.println("do POST runs /teachercontroller");
		session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		
		userBean = new UserBean();
		userBean.setUsername(username);
		userBean.getUserData();
		
		session.setAttribute("first_name", userBean.getFirstname());
		
		
		dispatcher = request.getRequestDispatcher("WEB-INF/views/student/studentHome.jsp"); 	
		dispatcher.forward(request, response);
	}

}
