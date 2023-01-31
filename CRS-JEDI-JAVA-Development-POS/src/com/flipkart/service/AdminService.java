package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

public interface AdminService {
    public void addCourse(Course course);

    public void removeCourse(int courseID);


    public void addProfessor(Professor professor);

    public void removeProfessor(String profID);

    public void assignProfessor(String profID, int courseID);

    public void generateGradeCard(String studentID);

    public List<Course> viewCourse();

    public void validateRegistration();

    public void approveStudent(String studentID);


    public List<Professor> viewProfessor();

    public List<Student> viewPending();




}
