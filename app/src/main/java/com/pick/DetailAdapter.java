package com.pick;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class DetailAdapter extends FragmentPagerAdapter {
    ArrayList<String> items = new ArrayList();
    ArrayList receiveServerData;

    public void add(String count, ArrayList receiveServerData) {
        items.add(count);
        this.receiveServerData = receiveServerData;
        notifyDataSetChanged();
    }

    public DetailAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DetailProfileFragment.newInstance(items.get(position),receiveServerData);//정적 메소드로 newInstance 생성해주는 것이 좋음
            case 1:
                //return DetailMapFragment.newInstance(items.get(position), receiveServerData);
        }
        return null;
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