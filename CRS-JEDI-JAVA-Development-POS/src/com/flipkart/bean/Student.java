package com.flipkart.bean;

import java.util.List;

public class Student extends User{

    private int studentId;
    private Branch branch;
    private int semester;
    private List coursesEnrolled;

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    private String isApproved;
    private String name;

    private List preferenceList;

    private float remainingPayment;

    public Student(int studentId, String name){
        this.studentId = studentId;
        this.name = name;
    }

    public Student(int studentId, int semester, float remainingPayment) {
        this.studentId = studentId;
        this.setId(studentId);
        this.semester = semester;
        this.remainingPayment = remainingPayment;
    }

    public Student(String name, String gender, String address, String username, String password, int id, String role, String dob, Branch branch, int semester, List coursesEnrolled, List preferenceList) {
        super(name, gender, address, username, password, id, role, dob);
        this.branch = branch;
        this.semester = semester;
        this.coursesEnrolled = coursesEnrolled;
        this.preferenceList = preferenceList;
    }

    public Student(String name, String gender, String address, String username, String password, String dob, String branch) {
        super(name, gender, address, username, password, dob);
        this.branch = new Branch(branch);
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public float getRemainingPayment() {
        return remainingPayment;
    }

    public void setRemainingPayment(float remainingPayment) {
        this.remainingPayment = remainingPayment;
    }

}
