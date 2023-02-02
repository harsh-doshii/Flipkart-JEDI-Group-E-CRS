package com.flipkart.client;

import com.flipkart.bean.Branch;
import com.flipkart.bean.Student;
import com.flipkart.data.TempData;
import com.flipkart.service.StudentServiceOperation;

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
                register();
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
        int id = 10000 + TempData.isStudentApproved.size()+1;
        String role = "student";
        System.out.println("Enter dob");
        String dob = scanner.next();
        System.out.println("Enter student branch id");
        String branchId = scanner.next();
        Branch branch = TempData.idToBranch.get(branchId);
        if(branch==null) {
            System.out.println("Please enter valid branch id");
        }
        int semester = 1;
        Student student = new Student(name,gender,address,username,password,id,role,dob,branch,semester,null,null);
//        TempData.idToStudent.put(id, student);
//        TempData.isStudentApproved.put(id, false);

        StudentServiceOperation studentServiceOperation = new StudentServiceOperation();
        studentServiceOperation.signUp(student);

        System.out.println("Your user id is " + id);
        System.out.println("Please use this id to login");

    }

    private static void loginMain(String userId, String password) {

    }
}
