package com.project.batacademy.helper;

import com.project.batacademy.model.RegisteredCourses;
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
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<RegisteredCourses> courseIdList = null;
        Transaction tx = null;
        try {
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

    public List fetchCoursesList(List<RegisteredCourses> selectedCourses)
    {
        ArrayList<Integer> courseIds = new ArrayList<Integer>();
        for(RegisteredCourses registeredCourse: selectedCourses)
        {
            courseIds.add(registeredCourse.getId().getCourseId());
        }
        
        return courseIds;
    }
    
    public boolean updateRegisteredCourses(List<RegisteredCourses> selectedCourses) {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            RegisteredCourses registeredCourses = new RegisteredCourses();
            for (RegisteredCourses selectedcourse : selectedCourses) {
                registeredCourses.setId(selectedcourse.getId());
                registeredCourses.setCourseName(selectedcourse.getCourseName());
                session.save(registeredCourses);
            }
            
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
            return false;
        }finally{
            session.flush();
            tx.commit();
        }
        return true;
    }
    
    public List getRegisteredCoursesForStudent() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<RegisteredCourses> registeredCourses = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from RegisteredCourses");
            registeredCourses =(List<RegisteredCourses>) q.list();
            System.out.println("course list " + registeredCourses.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
        }
        return registeredCourses;
    }
    
    

}
