package com.flipkart.bean;

public class Course {


    private String courseName;
    private int courseId;

    private String courseDescription;
    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }


//    private Professor


    public Course() {
    }

    public Course(int courseId, String courseName, String courseDescription) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseDescription = courseDescription;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
