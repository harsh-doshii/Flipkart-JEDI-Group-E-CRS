package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.PaymentNotification;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.exception.DatabaseException;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    /**
     * Get course catalogue
     * @return Course Catalogue displaying all courses offered
     */
    public List<Course> getCourseCatalogue() throws DatabaseException;

    /**
     * @param studentId
     * @param courseId
     * @return
     * @throws SQLException
     */
    public Boolean addCourse(int studentId, int courseId) throws SQLException;

    /**
     * @param studentId
     * @param courseId
     * @return
     * @throws SQLException
     */
    public Boolean dropCourse(int studentId, int courseId) throws SQLException;

    /**
     * @param studentID
     * @return
     * @throws SQLException
     */
    public float calculateRemainingFee(int studentID) throws SQLException;

    /**
     * @param studentId
     * @throws SQLException
     */
    public void registerCourses(int studentId) throws SQLException;

    /**
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<Integer> viewRegisteredCourses(int studentId) throws SQLException;

    /**
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<RegisteredCourse> viewGrades(int studentId) throws SQLException;

    /**
     * @param studentId
     * @param amount
     * @return
     * @throws SQLException
     */
    public boolean makePayment(int studentId, float amount) throws SQLException;

    public List<PaymentNotification> viewNotifications(int studentId) throws SQLException;
 }