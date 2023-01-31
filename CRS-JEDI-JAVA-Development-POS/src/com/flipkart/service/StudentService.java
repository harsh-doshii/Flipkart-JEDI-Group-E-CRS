package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;

import java.util.List;

public interface StudentService {
    public List<Course> viewCourseCatalouge();

    public List<RegisteredCourse> viewGrades(String studentId);

    public void signUp(Student student);

    public boolean isApproved(String studentId);

}
