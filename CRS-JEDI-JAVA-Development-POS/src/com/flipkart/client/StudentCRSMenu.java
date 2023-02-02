package com.flipkart.client;

import com.flipkart.service.StudentServiceOperation;

import java.awt.*;
import java.util.Scanner;

public class StudentCRSMenu {

    private int studentID;

    StudentServiceOperation studentServiceOperation = new StudentServiceOperation();
    Scanner sc = new Scanner(System.in);

    public StudentCRSMenu(Integer studentID){
        this.studentID = studentID;
    }



    public void displayMenu() {
        // Display the options available for the Student

        while (true) {

            System.out.println("\n==========================================================================\n");
            System.out.println("Student Menu");
            System.out.println("1. View Course Catalogue");
            System.out.println("2. View Grades");
            System.out.println("3. Register");
            System.out.println("4. Add Course");
            System.out.println("5. Drop Course");
            System.out.println("6. View registered courses");
            System.out.println("7. Make Payment");
            System.out.println("8. Logout");
            System.out.println("\n==========================================================================\n");
            System.out.println("Enter Option : ");
            int input = sc.nextInt();
            sc.nextLine();

            if(input==1) {
                viewCourseCatalogue();
            }
            else if(input==2) {
                viewGrades();
            }
            else if(input==3) {
                registerCourses();
            }
            else if(input==4) {
                addCourse();
            }
            else if(input==5) {
                dropCourse();
            }
            else if(input==6) {
                viewRegisterCourses();
            }
            else if(input==7) {
                payFee();
            }
            else if(input==8) {
                logout();
                break;
            }
            else {
                System.out.println("no such operation exists");
            }
        }
    }


    private void logout() {
        System.out.println("student" + studentID + " logged out");
    }

    private void viewCourseCatalogue(){
        int cur = 0;
        System.out.format("%15s %15s %32s\n", "Sr. No", "Course ID", "Course Name");
        for (var crs : studentServiceOperation.viewCourseCatalouge()) {
            cur++;
            System.out.format("%15d %15d %32s\n",cur, crs.getCourseId(), crs.getCourseName());
//            System.out.println();
        }
//        System.out.printf("view course");
    }

    private void viewGrades(){
        System.out.println(studentServiceOperation.viewGrades(studentID));
//        System.out.printf("view Grades");
    }
    private void registerCourses(){
//        studentServiceOperation.

        System.out.printf("Register course");
//      Register  viewCourseCatalogue();
//        System.out.printf("Enter courseId to register");
//        Scanner sc = new Scanner(System.in);
//        int cId = sc.nextInt();
//        studentServiceOperation.addCourse()


    }

    private void addCourse(){
        System.out.println("Enter course id to register");
        int courseid = sc.nextInt();
        studentServiceOperation.addCourse(studentID, courseid);
//        System.out.printf("add course");
    }

    private void dropCourse() {
        System.out.println("Enter course id to drop");
        int courseid = sc.nextInt();
        studentServiceOperation.dropCourse(studentID,courseid);
//        System.out.printf("Drop Course");
    }

    private void viewRegisterCourses(){
        System.out.println(studentServiceOperation.viewRegisteredCourse(studentID));
//        System.out.printf("view registered courses");
    }

    private  void payFee(){
        System.out.printf("pay fees");
    }

}
