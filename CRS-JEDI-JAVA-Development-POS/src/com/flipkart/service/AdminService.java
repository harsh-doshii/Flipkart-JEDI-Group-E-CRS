package com.flipkart.service;

import com.flipkart.bean.*;

import java.util.List;

public interface AdminService {
    public void addCourse(Course course);

    public void removeCourse(int courseID);


    public void addProfessor(Professor professor);

    public Boolean removeProfessor(int profID);

    public void assignProfessor(int profID, int courseID);

    public void generateGradeCard();

    public List<Course> viewCourse();

    public void validateRegistration();

    public void approveStudent(int studentID);

    public void addStudent(Student student);
    public List<Professor> viewProfessor();

    public List<Student> viewPending();

}
