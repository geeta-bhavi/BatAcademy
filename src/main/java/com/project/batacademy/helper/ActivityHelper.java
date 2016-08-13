/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.helper;

import com.project.batacademy.model.Activity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author swathi
 */
public class ActivityHelper {
     Session session = null;

    /**
     *
     */
    public ActivityHelper() {
    }

    public Object getActivityforGiveCouserAndStudent(int courseId,int studentId) {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        Activity activity = null;
        Transaction tx = null;
        
            System.out.println("getActivityforGiveCouserAndStudent courseId " + courseId + " student ID " + studentId);
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Activity");
            
            for(Activity activityObj: (List<Activity>) q.list())
            {
                if(activityObj.getId().getCourseId() == courseId 
                        && activityObj.getId().getStudentId() == studentId)
                    activity = activityObj;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return activity;
    }
}
