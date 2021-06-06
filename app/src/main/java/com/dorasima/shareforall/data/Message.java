package com.dorasima.shareforall.data;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Message implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // 타입0 = 새로고침(전체 작성글 목록 갖고오기), 타입1 = 로그인 요청, 타입2 = 회원가입, 타입3 = 글 업로드, 타입4 = 댓글
    private int Type = -1;
    private String NickName = "-1";
    private String Email = "-1";
    private String PassWord = "-1";
    private String PhoneNumber = "-1";
    private String Age = "-1";
    private String Date = "-1";
    private Message message = new Message();

    public void setMsg1(String nickName, String passWord, String Date)
    {
        this.Type = 1;
        this.NickName = nickName;
        this.PassWord = passWord;
        this.Date = Date;
    }

    public void setMsg2(String nickName,String Email, String passWord, String phoneNumber, String age, String Date)
    {
        this.Type = 1;
        this.NickName = nickName;
        this.Email = Email;
        this.PassWord = passWord;
        this.PhoneNumber = phoneNumber;
        this.Age = age;
        this.Date = Date;
    }
    public int getType() {
        return Type;
    }

    public String getNickName() {
        return NickName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassWord() {
        return PassWord;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getAge() {
        return Age;
    }

    public String getDate() {
        return Date;
    }
}