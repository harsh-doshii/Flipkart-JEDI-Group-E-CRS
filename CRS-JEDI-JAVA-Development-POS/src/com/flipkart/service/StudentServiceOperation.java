package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.StudentDAO;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.data.TempData;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAssignedException;
import com.flipkart.exception.StudentNotFoundException;

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
        try {
            StudentDAO studentDAOInterface = StudentDAOImpl.getInstance();
            return studentDAOInterface.getCourseCatalogue();
        }
        catch (Exception e){
           throw new SQLException();
        }
    }

    public Boolean addCourse(int studentId, int courseId, boolean isPrimary) throws StudentNotFoundException {
        try {
            return StudentDAOImpl.getInstance().addCourse(studentId, courseId, isPrimary);
        }
        catch (Exception e){
            throw new StudentNotFoundException(studentId);
        }
    }

    public Boolean dropCourse(int studentId, int courseId) throws StudentNotFoundException, CourseNotFoundException{
        try {
            return StudentDAOImpl.getInstance().dropCourse(studentId, courseId);
        }
        catch (Exception e){
            throw new CourseNotFoundException(courseId);
        }
    }

    public List<RegisteredCourse> viewGrades(int studentId) throws StudentNotFoundException, GradeNotAssignedException {
        try {
            return StudentDAOImpl.getInstance().viewGrades(studentId);
        }
        catch (Exception e){
            throw new GradeNotAssignedException(studentId);
        }
    }

    public void registerCourses(int studentID)  {
   try {
             StudentDAOImpl.getInstance().registerCourses(studentID);
        }
        catch (Exception e){

        }
    }

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

    @Override
    public float calculateFee(int studentID) {
        try {
            return StudentDAOImpl.getInstance().calculateRemainingFee(studentID);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    @Override
    public void payFee(int studentID, float amount) {
        try {
            StudentDAOImpl.getInstance().makePayment(studentID, amount);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<PaymentNotification> viewNotifications(int studentId) {
        List<PaymentNotification> notifications = new ArrayList<>();
        try {
            notifications = StudentDAOImpl.getInstance().viewNotifications(studentId);
            return notifications;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return notifications;
    }


    public PreferenceList viewCoursesInPreferenceList(int studentID) {
        PreferenceList preferenceList = new PreferenceList();
        try {
            preferenceList = StudentDAOImpl.getInstance().viewCoursesInPreferenceList(studentID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return preferenceList;
    }


}
