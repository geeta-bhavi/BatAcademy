/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.helper;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author geeta
 */
public class StudentHelper {

    Session session = null;

    public StudentHelper() {
    }

    public Object getStudentDetails(int studentId) {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        Object student = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Student where studentId =" + studentId);
            student = q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return student;
    
}

public Object getStudentDetails(int studentId, String pwd) {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        Object student = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Student where StudentId ="+studentId+" and Password =\""+pwd+"\"");
            student =  q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return student;
    }
    
}
