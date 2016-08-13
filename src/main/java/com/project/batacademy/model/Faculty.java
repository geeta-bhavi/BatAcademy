package com.project.batacademy.model;
// Generated Aug 12, 2016 12:54:43 PM by Hibernate Tools 4.3.1



/**
 * Faculty generated by hbm2java
 */
public class Faculty  implements java.io.Serializable {


     private int facultyId;
     private String firstName;
     private String lastName;
     private String gender;
     private String phno;
     private String designation;
     private boolean enable;
     private String password;

    public Faculty() {
    }

    public Faculty(int facultyId, String firstName, String lastName, String gender, String phno, String designation, boolean enable, String password) {
       this.facultyId = facultyId;
       this.firstName = firstName;
       this.lastName = lastName;
       this.gender = gender;
       this.phno = phno;
       this.designation = designation;
       this.enable = enable;
       this.password = password;
    }
   
    public int getFacultyId() {
        return this.facultyId;
    }
    
    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
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
    public String getDesignation() {
        return this.designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public boolean isEnable() {
        return this.enable;
    }
    
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}


