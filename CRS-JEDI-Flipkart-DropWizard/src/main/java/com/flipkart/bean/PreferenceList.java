package com.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

public class PreferenceList {
    private List<Course> primaryCourses;

    private List<Course> secondaryCourses;

    public PreferenceList () {
        primaryCourses = new ArrayList<>();
        secondaryCourses = new ArrayList<>();
    }

    public List<Course> getPrimaryCourses() {
        return primaryCourses;
    }

    public void setPrimaryCourses(List<Course> primaryCourses) {
        this.primaryCourses = primaryCourses;
    }

    public List<Course> getSecondaryCourses() {
        return secondaryCourses;
    }

    public void setSecondaryCourses(List<Course> secondaryCourses) {
        this.secondaryCourses = secondaryCourses;
    }

    public void addInPrimary(Course c) {
        this.primaryCourses.add(c);
    }

    public void addInSecondary(Course c) {
        this.secondaryCourses.add(c);
    }
}
