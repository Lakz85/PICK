package com.pick;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class DetailAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> items = new ArrayList();
    HashMap<String,String> receiveServerData;

    public void add(Fragment fragment) {
        items.add(fragment);
        notifyDataSetChanged();
    }

    public DetailAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    //갤러리 형식으로 만들기
    @Override
    public float getPageWidth(int position) {
        return 0.92f;
    }
}
