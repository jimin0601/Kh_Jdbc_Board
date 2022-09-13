package com.kh;

import com.kh.dao.BoardDAO;
import com.kh.vo.BoardVO;
import com.kh.vo.CommentsVO;
import com.kh.vo.MemberVO;
import com.kh.vo.WriteVO;

import java.util.List;
import java.util.Scanner;

public class JdbcMain {
    public static void main(String[] args) {
        BoardDAO dao = new BoardDAO();
        dao.memLogin();
        System.out.println("로그아웃 성공.");

        System.out.print("==== 테이블 선택 [1]MEMBER, [2]BOARD, [3]WRITE, [4]COMMENTS, [5]로그아웃 =====");
        Scanner sc = new Scanner(System.in);
        int sel = sc.nextInt();
        switch (sel) {
            case 1 :
        memSelect();
        break;
            case 2 :
        boardSelect();
        break;
            case  3 :
        writeSelect();
        break;
            case 4 :
        commentsSelect();
                System.out.println("로그아웃 성공.");
                return;
        }
    }
    public static void memSelect() {
        Scanner sc = new Scanner(System.in);
        BoardDAO dao = new BoardDAO();
        while (true) {
            System.out.println("====[MEMBER TABLE 조회]====");
            System.out.println("기능 선택 : ");
            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]로그아웃");
            int sel = sc.nextInt();
            switch (sel) {
                case 1 :
                    List<MemberVO> list = dao.memSelect();
                    dao.memSelectRst(list);
                    break;
                case 2 :
                    dao.memInsert();
                    break;
                case 3 :
                    dao.memUpdate();
                    break;
                case 4 :
                    dao.memDelete();
                    break;
                case 5 :
                    System.out.println("로그아웃 성공.");
                    return;
            }
        }
    }
    public static void boardSelect() {
        Scanner sc = new Scanner(System.in);
        BoardDAO dao = new BoardDAO();
        while (true) {
            System.out.println("=====[BOARD TABLE 조회]====");
            System.out.print("기능 선택 : ");
            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]로그아웃");
            int sel = sc.nextInt();
            switch (sel) {
                case 1 :
                    List<BoardVO> list = dao.boardSelect();
                    dao.boardSelectRst(list);
                    break;
                case 2 :
                    dao.boardInsert();
                    break;
                case 3 :
                    dao.boardUpdate();
                    break;
                case 4 :
                    dao.boardDelete();
                    break;
                case 5 :
                    System.out.println("로그아웃 성공.");
                    return;
            }
        }
    }
    public static void writeSelect() {
        Scanner sc = new Scanner(System.in);
        BoardDAO dao = new BoardDAO();
        while (true) {
            System.out.println("====[WRITE TABLE 조회]=====");
            System.out.print("기능 선택 : ");
            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]로그아웃");
            int sel = sc.nextInt();
            switch (sel) {
                case 1 :
                    List<WriteVO> list = dao.writeSelect();
                    dao.writeSelectRst(list);
                    break;
                case 2 :
                    dao.writeInsert();
                    break;
                case 3 :
                    dao.writeUpdate();
                    break;
                case 4 :
                    dao.writeDelete();
                    break;
                case 5 :
                    System.out.println("로그아웃 성공.");
                    return;
            }
        }
    }
    public static void commentsSelect() {
        Scanner sc = new Scanner(System.in);
        BoardDAO dao = new BoardDAO();
        while (true) {
            System.out.println("====[COMMENTS TABLE 조회]=====");
            System.out.print("기능 선택 : ");
            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]로그아웃");
            int sel = sc.nextInt();
            switch (sel) {
                case 1 :
                    List<CommentsVO> list = dao.commentsSelect();
                    dao.commentsSelectRst(list);
                    break;
                case 2 :
                    dao.commentsInsert();
                    break;
                case 3 :
                    dao.commentsUpdate();
                    break;
                case 4 :
                    dao.commentsDelete();
                    break;
                case 5 :
                    System.out.println("로그아웃 성공.");
                    return;
            }
        }
    }
}
