package com.flipkart.constant;

public class SQLQueries {


    //UserDao queries
    public static final String GET_USER_DETAILS_QUERY = "select * from Users where id = ?";
    public static final String GET_USER_NAME = "select name from Users where id = ?";
    public static final String UPDATE_PASSWORD="update Users set password= ? where id = ? ";


    //ProfessorDao queries
    public static final String GET_PROFESSOR_DETAILS_QUERY = "select * from Professors where id = ?";
    public static final String GET_PROFESSOR_NAME = "select name from Users where id = ?";
    public static final String ADD_GRADE = "update RegisteredCourse set grade=? where cid=? and sid=?";
    public static final String GET_ENROLLED_STUDENTS = "select * from RegisteredCourse where cid=?";
    //	public static final String GET_ENROLLED_STUDENTS = "select users.id, users.name from ((registeredcourse inner join users on users.id=registeredcourse.sid) inner join coursecatalogue on registeredcourse.cid=coursecatalogue.cid) where pid=?, cid=?";
    public static final String GET_COURSES = "select * from CourseCatalogue where pid=?";

    //AdminDao Queries
    public static final String GET_ADMIN_DETAILS_QUERY = "select * from admins where id = ?";
    public static final String DELETE_COURSE_QUERY = "delete from CourseCatalogue where cid = ?";
    public static final String ADD_COURSE_QUERY = "insert into CourseCatalogue(cid, cname, pid, valid, filledSeats) values (?, ?, ?, ?, ?)";
    public static final String ADD_USER_QUERY = "insert into Users(id, password, name, gender, role, address, username, dob) values (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String ADD_PROFESSOR_QUERY = "insert into Professors(id, department, qualification, doj) values (?, ?, ?, ?)";
    public static final String DELETE_PROFESSOR_QUERY = "delete from Professors where id = ?";
    public static final String ASSIGN_COURSE_QUERY = "update CourseCatalogue set pid = ? where cid = ?";
    public static final String ADD_STUDENT="insert into Students (id,branch,batchYear,paymentIsDone) values (?,?,?,?)";
    public static final String DELETE_STUDENT_QUERY = "delete from Students where id = ?";
    public static final String DELETE_USER_QUERY = "delete from Users where id = ?";
    public static final String DELETE_REGISTERED_COURSE_QUERY = "delete from RegisteredCourse where cid = ?";
    public static final String GET_STUDENT_GRADES = "select * from registeredcourse where sid = ?";
    public static final String APPROVE_STUDENT_QUERY = "update Students set isApproved = 1 where id = ?";
    public static final String IS_APPROVED_STUDENT_QUERY = "select isApproved from Students where id = ?";
    public static final String VIEW_PROFESSOR_QUERY = "select id from Professors";
    public static final String VIEW_PENDING_STUDENTS = "select id from Students where isApproved = 0";
    // StudentDao Queries
    public static final String GET_STUDENT_DETAILS_QUERY = "select * from Students where id = ?";

    //CourseCatalogue Queries
    public static final String GET_COURSE_CATALOGUE="select * from CourseCatalogue where valid = true";
    public static final String GET_COURSE_NAME="select cname from CourseCatalogue where cid = ?";
    public static final String SET_FILLED_SEATS="update CourseCatalogue set filledSeats=? where cid=?";

    //RegisteredCourse Queries
    public static final String GET_REGISTERED_COURSE_DETAILS="select * from RegistedCourse where sid = ?";
    public static final String CLEAR_CHOSEN_COURSES = "delete from ChosenCourse";
    public static final String CHOOSE_COURSE = "insert into ChosenCourse (sid,cid,isPrimary) values (?,?,?)";
    public static final String VIEW_PREFERRED_COURSES = "select * from ChosenCourse where isPrimary=true";
    public static final String VIEW_ALTERNATE_COURSES = "select * from ChosenCourse where isPrimary=false";
    public static final String ADD_COURSE = "insert into RegisteredCourse (sid,cid,semesterYear,semesterNum) values (?,?,?,?)";
    public static final String DECREMENT_COURSE_SEATS = "update CourseCatalogue set filledSeats = filledSeats-1 where cid = ? ";
    public static final String DROP_COURSE = "delete from RegisteredCourse where cid = ? AND sid = ?;";
    public static final String INCREMENT_COURSE_SEATS  = "update CourseCatalogue set filledSeats = filledSeats+1 where  cid = ?;";
    public static final String VIEW_REGISTERED_COURSES=" select * from CourseCatalogue inner join RegisteredCourse on CourseCatalogue.cid = RegisteredCourse.cid where RegisteredCourse.sid = ?";
    public static final String SET_REGISTRATION_STATUS="update Students set isRegistered = true  where id = ?";
    public static final String GET_REGISTRATION_STATUS="select isRegistered from Students where id = ?";
    public static final String GET_PAYMENT_STATUS = "select paymentIsDone from Students where id = ?";
    public static final String SET_PAYMENT_STATUS = "update Students set paymentIsDone = true  where id = ?";
    public static final float feesPerCourse = 1000;
    public static final int semesterYear = 2021;
    public static final int semesterNum = 1;
    public static final String GET_NUM_REGISTERED_COURSES = "select * from RegisteredCourse where sid = ? and semesterYear = ? and semesterNum = ?";
    public static final String GET_NOTIFICATION_MESSAGE = "select message from Notification where nid = ?";
    public static final String INSERT_NOTIFICATION = "insert into Notification values (?, ?, ?, ?)";
    public static final String SELECT_NOTIFICATION = "select * from Notification where sid = ?";
    public static final String SELECT_ALL_NOTIFICATIONS = "select * from Notification";
}
