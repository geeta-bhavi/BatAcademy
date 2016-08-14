package com.project.batacademy.service;

import com.project.batacademy.helper.FacultyHelper;
import com.project.batacademy.helper.StudentHelper;
import com.project.batacademy.model.Faculty;
import com.project.batacademy.model.Student;

/**
 *
 * @author geeta
 */
public class AuthenticateUser {

    private StudentHelper studentHelper;
    private FacultyHelper facultyHelper;

    public AuthenticateUser() {
    }

    public String checkIfStudentExists(int userId, String pwd) {
        studentHelper = new StudentHelper();
        Student student = (Student) studentHelper.getStudentDetails(userId, pwd);
        if (student != null) {
            return "student";
        }
        return "error";
    }

    public String checkIfFacultyExists(int userId, String pwd) {
        facultyHelper = new FacultyHelper();
        Faculty faculty = (Faculty) facultyHelper.getFacultyDetails(userId, pwd);
        if (faculty != null) {
            return "faculty";
        }
        return "error";
    }

}
