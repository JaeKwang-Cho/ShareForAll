package com.dorasima.shareforall.data.model;

import java.io.Serializable;

public class RegisterMessage implements Serializable {
    private byte[] profile;
    private String nickName;
    private String email;
    private String phoneNumber;
    private Integer isOld;
    private String date;

    public RegisterMessage(RegisterMessage input){
        this.profile = input.profile;
        this.nickName = input.nickName;
        this.email = input.email;
        this.phoneNumber = input.phoneNumber;
        this.isOld = input.isOld;
        this.date = input.date;
    }

    public RegisterMessage(byte[] profile, String nickName, String email, String phoneNumber, Integer isOld, String date) {
        this.profile = profile;
        this.nickName = nickName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isOld = isOld;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }
}
