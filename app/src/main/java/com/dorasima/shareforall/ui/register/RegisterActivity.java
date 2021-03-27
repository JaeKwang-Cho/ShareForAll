package com.dorasima.shareforall.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.dorasima.shareforall.R;

public class RegisterActivity extends AppCompatActivity {

    RegisterForm registerForm1= new RegisterForm();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.register_form_container, registerForm1);

        fragmentTransaction.commit();
    }
}