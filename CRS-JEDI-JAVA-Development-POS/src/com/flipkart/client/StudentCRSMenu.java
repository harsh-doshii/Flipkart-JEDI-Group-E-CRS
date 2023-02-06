package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.data.TempData;
import com.flipkart.service.StudentService;
import com.flipkart.service.StudentServiceOperation;

import java.awt.*;
import java.util.Scanner;

public class StudentCRSMenu {

    private int studentID;

    StudentService studentServiceOperation = StudentServiceOperation.getInstance();
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
            System.out.println("3. Registration for the courses add in the preference list");
            System.out.println("4. Add Course");
            System.out.println("5. Drop Course");
            System.out.println("6. View registered courses");
            System.out.println("7. Make Payment");
            System.out.println("8. Get Total Amount to Pay for the courses");
            System.out.println("9. Logout");
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
                System.out.println("Total amount to pay" + getTotalFeeToPay());
                System.out.println("Enter the amount you hav to pay for now");
                float amount = sc.nextFloat();
                payFee(amount);
            }
            else if(input==8) {
                getTotalFeeToPay();
            }
            else if(input==9) {
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
        // have to add here number of available seats.
        try {
            int cur = 0;
            System.out.format("%15s %15s %32s\n", "Sr. No", "Course ID", "Course Name");
            for (var crs : studentServiceOperation.viewCourseCatalouge()) {
                cur++;
                System.out.format("%15d %15d %32s\n",cur, crs.getCourseId(), crs.getCourseName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewGrades(){
//        System.out.println();
        System.out.println("Your grades for the registered courses : ");

        int cur = 0;
        System.out.format("%15s %15s %32s %15s\n", "Sr. No", "Course ID", "Course Name", "Grade");
        if (studentServiceOperation.viewGrades(studentID) == null) {
            return ;
        }
        if (studentServiceOperation.viewGrades(studentID).size() == 0) {
            return ;
        }
        for (RegisteredCourse regCourse : studentServiceOperation.viewGrades(studentID)) {
            cur++;
            String grade;
            if (regCourse.getGrade() == null) grade = null;
            else grade = regCourse.getGrade().getGrade();
            System.out.format("%15d %15d %32s %15s\n",cur, regCourse.getCourse().getCourseId(), regCourse.getCourse().getCourseName(), grade);
//            System.out.println();
            //System.out.println("suck");
        }
//        System.out.printf("view Grades");
    }
    private void registerCourses(){
//        studentServiceOperation.
        System.out.printf("Starting the Registration process");
//      Register  viewCourseCatalogue();
//        System.out.printf("Enter courseId to register");
//        Scanner sc = new Scanner(System.in);
//        int cId = sc.nextInt();
//        studentServiceOperation.addCourse()
        studentServiceOperation.registerCourses(studentID);
    }

    private void addCourse(){
        System.out.println("Enter course id to register");
        int courseId = sc.nextInt();
        studentServiceOperation.addCourse(studentID, courseId);
//        System.out.printf("add course");
    }

    private void dropCourse() {
        System.out.println("Enter course id to drop");
        int courseId = sc.nextInt();
        studentServiceOperation.dropCourse(studentID,courseId);
//        System.out.printf("Drop Course");
    }

    private void viewRegisterCourses(){
        int cur = 0;
//        System.out.format("%15s %15s %32s\n", "Sr. No", "Course ID", "Course Name");
        for(var regCourse : studentServiceOperation.viewRegisteredCourse(studentID)) {
            cur++;
//            System.out.format("%15d %15d %32s\n",cur, regCourse.getCourse().getCourseId(), regCourse.getCourse().getCourseName());
            System.out.println(regCourse);
//          System.out.println();
        }
//      System.out.printf("view registered courses");
    }

    private float getTotalFeeToPay() {
        float totalFee = studentServiceOperation.calculateFee(studentID);
        System.out.println("Total Fee remaining to be paid " + totalFee);
        return totalFee;
    }

    private void payFee(float amount) {
        System.out.printf("Initiating Fee payment");
        studentServiceOperation.payFee(studentID, amount);
    }

}
