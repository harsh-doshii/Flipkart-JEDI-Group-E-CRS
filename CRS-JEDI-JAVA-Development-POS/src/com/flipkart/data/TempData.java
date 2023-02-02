package com.flipkart.data;

import com.flipkart.bean.*;

import java.util.HashMap;
import java.util.Map;

public class TempData {
    public HashMap<Integer, Course> courseCatalogue = new HashMap<>();
    public HashMap<Integer, Professor> professorDatabase = new HashMap<>();

    public HashMap<Integer, Integer> courseToProfMapping = new HashMap<>();

    public HashMap<Integer, GradeCard> reportCards = new HashMap<>();

    public HashMap<Integer, Boolean> isStudentApproved = new HashMap<>();

    public HashMap<Integer, Student> idToStudent = new HashMap<>();

}
