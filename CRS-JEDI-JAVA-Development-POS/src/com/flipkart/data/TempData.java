package com.flipkart.data;

import com.flipkart.bean.*;

import java.util.HashMap;
import java.util.Map;

public class TempData {
    public static HashMap<Integer, Course> courseCatalogue = new HashMap<>();
    public static HashMap<Integer, Professor> professorDatabase = new HashMap<>();

    public static HashMap<Integer, Integer> courseToProfMapping = new HashMap<>();

    public static HashMap<Integer, GradeCard> reportCards = new HashMap<>();

    public static HashMap<Integer, Boolean> isStudentApproved = new HashMap<>();

    public static HashMap<Integer, Student> idToStudent = new HashMap<>();

    public TempData() {
        courseCatalogue.put(1, new Course(1, "DSA"));
    }

}
