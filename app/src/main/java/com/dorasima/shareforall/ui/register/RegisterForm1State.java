package com.dorasima.shareforall.ui.register;

import androidx.annotation.Nullable;

public class RegisterForm1State {
    @Nullable
    private Integer nicknameError;
    @Nullable
    private Integer emailError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer passwordDiscord;
    private boolean isDataValid;

    RegisterForm1State(@Nullable Integer nicknameError, @Nullable Integer emailError, @Nullable Integer passwordError, @Nullable Integer passwordDiscord) {
        this.nicknameError = nicknameError;
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.passwordDiscord = passwordDiscord;
        this.isDataValid = false;
    }

    RegisterForm1State(boolean isDataValid) {
        this.nicknameError = null;
        this.emailError = null;
        this.passwordError = null;
        this.passwordDiscord =null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getNicknameError() {
        return nicknameError;
    }

    @Nullable
    Integer getEmailError() {return emailError;}

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    Integer getPasswordDiscord(){return passwordDiscord;}

    boolean isDataValid() {
        return isDataValid;
    }
}


