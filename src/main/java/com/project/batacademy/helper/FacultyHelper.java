package com.project.batacademy.helper;

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
        Object faculty = null;
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
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
        Object faculty = null;
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Faculty where facultyId =" + facultyId + " and Password ='" + pwd + "'");
            faculty = q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return faculty;
    }

    public List getFacultyDetails() {
        List<Faculty> faculty = new ArrayList<Faculty>();
        //Object faculty = null;
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Faculty");
            faculty = (List<Faculty>) q.list();
            //System.out.println("getFacultyDetails " + q.list().toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return faculty;
    }

    public boolean getEnableValue() {
        Transaction tx = null;
        Faculty faculty = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Faculty where Designation ='PRESIDENT'");
            faculty = (Faculty) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return faculty.isEnable();
    }

    public String getFacultyNameForAGivenCourseID(int courseID) {
        Transaction tx = null;
        String facultyName = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("select F.firstName from Course C, Faculty F WHERE C.facultyId = F.facultyId and C.courseId=" + courseID);
            
            facultyName = (String) q.uniqueResult();
            //System.out.println(" query " + q.getQueryString().toString());
//            for (String facultyFirstName : (List<String>) q.list()) {
//                facultyName = facultyFirstName;
//            }
//            System.out.println(" First Name  " + facultyName);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return facultyName;
    }

    public String updateEnableColumn(boolean enable) {
        String updated = "error";
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("update Faculty set enable = :enable where facultyId = 1");
            q.setParameter("enable", enable);
            q.executeUpdate();
            updated = "success";

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            tx.commit();
        }

        return updated;

    }
}
