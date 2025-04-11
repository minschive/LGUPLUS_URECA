package com.mycom.myapp.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// Connection 객체 반납
public class DBManager {

    // Connection Pool (Datasource) 객체를 직접 획득하지 않고, Spring DI를 통해 얻는다.
//    public static Connection getConnection() {
//        Connection con = null;
//        try {
//            Context context = new InitialContext();
//            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/madang"); // ds -> Connection Pool
//            con = ds.getConnection();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return con;
//    }

    // Connection Pool 로 부터 얻는 Connection 객체의 close() 는 overriding(재정의) 되어 있다.
    // Connection 을 끊지 않고, Connection Pool 에 반납되도록 재정의 되어 있음.
    public static void releaseConnection(PreparedStatement pstmt, Connection con) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void releaseConnection(ResultSet rs, PreparedStatement pstmt, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
