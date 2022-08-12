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
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	HttpSession session;
	RequestDispatcher dispatcher;
	CourseDAO courseDAO = new CourseDAO();
	
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
		
    	switch(action) {
    		
    		case "to_courses":
    			List<CourseBean> courseList = courseDAO.getCourses();
    			request.setAttribute("courseList", courseList);
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminCourses.jsp"); 
    			break;
    			
    		case "add_course":
    			
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
			
    		case "delete_course":
    			
    			String deleteCourse = request.getParameter("course");
    			
    			courseDAO.deleteCourse(deleteCourse);
    			
    			courseList = courseDAO.getCourses();
				request.setAttribute("courseList", courseList);
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminCourses.jsp");
    			break;
    			
    		case "edit_course":

    			course = new CourseBean();
    			course.setCourseCodeId((String)request.getParameter("course_code_id"));
    			course.setName((String)request.getParameter("name"));
    			course.setTeacherId((String)request.getParameter("teacher_id"));
    			course.setStartDate((String)request.getParameter("start_date"));
    			course.setEndDate((String)request.getParameter("end_date"));
    			course.setPace((String)request.getParameter("pace"));
    			course.setPoints((String)request.getParameter("points"));
    			
    			
    			String editCourse = request.getParameter("course");
    			
    			courseDAO.editCourse(course, editCourse);
    			
    			courseList = courseDAO.getCourses();
				request.setAttribute("courseList", courseList);
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminCourses.jsp");
    			break;    			
    			
    		case "to_users":
    			
    			UserDAO userDAO = new UserDAO();
    			List<UserBean> userList = userDAO.getUsers();
    			request.setAttribute("userList", userList);			
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminUsers.jsp");
    			break;
    			
    		case "add_user":
    			
    			userDAO = new UserDAO();
    			
    			UserBean user = new UserBean();
    			user.setPersonalId((String)request.getParameter("personal_id"));
    			user.setFirstname((String)request.getParameter("firstname"));
    			user.setLastname((String)request.getParameter("lastname"));
    			user.setUsername((String)request.getParameter("username"));
    			user.setPassword((String)request.getParameter("password"));
    			user.setUsertype((String)request.getParameter("usertype"));
    			userDAO.addUser(user);
    			
    			System.out.println(request.getParameter("personal_id"));
    			
    			
    			
    			
    			
    			
				userList = userDAO.getUsers();
				request.setAttribute("userList", userList);
				
				dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminUsers.jsp");
			break;
			
    		case "delete_user":
    			
    			String deleteUser = request.getParameter("user");
    			
    			System.out.println(deleteUser);
    			
    			userDAO = new UserDAO();
    			
    			userDAO.deleteUser(deleteUser);
    			
    			userList = userDAO.getUsers();
				request.setAttribute("userList", userList);
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminUsers.jsp");
    			break;
    			
    		case "edit_user":
    			
    			System.out.println("in edituser");
    			user = new UserBean();
    			user.setPersonalId((String)request.getParameter("personal_id"));
    			user.setFirstname((String)request.getParameter("firstname"));
    			user.setLastname((String)request.getParameter("lastname"));
    			user.setUsername((String)request.getParameter("username"));
    			user.setPassword((String)request.getParameter("password"));
    			user.setUsertype((String)request.getParameter("usertype"));
    			
    			
    			String editUser = request.getParameter("user");
    			System.out.println("edit olduser: " + editUser);
    			
    			userDAO = new UserDAO();
    			
    			userDAO.editUser(user, editUser);
    			
    			userList = userDAO.getUsers();
				request.setAttribute("userList", userList);
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminUsers.jsp");
    			break;
    			
    		case "to_assign":
    			
    			courseList = courseDAO.getCourses();
    			request.setAttribute("courseList", courseList);
    			
    			userDAO = new UserDAO();
    			List<AssignedStudentBean> assignedStudentList = userDAO.getAssignedStudents();
    			request.setAttribute("assignedStudentList", assignedStudentList);
    			
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminAssign.jsp");
    			break;
    		
    		case "add_student":
    			
    			userDAO = new UserDAO();
    			
    			AssignedStudentBean assignedStudent = new AssignedStudentBean();
    			assignedStudent.setUsername((String)request.getParameter("username"));
    			assignedStudent.setCoursecode((String)request.getParameter("coursecode"));
    			assignedStudent.setCourse((String)request.getParameter("course"));
    			assignedStudent.setTeacherId((String)request.getParameter("teacher_id"));
    			userDAO.addStudent(assignedStudent);
    			
    			CourseContentsBean courseContentsBean = new CourseContentsBean();
    			courseContentsBean.setCoursecode((String)request.getParameter("coursecode"));
    			courseContentsBean.setCourse((String)request.getParameter("course"));
    			courseContentsBean.setStudentUsername((String)request.getParameter("username"));
    			courseContentsBean.setTeacherUsername((String)request.getParameter("teacher_id"));
    			courseDAO.addInitialCourseContents(courseContentsBean);
    			
    			courseList = courseDAO.getCourses();
    			request.setAttribute("courseList", courseList);
    			
    			userDAO = new UserDAO();
    			assignedStudentList = userDAO.getAssignedStudents();
    			request.setAttribute("assignedStudentList", assignedStudentList);
    			
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminAssign.jsp");
    			break;
    			
    		case "delete_student":
    			
    			String deleteStudent = request.getParameter("username");
    			String coursecode = request.getParameter("coursecode");
    			System.out.println(coursecode);
    			
    			userDAO = new UserDAO();
    			
    			userDAO.deleteStudent(deleteStudent, coursecode);
    			
    			
    			courseDAO = new CourseDAO();
    			courseList = courseDAO.getCourses();
    			request.setAttribute("courseList", courseList);
    			
    			userDAO = new UserDAO();
    			assignedStudentList = userDAO.getAssignedStudents();
    			request.setAttribute("assignedStudentList", assignedStudentList);	
    		
			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminAssign.jsp");
			break;

    		case "do_logout":
    			session = request.getSession();
    			session.invalidate();
    			dispatcher = request.getRequestDispatcher("login.jsp");
    			break;
    		
    		case "to_home":
    			dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminHome.jsp");
    			break;
    			
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
		
		session = request.getSession();
		//String username = (String) session.getAttribute("username");
		
		
		dispatcher = request.getRequestDispatcher("WEB-INF/views/admin/adminHome.jsp"); 	
		dispatcher.forward(request, response);
	}

}
