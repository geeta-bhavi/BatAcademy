package com.project.batacademy.controller;

import com.project.batacademy.helper.RegisteredCoursesHelper;
import com.project.batacademy.model.Course;
import com.project.batacademy.model.RegisteredCourses;
import com.project.batacademy.model.RegisteredCoursesId;
import com.project.batacademy.model.SelectedCoursesBean;
import com.project.batacademy.model.Student;
import com.project.batacademy.service.FacultyDetailsService;
import com.project.batacademy.service.StudentDetailsService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @author swathi, geeta
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

            if (session.getAttribute("studentId") != null) {

                int studentId = (Integer) session.getAttribute("studentId");

                StudentDetailsService studentDetailsService = new StudentDetailsService();

                FacultyDetailsService facultyDetailsService = new FacultyDetailsService();

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/StudentDetails.jsp");

                Student student = (Student) studentDetailsService.getStudentDetails(studentId);

                List<Course> courses = new ArrayList<Course>();
                List<SelectedCoursesBean> selectedCourses = new ArrayList<SelectedCoursesBean>();
                 Map<Integer, String> facultyMap =  new HashMap<>();
                //boolean studentRegisteredVal = studentDetailsService.isRegistered(studentId);
                boolean studentRegisteredVal = student.isRegister();
                boolean facultyEnableVal = facultyDetailsService.isEnabled();

                if (facultyEnableVal && !studentRegisteredVal) {
                    courses = studentDetailsService.getRemainingCourses(studentId);
                    facultyMap = facultyDetailsService.getFacultyName();
                    request.setAttribute("courses", courses);
                    request.setAttribute("faculty", facultyMap);
                } else {
                    selectedCourses = studentDetailsService.fetchRegisteredCourses(studentId);
                    request.setAttribute("selectedCourses", selectedCourses);
                }
                
                

                request.setAttribute("student", student);
                request.setAttribute("register", studentRegisteredVal);
                request.setAttribute("enable", facultyEnableVal);
                
                requestDispatcher.forward(request, response);
                
            } else {
                response.sendRedirect("index.html");
            }

        } else {
            response.sendRedirect("index.html");
        }

    }

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
        for (String str : json) {
            RegisteredCourses registeredCourse = new RegisteredCourses();
            JSONObject obj = new JSONObject(str);
            String studentId = obj.getString("studentId");
            System.out.println("studentId " + studentId);
            JSONArray arr = obj.getJSONArray("courseList");
            int arrSize = arr.length();
            for (int i = 0; i < arr.length(); i++) {
                RegisteredCoursesId registeredCourseId = new RegisteredCoursesId();

                String courseId = arr.getJSONObject(i).getString("courseId");
                String courseName = arr.getJSONObject(i).getString("coursename");
                System.out.println("courseId " + courseId);
                System.out.println("courseName " + courseName);

                registeredCourseId.setCourseId(Integer.parseInt(courseId));
                registeredCourseId.setStudentId(Integer.parseInt(studentId));

                registeredCourse.setId(registeredCourseId);
                registeredCourse.setCourseName(courseName);

                registeredCoursesList.add(registeredCourse);
                RegisteredCoursesHelper registeredCoursesHelper = new RegisteredCoursesHelper();
                registeredCoursesHelper.updateRegisteredCourses(registeredCoursesList);

            }

            if (arrSize > 0) {
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
    }

}
