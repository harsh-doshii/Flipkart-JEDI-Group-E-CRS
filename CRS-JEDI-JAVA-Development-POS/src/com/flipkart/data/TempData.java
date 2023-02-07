package com.flipkart.data;

import com.flipkart.bean.*;

import java.util.*;

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

    public static HashMap<Integer, User> userDatabase = new HashMap<>();
    public static HashMap<String, Branch> idToBranch = new HashMap<>();

    public static HashMap<Integer, Grade> integerToGrade = new HashMap<>();

    public static HashMap<Integer, Float> remainingPayment = new HashMap<>();

    public static boolean releaseReportCards;
    //public static HashMap<>
    static {
        releaseReportCards = false;
        //populating course catalog
        courseCatalogue.put(1, new Course(1, "DSA","kuchbhi"));
        courseCatalogue.put(2, new Course(2, "DBMS", "kuchbhi"));
        courseCatalogue.put(3, new Course(3, "DAA", "kuchbhi"));

        courseToEnrolledStudents.put(courseCatalogue.get(2), new ArrayList<>());
        courseToEnrolledStudents.put(courseCatalogue.get(3), new ArrayList<>());
//
//        userDatabase.put(555, new Student("Aman", "M", "Delhi", "aman.jham", "weak", 555, "Student", "10101111", new Branch("A7", "CS"), 2, new ArrayList<Course>(),  new ArrayList<Course>()));
//        userDatabase.put(556, new Student("Bhavya", "F", "Delhi", "bhavya.verma", "weak", 556, "Student", "10101111", new Branch("A7", "CS"), 2, new ArrayList<Course>(),  new ArrayList<Course>()));
//        userDatabase.put(557, new Student("Manav", "M", "Delhi", "manav.b", "weak", 557, "Student", "10101111", new Branch("A7", "CS"), 2, new ArrayList<Course>(),  new ArrayList<Course>()));
//        userDatabase.put(55, new Professor("Gaurav", "M", "Delhi", "gaurav.p", "weak", 55, "Professor", "10101111", "CS", new ArrayList<Course>()));
//        userDatabase.put(56, new Professor("Harsh", "M", "Delhi", "harsh.d", "weak", 56, "Professor", "10101111", "CS", new ArrayList<Course>()));
//        userDatabase.put(57, new Professor("Divya", "M", "Delhi", "divya", "weak", 57, "Professor", "10101111", "CS", new ArrayList<Course>()));
//
//        userDatabase.put(5, new Admin("Prakhar", "M", "Delhi", "prakhar.d", "strong", 5, "Admin", "10101111"));

        //populating branches
        idToBranch.put("A7", new Branch("A7", "Computer Science"));
        idToBranch.put("A1", new Branch("A1", "Chemical engineering"));
        idToBranch.put("A4", new Branch("A4", "Mechanical Engineering"));

        integerToGrade.put(10, new Grade("A", 10));
        integerToGrade.put(9, new Grade("B", 9));
        integerToGrade.put(8, new Grade("C", 8));
        integerToGrade.put(7, new Grade("D", 7));

        List<Student> enrolledStudents = new ArrayList<>();
        enrolledStudents.add((Student) userDatabase.get(555));
        courseToEnrolledStudents.put(courseCatalogue.get(1), enrolledStudents);

        List<Course> profCourseList = new ArrayList<>();
        profCourseList.add(courseCatalogue.get(1));
        profToCourses.put(55, profCourseList);

        courseToProfMapping.put(1, 55);

        Set<RegisteredCourse> regCourseList = new HashSet<>();
        regCourseList.add(new RegisteredCourse(courseCatalogue.get(1), idToStudent.get(555), 1, null));
        studentToRegisteredCourseList.put(555, regCourseList);
    }


}
