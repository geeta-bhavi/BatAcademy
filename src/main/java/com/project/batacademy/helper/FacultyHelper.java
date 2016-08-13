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
public class FacultyHelper {

    Session session = null;

    public FacultyHelper() {
    }

    public Object getFacultyDetails(int facultyId) {
        /*pass pwd later when column is added*/
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        Object faculty = null;
        Transaction tx = null;
        try {
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
        /*pass pwd later when column is added*/
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        Object faculty = null;
        Transaction tx = null;
        try {
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
