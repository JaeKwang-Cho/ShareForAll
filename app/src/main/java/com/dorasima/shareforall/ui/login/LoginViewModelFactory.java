package com.dorasima.shareforall.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.dorasima.shareforall.data.LoginDataSource;
import com.dorasima.shareforall.data.LoginRepository;

/**
 * 로그인 뷰 모델을 만드는 펙토리,
 * 로그인 뷰 모델 생성자(인자)를 요구한다.
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // 인자로 넘어온 개체가 LoginViewModel 인지(상속,구현) 체크
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            // LoginDataSource 개체를 인자로 가지는 LoginRepository 인스턴스를 뷰모델에 넘겨준다.
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        }
        else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}