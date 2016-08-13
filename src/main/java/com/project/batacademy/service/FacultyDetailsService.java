/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.batacademy.service;

import com.project.batacademy.helper.FacultyHelper;
import com.project.batacademy.model.Faculty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Swathi
 */
public class FacultyDetailsService {
    private FacultyHelper facultyHelper;
    private HashMap<Integer,String> facultyMap = new HashMap<>();
    
    public FacultyDetailsService(){
        facultyHelper = new FacultyHelper();
    }
    
    public HashMap<Integer,String> getFacultyName() {
        List<Faculty> facultyList = facultyHelper.getFacultyDetails();
        System.out.println("print faculty " + facultyList.toString());
        
        facultyMap.clear();
        for(Faculty faculty: facultyList)
        {
            facultyMap.put(faculty.getFacultyId(),faculty.getFirstName());
        }
        return facultyMap;
    }
    
    public boolean isEnabled()
    {
        facultyHelper = new FacultyHelper();
        return facultyHelper.getEnableValue();
    }
}
