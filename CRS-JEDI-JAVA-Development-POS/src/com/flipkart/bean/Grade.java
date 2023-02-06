package com.flipkart.bean;

public class Grade {

    private String grade;
    private int gradeValue;

    public Grade() {
    }



    public Grade(String grade, int gradeValue) {
        this.grade = grade;
        this.gradeValue = gradeValue;
    }

    public Grade(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
