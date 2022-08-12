package com.studyroom.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.studyroom.beans.LoginBean;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession session;
	RequestDispatcher dispatcher;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	//RequestDispatcher dispatcher = null;
    	System.out.println("do get runs");
    	
		//dispatcher = request.getRequestDispatcher("WEB-INF/views/login.jsp"); 	
		//dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("at do post loginC");
		RequestDispatcher dispatcher;
		session = request.getSession();
		session.setMaxInactiveInterval(600);
		
		
		LoginBean bean = new LoginBean();
		bean.setUsername(request.getParameter("username"));
		bean.setPassword(request.getParameter("password"));
		
		
		//Check if username exists
		if(bean.validateUsername()) {
			//Then check if password is correct
			if (bean.validatePassword()){
				
				String userType = bean.getUserType();
				
				
				//Check usertype (teacher or student) to determine next view for the dispatcher
				if(userType.equals("teacher")) {
					//System.out.println("is teacher");
					session.setAttribute("username", bean.getUsername());
					//dispatcher = request.getRequestDispatcher("WEB-INF/views/teacher/teacherHome.jsp");
					dispatcher = request.getRequestDispatcher("TeacherController");
					dispatcher.forward(request, response);
				}
				else if(userType.equals("student")){
					//System.out.println("is student");
					session.setAttribute("username", bean.getUsername());
					dispatcher = request.getRequestDispatcher("StudentController");
					dispatcher.forward(request, response);
				}
				
				else if(userType.equals("admin")) {
					session.setAttribute("username", "Admin");
					dispatcher = request.getRequestDispatcher("AdminController");
					dispatcher.forward(request, response);
				}
				else {
					//System.out.println("Could not get valid usertype");
				}
			}
			
			//if password is wrong refresh login view with error message:
			else {
				session.setAttribute("status", "wrong_password");
				session.setAttribute("username", bean.getUsername());
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
				//System.out.println("wrong password");
			}
		}
		
		//if username does not exist refresh login view with error message
		else{
			session.setAttribute("status", "wrong_username");
			session.setAttribute("username", bean.getUsername());
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			
			//System.out.println("Username does not exist");
		}
	}
}
