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

        /*
        * Have to think if we need profID
        *
        */
        List<Student> enrolledStudents = TempData.courseToEnrolledStudents.get(courseId);
        return enrolledStudents;
    }

    @Override
    public boolean assignGrade(int studentId, int courseId, Grade grade) {
        /*
        * Will see after student service.
        **/
        for(var course : TempData.studentToRegisteredCourseList.get(studentId)) {
            if(course.getCourse().getCourseId()==courseId) {
                course.setGrade(grade);
                return true;
            }
        }
        return false;
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
