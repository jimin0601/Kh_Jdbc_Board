package com.kh.dao;

import com.kh.util.Common;
import com.kh.vo.BoardVO;
import com.kh.vo.CommentsVO;
import com.kh.vo.MemberVO;
import com.kh.vo.WriteVO;

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
    // 로그인 기능 구현. ! .
    public List<MemberVO> memLogin() {
        List<MemberVO> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM MEMBER";
            rst = stmt.executeQuery(sql);

        System.out.println("====================  KH 게시판 로그인 하기 ====================");
        while (rst.next()) {
        System.out.print("닉네임 입력 : ");
        String nickname = sc.next();
        if(rst.getString("NICKNAME").equalsIgnoreCase(nickname)) {
            System.out.println("닉네임 입력 완료. ");
        } else {
                System.out.println("닉네임을 잘못 입력했습니다. 다시 입력하세요.");
                continue;
            }

                System.out.print("비밀번호 입력 : ");
                String pwd = sc.next();
                if(rst.getString("PWD").equalsIgnoreCase(pwd)) {
                    System.out.println("로그인 성공 ! . ! . ! ");
                    System.out.println("안녕하세요. " + nickname + "님 오늘도 KH 게시판을 찾아주셔서 감사합니다.");
                } else {
                    System.out.println("비밀번호를 잘못 입력했습니다.");
                    continue;
                } break;
        }
        Common.close(rst);
        Common.close(stmt);
        Common.close(conn);
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return list;
    }

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
            int rst = pmt.executeUpdate();
            System.out.println("Return : " + rst);

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

    // 게시판 보기
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
    // 게시판 보기.?
    public void boardSelectRst(List<BoardVO> list) {
        for (BoardVO e: list) {
            System.out.print("게시판 이름 : "+e.getBoardName());
            System.out.println();
        }
    }
    // 게시판 추가
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
            int rst = pmt.executeUpdate();
            System.out.println("Return : " + rst);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("게시판이 성공적으로 만들어졌습니다.");
        Common.close(pmt);
        Common.close(conn);
    }

    // 게시판 이름 변경
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

    // 게시판 삭제
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

    // 게시글 보기..
    public List<WriteVO> writeSelect() {
        List<WriteVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM WRITE";
            rst = stmt.executeQuery(sql);
            while (rst.next()) {
                String boardName = rst.getString("BOARD_NAME");
                int writeNum = rst.getInt("WRITE_NUM");
                String writeName = rst.getString("WRITE_NAME");
                int memNum = rst.getInt("MEMBER_NUM");
                String writeContents = rst.getString("WRITE_CONTENTS");
                Date writeDate = rst.getDate("WRITE_DATE");
                WriteVO vo = new WriteVO(boardName, writeNum, writeName,
                        memNum, writeContents, writeDate);
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

    // 게시글 보기.,.
    public void writeSelectRst(List<WriteVO> list) {
        for (WriteVO e : list) {
            System.out.print("게시판 이름 [" + e.getBoardName() + "] ");
            System.out.print("글 번호 [" + e.getWriteNum() + "] ");
            System.out.print("글 이름 [" + e.getWriteName() + "] ");
            System.out.print("회원 번호 [" + e.getMemNum() + "] ");
            System.out.print("글 내용 [" + e.getWriteContents() + "] ");
            System.out.print("글 작성시간 [" + e.getWriteDate() + "] ");
            System.out.println();
        }
    }
    // 게시글 쓰기
    public void writeInsert() {
        Scanner sc = new Scanner(System.in);
        System.out.println("**** 게시판 글 쓰기 ****");
        System.out.print("게시판을 선택 >>> [1]질문, [2]취업진로, [3]맛집추천, [4]자유게시판");
        String boardName = sc.next();
        // 추후에 수정 예정
        System.out.print("글 번호 입력");
        int writeNum = sc.nextInt();
        System.out.print("게시글 제목을 입력하세요.");
        String writeName = sc.nextLine();
        System.out.print("본인 회원번호 입력");
        int memberNum = sc.nextInt();
        System.out.print("글 내용을 작성하세요.");
        String contents = sc.nextLine();
        System.out.print("작성 시간 입력.");
        String writeDate = sc.nextLine();

        String sql = "INSERT INTO WRITE (BOARD_NAME, WRITE_NUM, WRITE_NAME, MEMBER_NUM, WRITE_CONTENTS, WRITE_DATE)" +
                "VALUES(?, ?, ?, ?, ?, ?)";

        try {
            conn = Common.getConnection();
            pmt = conn.prepareStatement(sql);
            pmt.setString(1, boardName);
            pmt.setInt(2, writeNum);
            pmt.setString(3, writeName);
            pmt.setInt(4, memberNum);
            pmt.setString(5, contents);
            pmt.setString(6, writeDate);
            int rst = pmt.executeUpdate();
            System.out.println("Return : " + rst);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("정상으록 글이 올라갔습니다.");
        Common.close(pmt);
        Common.close(conn);
    }

    // 게시글 수정
    public void writeUpdate() {
        System.out.println("게시글 수정 [1]내용 수정, [2]게시판 옮기기, [3]게시글 이름 변경 ");
        int sel = sc.nextInt();
        switch (sel) {
            case 1 :
                System.out.print("내용을 수정 : ");
                sc.nextLine();
                String contents = sc.nextLine();
                String sql1 = "UPDATE WRITE SET WRITE_CONTENTS = ? WHERE MEMBER_NUM = ?";
                try {
                    conn = Common.getConnection();
                    pmt = conn.prepareStatement(sql1);
                    pmt.setString(1, contents);
                    System.out.print("본인 회원번호 입력 : ");
                    int memberNum = sc.nextInt();
                    pmt.setInt(2, memberNum);
                    int ret = pmt.executeUpdate();
                    System.out.println("Return : " + ret);
                    System.out.println("글이 정상으로 수정되었습니다.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Common.close(pmt);
                Common.close(conn);
                break;

            case 2 :
                System.out.print("옮기는 게시판 이름 : ");
                sc.nextLine();
                String reBoard = sc.nextLine();
                String sql2 = "UPDATE WRITE SET BOARD_NAME = ? WHERE WRITE_NUM = ?";

                try {
                    conn = Common.getConnection();
                    pmt = conn.prepareStatement(sql2);
                    System.out.print("게시판을 옮기는 이유를 선택하세요 >> [1]게시판의 주제와 어울리지 않음, [2] 게시판을 처음에 잘못 선택, [3] 기타, [4] 잘못 들어옴 : ");
                    int sl = sc.nextInt();
                    if(sl == 1) {
                        pmt.setString(1, reBoard);
                    }
                    else if (sl == 2) {
                        pmt.setString(1, reBoard);
                    }
                    else if (sl == 3) {
                        pmt.setString(1, reBoard);
                    }
                    else {
                        System.out.println("게시판을 옮기지 않습니다.");
                        break;
                    }
                    System.out.print("게시판에 옮길 글 번호를 입력하세요 : ");
                    int writeNum = sc.nextInt();
                    pmt.setInt(2, writeNum);
                    int ret = pmt.executeUpdate();
                    System.out.println("Return : " + ret);
                    System.out.println("["+reBoard + "] 게시판에 정상으로 옮겼습니다");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Common.close(pmt);
                Common.close(conn);
                break;

            case 3 :
                System.out.print("변경할 게시글 이름 : ");
                sc.nextLine();
                String reWriteName = sc.nextLine();
                String sql3 = "UPDATE WRITE SET WRITE_NAME = ? WHERE WRITE_NUM = ?";

                try {
                    conn = Common.getConnection();
                    pmt = conn.prepareStatement(sql3);
                    pmt.setString(1, reWriteName);
                    System.out.println("이름 변경할 게시글 번호를 입력 : ");
                    int writeNum = sc.nextInt();
                    pmt.setInt(2, writeNum);
                    int ret = pmt.executeUpdate();
                    System.out.println("Return : " + ret);
                    System.out.println(reWriteName + "으로 이름 변경 완료..!");

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
    // 게시글 삭제
    public void writeDelete() {
        System.out.print("삭제할 글을 선택 : ");
        String writeName = sc.next();
        String sql = "DELETE FROM WRITE WHERE WRITE_NAME = ?";

        try {
            conn = Common.getConnection();
            pmt = conn.prepareStatement(sql);
            pmt.setString(1, writeName);
            pmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pmt);
        Common.close(conn);
    }
    // 댓글 보기
    public List<CommentsVO> commentsSelect() {
        List<CommentsVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM COMMENTS";
            rst = stmt.executeQuery(sql);
            while (rst.next()) {
                int memberNum = rst.getInt("MEMBER_NUM");
                String cmtContents = rst.getNString("COMMENT_CONTENT");
                Date writeDate = rst.getDate("WRITE_DATE");
                int writeNum = rst.getInt("WRITE_NUM");
                CommentsVO vo = new CommentsVO(memberNum, cmtContents, writeDate, writeNum);
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
    public void commentsSelectRst(List<CommentsVO> list) {
        for (CommentsVO e : list) {
            System.out.print("회원 번호 [" + e.getMemberNum() + "] ");
            System.out.print("댓글 내용 [" + e.getCmtContent() + "] ");
            System.out.print("댓글 작성일 [" + e.getWriteDate() + "] ");
            System.out.print("게시글 번호 [" + e.getWriteNum() + "] ");
            System.out.println();
        }
    }
    // 댓글 작성
    public void commentsInsert() {
        Scanner sc = new Scanner(System.in);
        System.out.println("**** 댓글 쓰기 ****");
        System.out.print("회원 번호 입력 : ");
        int memberNum = sc.nextInt();
        sc.nextLine();
        System.out.print("댓글 작성 : ");
        String contents = sc.nextLine();
//        System.out.print("댓글 작성일 : ");
//        String writeDate = sc.nextLine();
        System.out.print("댓글 작성할 게시글 번호 입력 : ");
        int writeNum = sc.nextInt();

        String sql = "INSERT INTO COMMENTS (MEMBER_NUM, COMMENT_CONTENT, WRITE_DATE, WRITE_NUM)" +
                "VALUES(?, ?, DEFAULT, ?)";

        try {
            conn = Common.getConnection();
            pmt = conn.prepareStatement(sql);
            pmt.setInt(1, memberNum);
            pmt.setString(2, contents);
//            pmt.setString(3, writeDate);
            pmt.setInt(3, writeNum);
            int rst = pmt.executeUpdate();
            System.out.println("Return : " + rst);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("댓글 작성 완료. ! .");
        Common.close(pmt);
        Common.close(conn);
    }
    // 댓글 수정
    public void commentsUpdate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("====== 댓글 수정하기 ======");
        System.out.print("댓글 수정 : ");
        String contents = sc.nextLine();
        String sql = "UPDATE COMMENTS SET COMMENT_CONTENT = ? WHERE WRITE_NUM = ?  AND MEMBER_NUM = ?";

        try {
            conn = Common.getConnection();
            pmt = conn.prepareStatement(sql);
            pmt.setString(1, contents);
            System.out.print("댓글 수정할 게시글 번호를 선택 : ");
            int writeNum = sc.nextInt();
            pmt.setInt(2, writeNum);
            System.out.print("본인 댓글 수정을 위한 회원번호 입력 : ");
            int memNum = sc.nextInt();
            pmt.setInt(3, memNum);
            int rst = pmt.executeUpdate();
            System.out.println("Return : " + rst);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pmt);
        Common.close(conn);
    }
    public void commentsDelete() {
        System.out.print("<< 게시글 댓글 삭제하기 >>");
        String sql = "DELETE FROM COMMENTS WHERE MEMBER_NUM = ? AND WRITE_NUM = ?";

        try {
            conn = Common.getConnection();
            pmt = conn.prepareStatement(sql);
            System.out.print("삭제 전에 본인 회원번호 입력 : ");
            int memNum = sc.nextInt();
            pmt.setInt(1, memNum);
            System.out.print("댓글 삭제할 글 번호 입력 : ");
            int writeNum = sc.nextInt();
            pmt.setInt(2, writeNum);
            pmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pmt);
        Common.close(conn);
    }
}