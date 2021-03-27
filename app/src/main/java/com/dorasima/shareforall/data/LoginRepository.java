package com.dorasima.shareforall.data;

import com.dorasima.shareforall.data.model.LoggedInUser;

/**
 * 권한을 요청하고, 데이터 소스?에서 사용자 정보를 요청하고
 * 사용자의 권한과 정보를 메모리에 유지하는 클래스
 */
public class LoginRepository {

    // 싱글톤 이여서 volatile 으로 선언(?)
    private static volatile LoginRepository instance;

    // LoginDataSource
    private LoginDataSource dataSource;

    // 암호화?
    private LoggedInUser user = null;

    // 싱글톤 패턴 이여서 private 생성자
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 생성자 대신 사용
    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    // 로그아웃
    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // TODO: 암호화
    }

    // Result 제네릭 클래스 반환
    public Result<LoggedInUser> login(String username, String password) {
        Result<LoggedInUser> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }
        return result;
    }
}