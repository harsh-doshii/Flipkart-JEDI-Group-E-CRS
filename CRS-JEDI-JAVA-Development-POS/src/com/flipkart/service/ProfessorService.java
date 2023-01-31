package com.flipkart.service;

import com.flipkart.bean.Grade;

import java.util.List;

public interface ProfessorService {
    public List viewCourses(String profId);

    public List viewStudents(int courseId, String ProfId);

    public void assignGrade(String studentId, int courseId, Grade grade);
}
