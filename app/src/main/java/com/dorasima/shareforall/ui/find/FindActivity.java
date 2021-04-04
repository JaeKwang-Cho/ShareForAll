package com.dorasima.shareforall.ui.find;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dorasima.shareforall.R;

public class FindActivity extends AppCompatActivity {

    private Button sendButton;
    private Button prevButton;
    private EditText emailForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        prevButton = findViewById(R.id.find_prev);
        sendButton = findViewById(R.id.find_send);
        emailForm = findViewById(R.id.find_emailForm);

        emailForm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                boolean b = isEmailValid(emailForm.getText().toString());
                if(!b){
                    sendButton.setEnabled(true);
                    sendButton.setError(null);
                }else{
                    sendButton.setEnabled(false);
                    sendButton.setError(getString(R.string.invalid_email));
                }
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevBtnMethod();
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBtnMethod(emailForm.getText().toString());
            }
        });
    }
    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        else {
            return !email.trim().isEmpty();
        }
    }
    private void prevBtnMethod(){
        finish();
    }
    private void sendBtnMethod(String email){
        Toast toast = Toast.makeText(this,email + "...send",Toast.LENGTH_SHORT);
        toast.show();
    }
}