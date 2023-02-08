package com.flipkart.service;
import com.flipkart.bean.*;
import com.flipkart.dao.AdminDAOImpl;
import com.flipkart.exception.*;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceOperation implements AdminService {



    AdminDAOImpl adminDAOImpl = AdminDAOImpl.getInstance();

    private static AdminServiceOperation instance=null;

    private AdminServiceOperation(){

    }

    public static AdminServiceOperation getInstance() {
        if(instance ==null){
            instance = new AdminServiceOperation();
        }
        return  instance;
    }
    @Override
    public void addCourse(Course course) throws CourseFoundException {
      try{
            adminDAOImpl.addCourse(course);
        } catch (CourseFoundException e) {
            throw e;
        }
    }

    @Override
    public void addStudent(Student student) throws StudentNotAddedException, StudentFoundException {
       try {
           adminDAOImpl.addStudent(student);
       }
       catch (Exception e) {

       }
    }


    @Override
    public void removeCourse(int courseID) throws CourseNotDeletedException, CourseNotFoundException {
        try{
            adminDAOImpl.removeCourse(courseID);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void addProfessor(Professor professor) throws ProfNotAddedException, ProfFoundException {
     try{
            adminDAOImpl.addProf(professor);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean removeProfessor(int profID) throws ProfNotFoundException, ProfNotDeletedException  {
        try {
            adminDAOImpl.removeProf(profID);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void assignProfessor(int profID, int courseID) throws CourseNotFoundException, ProfNotFoundException {
        try{
            adminDAOImpl.assignProf(profID, courseID);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void generateGradeCard() {
 try {
            adminDAOImpl.generateReportCard();
        } catch (Exception e) {
     System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Course> viewCourse() {
        List<Course> courseList = null;
        try {
            courseList = adminDAOImpl.viewCourses();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return courseList;
    }


    @Override
    public void approveStudent(int studentID) throws StudentNotFoundException {
        try {
            adminDAOImpl.approveStudent(studentID);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Professor> viewProfessor() {
        List<Professor> professorList = new ArrayList<Professor>();
        try {
            return adminDAOImpl.viewProfessors();
        } catch (Exception e) {
            return professorList;
        }
    }

    @Override
    public List<Student> viewPending() throws StudentsNotFoundException {

        try {
            return adminDAOImpl.viewUnapprovedStudents();
        } catch (Exception e) {
            throw new StudentsNotFoundException();
        }
    }
@Override
    public List<Student> viewApprovedStudents() throws StudentsNotFoundException{

    try {
        return adminDAOImpl.viewApprovedStudents();
    } catch (Exception e) {
        throw new StudentsNotFoundException();
    }
}

}
