package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;
import com.flipkart.constant.SQLQueries;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ProfessorDAOImpl implements ProfessorDAO{
    Connection conn = null;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crs_database";
    static final String USER = "root";
    static final String PASS = "root";
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
        public List<Student> viewStudents(int courseId) throws Exception {
            List<Integer> sidlist = new ArrayList<>();
            List<Student> listofStudents = new ArrayList<>();

            try{
                Class.forName("com.mysql.jdbc.Driver");
                statement = null;
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                String sql = SQLQueries.GET_ENROLLED_STUDENTS;
                //GET_ENROLLED_STUDENTS = "select * from RegisteredCourse where idCourse=?";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, courseId);
                ResultSet rs = statement.executeQuery(sql);
                while(rs.next()){
                    int studentId = rs.getInt("idStudent");
                    sidlist.add(studentId);
                }
                //add code to convert li<t<Integer> to list<Student>

            }
            catch(Exception e) {

            } finally {
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
            return listofStudents;
    }

    public boolean assignGrade(int studentId, int courseId, Grade grade) throws Exception{
            try{
                //Changing ResgistereCourse table
                Class.forName("com.mysql.jdbc.Driver");
                statement = null;
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                String sql = SQLQueries.ADD_GRADE;
                //ADD_GRADE = "update RegisteredCourse set grade=? where idCourse=? and idStudent=?";
                statement = conn.prepareStatement(sql);
                statement.setObject(1, grade);
                statement.setInt(2, courseId);
                statement.setInt(3, studentId);
                int r = statement.executeUpdate();
                if(r!=1){
                    throw new Exception();
                }
            } catch (Exception e){

            }finally {
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
            return false;
    }

    public void signUpForCourse(int courseId,  int profId) throws Exception{
        try{
            //Changing Course table
            Class.forName("com.mysql.jdbc.Driver");
            statement = null;
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
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

        }finally {
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

    public List<Course> viewCourses(int profId) throws Exception{
            List<Course> courseList= new ArrayList<>();
            try{
            //Changing Course table
            Class.forName("com.mysql.jdbc.Driver");
            statement = null;
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = SQLQueries.GET_COURSES;
            //GET_COURSES = "select * from Course where idProfessor=?";
            statement = conn.prepareStatement(sql);
            statement.setObject(1, profId);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                courseList.add(new Course(resultSet.getInt("idCourse"),resultSet.getString("courseName")));
            }
        } catch (Exception e){

        }finally {
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
        return courseList;
    }

}
