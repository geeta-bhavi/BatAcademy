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
        Object student = null;
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
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
        Object student = null;
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Student where StudentId =" + studentId + " and Password ='" + pwd + "'");
            student = q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return student;
    }

    public boolean isRegistered(int studentId) {
        Student student = null;
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("from Student where StudentId =" + studentId);
            student = (Student) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return student.isRegister();
    }

    public void setRegistered(int studentId) {
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("update Student set Register = true where StudentId = :studentId");
            q.setParameter("studentId", studentId);
            System.out.println(" userid " + studentId);
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
    }

    public String updateRegisterColumn(boolean completed) {
        String updated = "error";
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            System.out.println("completed" + completed);
            Query q = session.createQuery("update Student set Register = :completed");
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

    public float getStudentCGPA(int studentId) {
        float cgpa = 0f;
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("select cgpa from Student where studentId =" + studentId);
            cgpa = (float) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            tx.commit();
        }

        return cgpa;
    }

    public void updateStudentCGPA(int studentId, float cgpa) {

        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            System.out.println("student: " + studentId + ", cgpa: " + cgpa);
            Query q = session.createQuery("update Student set cgpa = :cgpa where studentId = :studentId");
            q.setParameter("cgpa", cgpa);
            q.setParameter("studentId", studentId);
            q.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            tx.commit();
        }
    }

    public String deleteStudent(int studentId) {
        String result = "error";
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Query q = session.createQuery("delete from Student where studentId =" + studentId);
            q.executeUpdate();
            result = "success";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }

        return result;
    }

    public int addStudent(String firstName, String lastName, String password, String phno, String gender) {
        int id = 0;
        Transaction tx = null;
        try {
            this.session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            int studentId = (int) (Math.random() * 1100 + 1006);

            Student student = new Student(studentId, firstName, lastName, gender, phno, 0.0f, false, password);

            session.save(student);

            id = studentId;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }

        return id;
    }
}
