package com.flipkart.dao;

import com.flipkart.bean.Professor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class AdminDAOImpl {
    Connection conn = null;
    PreparedStatement statement = null;



    public void addProfessor(Professor professor){
        statement = null;
        //Connection conn = DBUtil.getConnection();
    }
}
