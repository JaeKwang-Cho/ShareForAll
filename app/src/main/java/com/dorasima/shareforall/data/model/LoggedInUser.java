package com.dorasima.shareforall.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.Date;

/**
 * LoginRepository 에서 로그인 한 유저에 대한 정보를 저장하는 클래스
 */
public class LoggedInUser implements Parcelable {

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

    public static final Creator<LoggedInUser> CREATOR = new Creator<LoggedInUser>() {
        @Override
        public LoggedInUser createFromParcel(Parcel in) {
            return new LoggedInUser(in);
        }

        @Override
        public LoggedInUser[] newArray(int size) {
            return new LoggedInUser[size];
        }
    };

    protected LoggedInUser(Parcel in) {
        nickName = in.readString();
        email = in.readString();
        phoneNumber = in.readString();
        if (in.readByte() == 0) {
            isOld = null;
        }
        else {
            isOld = in.readInt();
        }
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nickName);
        dest.writeString(email);
        dest.writeString(phoneNumber);
        if (isOld == null) {
            dest.writeByte((byte) 0);
        }
        else {
            dest.writeByte((byte) 1);
            dest.writeInt(isOld);
        }
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {  return email;   }

    public String getPhoneNumber() {    return phoneNumber; }

    public Integer getIsOld() { return isOld;   }

    public String getDate(){return date;}
}