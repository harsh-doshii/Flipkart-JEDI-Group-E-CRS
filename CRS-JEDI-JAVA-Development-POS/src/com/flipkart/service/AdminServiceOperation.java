package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import javax.swing.*;
import java.util.List;

public class AdminServiceOperation implements AdminService {


    @Override
    public void addCourse(Course course) {

    }

    @Override
    public void removeCourse(int courseID) {

    }

    @Override
    public void addProfessor(Professor professor) {

    }

    @Override
    public void removeProfessor(String profID) {

    }

    @Override
    public void assignProfessor(String profID, int courseID) {

    }

    @Override
    public void generateGradeCard(String studentID) {

    }

    @Override
    public List<Course> viewCourse() {
        return null;
    }

    @Override
    public void validateRegistration() {

    }

    @Override
    public void approveStudent(String studentID) {

    }

    @Override
    public List<Professor> viewProfessor() {
        return null;
    }

    @Override
    public List<Student> viewPending() {
        return null;
    }
}
