package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.PaymentNotification;
import com.flipkart.bean.PreferenceList;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constant.SQLQueries;
import com.flipkart.service.PaymentServiceOperation;
import com.flipkart.service.StudentService;
import com.flipkart.service.StudentServiceOperation;

import java.util.List;
import java.util.Scanner;

public class CRSStudentMenu {

    private int studentID;

    StudentService studentServiceOperation = StudentServiceOperation.getInstance();
    Scanner sc = new Scanner(System.in);

    public CRSStudentMenu(Integer studentID){
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
            System.out.println("9. View Notifications");
            System.out.println("10. View added courses in Preference List");
            System.out.println("11. Logout");
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
                System.out.println("Enter the mode of Payment: ");
                String modeOfPayment = sc.next();
                payFee(amount, modeOfPayment);
            }
            else if (input==8) {
                getTotalFeeToPay();
            }
            else if (input == 9) {
                viewNotifications();
            }
            else if (input == 10) {
                viewCoursesInPreferenceList();
            }
            else if(input==11) {
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
            System.out.format(SQLQueries.ANSI_YELLOW + "%-15s %-15s %-32s\n", "Sr. No", "Course ID", "Course Name" + SQLQueries.ANSI_RESET);
            for (var crs : studentServiceOperation.viewCourseCatalouge()) {
                cur++;
                System.out.format("%-15d %-15d %-32s\n",cur, crs.getCourseId(), crs.getCourseName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewGrades(){
//        System.out.println();
        try {
            System.out.println("Your grades for the registered courses : ");

            int cur = 0;
            System.out.format(SQLQueries.ANSI_YELLOW + "%-15s %-15s %-32s %-15s\n", "Sr. No", "Course ID", "Course Name", "Grade" + SQLQueries.ANSI_RESET);
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
                System.out.format("%-15d %-15d %-32s %-15s\n",cur, regCourse.getCourse().getCourseId(), regCourse.getCourse().getCourseName(), grade);
//            System.out.println();
                //System.out.println("suck");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        try {
            viewCoursesInPreferenceList();
            System.out.println("Enter course id to register");
            int courseId = sc.nextInt();
            System.out.println("In which list you want to add this course");
            System.out.println("1. Primary Courses");
            System.out.println("2. Secondary Courses");
            int input = sc.nextInt();
            boolean isPrimary = input == 1;
            studentServiceOperation.addCourse(studentID, courseId, isPrimary);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        System.out.printf("add course");
    }

    private void dropCourse() {
        try {
            viewCoursesInPreferenceList();
            System.out.println("Enter course id to drop form above added courses");
            int courseId = sc.nextInt();
            studentServiceOperation.dropCourse(studentID,courseId);
        }  catch (Exception e){
            System.out.println(e.getMessage());
        }
//       System.out.printf("Drop Course");
    }

    private void viewRegisterCourses(){
        int cur = 0;
       System.out.format(SQLQueries.ANSI_YELLOW + "%-15s %-15s\n", "Sr. No", "Course ID" + SQLQueries.ANSI_RESET);
        for (var crs : studentServiceOperation.viewRegisteredCourse(studentID)) {
            cur++;
            System.out.format("%-15d %-15d\n", cur, crs);
        }

//        for(var regCourse : studentServiceOperation.viewRegisteredCourse(studentID)) {
//            cur++;
////            System.out.format("%15d %15d %32s\n",cur, regCourse.getCourse().getCourseId(), regCourse.getCourse().getCourseName());
//            System.out.println(regCourse);
////          System.out.println();
//        }
//      System.out.printf("view registered courses");
    }

    private float getTotalFeeToPay() {
        float totalFee = PaymentServiceOperation.getInstance().calculateFee(studentID);
        System.out.println("Total Fee remaining to be paid " + totalFee);
        return totalFee;
    }

    private void payFee(float amount, String modeOfPayment) {
        try {
            System.out.printf("Initiating Fee payment : ------>>\n");
            PaymentServiceOperation.getInstance().payFee(studentID, amount, modeOfPayment);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void viewNotifications() {
        List <PaymentNotification> notifications = StudentServiceOperation.getInstance().viewNotifications(studentID);
        for (PaymentNotification notification : notifications) {
            System.out.println("Timestamp :-" + notification.getTimestamp() + " ---------------- " + notification.getNotification());
        }
    }

    public void viewCoursesInPreferenceList() {
        PreferenceList preferenceList = StudentServiceOperation.getInstance().viewCoursesInPreferenceList(studentID);
        System.out.println("These are the courses which you already added in the preference List");
        System.out.println("Following are primary courses you added till now:");
        System.out.format(SQLQueries.ANSI_YELLOW + "%-15s %-15s %-15s\n", "Sr. No", "Course ID", "Course Name"  + SQLQueries.ANSI_RESET);
        int marker = 0;
        for (Course c :preferenceList.getPrimaryCourses()) {
            marker++;
            System.out.format("%-15d %-15d %-32s\n",marker, c.getCourseId(), c.getCourseName());
        }
        System.out.println();
        System.out.println("******************************************************************************");
        System.out.println("Following are Secondary courses you added till now:");
        System.out.format(SQLQueries.ANSI_YELLOW + "%-15s %-15s %-15s\n", "Sr. No", "Course ID", "Course Name" + SQLQueries.ANSI_RESET);
        marker = 0;
        for (Course c :preferenceList.getSecondaryCourses()) {
            marker++;
            System.out.format("%-15d %-15d %-32s\n",marker, c.getCourseId(), c.getCourseName());
        }
        System.out.println();
        System.out.println("******************************************************************************");
    }

}

