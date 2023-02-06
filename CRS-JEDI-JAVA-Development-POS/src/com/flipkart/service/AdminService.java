package com.flipkart.service;

import com.flipkart.bean.*;

import java.util.List;

public interface AdminService {
    /**
     * @param course
     */
    public void addCourse(Course course);

    /**
     * @param courseID
     */
    public void removeCourse(int courseID);


    /**
     * @param professor
     */
    public void addProfessor(Professor professor);

    /**
     * @param profID
     * @return
     */
    public Boolean removeProfessor(int profID);

    /**
     * @param profID
     * @param courseID
     */
    public void assignProfessor(int profID, int courseID);

    /**
     *
     */
    public void generateGradeCard();

    /**
     * @return
     */
    public List<Course> viewCourse();

    /**
     *
     */
    public void validateRegistration();

    /**
     * @param studentID
     */
    public void approveStudent(int studentID);

    /**
     * @param student
     */
    public void addStudent(Student student);

    /**
     * @return
     */
    public List<Professor> viewProfessor();

    /**
     * @return
     */
    public List<Student> viewPending();

}
