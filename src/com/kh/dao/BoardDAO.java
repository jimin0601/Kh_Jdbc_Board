package com.kh.dao;

import com.kh.util.Common;
import com.kh.vo.BoardVO;
import com.kh.vo.MemberVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pmt = null;
    ResultSet rst = null;
    Scanner sc = new Scanner(System.in);
    // 회원정보 조회
    public List<MemberVO> memSelect() {
        List<MemberVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection(); // 호출
            // DB에 SQl 문을 전달하기 위해 사용
            stmt = conn.createStatement();
            String sql = "SELECT * FROM MEMBER";
            rst = stmt.executeQuery(sql);
            while (rst.next()) {
                int memNO = rst.getInt("MEMBER_NUM");
                String nickname = rst.getString("NICKNAME");
                String pwd = rst.getString("PWD");
                Date in_date = rst.getDate("REG_DATE");
                MemberVO vo = new MemberVO(memNO, nickname, pwd, in_date);
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

    // 회원정보..?
    public void memSelectRst(List<MemberVO> list) {
        for(MemberVO e : list) {
            System.out.print("회원번호 [" +e.getMemNO() + "] ");
            System.out.print("닉네임 [" + e.getNickname() + "] ");
            System.out.print("패스워드 [" + e.getPwd() + "] ");
            System.out.print("가입일자 [" + e.getIn_date() + "] ");
            System.out.println();
        }
    }
    // 회원가입 ! . !
    public void memInsert() {
        Scanner sc = new Scanner(System.in);
        System.out.println("회원가입 버튼");
        System.out.println("MEMBER TABLE 정보 입력");
        System.out.print("회원번호(4자리) 입력 : ");
        int memNO = sc.nextInt();
        System.out.print("닉네임 입력 : ");
        String nickname = sc.next();
        System.out.print("패스워드 입력 : ");
        String pwd = sc.next();
        System.out.print("날짜 입력 : ");
        String in_date = sc.next();

        String sql = "INSERT INTO MEMBER(MEMBER_NUM, NICKNAME, PWD, REG_DATE)" +
                "VALUES(?,?,?,?)";

        try {
            conn = Common.getConnection();
            pmt = conn.prepareStatement(sql);
            pmt.setInt(1, memNO);
            pmt.setString(2, nickname);
            pmt.setString(3, pwd);
            pmt.setString(4, in_date);
            pmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(nickname + "정상적으로 가입 되었습니다.");
        Common.close(pmt);
        Common.close(conn);
    }
    // 회원정보 수정
    public void memUpdate() {
        System.out.println("변경할 사원 정보 선택 [1] 닉네임 [2] 비밀번호");
        int ut = sc.nextInt();
        switch (ut) {
            case 1 :
                System.out.print("닉네임 변경 : ");
                String nickname = sc.next();
                String sql1 = "UPDATE MEMBER SET NICKNAME = ? WHERE MEMBER_NUM = ?";
                try {
                    conn = Common.getConnection();
                    pmt = conn.prepareStatement(sql1);
                    pmt.setString(1, nickname);
                    System.out.print("변경할 닉네임의 회원번호를 입력 : ");
                    int sel1 = sc.nextInt();
                    pmt.setInt(2, sel1);
                    int ret = pmt.executeUpdate();
                    System.out.println("Return : " + ret);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Common.close(pmt);
                Common.close(conn);
                break;
            case 2 :
                System.out.print("비밀번호 변경 : ");
                String pwd = sc.next();
                String sql2 = "UPDATE MEMBER SET PWD = ? WHERE NICKNAME = ?";
                try {
                    conn = Common.getConnection();
                    pmt = conn.prepareStatement(sql2);
                    pmt.setString(1, pwd);
                    System.out.print("변경할 비밀번호의 닉네임을 입력 : ");
                    String name = sc.next();
                    pmt.setString(2, name);
                    int ret = pmt.executeUpdate();
                    System.out.println("Return : " + ret);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Common.close(pmt);
                Common.close(conn);
                break;
        }
    }

    // 회원탈퇴
    public void memDelete() {
        System.out.print("삭제할 닉네임을 입력하세요 : ");
        String nickname = sc.next();
        String sql = "DELETE FROM MEMBER WHERE NICKNAME = ?";

        try {
            conn = Common.getConnection();
            pmt = conn.prepareStatement(sql);
            pmt.setString(1, nickname);
            pmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pmt);
        Common.close(conn);
    }

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