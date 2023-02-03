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
//        ArrayList<Student> enrolledStudents =new ArrayList<>();
//        System.out.println("in here 1");
////        List<Student> enrolledStudents = (ArrayList<Student>) TempData.courseToEnrolledStudents.get(TempData.courseCatalogue.get(courseId));
//        System.out.println(TempData.courseToEnrolledStudents.size());
//        System.out.println(TempData.courseToEnrolledStudents);
//        if (TempData.courseToEnrolledStudents.get(TempData.courseCatalogue.get(courseId)) == null) {
//            //TempData.courseToEnrolledStudents.put()
//            //System.out.println("");
//            return ;
//        }
        return  TempData.courseToEnrolledStudents.get(TempData.courseCatalogue.get(courseId));
//        for(var cur : TempData.courseToEnrolledStudents.entrySet()) {
//            System.out.println(cur.getKey().getCourseId());
//            System.out.println(cur.getValue());
//
//            enrolledStudents = (ArrayList<Student>) cur.getValue();
//        }
////        enrolledStudents = (ArrayList<Student>) TempData.courseToEnrolledStudents.get(TempData.courseCatalogue.get(1));
//        if(enrolledStudents!=null) {
//            System.out.println("in here 2");
//            return enrolledStudents;
//        }
//        else
//            return new ArrayList<>();
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
