package com.dorasima.shareforall.data;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dorasima.shareforall.data.model.LoggedInUser;

import java.io.IOException;
import java.util.Date;

/**
 * 로그인 자격을 처리하고, 사용자 정보를 얻어오는 클래스
 */
public class LoginDataSource {
    
    public Result<LoggedInUser> login(LoggedInUser input) {
        try {
            // TODO: 여기서 Pool(?)이랑 입력값 비교하여서 유저 권한 부여
            LoggedInUser loggedInUser = new LoggedInUser(input);
            Log.d("loginDateSource",input.getNickName());
            Log.d("loginDateSource",input.getEmail());
            Log.d("loginDateSource",input.getPhoneNumber());
            Log.d("loginDateSource",input.getIsOld().toString());
            Log.d("loginDateSource",input.getDate());
            return new Result.Success<>(loggedInUser);
        }
        catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: 로그아웃 할거 하기
    }

}