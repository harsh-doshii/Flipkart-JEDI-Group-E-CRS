package com.flipkart.dao;

import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.PaymentAlreadyDoneException;

import java.sql.*;

public class PaymentDAOImpl implements PaymentDAO{

    Connection conn = null;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crs_db";
    static final String USER = "root";
    static final String PASS = "Root@123";
    PreparedStatement statement = null;

    private static volatile PaymentDAOImpl instance = null;

    private PaymentDAOImpl(){}

    public static PaymentDAOImpl getInstance() {
        if (instance == null) {
            synchronized (PaymentDAOImpl.class) {
                instance = new PaymentDAOImpl();
            }
        }
        return instance;
    }

    @Override
    public float calculateRemainingFee(int studentID) throws SQLException {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = conn.prepareStatement(SQLQueries.GET_REMAINING_PAY_FOR_A_STUDENT);
            statement.setInt(1, studentID);
            ResultSet rs = statement.executeQuery();
            rs.next();
            float amount = rs.getFloat("remainingPayment");
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    @Override
    public int makePayment(int studentId, float amount) throws PaymentAlreadyDoneException {

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = conn.prepareStatement(SQLQueries.GET_REMAINING_PAY_FOR_A_STUDENT);
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                System.out.println("Student not found with id:-   " + studentId);
            }

            float remainingAmount = rs.getFloat("remainingPayment");
            if (remainingAmount < amount) {
                System.out.println("You only have to pay :- " + remainingAmount);
                System.out.println("Initiate the payment process again with right amount.");
                return -1;
            } else {

                float updatedAmount = remainingAmount - amount;
                statement = conn.prepareStatement(SQLQueries.UPDATE_AMOUNT_FOR_A_STUDENT);
                statement.setFloat(1, updatedAmount);
                statement.setInt(2, studentId);
                int row1 = statement.executeUpdate();
                if (row1 == 0) {
                    System.out.println("Payment not finished, try again later!");
                    return -1;
                } else {
                    System.out.println("you have successfully paid -:" + amount + "\nyou have to pay: ----- " + updatedAmount);
                }


                int id = this.getNewTransactionID();
                statement = conn.prepareStatement(SQLQueries.ADD_PAYMENT_TRANSACTION);
                statement.setInt(1,id);
                statement.setFloat(2, amount);
                statement.setInt(3, studentId);
                int row = statement.executeUpdate();
                if (row == 0) {
                    System.out.println("Payment transaction not added, try again later!");
                    return -1;
                }
                else {
                    System.out.println("Your transaction id is " + id);
                    return id;
                }
            }
        } catch (SQLException se) {
            throw new PaymentAlreadyDoneException(studentId);
        } finally {
            try {
                conn.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        
    }

    public int getNewTransactionID() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL,USER,PASS);
        statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            String sql = SQLQueries.GET_CURRENT_TRANSACTION_ID_VALUE;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
//            System.out.println(resultSet.getString("variableValue"));

            int currentID = Integer.parseInt(resultSet.getString("variableValue")) +1;
            sql = SQLQueries.UPDATE_CURRENT_TRANSACTION_ID_VALUE;

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
