package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDAO;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.data.TempData;

import java.sql.SQLException;
import java.util.*;

public class StudentServiceOperation implements StudentService {

    private static StudentServiceOperation instance = null;

    private StudentServiceOperation (){}
    public static StudentServiceOperation getInstance() {
        if (instance == null) {
                instance = new StudentServiceOperation();
        }
        return instance;
    }
    public List<Course> viewCourseCatalouge() throws SQLException
    {
//        List<Course> coursesList = new ArrayList<Course>();
//        for (Map.Entry<Integer, Course> cur : TempData.courseCatalogue.entrySet()) {
//            coursesList.add(cur.getValue());
//        }
//        return coursesList;
        StudentDAO studentDAOInterface = StudentDAOImpl.getInstance();
        return studentDAOInterface.getCourseCatalogue();
    }

    public Boolean addCourse(int studentId, int courseId) {
//        if (TempData.studentToCoursePreferenceList.get(studentId) == null) {
//            TempData.studentToCoursePreferenceList.put(studentId, new HashSet<>());
//        }
//        TempData.studentToCoursePreferenceList.get(studentId).add(courseId);
//        return true;
        try {
            return StudentDAOImpl.getInstance().addCourse(studentId, courseId);
        }
        catch (Exception e){
            return false;
        }
    }

    public Boolean dropCourse(int studentId, int courseId) {
//        if(TempData.studentToRegisteredCourseList.get(studentId).contains(courseId)) {
//            TempData.studentToRegisteredCourseList.get(studentId).remove(courseId);
//            return true;
//        }
//        return false;
        try {
            return StudentDAOImpl.getInstance().dropCourse(studentId, courseId);
        }
        catch (Exception e){
            return false;
        }
    }

    public List<RegisteredCourse> viewGrades(int studentId) {
        List<RegisteredCourse> registeredCourses = new ArrayList<>();
//        if (TempData.releaseReportCards == false) {
//            System.out.println("Grade not released yet;)");
//            return registeredCourses;
//        }
//        if (TempData.studentToRegisteredCourseList.get(studentId) == null) return registeredCourses;
//        for(var cur : TempData.studentToRegisteredCourseList.get(studentId)) {
//            registeredCourses.add(cur);
//        }
        try {
            return StudentDAOImpl.getInstance().viewGrades(studentId);
        }
        catch (Exception e){
            return registeredCourses;

        }
    }

    public void registerCourses(int studentID) {
//        int semester = 1;
//        Student student = TempData.idToStudent.get(studentID);
//        if (TempData.studentToCoursePreferenceList.get(studentID) == null) {
//            System.out.println("Your preference list is empty, add courses first and then register");
//            return;
//        }
//        if (TempData.studentToRegisteredCourseList.get(studentID) == null) {
//            TempData.studentToRegisteredCourseList.put(studentID, new HashSet<RegisteredCourse>());
//        }
//        for (int courseId : TempData.studentToCoursePreferenceList.get(studentID)) {
//            Course course = TempData.courseCatalogue.get(courseId);
//            if (TempData.courseToEnrolledStudents.get(courseId) == null) {
//                TempData.courseToEnrolledStudents.put(course, new ArrayList<>());
//            }
//            if (TempData.studentToRegisteredCourseList.get(studentID).size() == 4) continue;
//            if (TempData.courseToEnrolledStudents.get(course).size() < 10) {
//                TempData.courseToEnrolledStudents.get(course).add(student);
//                RegisteredCourse registeredCourse = new RegisteredCourse(course, student, semester);
//                TempData.studentToRegisteredCourseList.get(studentID).add(registeredCourse);
//            }
//        }
//        System.out.println("According to your preferences and availability of the courses, you are enrolled in the following courses");
//        int count = 0;
//        for (RegisteredCourse course : TempData.studentToRegisteredCourseList.get(studentID)) {
//            count++;
//            System.out.println(count + ".     " + course.getCourse().getCourseName());
//        }
//        float totalFee = (float) 10000 * count;
//        TempData.remainingPayment.put(studentID, totalFee);
        try {
             StudentDAOImpl.getInstance().registerCourses(studentID);
        }
        catch (Exception e){

        }
    }
//    public boolean signUp(Student student) {
//        TempData.idToStudent.put(student.getId(), student);
//        TempData.isStudentApproved.put(student.getId(), false);
//        return true;
//    }

    public boolean isApproved(int studentId) {
        return TempData.isStudentApproved.get(studentId);
    }

    public List<Integer> viewRegisteredCourse(int studentId) {
        List<Integer> registeredCourseList = new ArrayList<>();
//        for (var cur : TempData.studentToRegisteredCourseList.get(studentID)) {
//            registeredCourseList.add(cur);
//        }
//        return registeredCourseList;
        try {
            return StudentDAOImpl.getInstance().viewRegisteredCourses(studentId);
        }
        catch (Exception e){
            return registeredCourseList;

        }
    }

//    public boolean isRegistrationDone(int studentID) {
//        return TempData.isStudentApproved.containsKey(studentID);
//
//    }

//    @Override
//    public boolean isPaymentDone(int studentID) {
//        return false;
//    }

    @Override
    public float calculateFee(int studentID) {
        try {
            return StudentDAOImpl.getInstance().calculateRemainingFee(studentID);
        }
        catch (Exception e){
            return -1;
        }
    }

    @Override
    public void payFee(int studentID, float amount) {
//        float amountRemaining = TempData.remainingPayment.get(studentID);
//        if (amountRemaining < amount) {
//            System.out.println("You reaming pay is :" + amountRemaining);
//            return;
//        }
//        TempData.remainingPayment.put(studentID, amountRemaining - amount);
//        System.out.println("You have successfully paid " + amount);
//        float remainingFee = TempData.remainingPayment.get(studentID);
//        System.out.println("Remaining Fee " + remainingFee);
        try {
            StudentDAOImpl.getInstance().makePayment(studentID, amount);
        }
        catch (Exception e){

        }
    }

}
