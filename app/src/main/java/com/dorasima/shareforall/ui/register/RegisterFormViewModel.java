package com.dorasima.shareforall.ui.register;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dorasima.shareforall.R;

public class RegisterFormViewModel extends ViewModel {
    // 프래그먼트들 끼리
    private MutableLiveData<RegisterForm> fragment1 = new MutableLiveData<>();
    private MutableLiveData<RegisterForm2> frament2 = new MutableLiveData<>();
    // 입력 확인용
    private MutableLiveData<RegisterForm1State> form1State = new MutableLiveData<>();

    public LiveData<RegisterForm> getLiveData1(){ return fragment1;}
    public LiveData<RegisterForm2> getLiveData2(){ return frament2;}
    public LiveData<RegisterForm1State> getForm1State(){ return form1State;}

    // 리스너의 메서드로 반복적으로 실행 됨.
    public void registerDataChanged(String nickname,String email,String password , String password2) {
        if (!isNicknameValid(nickname)) {
            form1State.setValue(new RegisterForm1State(R.string.invalid_username, null ,null,null));
        }
        else if(!isEmailValid(email)){
            form1State.setValue(new RegisterForm1State(null, R.string.invalid_password,null,null));
        }
        else if (!isPasswordValid(password)) {
            form1State.setValue(new RegisterForm1State(null,null, R.string.invalid_password,null));
        }
        else if(!isPasswordAccord(password, password2)){
            form1State.setValue(new RegisterForm1State(null,null,null, R.string.invalid_password));
        }
        else {
            form1State.setValue(new RegisterForm1State(true));
        }
    }

    private boolean isNicknameValid(String username) {
        return username != null && username.trim().length() > 10;
    }

    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        else {
            return !email.trim().isEmpty();
        }
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 10;
    }

    private boolean isPasswordAccord(String password, String password2) {
        return password.equals(password2);
    }

}