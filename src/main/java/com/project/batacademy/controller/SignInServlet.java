package com.project.batacademy.controller;

import com.project.batacademy.service.AuthenticateUser;
import com.project.batacademy.service.StudentDetailsService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author geeta
 */
@WebServlet(value = "/signin")
public class SignInServlet extends HttpServlet {

    private AuthenticateUser auth = new AuthenticateUser();
    private StudentDetailsService studentDetailsService = new StudentDetailsService();
    private final String CONTENT_TYPE = "text/html";
    private HttpSession session;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType(CONTENT_TYPE);
        String task = request.getParameter("task");

        if (task.equals("signin")) {
            String id = request.getParameter("id");
            String pwd = request.getParameter("password");
            String userType = request.getParameter("userType");
            String userExists = "";
            int userId = 0;

            if (id.length() != 0 && pwd.length() != 0) {
                userId = Integer.parseInt(id);
                if (userType.equalsIgnoreCase("student")) {
                    userExists = auth.checkIfStudentExists(userId, pwd);
                } else {
                    userExists = auth.checkIfFacultyExists(userId, pwd);
                }
            }

            if (userExists.equalsIgnoreCase("student")) {
                session = request.getSession(false);
                if (session == null) {
                    session = request.getSession(true);
                    /*create a new session*/
                } else {
                    session.setAttribute("facultyId", null);
                    /*if session exists, make other attribute as null */
                }
                session.setAttribute("studentId", userId);
                out.write("student");
            } else if (userExists.equalsIgnoreCase("faculty")) {
                session = request.getSession(false);
                if (session == null) {
                    session = request.getSession(true);/*create a new session*/
                } else {
                    session.setAttribute("studentId", null);
                    /*if session exists, make other attribute as null */
                }
                session.setAttribute("facultyId", userId);
                out.write("faculty");
            } else {
                out.write("error");
            }

            out.close();

        } else if (task.equals("signup")) {
            
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String phno = request.getParameter("phno");
            String gender = request.getParameter("gender");
            
            int id = studentDetailsService.addStudent(firstName, lastName, password, phno, gender);
            
            out.write(String.valueOf(id));
            out.close();
        } else if(task.equals("signout")) {
            
            session = request.getSession(false);
            if(session != null) {
                session.invalidate();
            }
            
            out.write("success");
            out.close();
            
        }

    }

}
