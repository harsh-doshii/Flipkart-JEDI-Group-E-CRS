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
    static final String PASS = "Fk!_186841";


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

    /**
     * @param course
     * @throws SQLException
     */
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


    /**
     * @param courseID
     * @throws SQLException
     */
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

    /**
     * @param professor
     * @throws SQLException
     */
    @Override
    public void addProf(Professor professor) throws SQLException {
        int id =-1;
        try {
            id = this.addUser(professor);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("professor not added: "+ professor.getId());
        }
        if(id==-1){
            System.out.println("Professor Can't be added.");
        }

        statement = null;
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        try {
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.ADD_PROFESSOR_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setString(2, professor.getDepartment());
            int row = statement.executeUpdate();

            if(row == 0) {
                System.out.println("Professor with professorId: " + id + " not added.");

                return;
            }

            System.out.println("Professor with professorId: " + id + " added.");

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


    /**
     * @param student
     * @throws SQLException
     */
    @Override
    public void addStudent(Student student) throws SQLException {
        int id =-1;
        try {
            id = this.addUser(student);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        if(id==-1){
            System.out.println("Student Can't be added");
            return;
        }

        statement = null;
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        try {
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.ADD_STUDENT;
            statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setInt(2, student.getSemester());
            statement.setString(3,student.getBranch().getName());

            int row = statement.executeUpdate();

            if(row == 0) {
                System.out.println("Student with StudentID: " + id + " not added.");
//                System.out.println("Professor not added.");
                return;
            }

            System.out.println("Student with StudentID: " + id + " added.");

        }catch(Exception se) {

            System.out.println(se.getMessage());
            System.out.println("Student not found");

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

    /**
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public int addUser(User user) throws SQLException {
        statement = null;
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        int id = this.getNewUserID();
//        System.out.println("add user id: "+ id);
        try {
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.ADD_USER_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setInt(2, user.getRoleId());
            statement.setString(3, user.getName());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getDob());
            statement.setString(7, user.getGender());
            statement.setString(8, user.getAddress());
            int row = statement.executeUpdate();

            if(row == 0) {
                System.out.println("User with userId: " + user.getId() + " not added.");
                return -1;
            }

            System.out.println("User with userId: " + user.getUsername() + " added.");
            return id;

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
        return -1;
    }

    /**
     * @param profID
     * @throws SQLException
     */
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

    /**
     * @param profID
     * @param courseID
     * @throws SQLException
     */
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

    /**
     * @throws SQLException
     */
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


    /**
     * @return
     * @throws SQLException
     */
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

    /**
     * @param studentId
     * @throws SQLException
     */
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

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<Professor> viewProfessors() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL,USER,PASS);
        statement = null;
        List<Professor> professorList = new ArrayList<Professor>();
        try {
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.VIEW_PROFESSOR_QUERY;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int idProfessor = resultSet.getInt("idProfessor");
                String sql2 = SQLQueries.GET_STUDENT_NAME;
                statement = connection.prepareStatement(sql2);
                statement.setInt(1,idProfessor);
                ResultSet resultSet2 = statement.executeQuery();
                Professor professor = new Professor(idProfessor,resultSet2.getString("name") );
                professorList.add(professor);
            }

            System.out.println("Professor list generated");

        }catch(Exception se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                connection.close();
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
        return professorList;
    }


    /**
     * @return
     * @throws SQLException
     */
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
                int idStudent = resultSet.getInt("idStudent");
                String sql2 = SQLQueries.GET_STUDENT_NAME;
                statement = connection.prepareStatement(sql2);
                statement.setInt(1,idStudent);
                ResultSet resultSet2 = statement.executeQuery();
                resultSet2.next();
//                System.out.println(resultSet2.getString("name"));
                Student student = new Student(idStudent,resultSet2.getString("name"));
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

    /**
     * @return
     * @throws SQLException
     */
    public int getNewUserID() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL,USER,PASS);
        statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.GET_CURRENT_ID_VALUE;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getString("variableValue"));

            int currentID = Integer.parseInt(resultSet.getString("variableValue")) +1;
            sql = SQLQueries.UPDATE_CURRENT_ID_VALUE;

            statement = connection.prepareStatement(sql);
            statement.setString(1,Integer.toString(currentID));
            int row = statement.executeUpdate();
            if(row==0){
                return -1;
            }
            else{

                return  currentID;
            }
        }
        catch (SQLException se){
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
        return 0;
    }


}