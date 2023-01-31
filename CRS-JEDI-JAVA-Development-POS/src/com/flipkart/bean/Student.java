package com.flipkart.bean;

import java.util.List;

public class Student extends User{
    private Branch branch;
    private int semester;
    private List coursesEnrolled;



    private String grade;
    private List preferenceList;

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public List getCoursesEnrolled() {
        return coursesEnrolled;
    }

    public void setCoursesEnrolled(List coursesEnrolled) {
        this.coursesEnrolled = coursesEnrolled;
    }

    public List getPreferenceList() {
        return preferenceList;
    }

    public void setPreferenceList(List preferenceList) {
        this.preferenceList = preferenceList;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
