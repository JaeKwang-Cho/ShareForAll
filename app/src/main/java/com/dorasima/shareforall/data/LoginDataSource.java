package com.dorasima.shareforall.data;

import com.dorasima.shareforall.data.model.LoggedInUser;

import java.io.IOException;

/**
 * 로그인 자격을 처리하고, 사용자 정보를 얻어오는 클래스
 */
public class LoginDataSource {
    
    public Result<LoggedInUser> login(String username, String password) {
        try {
            // TODO: 여기서 Pool(?)이랑 입력값 비교하여서 유저 권한 부여
            LoggedInUser fakeUser = new LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe");
            return new Result.Success<>(fakeUser);
        }
        catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: 로그아웃 할거 하기
    }
}