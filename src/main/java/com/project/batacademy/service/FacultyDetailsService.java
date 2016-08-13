/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.service;

import com.project.batacademy.helper.ActivityHelper;
import com.project.batacademy.helper.CourseHelper;
import com.project.batacademy.helper.FacultyHelper;
import com.project.batacademy.helper.RegisteredCoursesHelper;
import com.project.batacademy.helper.StudentHelper;
import com.project.batacademy.model.Course;
import com.project.batacademy.model.Faculty;
import com.project.batacademy.model.RegisteredCourses;
import java.util.List;

/**
 *
 * @author geeta
 */
public class FacultyDetailsService {
    private FacultyHelper facultyHelper;
    private CourseHelper courseHelper;
    private RegisteredCoursesHelper regCourseHelper;
    private StudentHelper studentHelper;
    private ActivityHelper activityHelper;
    
    public FacultyDetailsService() {
    }
    
    public Faculty getFacultyDetails(int userId) {
        facultyHelper = new FacultyHelper();
        Faculty faculty = (Faculty) facultyHelper.getFacultyDetails(userId);
        return faculty;
    }
    
    public List<Course> getCoursesForFaculty(int userId) {
        courseHelper = new CourseHelper();
        List<Course> courses = courseHelper.getCoursesForFaculty(userId);

        return courses;
    }
    
    public RegisteredCourses checkIfStudentHasTakenFacultyCourse(int studentId, int courseId) {
        regCourseHelper = new RegisteredCoursesHelper();
        RegisteredCourses regCourse = (RegisteredCourses) regCourseHelper.checkIfStudentHasTakenFacultyCourse(studentId, courseId);
     
        return regCourse;
    }
    
    public String updateCompletedColumn(boolean completed) {
        regCourseHelper = new RegisteredCoursesHelper();
        String updated = regCourseHelper.updateCompletedColumn(completed);
        return updated;
    }
    
    public String updateEnableColumn(boolean enable) {
        facultyHelper = new FacultyHelper();
        String enabled = facultyHelper.updateEnableColumn(enable);
        return enabled;
    }
    
    public String deleteStudent(int studentId) {
        studentHelper = new StudentHelper();
        regCourseHelper = new RegisteredCoursesHelper();
        activityHelper = new ActivityHelper();
        
        regCourseHelper.deleteRecordsOfStudent(studentId);
        activityHelper.deleteRecordsOfStudent(studentId);
        String result = studentHelper.deleteStudent(studentId);
        
        return result;
    }
}
