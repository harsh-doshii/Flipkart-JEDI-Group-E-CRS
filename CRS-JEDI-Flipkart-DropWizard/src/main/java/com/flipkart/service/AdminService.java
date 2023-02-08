package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.exception.*;

import java.util.List;

public interface AdminService {
    /**
     * @param course
     */
    public void addCourse(Course course) throws CourseFoundException;

    /**
     * @param courseID
     */
    public void removeCourse(int courseID) throws CourseNotDeletedException, CourseNotFoundException;


    /**
     * @param professor
     */
    public void addProfessor(Professor professor) throws ProfFoundException, ProfNotAddedException;

    /**
     * @param profID
     * @return
     */
    public Boolean removeProfessor(int profID) throws ProfNotFoundException, ProfNotDeletedException;

    /**
     * @param profID
     * @param courseID
     */
    public void assignProfessor(int profID, int courseID) throws  CourseNotFoundException, ProfNotFoundException;

    /**
     *
     */
    public void generateGradeCard() throws StudentNotFoundException;

    /**
     * @return
     */
    public List<Course> viewCourse();



    /**
     * @param studentID
     */
    public void approveStudent(int studentID) throws StudentNotFoundException ;

    /**
     * @param student
     */
    public void addStudent(Student student) throws StudentNotAddedException, StudentFoundException;

    /**
     * Method to fetch the list of all enrolled professors
     *
     * @return List of Professors in table Professors
     */
    public List<Professor> viewProfessor();

    /**
     * Method view pending approvals of students
     */
    public List<Student> viewPending() throws StudentsNotFoundException;

    /**
     * View Approved Students
     * @return
     * @throws StudentsNotFoundException
     */
    public List<Student> viewApprovedStudents() throws StudentsNotFoundException;


}
