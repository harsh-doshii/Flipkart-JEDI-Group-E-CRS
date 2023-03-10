package com.flipkart.client;
import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;
import com.flipkart.constant.SQLQueries;
import com.flipkart.service.ProfessorServiceOperation;

import java.util.List;
import java.util.Scanner;
public class CRSProfessorMenu {

    private int professorID;



    public CRSProfessorMenu(Integer profId){
        this.professorID = profId;
    }
    Scanner scanner = new Scanner(System.in);
    ProfessorServiceOperation professorServiceOperation = ProfessorServiceOperation.getInstance();

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
        try {
            int cur = 0;
            System.out.format(SQLQueries.ANSI_YELLOW + "%-15s %-15s %-32s\n", "Sr. No", "Course ID", "Course Name" + SQLQueries.ANSI_RESET);
            for (Course crs : professorServiceOperation.viewCourses(professorID)) {
                cur++;
                System.out.format("%-15d %-15d %-32s\n",
                        cur,
                        crs.getCourseId(),
                        crs.getCourseName()
                );
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void viewStudents(){
        try {
            System.out.println("Enter course id");
            int courseId = scanner.nextInt();
            System.out.println("##########################");

            System.out.println("List of registered students:");

            System.out.format(SQLQueries.ANSI_YELLOW + "%-15s %-15s %-32s\n", "Sr. No", "Student ID", "Student Name" + SQLQueries.ANSI_RESET);
            int cur = 0;
            List<Student> students = professorServiceOperation.viewStudents(courseId);
            for (var student : students) {
                cur++;
                System.out.format("%-15d %-15d -%32s\n",
                        cur,
                        student.getStudentId(),
                        student.getName()
                );
            }
            System.out.println("##########################");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void assignGrades(){
        try {
            System.out.println("Enter course id");
            int courseId = scanner.nextInt();
            System.out.println("##########################");

            System.out.println("List of registered students:");

            System.out.format(SQLQueries.ANSI_YELLOW + "%-15s %-15s %-32s\n", "Sr. No", "Student ID", "Student Name" + SQLQueries.ANSI_RESET);
            int cur = 0;
            for (var student : professorServiceOperation.viewStudents(courseId)) {
                cur++;
                System.out.format("%-15d %-15d %-32s\n",
                        cur,
                        student.getStudentId(),
                        student.getName()
                );
            }
            System.out.println("##########################");
            System.out.println("Enter student id");
            int studentId = scanner.nextInt();

            System.out.println("Enter grade in number");
            int gradeValue = scanner.nextInt();
            Grade grade = new Grade(gradeValue);

            System.out.println("Enter Semester");
            int sem = scanner.nextInt();

            professorServiceOperation.assignGrade(studentId, courseId, grade, sem);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void signUpForCourse() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the id of the course you want to teach");
            int courseId = sc.nextInt();
            professorServiceOperation.signUpForCourse(courseId, professorID);
            System.out.println("List of the Courses after adding this course : ");
            this.viewCourse(professorID);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void logout() {
        System.out.println("Logged out successfully");
    }
}
