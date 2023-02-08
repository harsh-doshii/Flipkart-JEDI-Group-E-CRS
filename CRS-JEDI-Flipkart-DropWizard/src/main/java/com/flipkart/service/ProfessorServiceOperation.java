package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.data.TempData;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAssignedException;
import com.flipkart.exception.ProfNotFoundException;
import com.flipkart.exception.StudentNotFoundException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorServiceOperation implements ProfessorService{

    ProfessorDAOImpl professorDaoImpl = ProfessorDAOImpl.getInstance();
//private static StudentServiceOperation instance = null;

    private static ProfessorServiceOperation instance=null;

    private ProfessorServiceOperation(){

    }

    public static ProfessorServiceOperation getInstance() {
        if(instance ==null){
            instance = new ProfessorServiceOperation();
        }
        return  instance;
    }
    @Override
    public List<Course> viewCourses(int profId) throws ProfNotFoundException {
        List<Course> courses = new ArrayList<>();
        try{
            courses = professorDaoImpl.viewCourses(profId);
            return courses;
        } catch(Exception e){
            throw new ProfNotFoundException(profId);
        }
    }

    @Override
    public List<Student> viewStudents(int courseId)  throws SQLException {
        List<Student> listOfStudents = new ArrayList<>();
        try{
            listOfStudents = professorDaoImpl.viewStudents(courseId);
        return listOfStudents;
        } catch(Exception e){
            throw new SQLException();
        }
    }

    @Override
    public boolean assignGrade(int studentId, int courseId, Grade grade, int sem) throws GradeNotAssignedException {
        try{
            professorDaoImpl.assignGrade(studentId, courseId, grade, sem);
            return true;

        } catch(Exception e){
          throw new  GradeNotAssignedException(studentId);
        }
    }

    @Override
    public void signUpForCourse(int courseId, int profId) throws CourseNotFoundException {
        try{
            professorDaoImpl.signUpForCourse(courseId, profId);
        } catch(Exception e){
           throw new CourseNotFoundException(courseId);
        }
    }
}
