/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.controller;

import com.project.batacademy.model.Activity;
import com.project.batacademy.model.Course;
import com.project.batacademy.model.Faculty;
import com.project.batacademy.model.RegisteredCourses;
import com.project.batacademy.model.Student;
import com.project.batacademy.service.ActivityService;
import com.project.batacademy.service.FacultyDetailsService;
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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author geeta
 */
@WebServlet(name = "FacultyDetailsController", urlPatterns = {"/FacultyDetailsController"})
public class FacultyDetailsController extends HttpServlet {

    FacultyDetailsService facultyDetailsService = new FacultyDetailsService();
    ActivityService activityService = new ActivityService();
    StudentDetailsService studentDetailsService = new StudentDetailsService();

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
            if (session.getAttribute("facultyId") != null) {

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/FacultyDetails.jsp");

                int facultyId = (Integer) session.getAttribute("facultyId");
                
                /* if president signed in, then go to president details page */
                if (facultyId == 1) {
                    requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/PresidentDetails.jsp");
                }
                /* info about the faculty who signed in */
                Faculty faculty = (Faculty) facultyDetailsService.getFacultyDetails(facultyId);
                /* List of courses the faculty is teaching */
                List<Course> courses = facultyDetailsService.getCoursesForFaculty(facultyId);
                request.setAttribute("faculty", faculty);
                request.setAttribute("courses", courses);

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

        String task = request.getParameter("task");

        /* post method can receive either to serach a student or update the activity of serached student*/
        if (task.equals("search")) {
            int studentId = Integer.parseInt(request.getParameter("sid"));
            int courseId = Integer.parseInt(request.getParameter("cid"));
            JSONObject json = new JSONObject();
            /* getting the student activity details after search  */
            RegisteredCourses regCourse = facultyDetailsService.checkIfStudentHasTakenFacultyCourse(studentId, courseId);
            /* if the searched student has taken the faculty's course */
            if (regCourse != null) {
                Activity activity = (Activity) activityService.getActivityyDetails(studentId, courseId);
                JSONObject activityDet = new JSONObject();
                try {
                    if (activity != null) {

                        activityDet.put("Activity1", activity.getA1());
                        activityDet.put("Activity2", activity.getA2());
                        activityDet.put("Activity3", activity.getA3());
                        activityDet.put("courseCompleted", regCourse.isCompleted());
                    } else {
                        activityDet.put("Activity1", 0);
                        activityDet.put("Activity2", 0);
                        activityDet.put("Activity3", 0);
                        activityDet.put("courseCompleted", regCourse.isCompleted());
                    }
                    json.put("activities", activityDet);
                } catch (JSONException jse) {
                    jse.printStackTrace();
                }

            }
            response.setContentType("application/json");
            out.write(json.toString());
            out.close();
        } else if (task.equals("update")) {
            int studentId = Integer.parseInt(request.getParameter("sid"));
            int courseId = Integer.parseInt(request.getParameter("cid"));
            /* updating the activity scores of student */
            int a1 = Integer.parseInt(request.getParameter("activity1"));
            int a2 = Integer.parseInt(request.getParameter("activity2"));
            int a3 = Integer.parseInt(request.getParameter("activity3"));

            String updated = activityService.updateActivity(studentId, courseId, a1, a2, a3);
            response.setContentType("text/html");

            out.write(updated);
            out.close();

        } else if (task.equals("enableRegistrationForNewSem")) {
            /* president has checked/unchecked the enable button */
            Boolean enable = Boolean.valueOf(request.getParameter("enable"));
            String enabled = "error";
            if (enable) {
                /* calculate cgpa, make completed column in RegisteredCourses as true and Register column in Student table as false */
                studentDetailsService.updateStudentGPA();
                String completed = facultyDetailsService.updateCompletedColumn(true);
                String register = studentDetailsService.updateRegisterColumn(false);
            }

            enabled = facultyDetailsService.updateEnableColumn(enable);

            out.write(enabled);
            out.close();
        } else if (task.equals("presidentEnSearch")) {
            /* president has searched a student */
            int studentId = Integer.parseInt(request.getParameter("sid"));
            Student student = studentDetailsService.getStudentDetails(studentId);
            JSONObject json = new JSONObject();
            if (student != null) {
                JSONObject stuObj = new JSONObject();
                try {
                    stuObj.put("StudentFirstName", student.getFirstName());
                    stuObj.put("StudentLastName", student.getLastName());
                    stuObj.put("CGPA", student.getCgpa());
                    json.put("studentDetails", stuObj);
                } catch (JSONException jse) {
                    jse.printStackTrace();
                }
            }

            response.setContentType("application/json");
            out.write(json.toString());
            out.close();
        } else if (task.equals("deleteStudent")) {
            int studentId = Integer.parseInt(request.getParameter("sid"));
            String result = facultyDetailsService.deleteStudent(studentId);
            
            response.setContentType("text/html");
            out.write(result);
            out.close();

        }

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
