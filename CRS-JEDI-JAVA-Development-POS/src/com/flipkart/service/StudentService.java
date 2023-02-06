package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    /**
     * @return
     * @throws SQLException
     */
    public List<Course> viewCourseCatalouge() throws SQLException;

    /**
     * @param studentId
     * @param courseId
     * @return
     */
    public Boolean addCourse(int studentId, int courseId);

    /**
     * @param studentId
     * @param courseId
     * @return
     */
    public Boolean dropCourse(int studentId, int courseId);

    /**
     * @param studentId
     * @return
     */
    public List<RegisteredCourse> viewGrades(int studentId);

//    public boolean signUp(Student student) ;

    /**
     * @param studentId
     */
    public void registerCourses(int studentId);

//    public boolean isApproved(int studentId) ;

    /**
     * @param studentID
     * @return
     */
    public List<Integer> viewRegisteredCourse(int studentID);

//    public boolean isRegistrationDone(int studentID);
//
//    public boolean isPaymentDone(int studentID);

    /**
     * @param studentID
     * @return
     */
    public float calculateFee(int studentID);

    /**
     * @param studentID
     * @param amount
     */
    public void payFee(int studentID, float amount);

}
