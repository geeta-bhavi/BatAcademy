package com.project.batacademy.service;

import com.project.batacademy.model.SelectedCoursesBean;
import com.project.batacademy.helper.ActivityHelper;
import com.project.batacademy.helper.CourseHelper;
import com.project.batacademy.helper.FacultyHelper;
import com.project.batacademy.helper.RegisteredCoursesHelper;
import com.project.batacademy.helper.StudentHelper;
import com.project.batacademy.model.Activity;
import com.project.batacademy.model.ActivityId;
import com.project.batacademy.model.Course;
import com.project.batacademy.model.RegisteredCourses;
import com.project.batacademy.model.RegisteredCoursesId;
import com.project.batacademy.model.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author geeta, swathi
 */
public class StudentDetailsService {

    private StudentHelper studentHelper;
    private CourseHelper courseHelper;
    private RegisteredCoursesHelper regCourseHelper;
    private FacultyHelper facultyHelper;
    private ActivityService activityService;

    public StudentDetailsService() {
    }

    public Student getStudentDetails(int userId) {
        studentHelper = new StudentHelper();
        Student student = (Student) studentHelper.getStudentDetails(userId);
        return student;
    }

    public Student getStudentDetails(int userId, String pwd) {
        studentHelper = new StudentHelper();
        Student student = (Student) studentHelper.getStudentDetails(userId, pwd);
        return student;
    }

    public List<Course> getRemainingCourses(int studentId) {
        regCourseHelper = new RegisteredCoursesHelper();
        courseHelper = new CourseHelper();
        List<Integer> takenCourses = regCourseHelper.getCourseIdGivenStudentId(studentId);
        List<Course> courses = courseHelper.getRemainingCourses(takenCourses);

        return courses;
    }

    public boolean isRegistered(int userId) {
        studentHelper = new StudentHelper();
        return studentHelper.isRegistered(userId);
    }

    public void setRegistered(int userId) {
        System.out.println(" userid " + userId);
        studentHelper = new StudentHelper();
        studentHelper.setRegistered(userId);
    }

    /*public List<SelectedCoursesBean> getRegisteredCourses(int userId){
        
        return regCourseHelper.fetchRegisteredCourses(userId);
    }*/
    public List fetchRegisteredCourses(int studentId) {
        regCourseHelper = new RegisteredCoursesHelper();
        facultyHelper = new FacultyHelper();
        List<RegisteredCourses> registeredCourses = regCourseHelper.getRegisteredCoursesForStudent();

        ArrayList<SelectedCoursesBean> selectedCourses = new ArrayList<SelectedCoursesBean>();
        ActivityHelper activityHelper = new ActivityHelper();
        for (RegisteredCourses registeredCourse : registeredCourses) {
            if (registeredCourse.getId().getStudentId() == studentId) {
                Activity activityObj = (Activity) activityHelper.getActivityforGiveCouserAndStudent(registeredCourse.getId().getCourseId(), studentId);
                int CourseID = registeredCourse.getId().getCourseId();

                SelectedCoursesBean selectedCourse = new SelectedCoursesBean();
                selectedCourse.setCourseID(registeredCourse.getId().getCourseId());
                selectedCourse.setCourseName(registeredCourse.getCourseName());
                selectedCourse.setCompleted(registeredCourse.isCompleted());
                selectedCourse.setFacultyName(facultyHelper.getFacultyNameForAGivenCourseID(CourseID));

                System.out.println("faculty" + facultyHelper.getFacultyNameForAGivenCourseID(CourseID));
                System.out.println("faculty name" + selectedCourse.getFacultyName());

                if (null != activityObj) {
                    selectedCourse.setA1(activityObj.getA1());
                    selectedCourse.setA2(activityObj.getA2());
                    selectedCourse.setA3(activityObj.getA3());
                } else {
                    selectedCourse.setA1(0);
                    selectedCourse.setA2(0);
                    selectedCourse.setA3(0);
                }

                selectedCourses.add(selectedCourse);
            }
        }

        return selectedCourses;
    }

    public String updateRegisterColumn(boolean enable) {
        studentHelper = new StudentHelper();
        String updated = studentHelper.updateRegisterColumn(enable);
        return updated;
    }

    public float getStudentCGPA(int studentId) {
        studentHelper = new StudentHelper();
        float cgpa = studentHelper.getStudentCGPA(studentId);
        return cgpa;
    }

    public void updateStudentCGPA(int studentId, float cgpa) {
        studentHelper = new StudentHelper();
        studentHelper.updateStudentCGPA(studentId, cgpa);
    }

    public void updateStudentGPA() {
        activityService = new ActivityService();
        regCourseHelper = new RegisteredCoursesHelper();

        List<RegisteredCoursesId> notCompletedCourses = regCourseHelper.getNotCompletedCoursesForStudents();
        System.out.println("courses " + notCompletedCourses);
        if (notCompletedCourses.size() > 0) {
            List<Activity> activities = activityService.sumOfActivities(notCompletedCourses);
            if (activities.size() > 0) {
                System.out.println("activities " + activities);
                Map<ActivityId, Integer> gradeMap = new HashMap<ActivityId, Integer>();
                Map<Integer, Float> gpa = new HashMap<Integer, Float>();

                /*calculate grade based on activity score*/
                for (Activity act : activities) {
                    ActivityId id = act.getId();
                    int grade = 0;
                    float avg = ((act.getA1() + act.getA2() + act.getA3()) / 300.0f) * 100;

                    if (isBetween(avg, 95, 100)) {
                        grade = 4;
                    } else if (isBetween(avg, 75, 94)) {
                        grade = 3;
                    } else if (isBetween(avg, 65, 74)) {
                        grade = 2;
                    }
                    System.out.println("avg " + avg + ", grade " + grade);
                    gradeMap.put(id, grade);
                }

                /*calculate current sem gpa based on the grades received in each course for a particular student*/
                for (Map.Entry<ActivityId, Integer> entry : gradeMap.entrySet()) {
                    int key = entry.getKey().getStudentId();
                    float value = entry.getValue();

                    System.out.println("studentid " + key + ", grade " + value);

                    if (!gpa.containsKey(key)) {
                        gpa.put(key, value);
                    } else {
                        float prevValue = gpa.get(key);
                        value = (prevValue + value) / 2.0f;
                        gpa.put(key, value);
                    }
                    System.out.println("studentid " + key + ", gpa " + value);
                }

                /* get cgpa for  a student and then based on current sem gpa - calculate cgpa again */
                for (Map.Entry<Integer, Float> entry : gpa.entrySet()) {
                    int key = entry.getKey();
                    float cgpa = getStudentCGPA(key);
                    float value = entry.getValue();
                    float finalCGPA = value;
                    if (cgpa > 0) {
                        finalCGPA = (cgpa + value) / 2.0f;
                    }
                    updateStudentCGPA(key, finalCGPA);

                }
            }
        }
    }

    public static boolean isBetween(float x, float lower, float upper) {
        return lower <= x && x <= upper;
    }

    public int addStudent(String firstName, String lastName, String password, String phno, String gender) {
        studentHelper = new StudentHelper();
        int id = studentHelper.addStudent(firstName, lastName, password, phno, gender);
        return id;
    }
}
