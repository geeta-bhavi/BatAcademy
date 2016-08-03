package com.project.batacademy.controller;

import com.project.batacademy.service.AuthenticateUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author geeta
 */
public class SignInServlet extends HttpServlet  {
    private AuthenticateUser authUser = new AuthenticateUser();
    private final String CONTENT_TYPE= "text/html";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType(CONTENT_TYPE);
        
        int userId = Integer.parseInt(request.getParameter("id"));
        String pwd = request.getParameter("password");
        
        System.out.println(userId+","+pwd);
        if(authUser.checkIfUserExists().equalsIgnoreCase("student")) {
            //request.getRequestDispatcher("/UserDetailsStudent.jsp").forward(request, response);
            out.write("student");
            
        } else if(authUser.checkIfUserExists().equalsIgnoreCase("faculty")) {
            //request.getRequestDispatcher("/UserDetailsFaculty.jsp").forward(request, response);
            out.write("faculty");
        } else {
            out.write("error");
            out.close();
        }
        

    }

}
