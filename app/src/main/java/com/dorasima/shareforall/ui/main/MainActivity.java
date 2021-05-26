package com.dorasima.shareforall.ui.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.dorasima.shareforall.R;
import com.dorasima.shareforall.data.LoginRepository;
import com.dorasima.shareforall.data.model.LoggedInUser;
import com.dorasima.shareforall.ui.main.agora.dummy.DummyContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import com.dorasima.shareforall.ui.main.ui.main.SectionsPagerAdapter;

import com.dorasima.shareforall.ui.main.addition.*;
import com.dorasima.shareforall.ui.main.agora.*;
import com.dorasima.shareforall.ui.main.mypage.*;


public class MainActivity extends AppCompatActivity {

    public MypageFragment mypageFragment = new MypageFragment();
    public AgoraFragment agoraFragment = new AgoraFragment();
    public AdditionFragment additionFragment = new AdditionFragment();

    private LoggedInUser loggedInUser;

    public LoggedInUser getLoggedInUserInfo(){
        return this.loggedInUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.view_pager);

        loggedInUser = getIntent().getParcelableExtra("loggedInUser");

        // Log.d("main", loggedInUser.getNickName());
        // Log.d("main", loggedInUser.getEmail());
        //Log.d("main", loggedInUser.getPhoneNumber());
        //  Log.d("main", loggedInUser.getIsOld().toString());
        // Log.d("main", loggedInUser.getDate());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        sectionsPagerAdapter.addFragment(additionFragment, "Addition");
        sectionsPagerAdapter.addFragment(agoraFragment, "Agora");
        sectionsPagerAdapter.addFragment(mypageFragment, "Mypage");

        viewPager.setAdapter(sectionsPagerAdapter); viewPager.setCurrentItem(1);

        TabLayout tabs = findViewById(R.id.tabs); tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
}