package com.dorasima.shareforall.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.dorasima.shareforall.R;
import com.dorasima.shareforall.data.model.LoggedInUser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.dorasima.shareforall.ui.main.ui.main.SectionsPagerAdapter;

import com.dorasima.shareforall.ui.main.addition.*;
import com.dorasima.shareforall.ui.main.agora.*;
import com.dorasima.shareforall.ui.main.mypage.*;


public class MainActivity extends AppCompatActivity {

    public MypageFragment mypageFragment = new MypageFragment();
    public AgoraFragment agoraFragment = new AgoraFragment();
    public AdditionFragment additionFragment = new AdditionFragment();

    public static LoggedInUser loggedInUser;
    public Context context = this;

    public LoggedInUser getLoggedInUserInfo(){
        return this.loggedInUser;
    }

    private Drawable AdditionOn;
    private Drawable AdditionOff;
    private Drawable AgoraOn;
    private Drawable AgoraOff;
    private Drawable MyPageOn;
    private Drawable MyPageOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.view_pager);

        loggedInUser = getIntent().getParcelableExtra("loggedInUser");

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        AdditionOn = getResources().getDrawable(android.R.drawable.btn_star);
        //AdditionOff = getResources().getDrawable(android.R.drawable.star_off);

        AgoraOn = getResources().getDrawable(R.drawable.agora_unselected);
       // AgoraOff = getResources().getDrawable(R.drawable.arora);

        MyPageOn = getResources().getDrawable(android.R.drawable.ic_dialog_info);
       // MyPageOff = getResources().getDrawable(android.R.drawable.ic_menu_info_details);


        sectionsPagerAdapter.addFragment(additionFragment, AdditionOn);
        sectionsPagerAdapter.addFragment(agoraFragment, AgoraOn);
        sectionsPagerAdapter.addFragment(mypageFragment, AgoraOn);

        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setCurrentItem(1);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        tabs.getTabAt(0).setIcon(AdditionOn);
        tabs.getTabAt(1).setIcon(AgoraOn);
        tabs.getTabAt(2).setIcon(MyPageOn);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(context, WriteActivity.class);
                intent.putExtra("loggedUser",loggedInUser);

                startActivityForResult(intent,RESULT_OK);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
            //DummyContent.addItem((DummyContent.DummyItem) data.getExtras().get("dummyData"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // todo: ?????? ??? ????????? ???????????? ????????? ??? ????????????.
    }
}