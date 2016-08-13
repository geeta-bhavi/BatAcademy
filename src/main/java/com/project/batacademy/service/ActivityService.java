/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.service;

import com.project.batacademy.helper.ActivityHelper;
import com.project.batacademy.model.Activity;
import com.project.batacademy.model.RegisteredCoursesId;
import java.util.List;

/**
 *
 * @author geeta
 */
public class ActivityService {

    public ActivityService() {

    }

    public Activity getActivityyDetails(int studentId, int courseId) {
        ActivityHelper activityHelper = new ActivityHelper();
        Activity activity = (Activity) activityHelper.getActivityDetails(studentId, courseId);
        return activity;
    }

    public String updateActivity(int studentId, int courseId, int a1, int a2, int a3) {
        ActivityHelper activityHelper = new ActivityHelper();
        String updated = activityHelper.updateActivity(studentId, courseId, a1, a2, a3);
        return updated;
    }
    
    public List<Activity> sumOfActivities(List<RegisteredCoursesId> courseIds) {
        ActivityHelper activityHelper = new ActivityHelper();
        List<Activity> activities = activityHelper.sumOfActivities(courseIds);
        return activities;
    }

}
