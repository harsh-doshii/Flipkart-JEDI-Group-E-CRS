package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;

import java.util.List;

public interface StudentService {
    public List<Course> viewCourseCatalouge();

    public Boolean addCourse(int studentId, int courseId);


    public Boolean dropCourse(int studentId, int courseId) ;

    public List<RegisteredCourse> viewGrades(int studentId) ;

    public boolean signUp(Student student) ;

    public boolean isApproved(int studentId) ;

    public List<RegisteredCourse> viewRegisteredCourse(int studentID);

    public boolean isRegistrationDone(int studentID);

    public boolean isPaymentDone(String studentID);

    public float calculateFee(String studentID);

    public void payFee(String studentID, float amount);

}
