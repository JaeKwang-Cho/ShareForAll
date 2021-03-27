package com.dorasima.shareforall.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dorasima.shareforall.R;
import com.dorasima.shareforall.ui.login.LoginViewModel;
import com.dorasima.shareforall.ui.login.LoginViewModelFactory;

import java.util.function.ToDoubleBiFunction;

public class LoginActivity extends AppCompatActivity {

    // 뷰모델 개체
    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory()).get(LoginViewModel.class);

        // 뷰
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.register);
        final Button forgotButton = findViewById(R.id.forgot);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        // LoginFormState LiveData 의 Observer.onChanged()으로 최신 값을 가져온다.
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override // 실시간으로 입력값 체크
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }                
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        // LoginResult LiveData 의 Observer.onChanged()으로 최신 값을 가져온다.
        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override 
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                // 입력값 체크
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    // Todo: 로그인 실패
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    // Todo: 로그인 성공
                    updateUiWithUser(loginResult.getSuccess());
                }
                // ActivityForResult의 콜백
                setResult(Activity.RESULT_OK);

                finish(); // 일단은 종료
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
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        };
        // 리스너 설정
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);

        // 키보드 완료 버튼 리스너
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                }
                return false;
            }
        });

        // 로그인 버튼 리스너 설정
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        // 레지스터 버튼 리스너 설정
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                //Todo: 아이디 만들기 만들기
            }
        });

        // 폴겟 버튼 리스너 설정 (음차 진짜 이상하네)
        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                // Todo: 아이디 찾기 만들기
            }
        });
    }

    // 여기서 뭔가 하는 코드
    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : 로그인 성공
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    //로그인 실패를 알림
    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}