package com.project.batacademy.model;
// Generated Aug 12, 2016 12:54:43 PM by Hibernate Tools 4.3.1

/**
 * Course generated by hbm2java
 */
public class Course implements java.io.Serializable {

    private int courseId;
    private String courseName;
    private int credits;
    private int facultyId;

    public Course() {
    }

    public Course(int courseId, String courseName, int credits, int facultyId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.facultyId = facultyId;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getFacultyId() {
        return this.facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

}
