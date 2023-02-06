package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorService {
    /**
     * @param profId
     * @return
     */
    public List<Course> viewCourses(int profId);

    /**
     * @param courseId
     * @return
     */
    public List<Student> viewStudents(int courseId);

    /**
     * @param studentId
     * @param courseId
     * @param grade
     * @param sem
     * @return
     */
    public boolean assignGrade(int studentId, int courseId, Grade grade, int sem);

    /**
     * @param courseId
     * @param profId
     */
    public void signUpForCourse(int courseId, int profId);
}
