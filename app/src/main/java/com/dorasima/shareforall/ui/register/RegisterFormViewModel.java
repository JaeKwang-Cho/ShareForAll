package com.dorasima.shareforall.ui.register;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dorasima.shareforall.R;

import java.time.LocalDate;

public class RegisterFormViewModel extends ViewModel {
    // 입력 데이터 저장용
    private NewUserData newUserData = new NewUserData(null, null, null, null, null, null);
    // 입력 확인용
    private MutableLiveData<RegisterForm1State> form1State = new MutableLiveData<>();
    private MutableLiveData<RegisterForm2State> form2State = new MutableLiveData<>();

    public LiveData<RegisterForm1State> getForm1State() {
        return form1State;
    }

    public LiveData<RegisterForm2State> getForm2State() {
        return form2State;
    }

    public NewUserData getNewUserData() {
        if (newUserData == null) {
            return newUserData = new NewUserData(null, null, null, null, null, null);
        }
        else {
            return newUserData;
        }
    }

    public void registerDataChanged(String nickname, String email, String password, String password2) {
        if (nickname != null) {
            newUserData.setNickname(nickname);
        } if (email != null) {
            newUserData.setEmail(email);
        } if (password != null) {
            newUserData.setPassword(password);
        } if (!isNicknameValid(nickname)) {
            form1State.setValue(new RegisterForm1State(R.string.invalid_nickname, null, null, null));
        }
        else if (!isEmailValid(email)) {
            form1State.setValue(new RegisterForm1State(null, R.string.invalid_email, null, null));
        }
        else if (!isPasswordValid(password)) {
            form1State.setValue(new RegisterForm1State(null, null, R.string.invalid_password, null));
        }
        else if (!isPasswordAccord(password, password2)) {
            form1State.setValue(new RegisterForm1State(null, null, null, R.string.discord_passwords));
        }
        else {
            form1State.setValue(new RegisterForm1State(true));
        }
    }

    public void registerData2Changed(String phoneNumber, Drawable profile) {
        newUserData.setPhoneNumber(phoneNumber); newUserData.setProfile(profile);
        if (!isPhoneNumberValid(phoneNumber)) {
            form2State.setValue(new RegisterForm2State(R.string.invalid_phoneNumber, null));
        } if (!isProfileValid(profile)) {
            form2State.setValue(new RegisterForm2State(null, R.string.invalid_profile));
        }
        else {
            form2State.setValue(new RegisterForm2State(true));
        }
    }

    public void registerData2Changed2(Integer isOld) {
        newUserData.setIsOld(isOld);
    }

    private boolean isNicknameValid(String username) {
        return username != null && username.trim().length() > 4;
    }

    private boolean isProfileValid(Drawable profile) {
        if (profile == null) {
            return false;
        } return true;
    }

    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        } if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        else {
            return email.trim().isEmpty();
        }
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 9;
    }

    private boolean isPasswordAccord(String password, String password2) {
        return password.equals(password2);
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber != null && phoneNumber.trim().length() > 9;
    }
}