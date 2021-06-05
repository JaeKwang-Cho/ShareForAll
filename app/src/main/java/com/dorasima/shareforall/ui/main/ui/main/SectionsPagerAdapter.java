package com.dorasima.shareforall.ui.main.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dorasima.shareforall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<Drawable> mFragmentTitleList = new ArrayList<>();
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }
    public void addFragment(Fragment fragment, Drawable icon){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(icon);
    }
    @Override
    public Fragment getItem(int position) { return mFragmentList.get(position); }

    @Override
    public int getCount() {
        return mFragmentList.size() ;
    }
    public CharSequence getPageTitle(int position) {
        return null;
    }

}