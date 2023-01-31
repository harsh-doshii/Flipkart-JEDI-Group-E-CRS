package com.flipkart.service;

public class StudentServiceOperation implements StudentService {
    private void showNotification(){
        System.out.printf("show notification");
    }

    private void viewCourseCatalogue(){
        System.out.printf("view course");
    }

    private void viewGrades(){
        System.out.printf("view Grades");
    }
    private void registerCourses(){
        System.out.printf("Register course");
    }

    private void addCourse(){
        System.out.printf("add course");
    }

    private void dropCourse() {
        System.out.printf("Drop Course");
    }

    private void viewRegisterCourses(){
        System.out.printf("view registered courses");
    }

    private  void payFee(){
        System.out.printf("pay fees");
    }

}
