package com.flipkart.client;
import com.flipkart.bean.Student;
import com.flipkart.service.AdminServiceOperation;
import com.flipkart.service.UserServiceOperation;

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

            String optionChosen = scanner.next();


            if(optionChosen.equals("1")){
                login();
            }
            else if(optionChosen.equals("2")){
                register();
            }
            else if(optionChosen.equals("3")){
                System.out.println("Update Password was chosen");
                updatePassword();
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
        try {
            System.out.println("Login :-->");
            System.out.println("UserId: ");
            int userId = scanner.nextInt();

            System.out.println("Password: ");
            String password = scanner.next();
            String role = UserServiceOperation.getInstance().login(userId, password);
            if (!role.equals("INVALID USER")) {
                switch (role) {
                    case "student":
                    case "Student":
                    case "STUDENT":
                        System.out.println("Welcome to the Student menu!");
                        //assuming username to be the student id as we don't have the student id yet
                        CRSStudentMenu studentMenu = new CRSStudentMenu(userId);
                        studentMenu.displayMenu();
                        break;

                    case "Professor":
                    case "professor":
                    case "PROFESSOR":
                    case "prof":
                        CRSProfessorMenu professorMenu = new CRSProfessorMenu(userId);
                        professorMenu.displayMenu();
                        break;

                    case "Admin":
                    case "admin":
                    case "ADMIN":
                        System.out.println("Welcome to the Admin menu!");
                        CRSAdminMenu cRSAdminMenu = new CRSAdminMenu(userId);
                        cRSAdminMenu.displayAdminMenu();
                        break;
                    default:
                        System.err.println("Invalid Option.");
                }
            } else {
                System.out.println("No user found!");

            }
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private static void register() {
        try {
            System.out.println("Enter student name");
            String name = scanner.next();
            System.out.println("Enter student gender");
            String gender = scanner.next();
            System.out.println("Enter student address");
            String address = scanner.next();
            System.out.println("Enter student username");
            String username = scanner.next();
            System.out.println("Enter new password");
            String password = scanner.next();
            String role = "student";
            System.out.println("Enter dob");
            String dob = scanner.next();
            System.out.println("Enter student branch Name");
            String branchName = scanner.next();

            Student student = new Student(name, gender, address, username, password, dob, branchName);

            AdminServiceOperation.getInstance().addStudent(student);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private static void updatePassword(){
        try {
            System.out.println("UserId: ");
            int userId = scanner.nextInt();
            System.out.println("Enter old Password: ");
            String oldPassword = scanner.next();
            System.out.println("Enter new Password: ");
            String newPassword = scanner.next();
            if (UserServiceOperation.getInstance().updatePassword(userId, oldPassword, newPassword)) {
                System.out.println("Password Updated Successfully");
            } else {
                System.out.println("Invalid Old Password");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
