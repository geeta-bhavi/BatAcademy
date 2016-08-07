package com.project.batacademy.helper;

import com.project.batacademy.service.*;
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
    }

    public List getCourseIdGivenStudentId(int studentId) {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<RegisteredCourses> courseIdList= null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from RegisteredCourses where studentId = "+studentId);
            courseIdList = (List<RegisteredCourses>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return courseIdList;
    }
    
}
