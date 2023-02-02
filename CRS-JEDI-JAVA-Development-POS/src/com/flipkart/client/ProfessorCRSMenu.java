package com.flipkart.client;
import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.data.TempData;
import com.flipkart.service.AdminServiceOperation;
import com.flipkart.service.ProfessorServiceOperation;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class ProfessorCRSMenu {

    private int professorID;



    public ProfessorCRSMenu(Integer profId){
        this.professorID = profId;
    }
    Scanner scanner = new Scanner(System.in);
    ProfessorServiceOperation professorServiceOperation = new ProfessorServiceOperation();

    public void displayMenu() {
        // Display the options available for the Professor
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Professor Menu! Enter option :");
            System.out.println("1. View Courses"); //done
            System.out.println("2. View Enrolled Students"); //done
            System.out.println("3. Assign grade");
            System.out.println("4. Signup for course");
            System.out.println("5. Logout\n");
            System.out.println("Enter Option : ");

            int input = sc.nextInt();
            sc.nextLine();

            if(input==1){
                viewCourse(professorID);
            }

            else if(input==2)
                viewStudents();

            else if(input==3)
                assignGrades();

            else if (input==4) {
                signUpForCourse();
            }
            else if(input==5) {
                logout();
                break;
            }
            else
                System.out.println("Invalid choice");
        }
    }




    private void viewCourse(int professorID){
        int cur = 0;
        for (var crs : professorServiceOperation.viewCourses(professorID)) {
            cur++;
            System.out.println("" + cur + " " + crs.getCourseId() + " " + crs.getCourseName());
        }
    }

    private void viewStudents(){
        System.out.println("Enter course id");
        int courseId = scanner.nextInt();
        Course course = TempData.courseCatalogue.get(courseId);
        System.out.println("##########################");

        System.out.println("List of registered students:");

        System.out.format("%15s %15s %32s\n", "Sr. No", "Student ID", "Student Name");
        int cur = 0;
        for(var student : TempData.courseToEnrolledStudents.get(course)) {
            cur++;
            System.out.format("%15d %15d %32s\n",
                    cur,
                    TempData.idToStudent.get(student).getId(),
                    TempData.idToStudent.get(student).getName()
            );
        }
        System.out.println("##########################");
    }

    private  void assignGrades(){
        System.out.println("Enter course id");
        int courseId = scanner.nextInt();
        Course course = TempData.courseCatalogue.get(courseId);
        System.out.println("##########################");

        System.out.println("List of registered students:");

        System.out.format("%15s %15s %32s\n", "Sr. No", "Student ID", "Student Name");
        int cur = 0;
        for(var student : TempData.courseToEnrolledStudents.get(course)) {
            cur++;
            System.out.format("%15d %15d %32s\n",
                    cur,
                    TempData.idToStudent.get(student).getId(),
                    TempData.idToStudent.get(student).getName()
            );
        }
        System.out.println("##########################");
        System.out.println("Enter student id");
        int studentId = scanner.nextInt();

        System.out.println("Enter grade in number");
        int gradeValue = scanner.nextInt();
        Grade grade = TempData.integerToGrade.get(gradeValue);

        professorServiceOperation.assignGrade(studentId, courseId, grade);

    }

    private void signUpForCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id of the course you want to teach");
        int courseId = sc.nextInt();
        professorServiceOperation.signUpForCourse(courseId, professorID);
        System.out.println("List of the Courses after adding this course : ");
        this.viewCourse(professorID);
    }

    private void logout() {
        System.out.println("Logged out successfully");
    }
}
