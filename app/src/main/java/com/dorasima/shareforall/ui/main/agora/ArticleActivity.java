package com.dorasima.shareforall.ui.main.agora;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dorasima.shareforall.R;
import com.dorasima.shareforall.ui.main.agora.dummy.DummyContent;

public class ArticleActivity extends AppCompatActivity {

    private static DummyContent.DummyItem article;

    private TextView nickname;
    private TextView title;
    private TextView contents;

    private ImageView profile;
    private ImageView icon;

    private Button comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        article = getIntent().getParcelableExtra("article");

        nickname = findViewById(R.id.nickname_article);
        title = findViewById(R.id.title_article);
        contents = findViewById(R.id.content_article);

        profile = findViewById(R.id.profile_article);
        icon = findViewById(R.id.icon_article);

        comments = findViewById(R.id.comments_article);
        // todo: comment 기능 만들어야 합니다.

        nickname.setText(article.getNickname());
        title.setText(article.getTitle());
        contents.setText(article.getContent());

        profile.setImageDrawable(article.profile);
        icon.setImageDrawable(article.icon);
    }


}