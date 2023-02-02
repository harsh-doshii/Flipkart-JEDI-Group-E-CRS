package com.flipkart.service;

import com.flipkart.bean.*;

import com.flipkart.data.TempData;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminServiceOperation implements AdminService {


    TempData tempData = new TempData();
    @Override
    public void addCourse(Course course) {
        tempData.courseCatalogue.put(course.getCourseId(), course);
    }

    @Override
    public void removeCourse(int courseID) {
        tempData.courseCatalogue.remove(courseID);
    }

    @Override
    public void addProfessor(Professor professor) {
        tempData.professorDatabase.put(professor.getId(), professor);
        TempData.userDatabase.put(professor.getId(), professor);
    }

    @Override
    public Boolean removeProfessor(int profID) {
        if (tempData.professorDatabase.get(profID) != null) {
            tempData.professorDatabase.remove(profID);
            TempData.userDatabase.remove(profID);
            return true;
        }
        return false;
    }

    @Override
    public void assignProfessor(int profID, int courseID) {
        tempData.courseToProfMapping.put(courseID, profID);
    }

    @Override
    public GradeCard generateGradeCard(int studentID) {
        return tempData.reportCards.get(studentID);
    }

    @Override
    public List<Course> viewCourse() {
        List<Course> coursesList = new ArrayList<Course>();
        for (Map.Entry<Integer, Course> cur : tempData.courseCatalogue.entrySet()) {
            coursesList.add(cur.getValue());
        }
        return coursesList;
    }

    @Override
    public void validateRegistration() {

    }

    @Override
    public void approveStudent(int studentID) {
        tempData.isStudentApproved.put(studentID, true);
        TempData.userDatabase.put(studentID, TempData.idToStudent.get(studentID));
    }

    @Override
    public List<Professor> viewProfessor() {
        List<Professor> professorList = new ArrayList<Professor>();
        for (Map.Entry<Integer, Professor> cur : tempData.professorDatabase.entrySet()) {
            professorList.add(cur.getValue());
        }
        return professorList;
    }

    @Override
    public List<Student> viewPending() {
        List<Student> unapprovedStudents = new ArrayList<Student>();
        for (Map.Entry<Integer, Boolean> cur : tempData.isStudentApproved.entrySet()) {
            if (cur.getValue() == false) {
                unapprovedStudents.add(tempData.idToStudent.get(cur.getKey()));
            }
        }
        return unapprovedStudents;
    }
}
