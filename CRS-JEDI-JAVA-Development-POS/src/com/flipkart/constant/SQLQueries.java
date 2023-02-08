package com.flipkart.constant;

public class SQLQueries {



    public static final String ANSI_RESET = "\u001B[0m";

    // Declaring the color
    // Custom declaration
    public static final String ANSI_YELLOW = "\u001B[33m";
    //UserDao queries
    public static final String GET_USER_DETAILS_QUERY = "select * from User where id = ?";
    public static final String GET_USER_NAME = "select name from User where id = ?";
    public static final String UPDATE_PASSWORD="update User set password= ? where id = ? ";


    //ProfessorDao queries
    public static final String ADD_GRADE = "update RegisteredCourse set grade=? where idCourse=? and idStudent=? and semester=?";
    public static final String GET_ENROLLED_STUDENTS = "select * from RegisteredCourse where idCourse=?";
    public static final String GET_COURSES = "select * from Course where idProfessor=?";
    public static final String SIGN_UP_PROF = "update Course set idProfessor=? where idCourse=?";

    //AdminDao Queries
    public static final String GET_ADMIN_DETAILS_QUERY = "select * from admins where id = ?";
    public static final String DELETE_COURSE_QUERY = "delete from Course where idCourse = ?";
    public static final String ADD_COURSE_QUERY = "insert into Course(idCourse, cname, idProfessor, valid, filledSeats) values (?, ?, ?, ?, ?)";
    public static final String ADD_USER_QUERY = "insert into User(userId, roleid, name, username, password, dob, gender, address) values (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String ADD_PROFESSOR_QUERY = "insert into Professor(idProfessor, department) values (?, ?)";
    public static final String DELETE_PROFESSOR_QUERY = "delete from Professor where idProfessor = ?";
    public static final String ASSIGN_COURSE_QUERY = "update Course set idProfessor = ? where idCourse = ?";
    public static final String ADD_STUDENT="insert into Student (idStudent,semester,branch,isApproved, remainingPayment) values (?,?,?,'false', 0)";
    public static final String DELETE_STUDENT_QUERY = "delete from Student where id = ?";
    public static final String DELETE_USER_QUERY = "delete from User where userId = ?";
    public static final String DELETE_REGISTERED_COURSE_QUERY = "delete from RegisteredCourse where idCourse = ?";
    public static final String GET_STUDENT_GRADES = "select * from RegisteredCourse where idStudent = ?";
    public static final String APPROVE_STUDENT_QUERY = "update Student set isApproved = ? where idStudent = ?";
    public static final String IS_APPROVED_STUDENT_QUERY = "select isApproved from Student where idStudent = ?";
    public static final String VIEW_PROFESSOR_QUERY = "select idProfessor from Professor";


    public static final String VIEW_PENDING_STUDENTS = "select idStudent from Student where isApproved = ?";

    public static final String GET_STUDENT_NAME = "select name from User where userId = ?";
    // StudentDao Queries
    public static final String GET_STUDENT_DETAILS_QUERY = "select * from Student where idStudent = ?";

    //Course Queries
    public static final String GET_COURSE_CATALOGUE="select * from Course";
    public static final String GET_COURSE_NAME="select cname from Course where idCourse = ?";
    public static final String SET_FILLED_SEATS="update Course set filledSeats=? where idCourse=?";

    //RegisteredCourse Queries
    public static final String GET_REGISTERED_COURSE_DETAILS="select * from RegisteredCourse where idStudent = ?";
    public static final String CLEAR_CHOSEN_COURSES = "delete from PreferenceList";
    public static final String CHOOSE_COURSE = "insert into PreferenceList (idStudent,idCourse,isPrimary) values (?,?,?)";
    public static final String VIEW_PREFERRED_COURSES = "select * from PreferenceList where isPrimary=true";
    public static final String VIEW_ALTERNATE_COURSES = "select * from PreferenceList where isPrimary=false";
    public static final String ADD_COURSE = "insert into RegisteredCourse (idStudent,idCourse,semesterYear,semesterNum) values (?,?,?,?)";
    public static final String DECREMENT_COURSE_SEATS = "update Course set filledSeats = filledSeats-1 where idCourse = ? ";
    public static final String DROP_COURSE = "delete from RegisteredCourse where idCourse = ? AND idStudent = ?;";
    public static final String INCREMENT_COURSE_SEATS  = "update Course set filledSeats = filledSeats+1 where  idCourse = ?;";
    public static final String VIEW_REGISTERED_COURSES=" select * from Course inner join RegisteredCourse on Course.idCourse = RegisteredCourse.idCourse where RegisteredCourse.idStudent = ?";
    public static final String GET_REGISTRATION_STATUS="select isRegistered from Student where id = ?";
    public static final String GET_PAYMENT_STATUS = "select paymentIsDone from Student where id = ?";
    public static final String SET_PAYMENT_STATUS = "update Student set paymentIsDone = true  where id = ?";
    public static final float feesPerCourse = 1000;
    public static final int semesterYear = 2021;
    public static final int semesterNum = 1;
    public static final String GET_NUM_REGISTERED_COURSES = "select * from RegisteredCourse where idStudent = ? and semesterYear = ? and semesterNum = ?";
    public static final String GET_NOTIFICATION_MESSAGE = "select message from Notification where nid = ?";
    public static final String INSERT_NOTIFICATION = "insert into Notification values (?, ?, ?, ?)";
    public static final String SELECT_NOTIFICATION = "select * from Notification where idStudent = ?";
    public static final String SELECT_ALL_NOTIFICATIONS = "select * from Notification";

    public static final String RELEASE_GRADE_CARD = "UPDATE SystemVariable set variableValue = 'true' where variableName='releaseReportCard'";


    public static final String GET_CURRENT_ID_VALUE = "select * from SystemVariable where variableName = 'count'";
    public static final String UPDATE_CURRENT_ID_VALUE = "UPDATE SystemVariable set variableValue = ? where variableName = 'count'";

    public static final String GET_CURRENT_TRANSACTION_ID_VALUE = "select * from SystemVariable where variableName = 'transactionCount'";
    public static final String UPDATE_CURRENT_TRANSACTION_ID_VALUE = "UPDATE SystemVariable set variableValue = ? where variableName = 'transactionCount'";
    /*
    * Started writing my commands from here -- aman.
    * */

    public static final String SELECT_ALL_COURSES_FOR_A_STUDENT = "select * from PreferenceList where idStudent = ?";

    public static final String ADD_COURSE_FOR_A_STUDENT = "insert into PreferenceList (idStudent, idCourse, isPrimary) values (?, ?, ?)";

    public static final String DROP_COURSE_FOR_A_STUDENT = "delete from PreferenceList where idStudent = ? AND idCourse = ?";

    public static final String GET_REMAINING_PAY_FOR_A_STUDENT = "select remainingPayment from Student where idStudent = ?";

    public static final String SELECT_ALL_REG_STUDENTS_FOR_A_COURSE = "select * from RegisteredCourse where idCourse = ?";

    public static final String SELECT_ALL_REG_COURSES_FOR_A_STUDENT = "select * from RegisteredCourse where idStudent = ?";

    public static final String REG_COURSE_FOR_A_STUDENT = "insert into RegisteredCourse (idStudent, idCourse, grade, semester) values (?, ?, ?, ?)";

    public static final String GET_STUDENT_FROM_ID = "select * from Student where idStudent = ?";

    public static final String GET_COURSE_FROM_ID = "select * from Course where idCourse = ?";

    //public static final String GET_REMAINING_PAY_FOR_A_STUDENT = "select * fro Student where idStudent = ?";

    public static final String UPDATE_AMOUNT_FOR_A_STUDENT = "update Student set remainingPayment = ? where idStudent = ?";

    public static final String GET_USER_FROM_USER_ID = "select * from User where userId = ?";

    public static final String GET_ROLE_FROM_ROLE_ID = "select roleName from Role where idRole = ?";

    public static final String UPDATE_USER_PASSWORD = "update User set password = ? where userId = ? ";

    public static final String ADD_PAYMENT_TRANSACTION = "insert into Payment(transactionId, amount, studentId) values(?, ?, ?)";

    public static final String ADD_NOTIFICATION = "insert into Notification values(?, ?, ?)";

    public static final String VIEW_NOTIFICATIONS = "select * from Notification where idStudent = ?";

    public static final String GET_STUDENT_APPROVAL_STATUS = "select isApproved from Student where idStudent = ?";

    public static final String GET_ALL_ADDED_COURSES_FOR_STUDENT = "select * from PreferenceList where idStudent = ?";

    public static final String GET_PROF_ID_FOR_A_COURSE = "select idProfessor from Course where idCourse = ?";

    public static final String UPDATE_PAYMENT_FOR_STUDENT = "update Student set remainingPayment = ? where idStudent = ?";
}


