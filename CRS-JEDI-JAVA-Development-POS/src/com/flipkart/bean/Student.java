package com.flipkart.bean;

import java.util.List;

public class Student extends User{
    private Branch branch;
    private int semester;
    private List coursesEnrolled;

    private List preferenceList;

    public Student(String name, String gender, String address, String username, String password, int id, String role, String dob, Branch branch, int semester, List coursesEnrolled, List preferenceList) {
        super(name, gender, address, username, password, id, role, dob);
        this.branch = branch;
        this.semester = semester;
        this.coursesEnrolled = coursesEnrolled;
        this.preferenceList = preferenceList;
    }



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

}
