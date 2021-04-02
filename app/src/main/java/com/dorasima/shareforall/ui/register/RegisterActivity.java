package com.dorasima.shareforall.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dorasima.shareforall.R;

public class RegisterActivity extends AppCompatActivity {

    boolean press_next;
    // 뷰
    private Button nextButton;
    private Button prevButton;
    private LifecycleOwner activity = this;
    private RegisterFormViewModel registerFormViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final RegisterForm registerForm1= new RegisterForm();
        final RegisterForm2 registerForm2 = new RegisterForm2();
        // 뷰
        nextButton = findViewById(R.id.register_next);
        prevButton = findViewById(R.id.register_prev);

        // 이미 존재하는 viewModel 검색
        registerFormViewModel = new ViewModelProvider(this).get(RegisterFormViewModel.class);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.register_form_container, registerForm1);
        press_next = false;
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();

        nextButton.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(press_next){
                    Log.d("userData", "complete");
                    Log.d("userData", registerFormViewModel.getNewUserData().getNickname());
                    Log.d("userData", registerFormViewModel.getNewUserData().getEmail());
                    Log.d("userData", registerFormViewModel.getNewUserData().getPassword());
                    Log.d("userData", registerFormViewModel.getNewUserData().getPhoneNumber());
                    Log.d("userData", registerFormViewModel.getNewUserData().getIsOld().toString());
                    finish();
                    press_next = false;
                }else{
                    changeFragment(registerForm2);
                    press_next = true;
                }
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(press_next){
                    changeFragment(registerForm1);
                    press_next = false;
                }else{
                    Log.d("register","cancel");
                    press_next = true;
                    registerFormViewModel.registerDataChanged(null,null,null,null);
                    registerFormViewModel.registerData2Changed(null);
                    registerFormViewModel.registerData2Changed2(null);
                    finish();
                }
            }
        });
    }
    private void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.register_form_container, fragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }
    public RegisterFormViewModel shareViewModel(){
        return registerFormViewModel;
    }
}