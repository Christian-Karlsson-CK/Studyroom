package com.studyroom.servlets;

import java.io.IOException;
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
import com.studyroom.beans.UserBean;
import com.studyroom.dao.CourseDAO;
import com.studyroom.dao.UserDAO;



/**  
 * 
 * 
 * This servlet handles all admin requests from all the admin pages.
 * It forwards data to either the CourseDAO or the UserDAO.
 * 
 * 
 * **/
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private HttpSession session;
	private RequestDispatcher dispatcher;
	private CourseDAO courseDAO = new CourseDAO();
	private UserDAO userDAO = new UserDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		/** 
		 *  The switch statement changes the view to the correct page if a link in the header has been clicked. It also handles all button presses from the admin pages.
		 * **/
		
    	switch(action) {
    		
    		//If 'Administer courses' has been pressed in header.
    		case "to_courses":
    			//Get all exisitng courses to display on adminCourses.jsp.
    			List<CourseBean> courseList = courseDAO.getCourses();
    			request.setAttribute("courseList", courseList);
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminCourses.jsp"); 
    			break;
    		
    		//If 'add' button has been pressed from adminCourses.jsp. 
    		case "add_course":
    			//Get all the course data to be added to the database via the CourseDAO. 
    			CourseBean course = new CourseBean();
    			course.setCourseCodeId((String)request.getParameter("course_code_id"));
    			course.setName((String)request.getParameter("name"));
    			course.setTeacherId((String)request.getParameter("teacher_id"));
    			course.setStartDate((String)request.getParameter("start_date"));
    			course.setEndDate((String)request.getParameter("end_date"));
    			course.setPace((String)request.getParameter("pace"));
    			course.setPoints((String)request.getParameter("points"));
    			courseDAO.addCourse(course);

    			
				courseList = courseDAO.getCourses();
				request.setAttribute("courseList", courseList);
				
				dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminCourses.jsp");
			break;
			
			//If 'delete' button has been pressed from adminCourses.jsp.
    		case "delete_course":
    			
    			//Get the course to be deleted.
    			String deleteCourse = request.getParameter("course");
    			
    			//Perform the database deletion in the database.
    			courseDAO.deleteCourse(deleteCourse);
    			
    			//Refresh page with updated course list.
    			courseList = courseDAO.getCourses();
				request.setAttribute("courseList", courseList);
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminCourses.jsp");
    			break;
    			
    		//If 'save edit' button has been pressed from adminCourses.jsp.
    		case "edit_course":
    			
    			//Get all the new data for the updated course.
    			course = new CourseBean();
    			course.setCourseCodeId((String)request.getParameter("course_code_id"));
    			course.setName((String)request.getParameter("name"));
    			course.setTeacherId((String)request.getParameter("teacher_id"));
    			course.setStartDate((String)request.getParameter("start_date"));
    			course.setEndDate((String)request.getParameter("end_date"));
    			course.setPace((String)request.getParameter("pace"));
    			course.setPoints((String)request.getParameter("points"));
    			
    			//Get the course to be updated.
    			String editCourse = request.getParameter("course");
    			
    			//Let the courseDAO update the course.
    			courseDAO.editCourse(course, editCourse);
    			
    			//Refresh page with updated course list.
    			courseList = courseDAO.getCourses();
				request.setAttribute("courseList", courseList);
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminCourses.jsp");
    			break;    			
    			
    		//If 'Administer users' has been pressed in header.
    		case "to_users":
    			
    			//Get all existing users to display on adminUsers.jsp.
    			List<UserBean> userList = userDAO.getUsers();
    			request.setAttribute("userList", userList);			
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminUsers.jsp");
    			break;
    		
    		//If 'add' button has been pressed from adminUsers.jsp.	
    		case "add_user":
    			
    			//Get new user data and let the UserDAO save it to the database
    			UserBean user = new UserBean();
    			user.setPersonalId((String)request.getParameter("personal_id"));
    			user.setFirstname((String)request.getParameter("firstname"));
    			user.setLastname((String)request.getParameter("lastname"));
    			user.setUsername((String)request.getParameter("username"));
    			user.setPassword((String)request.getParameter("password"));
    			user.setUsertype((String)request.getParameter("usertype"));
    			userDAO.addUser(user);
    			
    			//Refresh page with updated user table.
				userList = userDAO.getUsers();
				request.setAttribute("userList", userList);
				
				dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminUsers.jsp");
			break;
			
			//If 'delete' button has been pressed from adminUsers.jsp.	
    		case "delete_user":
    			
    			String deleteUser = request.getParameter("user");
    			
    			userDAO.deleteUser(deleteUser);
    			
    			userList = userDAO.getUsers();
				request.setAttribute("userList", userList);
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminUsers.jsp");
    			break;
    			
    		//If 'save edit' button has been pressed from adminUsers.jsp.		
    		case "edit_user":
    			
    			//Get edited user data to be saved to database using UserDAO.
    			user = new UserBean();
    			user.setPersonalId((String)request.getParameter("personal_id"));
    			user.setFirstname((String)request.getParameter("firstname"));
    			user.setLastname((String)request.getParameter("lastname"));
    			user.setUsername((String)request.getParameter("username"));
    			user.setPassword((String)request.getParameter("password"));
    			user.setUsertype((String)request.getParameter("usertype"));
    			
    			String editUser = request.getParameter("user");
    			
    			userDAO.editUser(user, editUser);
    			
    			userList = userDAO.getUsers();
				request.setAttribute("userList", userList);
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminUsers.jsp");
    			break;
    			
    		//If 'Assign students' has been pressed in header.
    		case "to_assign":
    			
    			//Get all existing courses
    			courseList = courseDAO.getCourses();
    			request.setAttribute("courseList", courseList);
    			
    			//Get all assigned students to be displayed under each respective course that they are assigned to on the adminAssign.jsp.
    			List<AssignedStudentBean> assignedStudentList = userDAO.getAssignedStudents();
    			request.setAttribute("assignedStudentList", assignedStudentList);
    			
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminAssign.jsp");
    			break;
    			
    		//If 'add' button has been pressed from adminAssign.jsp.	
    		case "add_student":
    			
    			//Assign a student to a course and save it to the database using UserDAO.
    			AssignedStudentBean assignedStudent = new AssignedStudentBean();
    			assignedStudent.setUsername((String)request.getParameter("username"));
    			assignedStudent.setCoursecode((String)request.getParameter("coursecode"));
    			assignedStudent.setCourse((String)request.getParameter("course"));
    			assignedStudent.setTeacherId((String)request.getParameter("teacher_id"));
    			userDAO.addStudent(assignedStudent);
    			
    			//Set up initial data a row in 'course_contents' table in the database.
    			//This is done to later be able to determine what row to update in 'course_contents' table in database with teacher or student exercise and examination answer/reply.
    			CourseContentsBean courseContentsBean = new CourseContentsBean();
    			courseContentsBean.setCoursecode((String)request.getParameter("coursecode"));
    			courseContentsBean.setCourse((String)request.getParameter("course"));
    			courseContentsBean.setStudentUsername((String)request.getParameter("username"));
    			courseContentsBean.setTeacherUsername((String)request.getParameter("teacher_id"));
    			courseDAO.addInitialCourseContents(courseContentsBean);
    			
    			//Refresh page.
    			courseList = courseDAO.getCourses();
    			request.setAttribute("courseList", courseList);
    			
    			assignedStudentList = userDAO.getAssignedStudents();
    			request.setAttribute("assignedStudentList", assignedStudentList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminAssign.jsp");
    			break;
    		
    		//If 'delete' button has been pressed from adminAssign.jsp.
    		case "delete_student":
    			
    			//Get the student to be deleted and from which course.
    			String deleteStudent = request.getParameter("username");
    			String coursecode = request.getParameter("coursecode");
    			
    			userDAO.deleteStudent(deleteStudent, coursecode);
    			
    			//Refresh page.
    			courseList = courseDAO.getCourses();
    			request.setAttribute("courseList", courseList);
    			
    			assignedStudentList = userDAO.getAssignedStudents();
    			request.setAttribute("assignedStudentList", assignedStudentList);	
    		
			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminAssign.jsp");
			break;
			
			//If 'logout' has been pressed in header.
    		case "do_logout":
    			session = request.getSession();
    			session.invalidate();
    			dispatcher = request.getRequestDispatcher("login.jsp");
    			break;
    		
    		//If the users name has been pressed in header.
    		case "to_home":
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminHome.jsp");
    			break;
    		
    		//Should never reach the default but just in case of a bug.
    		default:
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminHome.jsp"); 
    			break;
    	}
    		dispatcher.forward(request, response);
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//After admin logs in send to adminHome.jsp.
		
		session = request.getSession();
		
		
		dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminHome.jsp"); 	
		dispatcher.forward(request, response);
	}

}
