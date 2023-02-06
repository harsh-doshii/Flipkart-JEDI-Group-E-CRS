package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorDAO {
    /**
     * @param courseId
     * @return
     * @throws Exception
     */
    public List<Student> viewStudents(int courseId) throws Exception;

    /**
     * @param profId
     * @return
     * @throws Exception
     */
    public List<Course> viewCourses(int profId) throws Exception;

    /**
     * @param courseId
     * @param profId
     * @throws Exception
     */
    public void signUpForCourse(int courseId, int profId)  throws Exception;

    /**
     * @param studentId 
     * @param courseId
     * @param grade
     * @param sem
     * @return
     * @throws Exception
     */
    public boolean assignGrade(int studentId, int courseId, Grade grade, int sem)  throws Exception;
}
