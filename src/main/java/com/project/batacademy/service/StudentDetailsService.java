/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.service;

import com.project.batacademy.controller.SelectedCoursesBean;
import com.project.batacademy.helper.ActivityHelper;
import com.project.batacademy.helper.CourseHelper;
import com.project.batacademy.helper.FacultyHelper;
import com.project.batacademy.helper.RegisteredCoursesHelper;
import com.project.batacademy.helper.StudentHelper;
import com.project.batacademy.model.Activity;
import com.project.batacademy.model.Course;
import com.project.batacademy.model.RegisteredCourses;
import com.project.batacademy.model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author geeta
 */
public class StudentDetailsService {

    private StudentHelper studentHelper;
    private CourseHelper courseHelper;
    private RegisteredCoursesHelper regCourseHelper;
    private FacultyHelper facultyHelper;

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
    
    public boolean isRegistered(int userId)
    {
        studentHelper = new StudentHelper();
        return studentHelper.isRegistered(userId);
    }

    public void setRegistered(int userId)
    {
        System.out.println(" userid " + userId);
        studentHelper = new StudentHelper();
        studentHelper.setRegistered(userId);   
    }
    
    /*public List<SelectedCoursesBean> getRegisteredCourses(int userId){
        
        return regCourseHelper.fetchRegisteredCourses(userId);
    }*/
    
    public List fetchRegisteredCourses(int studentId)
    {
        regCourseHelper = new RegisteredCoursesHelper();
        facultyHelper=new FacultyHelper();
        List<RegisteredCourses> registeredCourses = regCourseHelper.getRegisteredCoursesForStudent();
        
        ArrayList<SelectedCoursesBean> selectedCourses = new ArrayList<SelectedCoursesBean>();
        ActivityHelper activityHelper = new ActivityHelper();
        for(RegisteredCourses registeredCourse: registeredCourses)
        {
            if(registeredCourse.getId().getStudentId() == studentId)
            {
                Activity activityObj = (Activity) activityHelper.getActivityforGiveCouserAndStudent(registeredCourse.getId().getCourseId(),studentId);
                int CourseID=registeredCourse.getId().getCourseId();
                
                SelectedCoursesBean selectedCourse = new SelectedCoursesBean();
                selectedCourse.setCourseID(registeredCourse.getId().getCourseId());
                selectedCourse.setCourseName(registeredCourse.getCourseName());
                selectedCourse.setCompleted(registeredCourse.isCompleted());
                selectedCourse.setFacultyName(facultyHelper.getFacultyNameForAGivenCourseID(CourseID));
                
                System.out.println("faculty"+facultyHelper.getFacultyNameForAGivenCourseID(CourseID));
                System.out.println("faculty name"+selectedCourse.getFacultyName());
                
                if(null!=activityObj)
                {
                    selectedCourse.setA1(activityObj.getA1());
                    selectedCourse.setA2(activityObj.getA2());
                    selectedCourse.setA3(activityObj.getA3());
                }
                else
                {
                    selectedCourse.setA1(0);
                    selectedCourse.setA2(0);
                    selectedCourse.setA3(0);
                }
                
                selectedCourses.add(selectedCourse);
            }
        }
       
        return selectedCourses;
    }
}
