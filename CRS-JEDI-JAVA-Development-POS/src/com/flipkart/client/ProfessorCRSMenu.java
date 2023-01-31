package com.flipkart.client;

public class ProfessorCRSMenu {

    private String professorID;



    public ProfessorCRSMenu(String profId){
        this.professorID = profId;
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
}
