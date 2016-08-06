package com.project.batacademy.service;

import com.project.batacademy.model.RegisteredCourses;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author geeta
 */
public class RegisteredCoursesHelper {
    Session session = null;

    /**
     *
     */
    public RegisteredCoursesHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List getCourseIdGivenStudentId(int studentId) {
        List<RegisteredCourses> courseIdList= null;
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from RegisteredCourses where studentId = "+studentId);
            courseIdList = (List<RegisteredCourses>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseIdList;
    }
    
}
