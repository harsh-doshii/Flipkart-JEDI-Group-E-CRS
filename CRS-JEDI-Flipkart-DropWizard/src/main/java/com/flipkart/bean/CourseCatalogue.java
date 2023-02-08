package com.flipkart.bean;

import java.util.List;

public class CourseCatalogue {
    private int noOfCourses;
    private List <Course> courses;
    public int getNoOfCourses() {
        return noOfCourses;
    }

    public void setNoOfCourses(int noOfCourses) {
        this.noOfCourses = noOfCourses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }




}
