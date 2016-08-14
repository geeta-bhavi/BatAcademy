package com.project.batacademy.helper;

import com.project.batacademy.model.Activity;
import com.project.batacademy.model.ActivityId;
import com.project.batacademy.model.RegisteredCoursesId;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author swathi, geeta
 */
public class ActivityHelper {

    Session session = null;

    /**
     *
     */
    public ActivityHelper() {
    }

    public Object getActivityforGiveCouserAndStudent(int courseId, int studentId) {
        Activity activity = null;
        Transaction tx = null;

        System.out.println("getActivityforGiveCouserAndStudent courseId " + courseId + " student ID " + studentId);
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Activity");

            for (Activity activityObj : (List<Activity>) q.list()) {
                if (activityObj.getId().getCourseId() == courseId
                        && activityObj.getId().getStudentId() == studentId) {
                    activity = activityObj;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return activity;
    }

    public Object getActivityDetails(int studentId, int courseId) {
        Object activity = null;
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Activity where studentId =" + studentId + " and CourseId =" + courseId);
            activity = q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return activity;
    }

    public String updateActivity(int studentId, int courseId, int a1, int a2, int a3) {
        String updated = "error";
        Transaction tx = null;

        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            ActivityId id = new ActivityId(studentId, courseId);
            Activity activity = new Activity(id, a1, a2, a3);

            session.saveOrUpdate(activity);
            updated = "success";

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            tx.commit();
        }

        return updated;
    }

    public List<Activity> sumOfActivities(List<RegisteredCoursesId> courseIds) {
        List<Activity> activities = new ArrayList<Activity>();
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            System.out.println("llll " + courseIds);
            Query q = null;
            for (RegisteredCoursesId reg : courseIds) {
                q = session.createQuery("from Activity where id.studentId = :studentId and id.courseId = :courseId");
                q.setParameter("studentId", reg.getStudentId());
                q.setParameter("courseId", reg.getCourseId());
                Activity act = (Activity) q.uniqueResult();
                System.out.println("act " + act);
                if (act != null) {
                    activities.add(act);
                }

            }

            System.out.println("dddd " + activities);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return activities;

    }

    public String deleteRecordsOfStudent(int studentId) {
        String result = "error";
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("delete from Activity where id.studentId =" + studentId);
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
