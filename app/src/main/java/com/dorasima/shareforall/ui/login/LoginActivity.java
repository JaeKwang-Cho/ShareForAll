package com.dorasima.shareforall.ui.login;

import android.Manifest;
import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dorasima.shareforall.R;
import com.dorasima.shareforall.data.Client;
import com.dorasima.shareforall.data.DBClass;
import com.dorasima.shareforall.data.model.LoggedInUser;
import com.dorasima.shareforall.data.model.LoggedMessage;
import com.dorasima.shareforall.data.model.LoginAttemptMessage;
import com.dorasima.shareforall.data.model.Message;
import com.dorasima.shareforall.ui.find.FindActivity;
import com.dorasima.shareforall.ui.main.MainActivity;
import com.dorasima.shareforall.ui.register.RegisterActivity;

import java.util.Date;

import static com.dorasima.shareforall.data.DBTable.*;

public class LoginActivity extends AppCompatActivity {

    // 뷰모델 개체
    private LoginViewModel loginViewModel;
    private LoggedInUser loggedInUser;
    public Context LoginContext = this;

    public static LoggedMessage loggedMessage;
    LoginAttemptMessage loginAttemptMessage;

    String [] permission_list = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private LoggedMessage sendLoginAttemptMessage(final LoginAttemptMessage loginAttemptMessage){
        // todo: 여기에서 LoginAttemptMessage 을 보내고 LoggedMessage 를 받기
        this.loginAttemptMessage = loginAttemptMessage;

        SendLoginAttemptMessageThread thread = new SendLoginAttemptMessageThread();
        thread.start();
        try {
            synchronized(thread){
                thread.join();
            }
        }
        catch (InterruptedException e) {
            return null;
        }
        if(loggedMessage == null){
            return null;
        }
        return loggedMessage;
    }

    private boolean getAuthentication(String email, String password) {

        // todo: 여기서 로그인 원래 입력을 받고 인증을 부여합니다
        LoggedMessage loggedMessage = sendLoginAttemptMessage(new LoginAttemptMessage(email,password));
        if(loggedMessage != null){
            // todo: 여기서 loggedInUser 에다가, LoginMessage 값을 넣어주면 됩니다. 그리고 true 반환
            Drawable profile = new BitmapDrawable(getResources(),BitmapFactory.decodeByteArray(loggedMessage.getProfile(), 0, loggedMessage.getProfile().length));
            loggedInUser = new LoggedInUser(profile,
                                            loggedMessage.getNickName(),
                                            loggedMessage.getEmail(),
                                            loggedMessage.getPhoneNumber(),
                                            loggedMessage.getIsOld(),
                                            loggedMessage.getDate());

            return true;
        }

        // 아래는 원래 있었던 코드니 무시
        Log.d("login", email); Log.d("login", password);
        DBClass dbClass = new DBClass(getApplication());
        SQLiteDatabase sqLiteDatabase = dbClass.getWritableDatabase();

        String sql1 = "select * from " + TABLE; Cursor cursor = sqLiteDatabase.rawQuery(sql1, null);

        // SQL 아직도 잘 모르겠음..
        cursor.moveToFirst(); while (cursor.moveToNext()) {
            int em_pos = cursor.getColumnIndex(EMAIL);
            String em = cursor.getString(em_pos);
            Log.d("login", em);
            int pw_pos = cursor.getColumnIndex(PASSWORD);
            String pw = cursor.getString(pw_pos);
            Log.d("login", pw);
            if (email.equals(em) && password.equals(pw)) {
                int nn_pos = cursor.getColumnIndex(NICKNAME);
                String nn = cursor.getString(nn_pos);
                Log.d("login", nn);
                int pn_pos = cursor.getColumnIndex(PHONE_NUMBER);
                String pn = cursor.getString(pn_pos);
                Log.d("login", pn);
                int age_pos = cursor.getColumnIndex(AGE);
                Integer age = cursor.getInt(age_pos);
                Log.d("login", age.toString());
                int date_pos = cursor.getColumnIndex(DATE);
                String date = cursor.getString(date_pos);
                Log.d("login", date);
                int profile_pos = cursor.getColumnIndex(PROFILE);
                Drawable profile = new BitmapDrawable(getResources(),getBitmap(cursor.getBlob(profile_pos)));

                loggedInUser = new LoggedInUser(profile,nn, email, pn, age, date);

                return true;
            }
        } return false;
    }

    class SendLoginAttemptMessageThread extends Thread{
        @Override
        public void run() {
            super.run();

            // todo: loginAttemptMessage을 이용해서 loggedMessage 가져오기
            loggedMessage = null;
        }
    }


    public void breakInBtn(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginViewModel.logout();
        // Toast toast = Toast.makeText(getApplicationContext(), "logout", Toast.LENGTH_SHORT);
        // toast.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int a1 : grantResults){
            if(a1 == PackageManager.PERMISSION_DENIED){
                return ;
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory()).get(LoginViewModel.class);

        Message msg = new Message();
        msg.setMsg1("start","1234","Email");
        Client client = new Client(msg);
        client.start();

        // 뷰
        final EditText emailEditText = findViewById(R.id.email);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button registerButton = findViewById(R.id.register);
        final Button forgotButton = findViewById(R.id.forgot);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            requestPermissions(permission_list,0);
        }

        // LoginFormState LiveData 의 Observer.onChanged()으로 최신 값을 가져온다.
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override // 실시간으로 입력값 체크
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                } loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    emailEditText.setError(getString(loginFormState.getUsernameError()));
                } if (loginFormState.getPasswordError() != null) {
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
                loadingProgressBar.setVisibility(View.GONE); if (loginResult.getError() != null) {
                    // Todo: 로그인 실패
                    showLoginFailed(loginResult.getError());
                } if (loginResult.getSuccess() != null) {
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
                loginViewModel.loginDataChanged(emailEditText.getText().toString(), passwordEditText.getText().toString());
            }
        };
        // 리스너 설정
        emailEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);

        // 키보드 완료 버튼 리스너
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (getAuthentication(emailEditText.getText().toString(), passwordEditText.getText().toString())) {
                        Log.d("login", loggedInUser.getNickName());
                        Log.d("login", loggedInUser.getEmail());
                        Log.d("login", loggedInUser.getPhoneNumber());
                        Log.d("login", loggedInUser.getIsOld().toString());
                        Log.d("login", loggedInUser.getDate()); loginViewModel.login(loggedInUser);
                        Intent intent = new Intent(LoginContext, MainActivity.class);
                        intent.putExtra("loggedInUser", loggedInUser);
                        startActivity(intent);
                    }
                    else {
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.login_failed, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } return false;
            }
        });

        // 로그인 버튼 리스너 설정
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                if (getAuthentication(emailEditText.getText().toString(), passwordEditText.getText().toString())) {
                    Log.d("login", loggedInUser.getNickName());
                    Log.d("login", loggedInUser.getEmail());
                    Log.d("login", loggedInUser.getPhoneNumber());
                    Log.d("login", loggedInUser.getIsOld().toString());
                    Log.d("login", loggedInUser.getDate());
                    loginViewModel.login(loggedInUser);
                    Intent intent = new Intent(LoginContext, MainActivity.class);
                    intent.putExtra("loggedInUser", loggedInUser);
                    startActivity(intent);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.login_failed, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        // 레지스터 버튼 리스너 설정
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE); toRegisterActivityBtn(v);
            }
        });

        // 폴겟 버튼 리스너 설정 (음차 진짜 이상하네)
        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE); toFindActivityBtn(v);
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

    public void toRegisterActivityBtn(View view) {
        Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(register);
    }

    public void toFindActivityBtn(View view) {
        Intent find = new Intent(getApplicationContext(), FindActivity.class); startActivity(find);
    }

    public Bitmap getBitmap(byte[] bytes){
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0, bytes.length);
        return bitmap;
    }
}