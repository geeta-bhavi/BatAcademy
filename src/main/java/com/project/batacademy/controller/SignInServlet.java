package com.project.batacademy.controller;

import com.project.batacademy.service.AuthenticateUser;
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
@WebServlet(value="/signin")
public class SignInServlet extends HttpServlet {

    private AuthenticateUser auth = new AuthenticateUser();
    private final String CONTENT_TYPE = "text/html";
    private HttpSession session;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType(CONTENT_TYPE);
        String id = request.getParameter("id");
        String pwd = request.getParameter("password");
        String userType = request.getParameter("userType");
        String userExists = "";
         int userId = 0;

        if (id.length() != 0 && pwd.length() != 0) {
            userId = Integer.parseInt(id);
            if (userType.equalsIgnoreCase("student")) {
                //TODO: add password
                userExists = auth.checkIfStudentExists(userId, pwd);
            } else {
                userExists = auth.checkIfFacultyExists(userId, pwd);
            }
        }
        
        if (userExists.equalsIgnoreCase("student")) {
            session = request.getSession(true);
            session.setAttribute("studentId", userId);
            out.write("student");
        } else if (userExists.equalsIgnoreCase("faculty")) {
            session = request.getSession(true);;
            out.write("faculty");
        } else {
            out.write("error");
        }

        out.close();

    }

}
