package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

public interface AdminService {
    public void addCourse(Course course);

    public void removeCourse(int courseID);


    public void addProfessor(Professor professor);

    public Boolean removeProfessor(int profID);

    public void assignProfessor(int profID, int courseID);

    public GradeCard generateGradeCard(int studentID);

    public List<Course> viewCourse();

    public void validateRegistration();

    public void approveStudent(int studentID);


    public List<Professor> viewProfessor();

    public List<Student> viewPending();

}
