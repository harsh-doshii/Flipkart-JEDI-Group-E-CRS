package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorService {
    public List<Course> viewCourses(int profId);

    public List<Student> viewStudents(int courseId, int ProfId);

    public void assignGrade(int studentId, int courseId, Grade grade);
}
