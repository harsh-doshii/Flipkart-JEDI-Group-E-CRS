package com.flipkart.bean;

import java.util.List;

public class Professor extends User{

    private String department;
    private List courseList;


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
