package com.flipkart.dao;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import java.util.HashMap;
import java.util.List;


public interface AdminDAO {

    public void addCourse(Course course);

    public void removeCourse(int courseID);


    public void addProf(Professor professor);

    public void addUser(User user);

    public void removeProf(String profID);

    public void removeStudent(String studentID);

    public void assignProf(String profID, int courseID);

    public HashMap<String,List<Integer>> getAlternateCourses();

    public HashMap<String,List<Integer>> getPreferredCourses();

    public void generateReportCard(String studentID);


    public List<Course> viewCourses();

    public void setRegistrationStatus(String studentID);

    public void deleteChosenCourses();

    public void validateRegistration();

    public void approveStudent(String studentId);

    public List<Professor> viewProfessors();

    public List<Student> viewPending();
}