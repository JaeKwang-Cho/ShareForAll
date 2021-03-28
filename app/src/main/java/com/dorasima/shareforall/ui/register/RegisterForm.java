package com.dorasima.shareforall.ui.register;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dorasima.shareforall.R;
public class RegisterForm extends Fragment {

    private RegisterFormViewModel mViewModel;
    // 뷰
    private EditText nickname_form;
    private EditText email_form;
    private EditText password_form;
    private EditText password2_form;

    public static RegisterForm newInstance() {
        return new RegisterForm();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.register_form_fragment, container, false);

        nickname_form = view.findViewById(R.id.nickname_form);
        email_form = view.findViewById(R.id.email_form);
        password_form = view.findViewById(R.id.password_form);
        password2_form = view.findViewById(R.id.password2_form);

        mViewModel = new ViewModelProvider(this).get(RegisterFormViewModel.class);

        mViewModel.getForm1State().observe(getViewLifecycleOwner(), new Observer<RegisterForm1State>() {
            @Override // 실시간으로 입력값 체크
            public void onChanged(@Nullable RegisterForm1State registerForm1State) {
                if (registerForm1State == null) {
                    return;
                }
                getActivity().findViewById(R.id.register_next).setEnabled(registerForm1State.isDataValid());
                if (registerForm1State.getNicknameError() != null) {
                    nickname_form.setError(getString(registerForm1State.getNicknameError()));
                }
                if (registerForm1State.getEmailError() != null) {
                    email_form.setError(getString(registerForm1State.getEmailError()));
                }
                if (registerForm1State.getPasswordError() != null) {
                    password_form.setError(getString(registerForm1State.getPasswordError()));
                }
                if (registerForm1State.getPasswordDiscord() != null) {
                    password2_form.setError(getString(registerForm1State.getPasswordDiscord()));
                }
            }
        });

        return view;
    }
}