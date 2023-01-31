package com.flipkart.service;

import com.flipkart.bean.Grade;

import java.util.List;

public class ProfessorServiceOperation implements ProfessorService{

    @Override
    public List viewCourses(String profId) {
        return null;
    }

    @Override
    public List viewStudents(int courseId, String ProfId) {
        return null;
    }

    @Override
    public void assignGrade(String studentId, int courseId, Grade grade) {

    }
}
