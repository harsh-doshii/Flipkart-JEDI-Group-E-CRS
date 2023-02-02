package com.flipkart.client;

import java.util.Scanner;
public class CRSApplication {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        while(true){
            System.out.println("");
            System.out.println("Welcome to CRS Application! Choose from the options given below!");
            System.out.println("**************************************");
            System.out.println("Main Menu :->");
            System.out.println("1. Login");
            System.out.println("2. Registration of the Student");
            System.out.println("3. Update Password");
            System.out.println("4. Exit");

            String optionChosen = scanner.nextLine();


            if(optionChosen.equals("1")){
                login();
            }
            else if(optionChosen.equals("2")){
                System.out.println(" Registration of the Student was chosen");
            }
            else if(optionChosen.equals("3")){
                System.out.println("Update Password was chosen");
            }
            else if(optionChosen.equals("4")){
                System.out.println("Thank you for using CRS, may the force be with you!");
                return;
            }
        }

    }

    private static void exit() {
        System.out.println("Exit App");
    }

    private static void login() {
        System.out.println("Login :-->");
        System.out.println("UserId: ");
        int userId = scanner.nextInt();

        System.out.println("Password: ");
        String password = scanner.next();
        System.out.println("Role: ");
        String role = scanner.next();
        switch(role) {
            case "student" :
            case "Student" :
            case "STUDENT" :
                System.out.println("Welcome to the Student menu!");
                //assuming username to be the student id as we don't have the student id yet
                StudentCRSMenu studentMenu = new StudentCRSMenu(userId);
                studentMenu.displayMenu();
                break;

            case "Professor" :
            case "professor" :
            case "PROFESSOR" :
            case "prof":
                System.out.println("Welcome to the Professor menu!");
                ProfessorCRSMenu professorMenu = new ProfessorCRSMenu(userId);
                professorMenu.displayMenu();
                break;

            case "Admin":
            case "admin":
            case "ADMIN":
                System.out.println("Welcome to the Admin menu!");
                AdminCRSMenu adminCRSMenu = new AdminCRSMenu(userId);
                adminCRSMenu.displayAdminMenu();
                break;
            default:
                System.err.println("Invalid Option.");
        }
    }

    private static void register() {

    }

    private static void loginMain(String userId, String password) {

    }
}
