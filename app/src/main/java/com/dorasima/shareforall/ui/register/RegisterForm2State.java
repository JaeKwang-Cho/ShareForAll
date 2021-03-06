package com.dorasima.shareforall.ui.register;

import androidx.annotation.Nullable;

public class RegisterForm2State {
    @Nullable
    private Integer phoneNumberError;
    private Integer profileError;

    private boolean isDataValid;

    RegisterForm2State(@Nullable Integer phoneNumberError, @Nullable Integer profileError) {
        this.phoneNumberError = phoneNumberError;
        this.profileError = profileError;
        this.isDataValid = false;
    }

    RegisterForm2State(boolean isDataValid) {
        this.phoneNumberError = null;
        this.profileError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getPhoneNumberError() {
        return phoneNumberError;
    }

    @Nullable
    Integer getProfileError(){return profileError;}

    boolean isDataValid() {
        return isDataValid;
    }
}
