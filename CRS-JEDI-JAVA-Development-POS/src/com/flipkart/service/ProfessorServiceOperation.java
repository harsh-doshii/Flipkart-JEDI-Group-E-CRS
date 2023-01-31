package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class ProfessorServiceOperation implements ProfessorService{

    @Override
    public List<Course> viewCourses(String profId) {

        List<Course> courses = new ArrayList<Course>();
        return courses;
    }

    @Override
    public List<Student> viewStudents(int courseId, String ProfId) {

        List<Student> enrolled = new ArrayList<Student>();
        return enrolled;
    }

    @Override
    public void assignGrade(String studentId, int courseId, Grade grade) {
    return;
    }
}
