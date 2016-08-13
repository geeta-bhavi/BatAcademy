/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.controller;

import com.project.batacademy.helper.CourseHelper;
import com.project.batacademy.helper.RegisteredCoursesHelper;
import com.project.batacademy.model.Course;
import com.project.batacademy.model.RegisteredCourses;
import com.project.batacademy.model.Student;
import com.project.batacademy.service.StudentDetailsService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author geeta
 */
@WebServlet(name = "StudentDetailsController", urlPatterns = {"/StudentDetailsController"})
public class StudentDetailsController extends HttpServlet {

    private final String CONTENT_TYPE = "text/html";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            StudentDetailsService studentDetailsService = new StudentDetailsService();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("StudentDetails.jsp");
            if (session.getAttribute("studentId") != null) {
                int studentId = (Integer) session.getAttribute("studentId");

                Student student = (Student) studentDetailsService.getStudentDetails(studentId);

                List<Course> courses = studentDetailsService.getCourses(studentId);

                request.setAttribute("student", student);
                request.setAttribute("courses", courses);

                requestDispatcher.forward(request, response);
            } else {
                response.sendRedirect("index.html");
            }

        } else {
            response.sendRedirect("index.html");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType(CONTENT_TYPE);

        String json[] = request.getParameterValues("courses");
        for (String str : json) {
            JSONObject obj = new JSONObject(str);
            String studentId = obj.getString("studentId");
            JSONArray arr = obj.getJSONArray("courseList");
            for (int i = 0; i < arr.length(); i++) {
                String courseId = arr.getJSONObject(i).getString("courseId");
                String courseName = arr.getJSONObject(i).getString("coursename");
                System.out.println("courseId " + courseId);
                System.out.println("courseName " + courseName);
            }

            out.write("success");
        }

        out.close();

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
