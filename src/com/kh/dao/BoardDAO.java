package com.kh.dao;

import com.kh.util.Common;
import com.kh.vo.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pmt = null;
    ResultSet rst = null;
    Scanner sc = new Scanner(System.in);

    public List<BoardVO> boardSelect() {
        List<BoardVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM BOARD";
            rst = stmt.executeQuery(sql);
            while (rst.next()) {
                String boardName = rst.getString("BOARD_NAME");
                BoardVO vo = new BoardVO(boardName);
                list.add(vo);
            }
            Common.close(rst);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public void boardSelectRst(List<BoardVO> list) {
        for (BoardVO e: list) {
            System.out.print("게시판 이름 : "+e.getBoardName());
            System.out.println();
        }
    }
    public void boardInsert() {
        Scanner sc = new Scanner(System.in);
        System.out.println("게시판 추가하기");
        System.out.print("게시판 이름 입력 : ");
        String boardName = sc.next();

        String sql = "INSERT INTO BOARD(BOARD_NAME)" + "VALUES(?)";

        try {
            conn = Common.getConnection();
            pmt = conn.prepareStatement(sql);
            pmt.setString(1, boardName);
            pmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("게시판이 성공적으로 만들어졌습니다.");
        Common.close(pmt);
        Common.close(conn);
    }
    public void boardUpdate() {
        System.out.print("새로운 게시판 이름 설정 : ");
        String rename = sc.next();
        String sql = "UPDATE BOARD SET BOARD_NAME = ? WHERE BOARD_NAME = ?";

        try {
            conn = Common.getConnection();
            pmt = conn.prepareStatement(sql);
            pmt.setString(1, rename);
            System.out.print("변경할 게시판 이름 선택 : ");
            String boardName = sc.next();
            pmt.setString(2, boardName);
            int rst = pmt.executeUpdate();
            System.out.print("Return : " + rst);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pmt);
        Common.close(conn);
    }
    public void boardDelete() {
        System.out.print("삭제할 게시판을 선택 : ");
        String boardName = sc.next();
        String sql = "DELETE FROM BOARD WHERE BOARD_NAME = ?";

        try {
            conn = Common.getConnection();
            pmt = conn.prepareStatement(sql);
            pmt.setString(1, boardName);
            pmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pmt);
        Common.close(conn);
    }
}
