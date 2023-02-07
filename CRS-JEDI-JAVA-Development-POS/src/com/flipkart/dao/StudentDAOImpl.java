package com.flipkart.dao;

import java.sql.*;
import java.util.*;

import com.flipkart.bean.*;
import com.flipkart.exception.*;
import java.sql.SQLException;
import com.flipkart.client.CRSApplication;
import com.flipkart.constant.SQLQueries;

/**
* @author Aman Jham
*/

public class StudentDAOImpl implements StudentDAO {
    private static StudentDAOImpl instance = null;

    private PreparedStatement statement = null;
    private StudentDAOImpl(){}

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crs_db";

    //  Database credentials
    static final String USER = "root";

    // Enter your passwords here.
    static final String PASS = "Root@123";
    public static StudentDAOImpl getInstance() {
        if (instance == null) {
            instance = new StudentDAOImpl();
        }
        return instance;
    }

    public List<Course> getCourseCatalogue() throws DatabaseException {
        //Connection conn = DBUtil.getConnection();
        List<Course> courseCatalogue = new ArrayList<Course>();
        Connection connection = null;

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }

            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.prepareStatement(SQLQueries.GET_COURSE_CATALOGUE);
            ResultSet catalogueCourses = statement.executeQuery();
            while (catalogueCourses.next()) {
                courseCatalogue.add(new Course(catalogueCourses.getInt("idCourse"), catalogueCourses.getString("courseName"), catalogueCourses.getString("courseDescription")));
            }
        } catch (SQLException se) {
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return courseCatalogue;
    }

    public Boolean addCourse(int studentId, int courseId) throws SQLException {
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.prepareStatement(SQLQueries.SELECT_ALL_COURSES_FOR_A_STUDENT);
            statement.setInt(1, studentId);
            ResultSet totalCourses = statement.executeQuery();
            Set<Course> addedCourses = new HashSet<>();
            while (totalCourses.next()) {
                addedCourses.add(new Course(totalCourses.getInt("idCourse")));
            }
            if (addedCourses != null && addedCourses.size() > 0) {
                if (addedCourses.contains(courseId)) {
                    System.out.println("Course is already present in preference List");
                    return false;
                }
            }
            if (addedCourses.size() == 6) {
                System.out.println("There are already 6 courses in the preference List");
                return false;
            }

            // check if reg students is less than 10 in this course
            // but everyone can add, we will check this condition while registering

            // add course in db here
            statement = connection.prepareStatement(SQLQueries.ADD_COURSE_FOR_A_STUDENT);
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            int row = statement.executeUpdate();
            if (row == 0) {
                System.out.println("Course not added, was already present");
                return false;
            }
            System.out.println("Course added");
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException se2) {
                throw new SQLException();
            }// nothing we can do
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public Boolean dropCourse(int studentId, int courseId) throws SQLException {
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.prepareStatement(SQLQueries.SELECT_ALL_COURSES_FOR_A_STUDENT);
            statement.setInt(1, studentId);
            ResultSet totalCourses = statement.executeQuery();
            Set<Integer> addedCourses = new HashSet<>();
            while (totalCourses.next()) {
                addedCourses.add(totalCourses.getInt("idCourse"));
            }
            if (addedCourses != null && addedCourses.size() > 0) {
                if (!addedCourses.contains(courseId)) {
                    System.out.println("Course is not present in preference List, can't be dropped");
                    return false;
                }
            }
            // add course in db here
            statement = connection.prepareStatement(SQLQueries.DROP_COURSE_FOR_A_STUDENT);
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.execute();
            System.out.println("Course with courseId" + courseId + " dropped!");
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException se2) {
                throw new SQLException();
            }// nothing we can do
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public float calculateRemainingFee(int studentID) throws SQLException{
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.prepareStatement(SQLQueries.GET_REMAINING_PAY_FOR_A_STUDENT);
            statement.setInt(1, studentID);
            ResultSet rs = statement.executeQuery();
            float amount = rs.findColumn("remainingPayment");
            return amount;
        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException se2) {
                throw new SQLException();
            }// nothing we can do
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void registerCourses(int studentId) throws SQLException{
        /*
        *   Think if we should empty preference List here.
        * */
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.prepareStatement(SQLQueries.SELECT_ALL_COURSES_FOR_A_STUDENT);
            statement.setInt(1, studentId);
            ResultSet totalCourses = statement.executeQuery();
            Set<Course> preferenceList = new HashSet<>();
            while (totalCourses.next()) {
                preferenceList.add(new Course(totalCourses.getInt("idCourse")));
            }
            /*
            *  Will think if we should print the courses registered.
            * */

            List<Course> registeredCourses = new ArrayList<>();
            for (Course cur : preferenceList) {
                int courseId = cur.getCourseId();
                statement = connection.prepareStatement(SQLQueries.SELECT_ALL_REG_STUDENTS_FOR_A_COURSE);
                statement.setInt(1, courseId);
                ResultSet studentsRegistered = statement.executeQuery();
                if (studentsRegistered.getRow() < 10) {
                    registeredCourses.add(cur);
                    // add courses in the registeredCourse table
                    //TODO : if we should remove this course from preference List.
                    statement = connection.prepareStatement(SQLQueries.REG_COURSE_FOR_A_STUDENT);
                    statement.setInt(1, studentId);
                    statement.setInt(2, courseId);
                    statement.setInt(3, 0);
                    statement.setInt(4, SQLQueries.semesterNum);
                    int row = statement.executeUpdate();
                    if (row == 0) {
                        System.out.println("Course can't be registered for some reason, try again!");
                    }
                } else {
                    System.out.println("Course: " + cur.getCourseName() + " can't be added, already filled");
                }
            }
            // TODO : see if we should return list of added courses.
        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException se2) {
                throw new SQLException();
            }// nothing we can do
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public List<Integer> viewRegisteredCourses(int studentId) throws SQLException {
        //TODO : have to see if returning ids of reg courses is fine or should we return RegisteredCourse Object.
        List<Integer> registeredCourses = new ArrayList<>();
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }

            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.prepareStatement(SQLQueries.SELECT_ALL_REG_COURSES_FOR_A_STUDENT);
            ResultSet regCourses = statement.executeQuery();
            while (regCourses.next()) {
                registeredCourses.add(regCourses.getInt("idCourse"));
                //registeredCourses.add(new RegisteredCourse(regCourses.getInt("idStudent"), regCourses.getInt("idCourse"), regCourses.getInt("grade"), regCourses.getInt("semester")));
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException se2) {
                throw new SQLException();
            }// nothing we can do
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return registeredCourses;
    }

    public List<RegisteredCourse> viewGrades(int studentId) throws SQLException {
        List<RegisteredCourse> registeredCourses = new ArrayList<>();
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }

            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.prepareStatement(SQLQueries.SELECT_ALL_REG_COURSES_FOR_A_STUDENT);
            ResultSet regCourses = statement.executeQuery();
            while (regCourses.next()) {
                int courseId = regCourses.getInt("idCourse");
                int sem = regCourses.getInt("semester");
                Grade grade = new Grade(regCourses.getInt("grade"));
                statement = connection.prepareStatement(SQLQueries.GET_STUDENT_FROM_ID);
                statement.setInt(1, studentId);
                ResultSet rs = statement.executeQuery();
                if (!rs.next()) {
                    throw new SQLException();
                }
                Student student = new Student(rs.getInt("idStudent"), rs.getInt("semester"), rs.getFloat("remainingPayment"));

                statement = connection.prepareStatement(SQLQueries.GET_COURSE_FROM_ID);
                statement.setInt(1, courseId);
                rs = statement.executeQuery();
                if (!rs.next()) {
                    throw new SQLException();
                }

                Course course = new Course(rs.getInt("idCourse"), rs.getString("courseName"), rs.getString("courseDescription"));

                registeredCourses.add(new RegisteredCourse(course, student, sem, grade));
                //registeredCourses.add(new RegisteredCourse(regCourses.getInt("idStudent"), regCourses.getInt("idCourse"), regCourses.getInt("grade"), regCourses.getInt("semester")));
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException se2) {
                throw new SQLException();
            }// nothing we can do
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return registeredCourses;
    }

    public boolean makePayment(int studentId, float amount) throws SQLException {
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }

            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.prepareStatement(SQLQueries.GET_REMAINING_PAY_FOR_A_STUDENT);
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                System.out.println("Student not found with id:-   " + studentId);
            }

            float remainingAmount = rs.findColumn("remainingPayment");
            if (remainingAmount < amount) {
                System.out.println("You only have to pay :- " + remainingAmount);
                System.out.println("Iniaoite the payment process again with right amount.");
                return false;
            } else {
                float updatedAmount = remainingAmount - amount;
                statement = connection.prepareStatement(SQLQueries.UPDATE_AMOUNT_FOR_A_STUDENT);
                statement.setFloat(1, updatedAmount);
                statement.setInt(2, studentId);
                int row = statement.executeUpdate();
                if (row == 0) {
                    System.out.println("Payment not finished, try again later!");
                    return false;
                } else {
                    System.out.println("you have successfully paid -:" + amount + "you have to pay:" + updatedAmount);
                    return true;
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException se2) {
                throw new SQLException();
            }// nothing we can do
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public List<PaymentNotification> viewNotifications(int studentId) throws SQLException {
        Connection connection = null;
        List <PaymentNotification> notifications = new ArrayList<>();
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }

            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.prepareStatement(SQLQueries.VIEW_NOTIFICATIONS);

            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                notifications.add(new PaymentNotification(rs.getString("timestamp"), rs.getString("message")));
                //registeredCourses.add(new RegisteredCourse(regCourses.getInt("idStudent"), regCourses.getInt("idCourse"), regCourses.getInt("grade"), regCourses.getInt("semester")));
            }
            return notifications;
        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException se2) {
                throw new SQLException();
            }// nothing we can do
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }




}
