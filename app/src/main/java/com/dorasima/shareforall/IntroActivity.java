package com.dorasima.shareforall;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dorasima.shareforall.data.Client;
import com.dorasima.shareforall.data.Message;
import com.dorasima.shareforall.ui.login.LoginActivity;
import com.dorasima.shareforall.ui.main.MainActivity;

public class IntroActivity extends AppCompatActivity {

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
    };
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("");
        init();
        handler.postDelayed(runnable,2000);
    }
    private void init(){
        handler = new Handler();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }
}