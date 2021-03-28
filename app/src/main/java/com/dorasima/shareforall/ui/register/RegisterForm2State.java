package com.dorasima.shareforall.ui.register;

import androidx.annotation.Nullable;

public class RegisterForm2State {
    @Nullable
    private Integer phoneNumberError;

    private boolean isDataValid;

    RegisterForm2State(@Nullable Integer phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
        this.isDataValid = false;
    }

    RegisterForm2State(boolean isDataValid) {
        this.phoneNumberError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getPhoneNumberError() {
        return phoneNumberError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}
