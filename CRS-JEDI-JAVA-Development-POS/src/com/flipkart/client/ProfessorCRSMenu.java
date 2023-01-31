package com.flipkart.client;
import java.util.Scanner;
public class ProfessorCRSMenu {

    private String professorID;



    public ProfessorCRSMenu(String profId){

        this.professorID = profId;
    }

    public void displayMenu() {
        // Display the options available for the Professor
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Professor Menu! Enter option :");
            System.out.println("1. View Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. Assign grade");
            System.out.println("4. Logout\n");
            System.out.println("Enter Option : ");

            int input = sc.nextInt();
            sc.nextLine();

            if(input==1)
                viewCourse();
            else if(input==2)
                viewStudents();

            else if(input==3)
                assignGrades();

            else if(input==4) {
                logout();
                break;
            }
            else
                System.out.println("Invalid choice");
        }
    }




    private  void viewCourse(){
        System.out.printf("view course");
    }

    private void viewStudents(){
        System.out.printf("view student");
    }

    private  void assignGrades(){
        System.out.printf("assign grades");
    }

    private void logout() {
        System.out.println("Loging out");
    }
}
