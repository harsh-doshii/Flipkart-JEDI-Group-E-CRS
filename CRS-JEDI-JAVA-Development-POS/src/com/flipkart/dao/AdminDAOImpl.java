package com.flipkart.dao;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.flipkart.constant.SQLQueries;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.sql.Date;
import java.util.*;


public class AdminDAOImpl implements AdminDAO {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crs_db";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Root@123!";


    private static volatile AdminDAOImpl instance = null;
    private PreparedStatement statement = null;

    private AdminDAOImpl(){}

    public static AdminDAOImpl getInstance() {
        if (instance == null) {
            synchronized (AdminDAOImpl.class) {
                instance = new AdminDAOImpl();
            }
        }
        return instance;
    }

    @Override
    public void addCourse(Course course) throws SQLException {
        statement = null;
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

        try {
            Class.forName(JDBC_DRIVER);
            String sql = "insert into Course(idCourse, courseName, courseDescription) values (?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, course.getCourseId());
            statement.setString(2, course.getCourseName());
            statement.setString(3, course.getCourseDescription());
            int row = statement.executeUpdate();

            System.out.println(row + " course added");
            if (row == 0) {
                System.out.println("Course with courseCode: " + course.getCourseId() + "not added to catalog.");
            }
            else {
                System.out.println("Course with courseCode : " + course.getCourseId() + " is added to catalog.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }


    @Override
    public void removeCourse(int courseID) throws SQLException {
        statement = null;
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        try{
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.DELETE_COURSE_QUERY;
            statement = conn.prepareStatement(sql);
            statement.setInt(1, courseID);
            int row = statement.executeUpdate();

            System.out.println(row + " entries deleted");
            if (row == 0) {
                System.out.println(courseID + " not in catalogue");
            }
            else {
                System.out.println("Course with course code : " + courseID + " deleted.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void addProf(Professor professor) throws SQLException {
        try {
            this.addUser(professor);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("professor not added: "+ professor.getId());
        }


        statement = null;
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        try {
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.ADD_PROFESSOR_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setInt(1, professor.getId());
            statement.setString(2, professor.getDepartment());
            int row = statement.executeUpdate();

            System.out.println(row + " professor added.");
            if(row == 0) {
                System.out.println("Professor with professorId: " + professor.getId() + " not added.");
//                System.out.println("Professor not added.");
                return;
            }

            System.out.println("Professor with professorId: " + professor.getId() + " added.");

        }catch(Exception se) {

            System.out.println(se.getMessage());
            System.out.println("Professor not found");

        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void addUser(User user) throws SQLException {
        statement = null;
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        try {
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.ADD_USER_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setInt(1, user.getId());
            statement.setInt(2, user.getRoleId());
            statement.setString(3, user.getName());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getDob());
            statement.setString(7, user.getGender());
            statement.setString(8, user.getAddress());
            int row = statement.executeUpdate();

            System.out.println(row + " user added.");
            if(row == 0) {
                System.out.println("User with userId: " + user.getId() + " not added.");
                return;
            }

            System.out.println("User with userId: " + user.getUsername() + " added.");

        }catch(Exception se) {

            System.out.println(se.getMessage());

        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    System.out.println("Database error");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void removeProf(int profID) throws SQLException {
        statement = null;
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        try {
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.DELETE_PROFESSOR_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setInt(1, profID);
            int row = statement.executeUpdate();

            System.out.println(row + " entries deleted.");
            if(row == 0) {
                System.out.println("Prof with userId: " + profID + " not deleted.");
                System.out.println("Professor not found error");
                return;
            }

            String sq2 = SQLQueries.DELETE_USER_QUERY;
            statement = conn.prepareStatement(sq2);

            statement.setInt(1, profID);
            row = statement.executeUpdate();

            System.out.println(row + " entries deleted.");
            if(row == 0) {
                System.out.println("User with userId: " + profID + " not deleted.");
                System.out.println("Professor not found error");
                return;
            }

            System.out.println("Prof with userId: " + profID + " deleted.");

        }catch(Exception se) {

            if (se.getMessage().contains("Cannot delete or update a parent row: a foreign key constraint fails")) {
                System.out.println("Professor not deleted exception");
                return;
            }
            else
            {
                System.out.println(se.getMessage());
                System.out.println("Professor not deleted exception");
                return;
            }

        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new SQLException();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void assignProf(int profID, int courseID) throws SQLException {
        statement = null;
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        try {
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.ASSIGN_COURSE_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setInt(1, profID);
            statement.setInt(2, courseID);
            int row = statement.executeUpdate();

            System.out.println(row + " entries updated.");
            if(row == 0) {
                System.out.println("Prof with userId: " + profID + " cannot be assigned to course : " + courseID);
                System.out.println("Course Not Found");
                return;
            }

            System.out.println("Prof with userId: " + profID + " assigned to course : " + courseID);

        }catch(Exception se) {

            System.out.println(se.getMessage());
            

        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new SQLException
();
                } catch (SQLException
 e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void generateReportCard() throws SQLException{
        statement = null;
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        try {
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.RELEASE_GRADE_CARD;
            statement = conn.prepareStatement(sql);
            int row = statement.executeUpdate();

            System.out.println(row + " entries updated.");
            if(row == 0) {
                System.out.println("grades not released");
                return;
            }

            System.out.println("Grades released successfully!!");

        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new SQLException
();
                } catch (SQLException
 e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

  
    @Override
    public List<Course> viewCourses() throws SQLException {
        statement = null;
        List<Course> coursesList = new ArrayList<>();
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        try{
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.GET_COURSE_CATALOGUE;
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                    Course course = new Course(resultSet.getInt(1), resultSet.getString(2),
                            resultSet.getString(3));
                    coursesList.add(course);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new SQLException
();
                } catch (SQLException
 e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return coursesList;
    }

    @Override
    public void approveStudent(int studentId) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.APPROVE_STUDENT_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setInt(1,studentId);
            int row = statement.executeUpdate();

            System.out.println(row + " student approved.");
            if(row == 0) {
                System.out.println("Student with studentId: " + studentId + " not approved.");
                System.out.println("Student not found error");
                return;
            }

            System.out.println("Student with studentId: " + studentId + " approved by admin.");

        }catch(Exception se) {
            System.out.println(se.getMessage());
        }

    }

    @Override
    public List<Student> viewUnapprovedStudents() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL,USER,PASS);
        statement = null;
        List<Student> studentList  = new ArrayList<Student>();
        try{
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.VIEW_PENDING_STUDENTS;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String idStudent = resultSet.getString("idStudent");
                Student student = (Student) UserDAO.getInstance().getDetails(idStudent);
                studentList.add(student);
            }
        }catch (SQLException se){
            System.out.println(se.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new SQLException
();
                } catch (SQLException
 e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return studentList;
    }
}