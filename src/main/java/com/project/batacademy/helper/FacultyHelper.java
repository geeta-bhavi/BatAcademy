/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.helper;

import com.project.batacademy.model.Activity;
import com.project.batacademy.model.Course;
import com.project.batacademy.model.Faculty;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author geeta
 */
public class FacultyHelper {

    Session session = null;

    public FacultyHelper() {
    }

    public Object getFacultyDetails(int facultyId) {
        /*pass pwd later when column is added*/
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        Object faculty = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Faculty where facultyId =" + facultyId);
            faculty = q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return faculty;
    }

    public Object getFacultyDetails(int facultyId, String pwd) {
        /*pass pwd later when column is added*/
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        Object faculty = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Faculty where facultyId =" + facultyId + " and Password =\"" + pwd + "\"");
            faculty = q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return faculty;
    }

    public List getFacultyDetails() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Faculty> faculty = new ArrayList<Faculty>();
        //Object faculty = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Faculty");
            //q.setParameter("faculty", faculty);
            faculty = (List<Faculty>) q.list();
            System.out.println("getFacultyDetails " + q.list().toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return faculty;
    }

    public boolean getEnableValue() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        boolean value = false;
        Faculty faculty = null;
        try {
            String desig = "'PRESIDENT'";
            Query q = session.createQuery("from Faculty where Designation ="+desig);
            faculty = (Faculty) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return faculty.isEnable();
    }
    
    public String getFacultyNameForAGivenCourseID(int courseID){
        Transaction tx = null;
        String facultyName=null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("select F.firstName from Course C, Faculty F WHERE C.facultyId = F.facultyId and C.courseId="+courseID);
                
            //System.out.println(" query " + q.getQueryString().toString());
            for(String facultyFirstName: (List<String>) q.list())
            {
                facultyName = facultyFirstName;
            }
            System.out.println(" First Name  " + facultyName );
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return facultyName;
    }
}
