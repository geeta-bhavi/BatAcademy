/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.service;

import com.project.batacademy.model.Course;
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
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List getRemainingCourses(List courseIds) {
        List<Course> courses = null;
        Iterator it = courseIds.iterator();
        String coursesString = "";
        while (it.hasNext()) {
            coursesString += it.next() + ",";
        }

        coursesString = coursesString.substring(0, coursesString.length() - 2);

        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Course where CourseId NOT IN( " + coursesString + ")");
            courses = (List<Course>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

}
