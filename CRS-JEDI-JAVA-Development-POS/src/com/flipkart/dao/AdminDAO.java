package com.flipkart.dao;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;


public interface AdminDAO {

    public void addCourse(Course course) throws SQLException;

    public void removeCourse(int courseID)throws SQLException;


    public void addProf(Professor professor)throws SQLException;

    public void addUser(User user)throws SQLException;

    public void removeProf(int profID)throws SQLException;

    public void assignProf(int profID, int courseID) throws SQLException;

    public void generateReportCard()throws SQLException;
    public List<Professor> viewProfessors() throws  SQLException;

    public List<Course> viewCourses() throws SQLException;

    public void approveStudent(int studentId) throws SQLException;

    public List<Student> viewUnapprovedStudents() throws SQLException;
}