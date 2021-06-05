package com.dorasima.shareforall.ui.main.comments;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.dorasima.shareforall.R;
import com.dorasima.shareforall.data.model.LoggedInUser;
import com.dorasima.shareforall.ui.main.agora.WriteActivity;
import com.dorasima.shareforall.ui.main.agora.dummy.DummyContent;
import com.dorasima.shareforall.ui.main.comments.Comments.CommentsContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class CommentsActivity extends AppCompatActivity {

    private static DummyContent.DummyItem userInfo;
    private CommentsFragment list = new CommentsFragment();
    private Context context = this;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        getSupportActionBar().hide();

        userInfo = getIntent().getParcelableExtra("loggedUser");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.comments_container,list);

        fragmentTransaction.commit();

        fab = findViewById(R.id.comment_button);
        fab.setOnClickListener(this::customDialogMethod);
    }
    public void customDialogMethod(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Comments");
        builder.setIcon(android.R.drawable.stat_notify_chat);

        // 다이얼로그를 통해 보여줄 뷰 생성
        LayoutInflater inflater = getLayoutInflater();
        View v1 = inflater.inflate(R.layout.comment_layout,null);

        // 다이얼로그 리스너 세팅
        CustomDialogListener listener = new CustomDialogListener();

        // 생성된 뷰를 다이얼로그에 세팅한다.
        builder.setView(v1);

        builder.setPositiveButton("comment",listener);
        builder.setNegativeButton("cancel",null);

        builder.show();
    }

    // 커스텀 다이얼로그 리스너
    class CustomDialogListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            // editText부분의 내용을 가져온다.
            AlertDialog alert = (AlertDialog)dialogInterface;

            // AlertDialog 가 가지고있는 view를 추출한다.
            EditText editText1 = (EditText)alert.findViewById(R.id.comment_editText);

            // 사용자가 입력한 문자열을 가져온다.
            String comments = editText1.getText().toString();

            CommentsContent.addItem(CommentsContent.createCommentsItem(userInfo.getProfile(), userInfo.getNickname(), comments));
        }
    }
}