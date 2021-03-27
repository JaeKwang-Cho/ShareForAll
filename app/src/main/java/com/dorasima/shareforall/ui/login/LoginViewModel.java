package com.dorasima.shareforall.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.dorasima.shareforall.data.LoginRepository;
import com.dorasima.shareforall.data.Result;
import com.dorasima.shareforall.data.model.LoggedInUser;
import com.dorasima.shareforall.R;

public class LoginViewModel extends ViewModel {

    // 로그인 폼 정보
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    // 로그인한 사용자 정보
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }
    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // 사용자 정보를 저장
        Result<LoggedInUser> result = loginRepository.login(username, password);

        // Result 으로 확인
        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        }
        else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    // 리스너의 메서드로 반복적으로 실행 됨.
    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        }
        else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        }
        else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // ID 형식 유효성 체크
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        }
        else {
            return !username.trim().isEmpty();
        }
    }

    // 패스워드 형식 유효성 체크
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}