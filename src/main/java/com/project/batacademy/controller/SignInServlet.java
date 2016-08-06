package com.project.batacademy.controller;

import com.project.batacademy.model.Course;
import com.project.batacademy.model.RegisteredCourses;
import com.project.batacademy.model.Student;
import com.project.batacademy.service.CourseHelper;
import com.project.batacademy.service.RegisteredCoursesHelper;
import com.project.batacademy.service.StudentHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author geeta
 */
public class SignInServlet extends HttpServlet {

    private StudentHelper studentHelper = new StudentHelper();
    private CourseHelper courseHelper = new CourseHelper();
    private RegisteredCoursesHelper regCourseHelper = new RegisteredCoursesHelper();
    private final String CONTENT_TYPE = "text/html";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType(CONTENT_TYPE);
        String id = request.getParameter("id");
        String pwd = request.getParameter("password");
        String userType = request.getParameter("userType");

        if (id.length() != 0 && pwd.length() != 0) {
            int userId = Integer.parseInt(id);
            if (userType.equalsIgnoreCase("student")) {
                Student student = (Student) studentHelper.getStudentDetails(userId);

                if (student != null) {

//                    List<RegisteredCourses> takenCourses = regCourseHelper.getCourseIdGivenStudentId(userId);
//                    Course courses = (Course) courseHelper.getRemainingCourses(takenCourses);

                    request.setAttribute("student", student);
                    //request.setAttribute("courses", courses);
                    request.getRequestDispatcher("StudentDetails.jsp").forward(request, response);
                } else {
                    out.write("error");
                    out.close();
                }

            } else {
                /* faculty page */
            }

        }

    }

}
