package com.example.user.screenslideexam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mData;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        // 세 가지 프래그먼트를 준비
        mData = new ArrayList<>();
        mData.add(new ColorFragment());
        mData.add(new ItemFragment());
        mData.add(new PlusOneFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position + " 번째";
    }
}
