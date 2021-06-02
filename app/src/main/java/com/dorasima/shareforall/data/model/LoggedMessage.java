package com.dorasima.shareforall.data.model;

import java.io.Serializable;

public class LoggedMessage implements Serializable {
    private byte[] profile;
    private String nickName;
    private String email;
    private String phoneNumber;
    private Integer isOld;
    private String date;

    public LoggedMessage(LoggedMessage input){
        this.profile = input.profile;
        this.nickName = input.nickName;
        this.email = input.email;
        this.phoneNumber = input.phoneNumber;
        this.isOld = input.isOld;
        this.date = input.date;
    }

    public LoggedMessage(byte[] profile, String nickName, String email, String phoneNumber, Integer isOld, String date) {
        this.profile = profile;
        this.nickName = nickName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isOld = isOld;
        this.date = date;
    }

    public byte[] getProfile() {
        return profile;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getIsOld() {
        return isOld;
    }

    public void setIsOld(Integer isOld) {
        this.isOld = isOld;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
