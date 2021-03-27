package com.dorasima.shareforall.data.model;

/**
 * LoginRepository 에서 로그인 한 유저에 대한 정보를 저장하는 클래스
 */
public class LoggedInUser {

    private String userId;
    private String displayName;

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}