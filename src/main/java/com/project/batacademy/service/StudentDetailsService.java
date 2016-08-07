/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.service;

import com.project.batacademy.helper.CourseHelper;
import com.project.batacademy.helper.RegisteredCoursesHelper;
import com.project.batacademy.helper.StudentHelper;
import com.project.batacademy.model.Course;
import com.project.batacademy.model.RegisteredCourses;
import com.project.batacademy.model.Student;
import java.util.List;

/**
 *
 * @author geeta
 */
public class StudentDetailsService {

    private StudentHelper studentHelper;
    private CourseHelper courseHelper;
    private RegisteredCoursesHelper regCourseHelper;

    public StudentDetailsService() {
    }
    
    public Student getStudentDetails(int userId) {
        studentHelper = new StudentHelper();
        Student student = (Student) studentHelper.getStudentDetails(userId);
        return student;
    }

    public Student getStudentDetails(int userId, String pwd) {
        studentHelper = new StudentHelper();
        Student student = (Student) studentHelper.getStudentDetails(userId, pwd);
        return student;
    }
    
    public List<Course> getCourses(int userId) {
        List<RegisteredCourses> takenCourses = regCourseHelper.getCourseIdGivenStudentId(userId);
        List<Course> courses = courseHelper.getRemainingCourses(takenCourses);
        
        return courses;
    }

}
