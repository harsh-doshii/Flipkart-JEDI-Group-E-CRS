package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.service.AdminServiceOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CRSAdminMenu {

    int adminId;
    public CRSAdminMenu(Integer adminId) {
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
                    System.out.format("%15s %15s %32s\n", "Sr. No", "Student ID", "Student Name");
                    List<Student> student = AdminServiceOperation.getInstance().viewPending();
                    int cur = 0;
                    for(var data : student){
                        cur++;
                        System.out.format("%15d %15d %32s\n", cur, data.getStudentId(), data.getName());
                    }
                    System.out.println("Enter the studentId to be approved: ");
                    int studentId = scanner.nextInt();
                   approveStudentRegistration(studentId);
//                    int count = 0;
//                    int cur = 0;
//                    for(Map.Entry<Integer, Boolean> entry : TempData.isStudentApproved.entrySet()) {
//                        cur++;
//                        if(!entry.getValue()) {
//                            count++;
//                            System.out.format("%15d %15d %32s\n",
//                                    cur,
//                                    TempData.idToStudent.get(entry.getKey()).getId(),
//                                    TempData.idToStudent.get(entry.getKey()).getName()
//                            );
//                        }
//
//                    }
//                    System.out.println("##########################");
//                    if(count!=0) {
//                        System.out.println("Enter the id of student to approve");
//                        int studentId = scanner.nextInt();
//                        approveStudentRegistration(studentId);
//                    }
//                    else {
//                        System.out.println("No students to approve");
//                    }
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

    private Boolean removeProf(int profId) {
        try {
            return AdminServiceOperation.getInstance().removeProfessor(profId);

        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }
        return false;
    }

    private void logout() {
        System.out.println("Logged out successfully");
    }

    private void generateReport() {
        AdminServiceOperation.getInstance().generateGradeCard();
    }

    private void approveStudentRegistration(int studentId) {
        try {
            AdminServiceOperation.getInstance().approveStudent(studentId);
        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    private boolean addProf() {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the name of the professor");
            String profName = sc.next();
            System.out.println("Enter the gender of the professor");
            String profGender = sc.next();
            System.out.println("Enter the add of the professor");
            String profAddress = sc.next();
            System.out.println("Enter the username of the professor");
            String profUsername = sc.next();
            System.out.println("Enter the pw of the professor");
            String profPassword = sc.next();
            System.out.println("Enter the Department of the professor");
            String department = sc.next();
            List courseList = new ArrayList<>();
            Professor prof = new Professor(profName, profGender, profAddress, profUsername, profPassword, 1, 1, "161100", department, courseList);
            AdminServiceOperation.getInstance().addProfessor(prof);
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return  false;
    }

    private Boolean removeCourse(int courseId) {
        try {
            AdminServiceOperation.getInstance().removeCourse(courseId);
            System.out.println("List of the Courses after removing this course : ");
            this.viewCourseCatalogue();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }
        return false;
    }

    private Boolean addCourse() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the id of the course");
            int courseId = sc.nextInt();
            System.out.println("Enter the name of the course");
            String courseName = sc.next();
            System.out.println("Enter the description of the course");
            String courseDesc = sc.next();
            Course course = new Course(courseId, courseName, courseDesc);
            AdminServiceOperation.getInstance().addCourse(course);
            System.out.println("List of the Courses after adding this course : ");
            this.viewCourseCatalogue();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }
        return false;
    }

    private void viewCourseCatalogue() {
        int cur = 0;
        System.out.format("%15s %15s %32s\n", "Sr. No", "Course ID", "Course Name");
        for (var crs : AdminServiceOperation.getInstance().viewCourse()) {
            cur++;
            System.out.format("%15d %15d %32s\n",cur, crs.getCourseId(), crs.getCourseName());
        }
    }
}


