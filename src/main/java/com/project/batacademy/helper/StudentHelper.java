/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.helper;

import com.project.batacademy.model.Student;
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
    
    public boolean isRegistered(int studentId)
    {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        Student student = null;    
        Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Query q = session.createQuery("from Student where StudentId ="+studentId);
                student = (Student)q.uniqueResult();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                tx.commit();
            }
            return student.isRegister();
    }

    public void setRegistered(int studentId)
    {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();   
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("update Student set Register = true " + " where StudentId = :studentId");
            q.setParameter("studentId", studentId);
            System.out.println(" userid " + studentId);
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
    }
}
