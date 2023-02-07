package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import java.sql.SQLException;
import java.util.List;
import com.flipkart.exception.*;

public interface ProfessorService {

    /**
     * Method to view course which professor is teaching
     * @param profId -> id of professor who is viewing courses
     * @return -> List of courses professor is teaching
     * @throws ProfNotFoundException
     */

    public List<Course> viewCourses(int profId) throws ProfNotFoundException;

    /**
     * Return enrolled student list for given course.
     * @param courseId -> ID of course whose students are requested.
     * @return -> List of students enrolled in course.
     * @throws StudentNotFoundException
     */
    public List<Student> viewStudents(int courseId) throws SQLException;

    /**
     * Method to assign grade to student.
     * @param studentId -> ID of student to whom grade will be assigned.
     * @param courseId -> ID of course in which grade is given.
     * @param grade -> Grade given to student.
     * @throws GradeNotAssignedException
     * @throws StudentNotFoundException
     */
    public boolean assignGrade(int studentId, int courseId, Grade grade, int sem)  throws GradeNotAssignedException, StudentNotFoundException;

    /**
     * @param courseId
     * @param profId
     */
    public void signUpForCourse(int courseId, int profId) throws CourseNotFoundException;
}
