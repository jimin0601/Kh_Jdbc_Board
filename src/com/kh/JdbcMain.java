package com.kh;

import com.kh.dao.MemberDAO;
import com.kh.vo.MemberVO;

import java.util.List;
import java.util.Scanner;

public class JdbcMain {
    public static void main(String[] args) {
        menuSelect();
    }
    public static void menuSelect() {
        Scanner sc = new Scanner(System.in);
        MemberDAO dao = new MemberDAO();
        while (true) {
            System.out.println("====[MEMBER TABLE 조회]====");
            System.out.println("기능 선택 : ");
            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT");
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
                    System.out.println("메뉴를 종료합니다.");
                    return;
            }
        }
    }
}
