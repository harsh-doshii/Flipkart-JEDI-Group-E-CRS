package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.service.AdminServiceOperation;

import java.util.Scanner;

public class AdminCRSMenu {

    String adminId;
    public AdminCRSMenu(String adminId) {
        this.adminId = adminId;
    }
    Scanner scanner = new Scanner(System.in);

    AdminServiceOperation adminServiceOperation = new AdminServiceOperation();

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
                    System.out.println("Enter the id of Prof to remove");
                    int profId = scanner.nextInt();
                    removeProf(profId);
                    break;

                case 4:
                    addCourse();
                    break;

                case 5:
                    System.out.println("Enter the id of course to remove");
                    int courseId = scanner.nextInt();
                    removeCourse(courseId);
                    break;

                case 6:
                    System.out.println("Students to be approved : ");
                    System.out.println("##########################");
                    System.out.println();
                    System.out.println("##########################");
                    System.out.println("Enter the id of student to approve");
                    int studentId = scanner.nextInt();
                    approveStudentRegistration(studentId);
                    break;

                case 7:
                    System.out.println("Enter the id of student for which we have to generate report card");
                    int studenId = scanner.nextInt();
                    generateReport(studenId);
                    break;

                case 8:
                    logout();
                    return;
            }
        }
    }

    private Boolean removeProf(int profId) {
        return adminServiceOperation.removeProfessor(profId);
    }

    private void logout() {
        System.out.println("Logged out successfully");
        System.exit(0);
    }

    private GradeCard generateReport(int studentId) {
        return adminServiceOperation.generateGradeCard(studentId);
    }

    private void approveStudentRegistration(int studentId) {
        adminServiceOperation.approveStudent(studentId);
        //adminServiceOperation.approveStudent();
    }

    private boolean addProf() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id of the professor");
        int profId = sc.nextInt();
        System.out.println("Enter the name of the professor");
        String profName = sc.next();
        Professor prof = new Professor();
        prof.setId(profId);
        prof.setName(profName);
        adminServiceOperation.addProfessor(prof);
        System.out.println("List of the Professors after adding this prof : ");
        int cur = 0;
        for (var pro : adminServiceOperation.viewProfessor()) {
            cur++;
            System.out.print(cur + ".   -->       ");
            System.out.println("id:- " + pro.getId() + "     ||   name:-  " + pro.getName());
        }
        return true;
        //adminServiceOperation.addProfessor();
    }

    private Boolean removeCourse(int courseId) {
        adminServiceOperation.removeCourse(courseId);
        System.out.println("List of the Courses after removing this course : ");
        int cur = 0;
        for (var crs : adminServiceOperation.viewCourse()) {
            cur++;
            System.out.print(cur + ".   -->       ");
            System.out.println("id:- " + crs.getCourseId() + "     ||   name:-  " + crs.getCourseName());
        }
        return true;
    }

    private Boolean addCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id of the course");
        int courseId = sc.nextInt();
        System.out.println("Enter the name of the course");
        String courseName = sc.next();
        Course course = new Course(courseId,courseName);
//        course.setCourseId(courseId);
//        course.setCourseName(courseName);
        adminServiceOperation.addCourse(course);
        System.out.println("List of the Courses after adding this course : ");
        int cur = 0;
        for (var crs : adminServiceOperation.viewCourse()) {
            cur++;
            System.out.print(cur + ".   -->       ");
            System.out.println("id:- " + crs.getCourseId() + "     ||   name:-  " + crs.getCourseName());
        }
        return true;
    }

    private void viewCourseCatalogue() {
        int cur = 0;
        System.out.format("%15s %15s %32s\n", "Sr. No", "Course ID", "Course Name");
        for (var crs : adminServiceOperation.viewCourse()) {
            cur++;
            System.out.format("%15d %15d %32s\n",cur, crs.getCourseId(), crs.getCourseName());
//            System.out.println();
        }
    }
}


