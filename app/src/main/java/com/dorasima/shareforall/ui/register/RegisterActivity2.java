package com.dorasima.shareforall.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dorasima.shareforall.R;

public class RegisterActivity2 extends AppCompatActivity {

    final RegisterForm registerForm1= new RegisterForm();
    final RegisterForm2 registerForm2 = new RegisterForm2();
    boolean press_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 뷰
        final Button nextButton = findViewById(R.id.register_next);
        final Button prevButton = findViewById(R.id.register_prev);

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
                    Log.d("register", "complete");
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
}