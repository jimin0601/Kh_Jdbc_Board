package com.kh.vo;

import java.sql.Date;

public class CommentsVO {
    private int memberNum;
    private String cmtContent;
    private Date writeDate;
    private int writeNum;

    public CommentsVO(int memberNum, String cmtContent, Date writeDate, int writeNum) {
        this.memberNum = memberNum;
        this.cmtContent = cmtContent;
        this.writeDate = writeDate;
        this.writeNum = writeNum;
    }
    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public void setCmtContent(String cmtContent) {
        this.cmtContent = cmtContent;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public int getWriteNum() {
        return writeNum;
    }

    public void setWriteNum(int writeNum) {
        this.writeNum = writeNum;
    }
}
