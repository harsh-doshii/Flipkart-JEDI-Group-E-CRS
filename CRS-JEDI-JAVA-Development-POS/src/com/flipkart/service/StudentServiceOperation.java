package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceOperation implements StudentService {
    public List<Course> viewCourseCatalouge()
    {
        List<Course> courses = new ArrayList<Course>();
        return courses;
    }

    public List<RegisteredCourse> viewGrades(String studentId)
    {
        List<RegisteredCourse> registeredCourses = new ArrayList<RegisteredCourse>();
        return registeredCourses;
    }

    public void signUp(Student student)
    {
        System.out.println("Sign Up Successful");
    }

    public boolean isApproved(String studentId)
    {
        return true;
    }

}
