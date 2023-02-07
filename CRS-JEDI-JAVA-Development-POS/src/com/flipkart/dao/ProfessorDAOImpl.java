package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ProfessorDAOImpl implements ProfessorDAO{
    Connection conn = null;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crs_db";
    static final String USER = "root";
    static final String PASS = "Root@123";
    PreparedStatement statement = null;

    private static volatile ProfessorDAOImpl instance = null;

    private ProfessorDAOImpl(){}

    public static ProfessorDAOImpl getInstance() {
        if (instance == null) {
            synchronized (ProfessorDAOImpl.class) {
                instance = new ProfessorDAOImpl();
            }
        }
        return instance;
    }

    public List<Student> viewStudents(int courseId) throws SQLException {
        List<Integer> sidlist = new ArrayList<>();
        List<Student> listofStudents = new ArrayList<>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            statement = null;
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = SQLQueries.GET_ENROLLED_STUDENTS;
            //GET_ENROLLED_STUDENTS = "select * from RegisteredCourse where idCourse=?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, courseId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int studentId = rs.getInt("idStudent");
                sidlist.add(studentId);
            }
            //add code to convert list<Integer> to list<Student>
            for (int cur: sidlist) {
                statement = conn.prepareStatement(SQLQueries.GET_STUDENT_FROM_ID);
                //"select * from Student where idStudent = ?"
                statement.setInt(1, cur);
                rs = statement.executeQuery();
                statement = conn.prepareStatement(SQLQueries.GET_USER_FROM_USER_ID);
                // "select * from User where userId = ?"
                statement.setInt(1, cur);
                ResultSet rs2 = statement.executeQuery();
                rs.next();
                rs2.next();
                Student student = new Student(rs.getInt("idStudent"), rs2.getString("name"));
                listofStudents.add(student);
            }
            return listofStudents;
        } catch (Exception e) {
            throw new SQLException();
        }finally {
            try {
                conn.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean assignGrade(int studentId, int courseId, Grade grade, int sem) throws StudentNotFoundException, GradeNotAssignedException{
        Connection conn = null;
        try{
                //Changing ResgisteredCourse table
                Class.forName("com.mysql.cj.jdbc.Driver");
                statement = null;
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
                String sql = SQLQueries.ADD_GRADE;
                //ADD_GRADE = "update RegisteredCourse set grade=? where idCourse=? and idStudent=? and sem=?";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, grade.getGradeValue());
                statement.setInt(2, courseId);
                statement.setInt(3, studentId);
                statement.setInt(4, sem);
                int r = statement.executeUpdate();
                if(r!=1){
                    throw new GradeNotAssignedException(studentId);
                }
                return true;
            } catch (Exception e) {
            throw new StudentNotFoundException(studentId);
            }finally {
            try {
                conn.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void signUpForCourse(int courseId,  int profId)  throws CourseNotFoundException {
        Connection conn = null;
        try{
            //Changing Course table
            Class.forName("com.mysql.cj.jdbc.Driver");
            statement = null;
             conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = SQLQueries.SIGN_UP_PROF;
            //SIGN_UP_PROF = "update Course set idProfessor=? where idCourse=?";
            statement = conn.prepareStatement(sql);
            statement.setObject(1, profId);
            statement.setInt(2, courseId);
            int r = statement.executeUpdate();
            if(r!=1){
                throw new Exception();
            }
        } catch (Exception e){
throw  new CourseNotFoundException(courseId);
        }
        finally {
            try {
                conn.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Course> viewCourses(int profId) throws ProfNotFoundException{
        Connection conn = null;
        List<Course> courseList= new ArrayList<>();
        try{
        //Changing Course table
        Class.forName("com.mysql.cj.jdbc.Driver");
        statement = null;
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
        String sql = SQLQueries.GET_COURSES;
        //GET_COURSES = "select * from Course where idProfessor=?";
        statement = conn.prepareStatement(sql);
        statement.setInt(1, profId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            courseList.add(new Course(resultSet.getInt("idCourse"),resultSet.getString("courseName")));
        }
        return courseList;
    } catch (Exception e) {
            throw new ProfNotFoundException(profId);
        }
        finally {
            try {
                conn.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

}
