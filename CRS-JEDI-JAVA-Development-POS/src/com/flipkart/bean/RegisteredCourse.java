package com.flipkart.bean;

public class RegisteredCourse {
    private Course course;

    private Student student;
    private int semester;
    private Professor professor;

    private Grade grade;

    public RegisteredCourse(Course course, Professor professor, Student student, int semester,
                            Grade grade) {
        this.course = course;
        this.professor = professor;
        this.student = student;
        this.semester = semester;
        this.grade = grade;
    }
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    //    private Grade grade;
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }



}
