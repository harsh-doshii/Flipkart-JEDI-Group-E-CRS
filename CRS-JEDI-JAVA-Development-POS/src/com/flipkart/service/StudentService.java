package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.PaymentNotification;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseFoundException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAssignedException;
import com.flipkart.exception.StudentNotFoundException;

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
    public Boolean addCourse(int studentId, int courseId)  throws StudentNotFoundException;

    /**
     * @param studentId
     * @param courseId
     * @return
     */
    public Boolean dropCourse(int studentId, int courseId) throws StudentNotFoundException, CourseNotFoundException;

    /**
     * @param studentId
     * @return
     */
    public List<RegisteredCourse> viewGrades(int studentId) throws StudentNotFoundException, GradeNotAssignedException;

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

    public List<PaymentNotification> viewNotifications(int studentId);


}
