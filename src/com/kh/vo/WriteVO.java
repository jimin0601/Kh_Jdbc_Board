package com.kh.vo;

import java.sql.Date;

public class WriteVO {
    private String boardName;
    private int writeNum;
    private String writeName;
    private int memNum;
    private String writeContents;
    private Date writeDate;

    public WriteVO(String boardName, int writeNum, String writeName,
                   int memNum, String writeContents, Date writeDate) {
        this.boardName = boardName;
        this.writeNum = writeNum;
        this.writeName = writeName;
        this.memNum = memNum;
        this.writeContents = writeContents;
        this.writeDate = writeDate;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public int getWriteNum() {
        return writeNum;
    }

    public void setWriteNum(int writeNum) {
        this.writeNum = writeNum;
    }

    public String getWriteName() {
        return writeName;
    }

    public void setWriteName(String writeName) {
        this.writeName = writeName;
    }

    public int getMemNum() {
        return memNum;
    }

    public void setMemNum(int memNum) {
        this.memNum = memNum;
    }

    public String getWriteContents() {
        return writeContents;
    }

    public void setWriteContents(String writeContents) {
        this.writeContents = writeContents;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }
}
