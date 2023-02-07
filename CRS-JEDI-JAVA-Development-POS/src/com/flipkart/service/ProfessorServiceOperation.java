package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.data.TempData;

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
    public List<Course> viewCourses(int profId) {
        List<Course> courses = new ArrayList<>();
        try{
            courses = professorDaoImpl.viewCourses(profId);
            return courses;
        } catch(Exception e){}
        return courses;
    }

    @Override
    public List<Student> viewStudents(int courseId) {
        List<Student> listOfStudents = new ArrayList<>();
        try{
            listOfStudents = professorDaoImpl.viewStudents(courseId);
        } catch(Exception e){}
        return listOfStudents;
//        return  TempData.courseToEnrolledStudents.get(TempData.courseCatalogue.get(courseId));
    }

    @Override
    public boolean assignGrade(int studentId, int courseId, Grade grade, int sem) {
        try{
            professorDaoImpl.assignGrade(studentId, courseId, grade, sem);
            return true;
        } catch(Exception e){}
        return false;
        /*
        * Will see after student service.
        **/
//        for(var course : TempData.studentToRegisteredCourseList.get(studentId)) {
//            if(course.getCourse().getCourseId()==courseId) {
//                course.setGrade(grade);
//                return true;
//            }
//        }
//        return false;
    }

    @Override
    public void signUpForCourse(int courseId, int profId){
        try{
            professorDaoImpl.signUpForCourse(courseId, profId);
        } catch(Exception e){}
//        TempData.courseToProfMapping.put(courseId, profId);
//        //chance of error
//        if(TempData.profToCourses.containsKey(profId) == false){
//            List<Course> emptyList = new ArrayList<>();
//            TempData.profToCourses.put(profId, emptyList);
//        }
//        TempData.profToCourses.get(profId).add(TempData.courseCatalogue.get(courseId));
    }
}
