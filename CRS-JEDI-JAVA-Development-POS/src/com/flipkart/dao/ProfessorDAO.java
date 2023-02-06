package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorDAO {
    public List<Student> viewStudents(int courseId) throws Exception;
    public List<Course> viewCourses(int profId) throws Exception;
    public void signUpForCourse(int courseId, int profId)  throws Exception;
    public boolean assignGrade(int studentId, int courseId, Grade grade)  throws Exception;
}
