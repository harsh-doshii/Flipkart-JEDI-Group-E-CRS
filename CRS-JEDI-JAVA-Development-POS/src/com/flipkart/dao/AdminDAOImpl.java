package com.flipkart.dao;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.flipkart.constant.SQLQueries;
import java.sql.SQLException;
import java.sql.ResultSet;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtil;
import java.util.*;


public class AdminDAOImpl implements AdminDAO {



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
     * Method to add course in database
     * @param course -> Course to be added
     * @throws CourseFoundException
     */
    @Override
    public void addCourse(Course course) throws CourseFoundException {
        statement = null;
        Connection conn = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "insert into Course(idCourse, courseName, courseDescription) values (?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, course.getCourseId());
            statement.setString(2, course.getCourseName());
            statement.setString(3, course.getCourseDescription());
            int row = statement.executeUpdate();

            System.out.println(row + " course added");
            if (row == 0) {
                System.out.println("Course with courseCode: " + course.getCourseId() + "not added to catalog.");
                throw new CourseFoundException(course.getCourseId());
            }
            else {
                System.out.println("Course with courseCode : " + course.getCourseId() + " is added to catalog.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new CourseFoundException(course.getCourseId());
        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new DatabaseException();
                } catch (DatabaseException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }


    /**
     * Method to remove course from database.
     * @param courseID -> ID of course which is to be removed
     * @throws CourseNotFoundException
     * @throws CourseNotDeletedException
     */
    @Override
    public void removeCourse(int courseID) throws CourseNotFoundException, CourseNotDeletedException {
        statement = null;
        Connection conn = null;

        try{
            conn = DBUtil.getConnection();
            
            String sql = SQLQueries.DELETE_COURSE_QUERY;
            statement = conn.prepareStatement(sql);
            statement.setInt(1, courseID);
            int row = statement.executeUpdate();

            //System.out.println(row + " entries deleted");
            if (row == 0) {
                //System.out.println(courseID + " not in catalogue");
                throw new CourseNotFoundException(courseID);
            }
            else {
                System.out.println("Course with course code : " + courseID + " deleted.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new CourseNotDeletedException(courseID);
        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new DatabaseException();
                } catch (DatabaseException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Adds a professor object to the database
     * @param professor : professor object containing the details of the prof
     * @throws ProfNotAddedException
     * @throws ProfFoundException
     */
    @Override
    public void addProf(Professor professor) throws ProfNotAddedException, ProfFoundException {
        int id;
        try {
            id = this.addUser(professor);
        }catch(UserNotAddedException e) {
            System.out.println(e.getMessage());
            throw new ProfNotAddedException(professor.getName());
            }
        if(id==-1){
            System.out.println("Professor Can't be added.");
        }

        statement = null;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            
            String sql = SQLQueries.ADD_PROFESSOR_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setString(2, professor.getDepartment());
            int row = statement.executeUpdate();

            if(row == 0) {
                System.out.println("Professor with professorId: " + id + " not added.");
                throw new ProfNotAddedException(professor.getName());
//                return;
            }

            System.out.println("Professor with professorId: " + id + " added.");

        }catch(Exception se) {

            System.out.println(se.getMessage());
            System.out.println("Professor not found");
            throw new ProfFoundException(id);

        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new DatabaseException();
                } catch (DatabaseException e) {
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
    public void addStudent(Student student) throws StudentNotAddedException, StudentFoundException{
        int id =-1;
        try {
            id = this.addUser(student);
        }catch(UserNotAddedException e) {
            System.out.println(e.getMessage());
            throw new StudentNotAddedException(student.getName());
        }
        if(id==-1){
            System.out.println("Student Can't be added");
            return;
        }

        statement = null;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();

            
            String sql = SQLQueries.ADD_STUDENT;
            statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setInt(2, student.getSemester());
            statement.setString(3,student.getBranch().getName());

            int row = statement.executeUpdate();

            if(row == 0) {
                System.out.println("Student with StudentID: " + id + " not added.");
              throw new StudentNotAddedException(student.getName());
            }

            System.out.println(SQLQueries.ANSI_GREEn +"Student Successfully Registered with StudentID: " + id+ SQLQueries.ANSI_RESET);
            System.out.println(SQLQueries.ANSI_YELLOW+ "\nContact Admin For Approval"+SQLQueries.ANSI_RESET);

        }catch(Exception se) {

            System.out.println(se.getMessage());
           throw new StudentFoundException(student.getName());

        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new DatabaseException();
                } catch (DatabaseException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Adds a user object to the database
     * @param user : user object containing the details of the user
     * @throws  UserNotAddedException
     */
    @Override
    public int addUser(User user) throws UserNotAddedException {
        statement = null;
        Connection conn = null;
        int id = -1;

        try {
            id = this.getNewUserID();

            conn = DBUtil.getConnection();

            System.out.println("add user me "+user.getName());
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
                System.out.println("User with userId: " + id + " not added.");
                throw new UserNotAddedException(id);
            }

//            System.out.println("User with userId: " + id + " added.");
            return id;

        }catch(SQLException se) {

            System.out.println(se.getMessage());
            throw new UserNotAddedException(id);

        }
        finally {
            try {

                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new DatabaseException();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * removes a professor object from the database
     * @param profID : professor ID of professor to be removed
     * @throws ProfFoundException
     */
    @Override
    public void removeProf(int profID) throws ProfNotFoundException, ProfNotDeletedException {
        statement = null;
        Connection conn = null;

        try {
            conn = DBUtil.getConnection();

            
            String sql = SQLQueries.DELETE_PROFESSOR_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setInt(1, profID);
            int row = statement.executeUpdate();

            System.out.println(row + " entries deleted.");
            if(row == 0) {
                System.out.println("Prof with userId: " + profID + " not deleted.");
                throw new ProfNotFoundException(profID);
            }

            String sq2 = SQLQueries.DELETE_USER_QUERY;
            statement = conn.prepareStatement(sq2);

            statement.setInt(1, profID);
            row = statement.executeUpdate();

            System.out.println(row + " entries deleted.");
            if(row == 0) {
                System.out.println("User with userId: " + profID + " not deleted.");
                throw new ProfNotFoundException(profID);
            }

            System.out.println("Prof with userId: " + profID + " deleted.");

        }catch(SQLException se) {

            if (se.getMessage().contains("Cannot delete or update a parent row: a foreign key constraint fails")) {
                throw new ProfNotDeletedException(profID);
            }
            else
            {
                System.out.println(se.getMessage());
                throw new ProfNotFoundException(profID);
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
     * Add professor as instructor in the given course
     * @param profID   -> ID of professor to be added
     * @param courseID -> ID of course which professor is requesting
     */
    @Override
    public void assignProf(int profID, int courseID) throws CourseNotFoundException, ProfNotFoundException {
        statement = null;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();

            
            String sql = SQLQueries.ASSIGN_COURSE_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setInt(1, profID);
            statement.setInt(2, courseID);
            int row = statement.executeUpdate();

            System.out.println(row + " entries updated.");
            if(row == 0) {
                System.out.println("Prof with userId: " + profID + " cannot be assigned to course : " + courseID);
                throw new CourseNotFoundException(courseID);
            }

            System.out.println("Prof with userId: " + profID + " assigned to course : " + courseID);

        }catch(Exception se) {

            System.out.println(se.getMessage());
            throw new ProfNotFoundException(profID);
            

        }
        finally {
            try {
                conn.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new DatabaseException();

                } catch (DatabaseException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * function to release the GradeCard for all students
     * @throws SQLException
     */
    @Override
    public void generateReportCard() throws SQLException{
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {
            String sql = SQLQueries.RELEASE_GRADE_CARD;
            statement = conn.prepareStatement(sql);
            int row = statement.executeUpdate();

            //System.out.println(row + " entries updated.");
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
     * Method to get list of courses
     * @return List of courses in CourseCatalogue
     * @throws SQLException
     */
    @Override
    public List<Course> viewCourses() throws SQLException {
        statement = null;
        List<Course> coursesList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        try{
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
     *  Method to approve Student
     *  @param studentId -> ID of student to be approved
     *  @throws StudentNotFoundException
     */
    @Override
    public void approveStudent(int studentId) throws StudentNotFoundException {
        Connection conn = null;
        statement = null;
        try {
            conn = DBUtil.getConnection();

            
            String sql = SQLQueries.APPROVE_STUDENT_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1,"true");
            statement.setInt(2,studentId);
            int row = statement.executeUpdate();

            System.out.println(row + " student approved.");
            if(row == 0) {
                System.out.println("Student with studentId: " + studentId + " not approved.");
                throw new StudentNotFoundException(studentId);
            }

            System.out.println("Student with studentId: " + studentId + " approved by admin.");

        }catch(SQLException se) {
            System.out.println(se.getMessage());
        }

    }


    /**
     * Method to fetch the list of professors
     * @return List of Professors in table Professors
     */
    @Override
    public List<Professor> viewProfessors() throws SQLException {
        Connection connection = DBUtil.getConnection();
        statement = null;
        List<Professor> professorList = new ArrayList<>();
        try {
            String sql = SQLQueries.VIEW_PROFESSOR_QUERY;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int idProfessor = resultSet.getInt("idProfessor");
                String sql2 = SQLQueries.GET_STUDENT_NAME;
                statement = connection.prepareStatement(sql2);
                statement.setInt(1,idProfessor);
                ResultSet resultSet2 = statement.executeQuery();
                resultSet2.next();
                String profName = resultSet2.getString("name");
                Professor professor = new Professor(idProfessor,profName );
                professorList.add(professor);
            }
            return  professorList;

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
     * Method to view unapproved students
     */
    @Override
    public List<Student> viewUnapprovedStudents() throws StudentsNotFoundException {
        Connection connection = DBUtil.getConnection();
        statement = null;
        List<Student> studentList  = new ArrayList<Student>();
        try{
            String sql = SQLQueries.VIEW_PENDING_STUDENTS;
            statement = connection.prepareStatement(sql);
            statement.setString(1, "false");
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
          throw new  StudentsNotFoundException();
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


    @Override
    public List<Student> viewApprovedStudents() throws StudentsNotFoundException {
        Connection connection = DBUtil.getConnection();
        statement = null;
        List<Student> studentList  = new ArrayList<Student>();
        try{
            String sql = SQLQueries.VIEW_APPROVED_STUDENTS;
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
            throw new StudentsNotFoundException();
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
     * @return new userID to insert into db.
     * @throws SQLException
     */
    public int getNewUserID() throws SQLException {
        Connection connection = DBUtil.getConnection();
        statement = null;
        try {
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