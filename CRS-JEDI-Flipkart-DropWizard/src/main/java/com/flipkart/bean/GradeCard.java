package com.flipkart.bean;

import java.util.List;

public class GradeCard {
    private Student student;
    private int gradeCardId;
    private float cgpa;
    private List courses;
    private int semester;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getGradeCardId() {
        return gradeCardId;
    }

    public void setGradeCardId(int gradeCardId) {
        this.gradeCardId = gradeCardId;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public List getCourses() {
        return courses;
    }

    public void setCourses(List courses) {
        this.courses = courses;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
