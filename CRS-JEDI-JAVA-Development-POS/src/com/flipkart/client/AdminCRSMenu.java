package com.flipkart.client;

import java.util.Scanner;

public class AdminCRSMenu {

    String adminId;
    public AdminCRSMenu(String adminId) {
        this.adminId = adminId;
    }
    Scanner scanner = new Scanner(System.in);

    public void displayAdminMenu() {
        while (true) {
            System.out.println("\n***********************************************************************\n");
            System.out.println("Admin Menu");

            System.out.println(" 1. View Course Catalog");

            System.out.println(" 2. Add Professor");

            System.out.println(" 3. Remove Professor");

            System.out.println(" 4. Add Course");

            System.out.println(" 5. Remove Course");
            
            System.out.println(" 6. Approve Student Registrations");

            System.out.println(" 7. Generate report card");

            System.out.println(" 8. Logout");

            System.out.println("\n***********************************************************************\n");

            System.out.println("Enter the choice : ");

            int optionChosen = scanner.nextInt();

            scanner.nextLine();

            switch (optionChosen) {
                case 1:
                    viewCourseCatalogue();
                    break;

                case 2:
                    addProf();
                    break;

                case 3:
                    removeProf();
                    break;

                case 4:
                    addCourse();
                    break;

                case 5:
                    removeCourse();
                    break;

                case 6:
                    approveStudentRegistration();
                    break;

                case 7:
                    generateReport();
                    break;

                case 8:
                    logout();
                    return;
            }
        }
    }

    private void removeProf() {
    }

    private void logout() {
    }

    private void generateReport() {
    }

    private void approveStudentRegistration() {
    }

    private void addProf() {
    }

    private void removeCourse() {
    }

    private void addCourse() {
    }

    private void viewCourseCatalogue() {
    }
}


