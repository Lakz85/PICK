package com.pick;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.view.Menu;
import android.view.Window;

import com.github.clans.fab.FloatingActionMenu;

/**
 * Created by 10 on 2016-07-19.
 */
public class ItemDetailActivity extends AppCompatActivity {

    ViewPager pager;
    FloatingActionMenu fab;
    ItemDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_layout);

        Intent i = getIntent();
        final String mType = i.getStringExtra("Type");
        final String mBandName = i.getStringExtra("BandName");
        final String mPart = i.getStringExtra("Part");
        final String mLocation = i.getStringExtra("Location");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window  = getWindow();
            Transition exitTrans = new Explode();

            Transition reenterTrans = new Explode();

            window.setExitTransition(exitTrans);
            window.setEnterTransition(reenterTrans);
            window.setReenterTransition(reenterTrans);
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(R.drawable.appli_btn);

        //memberImage.setImageResource(imageResValue);

        pager = (ViewPager)findViewById(R.id.viewpager);
        //mAdapter = new MyPagerAdapter(this);
        mAdapter = new ItemDetailAdapter(getSupportFragmentManager());

        pager.setAdapter(mAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initData();
        fab = (FloatingActionMenu) findViewById(R.id.fab);


    }
    private void initData() {
        for (int i = 0; i < 2; i++) {
            mAdapter.add("item " + i);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }



}


