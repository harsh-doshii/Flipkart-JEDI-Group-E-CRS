package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    public List<Course> getCourseCatalogue() throws SQLException;

    public Boolean addCourse(int studentId, int courseId) throws SQLException;

    public Boolean dropCourse(int studentId, int courseId) throws SQLException;

    public float calculateRemainingFee(int studentID) throws SQLException;

    public void registerCourses(int studentId) throws SQLException;

    public List<Integer> viewRegisteredCourses(int studentId) throws SQLException;

    public List<RegisteredCourse> viewGrades(int studentId) throws SQLException;

    public boolean makePayment(int studentId, float amount) throws SQLException;
 }