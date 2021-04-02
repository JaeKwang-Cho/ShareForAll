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
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

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

        mViewModel = new ViewModelProvider(requireActivity()).get(RegisterFormViewModel.class);

        mViewModel.getForm1State().observe(getViewLifecycleOwner(), new Observer<RegisterForm1State>() {
            @Override // 실시간으로 입력값 체크
            public void onChanged(@Nullable RegisterForm1State registerForm1State) {
                if (registerForm1State == null) {
                    return;
                }
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
                if(registerForm1State.isDataValid()){
                    getActivity().findViewById(R.id.register_next).setEnabled(registerForm1State.isDataValid());
                }
            }
        });

        // 텍스트가 바뀌는지 확인하는 리스너
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            // 텍스트가 바뀌면 호출 되는 메서드
            public void afterTextChanged(Editable s) {
                mViewModel.registerDataChanged(nickname_form.getText().toString(), email_form.getText().toString(),password_form.getText().toString(),password2_form.getText().toString());
            }
        };
        nickname_form.addTextChangedListener(afterTextChangedListener);
        email_form.addTextChangedListener(afterTextChangedListener);
        password_form.addTextChangedListener(afterTextChangedListener);
        password2_form.addTextChangedListener(afterTextChangedListener);



        return view;
    }
}