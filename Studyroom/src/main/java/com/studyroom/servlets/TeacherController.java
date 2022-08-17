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
 * This servlet handles all teacher requests from all the teacher pages.
 * It forwards data to either the CourseDAO, UserDAO or the MessageDAO.
 * 
 * 
 * **/
@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession session;
	RequestDispatcher dispatcher;
	UserBean userBean = null;
	FriendBean friendBean = null;
	String friend;
	MessageDAO messageDAO = new MessageDAO();
	CourseDAO courseDAO = new CourseDAO();
	UserDAO userDAO = new UserDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherController() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String action = request.getParameter("action");
    	
    	/** 
		 *  The switch statement changes the view to the correct page if a link in the header has been clicked. It also handles all button presses from the student pages.
		 * **/
    	
    	switch(action) {
    		
    	    //If 'handle courses' has been pressed in header.
    		case "to_courses":
    			
    			//Get teachers courses.
    			List<CourseBean> courseList = courseDAO.getTeacherCourses((String) session.getAttribute("username"));
    			request.setAttribute("courseList", courseList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/handleCourses.jsp");
    			break;
    			
    		//if 'edit' button has been pressed on handlecourses.jsp
    		case "edit_course":
    			
    			String coursecode = request.getParameter("coursecode");	
    			
    			//gets the course data to display on handlecourses.jsp
    			courseDAO.getCourse(coursecode);
    			CourseBean course = courseDAO.getCourse(coursecode);
    			request.setAttribute("selectedCourse", course);
    			
    			//refresh page
    			courseList = courseDAO.getTeacherCourses((String) session.getAttribute("username"));
    			request.setAttribute("courseList", courseList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/handleCourses.jsp");
    			break;
    			
    		//if 'save exercises' button has been pressed on handlecourses.jsp
    		case "save_exercises":
    			
    			coursecode = request.getParameter("coursecode");
    			
    			//Get coursedata abd save it to database.
    			course = new CourseBean();
    			course.setCourseCodeId(coursecode);
    			course.setName(request.getParameter("course"));
    			course.setExercise1(request.getParameter("exercise1"));
    			course.setExercise2(request.getParameter("exercise2"));
    			course.setExercise3(request.getParameter("exercise3"));
    			course.setExamination(request.getParameter("examination"));
    			courseDAO.saveExercises(course);
    			
    			//refresh page
    			courseDAO.getCourse(coursecode);
    			course = courseDAO.getCourse(coursecode);
    			request.setAttribute("selectedCourse", course);
    			
    			CourseBean courseBean = new CourseBean();
    			courseBean = courseDAO.getCourse(coursecode);
    			request.setAttribute("courseContents", courseBean);
    			
    			
    			courseList = courseDAO.getTeacherCourses((String) session.getAttribute("username"));
    			request.setAttribute("courseList", courseList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/handleCourses.jsp");
    			break;
    			
    		// if 'examine students' has been pressed in the header.
    		case "to_examine_students":
    			
    			//Get students that the teacher is teaching to.
    			List<UserBean> userList = userDAO.getTeacherStudents((String) session.getAttribute("username"));
    			request.setAttribute("studentList", userList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/examineStudents.jsp");
    			break;
    			
    		//if 'examine' button has been pressed on examineStudents.jsp
    		case "select_student":
    			
    			//Save student name and username to be used when page gets updated.
    			String studentUsername = request.getParameter("student");
    			String studentName = request.getParameter("student_name");
    			request.setAttribute("student", studentUsername);
    			request.setAttribute("studentName", studentName);

    			//Get the courses that logged in teacher is teaching for this particular student.
    			List<AssignedStudentBean> courses = courseDAO.getStudentCourses(studentUsername, (String)session.getAttribute("username"));
    			request.setAttribute("courses", courses);
    			
    			//refresh page
    			userList = userDAO.getTeacherStudents((String) session.getAttribute("username"));
    			request.setAttribute("studentList", userList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/examineStudents.jsp");
    			break;
    			
    		//if 'check' button has been pressed on examineStudents.jsp
    		case "select_student_course":
    			
    			studentUsername = request.getParameter("student");
    			studentName = request.getParameter("student_name");
    			coursecode = request.getParameter("coursecode");

    			//Get the choosen course data.
    			course = courseDAO.getCourse(coursecode);
    			request.setAttribute("course", course);
    			
    			CourseContentsBean courseContents = courseDAO.getCourseContents(studentUsername, coursecode);
    			request.setAttribute("selectedCourse", courseContents);
    			
    			//refresh page
    			request.setAttribute("student", studentUsername);
    			request.setAttribute("studentName", studentName);

    			courses = courseDAO.getStudentCourses(studentUsername, (String)session.getAttribute("username"));
    			request.setAttribute("courses", courses);
    			
    			userList = userDAO.getTeacherStudents((String) session.getAttribute("username"));
    			request.setAttribute("studentList", userList);

    			dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/examineStudents.jsp");
    			break;
    		
    		//if 'Save Replies' button has been pressed on examinestudents.jsp.
    		case "save_examinations":
    			
    			
    			studentUsername = request.getParameter("student");
    			studentName = request.getParameter("student_name");
    			coursecode = request.getParameter("coursecode");
    			
    			request.setAttribute("student", studentUsername);
    			request.setAttribute("studentName", studentName);
    			
    			//Get teacher replies and save to database.
    			CourseContentsBean newCourseContents = new CourseContentsBean();
    			newCourseContents.setTeacherReply1((String)request.getParameter("teacher_reply1"));
    			newCourseContents.setTeacherReply2((String)request.getParameter("teacher_reply2"));
    			newCourseContents.setTeacherReply3((String)request.getParameter("teacher_reply3"));
    			newCourseContents.setExaminationTeacherReply((String)request.getParameter("teacher_examination_reply"));
    			newCourseContents.setCoursecode(coursecode);
    			courseDAO.saveTeacherAnswers(newCourseContents, studentUsername);
    			
    			//Refresh page.
    			course = courseDAO.getCourse(coursecode);
    			request.setAttribute("course", course);
    			
    			courseContents = courseDAO.getCourseContents(studentUsername, coursecode);
    			request.setAttribute("selectedCourse", courseContents);

    			courses = courseDAO.getStudentCourses(studentUsername, (String)session.getAttribute("username"));
    			request.setAttribute("courses", courses);
    			
    			userList = userDAO.getTeacherStudents((String) session.getAttribute("username"));
    			request.setAttribute("studentList", userList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/examineStudents.jsp");
    			break;
    			
    			
    		case "to_messages":
    			
    			//before dispatching to message page get friendlist of user
    			List<FriendBean> friendList = messageDAO.getTeacherFriendList((String)session.getAttribute("username"));
    			request.setAttribute("friendList", friendList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/messages.jsp");
    			break;
    			
    		case "messages_click_friend":
    			//before dispatching to message page get friendlist and fill messagearea
    			
    			messageDAO.setMessagesRead((String)session.getAttribute("username"), (String)request.getParameter("friend"));
    			
    			friendList = messageDAO.getTeacherFriendList((String)session.getAttribute("username"));
    			request.setAttribute("friendList", friendList);
    			
    			String user =(String)session.getAttribute("username");
    			friend = request.getParameter("friend");
    			session.setAttribute("friend", friend);
    			request.setAttribute("friend", friend);
    			String friendFullname = request.getParameter("friend_fullname");
    			String message_date = request.getParameter("message_date");
    			request.setAttribute("friendFullname", friendFullname);
    			request.setAttribute("message_date", message_date);
    			session.setAttribute("friendFullname", friendFullname);
    			session.setAttribute("message_date", message_date);
    			
    			
    			
    			List<MessageBean> messageList = messageDAO.getMessageList(user, friend);
    			request.setAttribute("messageList", messageList);
    			
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/messages.jsp");
    			break;
    			
    		case "send_message":
    			session = request.getSession();
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
    			
    			friendList = messageDAO.getTeacherFriendList((String)session.getAttribute("username"));
    			request.setAttribute("friendList", friendList);
    			
    			user =(String)session.getAttribute("username");
    			
    			
    			messageList = messageDAO.getMessageList(user, friend);
    			request.setAttribute("messageList", messageList);
    			
    			
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/messages.jsp");
    			break;
    			
    		case "do_logout":
    			session = request.getSession();
    			session.invalidate();
    			dispatcher = request.getRequestDispatcher("login.jsp");
    			break;
    		
    		case "to_user_profile":
    			
    			int newStudentAnswerCount = courseDAO.getNewStudentAnswersCount((String)session.getAttribute("username"));
    			Integer wrapper = newStudentAnswerCount;
    			String answerCount = wrapper.toString();
    			request.setAttribute("newStudentAnswerCount", answerCount);
    			
    			int newStudentMessageCount = messageDAO.getNewMessagesCount((String)session.getAttribute("username"));
    			request.setAttribute("newStudentMessageCount", newStudentMessageCount);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/teacherHome.jsp");
    			break;
    			
    		default:
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/teacherHome.jsp"); 
    			break;
    	}
    		dispatcher.forward(request, response);
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		
		userBean = new UserBean();
		userBean.setUsername(username);
		userBean.getUserData();
		
		session.setAttribute("first_name", userBean.getFirstname());
		
		int newStudentAnswerCount = courseDAO.getNewStudentAnswersCount(username);
		Integer wrapper = newStudentAnswerCount;
		String answerCount = wrapper.toString();
		request.setAttribute("newStudentAnswerCount", answerCount);
		
		int newStudentMessageCount = messageDAO.getNewMessagesCount(username);
		request.setAttribute("newStudentMessageCount", newStudentMessageCount);
		
		
		
		dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/teacherHome.jsp"); 	
		dispatcher.forward(request, response);
		
		
		
		
	}

}









