/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.helper;

import com.project.batacademy.service.*;
import com.project.batacademy.model.Course;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author geeta
 */
public class CourseHelper {

    Session session = null;

    public CourseHelper() {
    }

    public List getRemainingCourses(List courseIds) {
        List<Course> courses = new ArrayList<Course>();
        Transaction tx = null;
 
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Course where CourseId NOT IN(:courses)");
            q.setParameter("courses", courses);
            courses = (List<Course>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return courses;
    }

}
