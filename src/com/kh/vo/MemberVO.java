package com.kh.vo;

import java.sql.Date;

// 회원정보 테이블 VO
public class MemberVO {
    private int memNO;
    private String nickname;
    private String pwd;
    private Date in_date;

    public MemberVO(int memNO, String nickname, String pwd, Date in_date) {
        this.memNO = memNO;
        this.nickname = nickname;
        this.pwd = pwd;
        this.in_date = in_date;
    }

    public int getMemNO() {
        return memNO;
    }

    public void setMemNO(int memNO) {
        this.memNO = memNO;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getIn_date() {
        return in_date;
    }

    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }
}
