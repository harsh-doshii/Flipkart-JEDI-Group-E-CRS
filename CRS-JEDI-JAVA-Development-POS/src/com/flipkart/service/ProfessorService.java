package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorService {
    public List<Course> viewCourses(String profId);

    public List<Student> viewStudents(int courseId, String ProfId);

    public void assignGrade(String studentId, int courseId, Grade grade);
}
