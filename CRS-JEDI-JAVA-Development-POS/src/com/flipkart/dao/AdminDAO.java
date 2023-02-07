package com.flipkart.dao;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.*;
import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;


public interface AdminDAO {

    /**
     * Method to add course in database
     * @param course -> Course to be added
     * @throws CourseFoundException
     */
    public void addCourse(Course course) throws CourseFoundException;

    /**
     * Method to remove course from database.
     * @param courseID -> ID of course which is to be removed
     * @throws CourseNotFoundException
     * @throws CourseNotDeletedException
     */
    public void removeCourse(int courseID) throws CourseNotFoundException, CourseNotDeletedException ;


    /**
     * Adds a professor object to the database
     * @param professor : professor object containing the details of the prof
     * @throws ProfNotAddedException
     * @throws ProfFoundException
     */
    public void addProf(Professor professor) throws ProfNotAddedException, ProfFoundException;

    /**
     * @param user
     * @return
     * @throws SQLException
     */
    public int addUser(User user) throws UserNotAddedException ;

    /**
     * @param profID
     * @throws SQLException
     */
    public void removeProf(int profID) throws ProfNotFoundException, ProfNotDeletedException;

    /**
     * @param profID
     * @param courseID
     * @throws SQLException
     */
    public void assignProf(int profID, int courseID) throws CourseNotFoundException, ProfNotFoundException;

    /**
     * @throws SQLException
     */
    public void generateReportCard()throws SQLException;
    public List<Professor> viewProfessors() throws  SQLException;

    /**
     * @param student
     * @throws SQLException
     */
    public void addStudent(Student student) throws StudentNotAddedException, StudentFoundException;

    /**
     * @return
     * @throws SQLException
     */
    public List<Course> viewCourses() throws SQLException;

    /**
     * @param studentId
     * @throws SQLException
     */
    public void approveStudent(int studentId) throws StudentNotFoundException;

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