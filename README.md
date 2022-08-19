# What is Studyroom?
Studyroom is a simple teacher-student learning platform.
This project exists as a fun learning experience for me. I'm happy to recieve any improvement or suggestions to this project.
The project uses MVC pattern and consists of JSP pages on the frontend. Servlets, javaBeans and DAO's on the backend doing all the work with a mySQL database.
Examples of languages and techniques used are HTML, CSS, java, JSP pages, Javascript, JSTL (JSP Standard Tag Library), JDBC, mySQL.

# How studyroom is used:
Available users are admin, teacher and student.
Admins can add,remove or edit all users and courses in the database. Admins can also assign teachers and enroll students to courses.
Teachers can write exercises and examinations for assigned courses as well as examine student answers.
Students can write answers to exercises and examinations as well as read teachers replies.
There is also a student-teacher chat.


# Installation
1. Download the source code by choosing an option on the green 'code' button up to the right in the 'Code' tab section.
2. Import the project into an IDE like eclipse.
3. Import the DumpStudyroom.sql file to your mySQL database. If you don't have mySQL server you can download it from www.mysql.com. 
You will also need the Connector/J from them. Connector/J implements the Java Database Connectivity (JDBC) API.
4. In the DatabaseConnection class on row 35 change the parameter on DriverManager.getConnection() to match your own setup to mySQL.
5. Run the project on a server.


# Project status
The project is fully functional but could do with a nicer looking study page that displays the exercises and examination.
Also the teachers can see how many new unanswered exercises there is but not whom turned it in or for which course.


Have a nice day!
