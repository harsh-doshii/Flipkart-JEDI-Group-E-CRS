package com.flipkart.dao;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;


public interface AdminDAO {

    /**
     * @param course
     * @throws SQLException
     */
    public void addCourse(Course course) throws SQLException;

    /**
     * @param courseID
     * @throws SQLException
     */
    public void removeCourse(int courseID)throws SQLException;


    /**
     * @param professor
     * @throws SQLException
     */
    public void addProf(Professor professor)throws SQLException;

    /**
     * @param user
     * @return
     * @throws SQLException
     */
    public int addUser(User user)throws SQLException;

    /**
     * @param profID
     * @throws SQLException
     */
    public void removeProf(int profID)throws SQLException;

    /**
     * @param profID
     * @param courseID
     * @throws SQLException
     */
    public void assignProf(int profID, int courseID) throws SQLException;

    /**
     * @throws SQLException
     */
    public void generateReportCard()throws SQLException;
    public List<Professor> viewProfessors() throws  SQLException;

    /**
     * @param student
     * @throws SQLException
     */
    public void addStudent(Student student) throws SQLException;

    /**
     * @return
     * @throws SQLException
     */
    public List<Course> viewCourses() throws SQLException;

    /**
     * @param studentId
     * @throws SQLException
     */
    public void approveStudent(int studentId) throws SQLException;

    /**
     * @return
     * @throws SQLException
     */
    public List<Student> viewUnapprovedStudents() throws SQLException;


    /**
     * @return
     * @throws SQLException
     */
    public int getNewUserID() throws SQLException;
}