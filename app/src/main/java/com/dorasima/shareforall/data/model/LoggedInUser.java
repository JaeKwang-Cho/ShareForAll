package com.dorasima.shareforall.data.model;

import android.util.Log;

import java.util.Date;

/**
 * LoginRepository 에서 로그인 한 유저에 대한 정보를 저장하는 클래스
 */
public class LoggedInUser {

    private String nickName;
    private String email;
    private String phoneNumber;
    private Integer isOld;
    private String date;

    public LoggedInUser( String nickName,String email,String phoneNumber,Integer isOld,String date) {
        this.nickName = nickName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isOld = isOld;
        this.date = date;
    }
    public LoggedInUser(LoggedInUser input){
        this.nickName = input.getNickName();
        this.email = input.getEmail();
        this.phoneNumber = input.getPhoneNumber();
        this.isOld = input.getIsOld();
        this.date = input.getDate();
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {  return email;   }

    public String getPhoneNumber() {    return phoneNumber; }

    public Integer getIsOld() { return isOld;   }

    public String getDate(){return date;}
}