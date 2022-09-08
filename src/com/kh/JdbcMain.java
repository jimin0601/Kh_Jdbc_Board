package com.kh;

import com.kh.util.Common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// git . . .
public class JdbcMain {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rst = null;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM EMP";
            rst = stmt.executeQuery(sql);

            System.out.println(rst);
            while (rst.next()) {
            System.out.print(rst.getInt("EMPNO") + " ");
            System.out.print(rst.getString("ENAME") + " ");
            System.out.print(rst.getString("JOB") + " ");
            System.out.print(rst.getInt("MGR") + " ");
            System.out.print(rst.getDate("HIREDATE") + " ");
            System.out.print(rst.getInt("SAL") + " ");
            System.out.print(rst.getInt("COMM") + " ");
            System.out.print(rst.getInt("DEPTNO") + " ");
            System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
