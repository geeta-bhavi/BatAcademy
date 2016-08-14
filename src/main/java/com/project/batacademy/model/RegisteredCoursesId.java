package com.project.batacademy.model;
// Generated Aug 12, 2016 12:54:43 PM by Hibernate Tools 4.3.1



/**
 * RegisteredCoursesId generated by hbm2java
 */
public class RegisteredCoursesId  implements java.io.Serializable {


     private int courseId;
     private int studentId;

    public RegisteredCoursesId() {
    }

    public RegisteredCoursesId(int courseId, int studentId) {
       this.courseId = courseId;
       this.studentId = studentId;
    }
   
    public int getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public int getStudentId() {
        return this.studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RegisteredCoursesId) ) return false;
		 RegisteredCoursesId castOther = ( RegisteredCoursesId ) other; 
         
		 return (this.getCourseId()==castOther.getCourseId())
 && (this.getStudentId()==castOther.getStudentId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getCourseId();
         result = 37 * result + this.getStudentId();
         return result;
   }   


}


