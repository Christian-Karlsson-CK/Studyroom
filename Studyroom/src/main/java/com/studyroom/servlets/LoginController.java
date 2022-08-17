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
import com.studyroom.dao.LoginDAO;

/**  
 * 
 * 
 * This servlet handles all login requests from login.jsp.
 * It sends login data to the LoginDAO to do the login validation.
 * 
 * 
 * **/
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
    	super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		session = request.getSession();
		session.setMaxInactiveInterval(600);
		
		
		LoginBean bean = new LoginBean();
		bean.setUsername(request.getParameter("username"));
		bean.setPassword(request.getParameter("password"));
		
		LoginDAO loginDAO = new LoginDAO();
		
		
		//Check if username exists
		if(loginDAO.validateUsername(bean)) {
			//Then check if password is correct
			if (loginDAO.validatePassword(bean)){
				
				String userType = loginDAO.getUserType(bean);
				
				
				//Check usertype (teacher or student) to determine next view for the dispatcher
				if(userType.equals("teacher")) {
					
					session.setAttribute("username", bean.getUsername());
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
					System.out.println("Could not get valid usertype");
				}
			}
			
			//if password is wrong refresh login view with error message:
			else {
				session.setAttribute("status", "wrong_password");
				session.setAttribute("username", bean.getUsername());
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		//if username does not exist refresh login view with error message
		else{
			session.setAttribute("status", "wrong_username");
			session.setAttribute("username", bean.getUsername());
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
