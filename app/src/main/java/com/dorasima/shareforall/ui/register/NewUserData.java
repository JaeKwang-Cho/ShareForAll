package com.dorasima.shareforall.ui.register;

import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

public class NewUserData {
    @Nullable
    private String nickname;
    @Nullable
    private String email;
    @Nullable
    private String password;
    @Nullable
    private String phoneNumber;
    @Nullable
    private Integer isOld;
    @Nullable
    private Drawable profile;
    private NewUserData(){
        nickname = null;
        email = null;
        password = null;
        phoneNumber = null;
        isOld = -1;
        profile = null;
    }
    public NewUserData(@Nullable String nickname,@Nullable  String email,@Nullable  String password,@Nullable String phoneNumber,@Nullable  Integer isOld, @Nullable Drawable profile){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isOld = isOld;
        this.profile = profile;
    }
    public String getNickname() {return nickname;}

    public void setNickname(@Nullable String nickname) { this.nickname = nickname;}

    public String getEmail() { return email;}

    public void setEmail(@Nullable String email) {this.email = email;}

    public String getPassword() { return password;}

    public void setPassword(@Nullable String password) {this.password = password;}

    public String getPhoneNumber() { return phoneNumber;}

    public void setPhoneNumber(@Nullable String phoneNumber) {this.phoneNumber = phoneNumber;}

    public Integer getIsOld() { return isOld;}

    public void setIsOld(@Nullable Integer isOld) {this.isOld = isOld;}

    public Drawable getProfile() {return profile;}

    public void setProfile(@Nullable Drawable profile){
        this.profile = profile;
    }
}
