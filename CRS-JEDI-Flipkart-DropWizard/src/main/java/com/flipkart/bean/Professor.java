package com.flipkart.bean;

import java.util.List;

public class Professor extends User{

    private String department;
    private List courseList;

    private String name;
    private int id;

    public Professor() {
    }

    public Professor(String name, String gender, String address, String username, String password, int id, int roleId, String dob, String department, List courseList) {
        super(name, gender, address, username, password, id, roleId, dob);
        this.department = department;
        this.courseList = courseList;
    }

    public Professor(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List getCourseList() {
        return courseList;
    }

    public void setCourseList(List courseList) {
        this.courseList = courseList;
    }


}
