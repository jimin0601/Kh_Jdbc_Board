package com.kh.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Common {
    final static String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final static String ORACLE_ID = "scott";
    final static String ORACLE_PWD = "tiger";
    final static String ORACLE_DRV = "oracle.jdbc.OracleDriver";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(ORACLE_DRV); // 드라이버 로딩
            // 연결 얻기
            conn = DriverManager.getConnection(ORACLE_URL, ORACLE_ID, ORACLE_PWD);
            conn.setAutoCommit(false);
            System.out.println("오라클 DB 연결 성공 ! . ! . ! . ! . !");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(Connection conn) {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("연결 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(Statement stmt) {
        try {
            if(stmt != null && !stmt.isClosed()) {
                stmt.close();
                System.out.println("Statement 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rst) {
        try {
            if(rst != null && !rst.isClosed()) {
                rst.close();
                System.out.println("ResultSet 해제 성공");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
