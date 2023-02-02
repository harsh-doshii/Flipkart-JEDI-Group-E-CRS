package com.flipkart.client;

import java.util.Scanner;

public class StudentCRSMenu {

    private int studentID;

    public StudentCRSMenu(Integer studentID){
        this.studentID = studentID;
    }

    public void displayMenu() {
        // Display the options available for the Student
        Scanner sc = new Scanner(System.in);
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
