package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;
import com.flipkart.data.TempData;

import java.util.ArrayList;
import java.util.List;


public class ProfessorServiceOperation implements ProfessorService{

    @Override
    public List<Course> viewCourses(int profId) {
        List<Course> courses = TempData.profToCourses.get(profId);
        return courses;
    }

    @Override
    public List<Student> viewStudents(int courseId) {
        List<Student> enrolledStudents = TempData.courseToEnrolledStudents.get(courseId);
        return enrolledStudents;
    }

    @Override
    public void assignGrade(int studentId, int courseId, Grade grade) {
        /*
        * Will see after student service.
        **/
//        TempData.profGivingGrade.put(studentId, );
        return;
    }

    @Override
    public void signUpForCourse(int courseId, int profId){
        TempData.courseToProfMapping.put(courseId, profId);
        //chance of error
        if(TempData.profToCourses.containsKey(profId) == false){
            List<Course> emptyList = new ArrayList<>();
            TempData.profToCourses.put(profId, emptyList);
        }
        TempData.profToCourses.get(profId).add(TempData.courseCatalogue.get(courseId));
    }
}
