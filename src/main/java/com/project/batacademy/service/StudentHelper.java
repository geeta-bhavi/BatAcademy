/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.service;

import com.project.batacademy.model.Student;
import java.util.List;
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
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Object getStudentDetails(int studentId) { /*pass pwd later when column is added*/
        Object student = null;
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Student where studentId ="+studentId);
            student =  q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
    
}
