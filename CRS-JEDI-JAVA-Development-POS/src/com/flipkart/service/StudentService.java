package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    public List<Course> viewCourseCatalouge() throws SQLException;

    public Boolean addCourse(int studentId, int courseId);

    public Boolean dropCourse(int studentId, int courseId);

    public List<RegisteredCourse> viewGrades(int studentId);

    public boolean signUp(Student student) ;

    public void registerCourses(int studentId);

    public boolean isApproved(int studentId) ;

    public List<RegisteredCourse> viewRegisteredCourse(int studentID);

    public boolean isRegistrationDone(int studentID);

    public boolean isPaymentDone(int studentID);

    public float calculateFee(int studentID);

    public void payFee(int studentID, float amount);

}
