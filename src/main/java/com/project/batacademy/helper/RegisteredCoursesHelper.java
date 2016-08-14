package com.project.batacademy.helper;

import com.project.batacademy.model.RegisteredCourses;
import com.project.batacademy.model.RegisteredCoursesId;
import java.util.ArrayList;
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

        List<RegisteredCourses> courseIdList = null;
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("from RegisteredCourses where studentId = " + studentId);
            courseIdList = fetchCoursesList((List<RegisteredCourses>) q.list());
            System.out.println("course list " + courseIdList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return courseIdList;
    }

    public List fetchCoursesList(List<RegisteredCourses> selectedCourses) {
        ArrayList<Integer> courseIds = new ArrayList<Integer>();
        for (RegisteredCourses registeredCourse : selectedCourses) {
            courseIds.add(registeredCourse.getId().getCourseId());
        }

        return courseIds;
    }

    public boolean updateRegisteredCourses(List<RegisteredCourses> selectedCourses) {

        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            RegisteredCourses registeredCourses = new RegisteredCourses();
            for (RegisteredCourses selectedcourse : selectedCourses) {
                registeredCourses.setId(selectedcourse.getId());
                registeredCourses.setCourseName(selectedcourse.getCourseName());
                session.save(registeredCourses);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            tx.commit();
        }
        return true;
    }

    public List getRegisteredCoursesForStudent() {
        
        List<RegisteredCourses> registeredCourses = null;
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("from RegisteredCourses");
            registeredCourses = (List<RegisteredCourses>) q.list();
            System.out.println("course list " + registeredCourses.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return registeredCourses;
    }

    public Object checkIfStudentHasTakenFacultyCourse(int studentId, int courseId) {
        Transaction tx = null;
        Object regCourse = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("from RegisteredCourses where studentId=" + studentId + " and CourseId=" + courseId);
            regCourse = q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }

        return regCourse;
    }

    public String updateCompletedColumn(boolean completed) {
        String updated = "error";
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("update RegisteredCourses set Completed = :completed");
            q.setParameter("completed", completed);
            q.executeUpdate();
            updated = "success";

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            tx.commit();
        }

        return updated;
    }

    public List<RegisteredCoursesId> getNotCompletedCoursesForStudents() {
        Transaction tx = null;
        List<RegisteredCoursesId> notCompletedCourses = new ArrayList<RegisteredCoursesId>();
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("select id from RegisteredCourses where completed = false");
            notCompletedCourses = q.list();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            tx.commit();
        }

        return notCompletedCourses;
    }

    public String deleteRecordsOfStudent(int studentId) {
        String result = "error";
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("delete from RegisteredCourses where id.studentId =" + studentId);
            q.executeUpdate();
            result = "success";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }

        return result;
    }

}
