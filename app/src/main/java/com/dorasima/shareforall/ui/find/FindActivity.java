package com.dorasima.shareforall.ui.find;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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


    }
}