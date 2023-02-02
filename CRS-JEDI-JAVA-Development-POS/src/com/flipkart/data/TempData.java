package com.flipkart.data;

import com.flipkart.bean.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TempData {
    public static HashMap<Integer, Course> courseCatalogue = new HashMap<>();
    public static HashMap<Integer, Professor> professorDatabase = new HashMap<>();

    public static HashMap<Integer, Integer> courseToProfMapping = new HashMap<>();

    public static HashMap<Integer, GradeCard> reportCards = new HashMap<>();

    public static HashMap<Integer, Boolean> isStudentApproved = new HashMap<>();

    public static HashMap<Integer, Student> idToStudent = new HashMap<>();

    public static HashMap<Integer, List<Course>> profToCourses = new HashMap<>();

    public static HashMap<Course, List<Student>> courseToEnrolledStudents = new HashMap<>();

    public static HashMap<Integer, Set<RegisteredCourse>> studentToRegisteredCourseList = new HashMap<>();

    public static HashMap<Integer, Set<Integer>> studentToCoursePreferenceList = new HashMap<>();


    //public static HashMap<>
    static {
        //populating course catalog
        courseCatalogue.put(1, new Course(1, "DSA"));
        courseCatalogue.put(2, new Course(2, "DBMS"));
        courseCatalogue.put(3, new Course(3, "DAA"));

    }


}
