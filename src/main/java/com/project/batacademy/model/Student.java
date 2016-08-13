package com.project.batacademy.model;
// Generated Aug 12, 2016 12:54:43 PM by Hibernate Tools 4.3.1



/**
 * Student generated by hbm2java
 */
public class Student  implements java.io.Serializable {


     private int studentId;
     private String firstName;
     private String lastName;
     private String gender;
     private String phno;
     private float cgpa;
     private boolean register;
     private String password;

    public Student() {
    }

    public Student(int studentId, String firstName, String lastName, String gender, String phno, float cgpa, boolean register, String password) {
       this.studentId = studentId;
       this.firstName = firstName;
       this.lastName = lastName;
       this.gender = gender;
       this.phno = phno;
       this.cgpa = cgpa;
       this.register = register;
       this.password = password;
    }
   
    public int getStudentId() {
        return this.studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getPhno() {
        return this.phno;
    }
    
    public void setPhno(String phno) {
        this.phno = phno;
    }
    public float getCgpa() {
        return this.cgpa;
    }
    
    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }
    public boolean isRegister() {
        return this.register;
    }
    
    public void setRegister(boolean register) {
        this.register = register;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}


