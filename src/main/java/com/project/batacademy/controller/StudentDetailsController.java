/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.controller;

import com.project.batacademy.helper.CourseHelper;
import com.project.batacademy.helper.RegisteredCoursesHelper;
import com.project.batacademy.model.Course;
import com.project.batacademy.model.Faculty;
import com.project.batacademy.model.RegisteredCourses;
import com.project.batacademy.model.RegisteredCoursesId;
import com.project.batacademy.model.Student;
import com.project.batacademy.service.FacultyDetailsService;
import com.project.batacademy.service.StudentDetailsService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
            RegisteredCoursesHelper regCourseHelper = new RegisteredCoursesHelper();
            FacultyDetailsService facultyDetailsService=new FacultyDetailsService();
            
            CourseHelper courseHelper = new CourseHelper();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("StudentDetails.jsp");
            int studentId = (Integer) session.getAttribute("studentId");
            Student student = (Student) studentDetailsService.getStudentDetails(studentId);
            
            List<Integer> takenCourses = regCourseHelper.getCourseIdGivenStudentId(studentId);
            List<Course> courses = courseHelper.getRemainingCourses(takenCourses);
            HashMap<Integer,String> facultyMap = facultyDetailsService.getFacultyName();
            System.out.println(facultyMap.get(2));
            
            boolean studentRegisteredVal=studentDetailsService.isRegistered(studentId);
            boolean facultyEnableVal=facultyDetailsService.isEnabled();
            
            List<SelectedCoursesBean> selectedCourses = studentDetailsService.fetchRegisteredCourses(studentId);
            
            request.setAttribute("student", student);
            request.setAttribute("courses", courses);
            request.setAttribute("faculty", facultyMap);
            
            request.setAttribute("register",studentRegisteredVal);
            request.setAttribute("enable", facultyEnableVal);
            request.setAttribute("selectedCourses", selectedCourses);
            
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
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
        ArrayList<RegisteredCourses> registeredCoursesList = new ArrayList<RegisteredCourses>();
        
        String json[] = request.getParameterValues("courses");
        for(String str : json) {
            RegisteredCourses registeredCourse = new RegisteredCourses();
            JSONObject obj = new JSONObject(str);
            String studentId = obj.getString("studentId");
            System.out.println("studentId "+studentId);
            JSONArray arr = obj.getJSONArray("courseList");
            int arrSize = arr.length();
            for (int i = 0; i < arr.length(); i++) {
                RegisteredCoursesId registeredCourseId=new RegisteredCoursesId();
                
                String courseId = arr.getJSONObject(i).getString("courseId");
                String courseName = arr.getJSONObject(i).getString("coursename");
                System.out.println("courseId "+courseId);
                System.out.println("courseName "+courseName);
                
                registeredCourseId.setCourseId(Integer.parseInt(courseId));
                registeredCourseId.setStudentId(Integer.parseInt(studentId));
                
                registeredCourse.setId(registeredCourseId);
                registeredCourse.setCourseName(courseName);
                
                registeredCoursesList.add(registeredCourse);
                RegisteredCoursesHelper registeredCoursesHelper = new RegisteredCoursesHelper();
                registeredCoursesHelper.updateRegisteredCourses(registeredCoursesList);
            
            }
            
            if(arrSize > 0)
            {
                StudentDetailsService studentDetailsService = new StudentDetailsService();
                studentDetailsService.setRegistered(Integer.parseInt(studentId));
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
