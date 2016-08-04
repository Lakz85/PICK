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
import android.util.Log;
import android.view.Menu;
import android.view.Window;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.clans.fab.FloatingActionMenu;
import com.navercorp.volleyextensions.volleyer.Volleyer;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by 10 on 2016-07-19.
 */
public class ItemDetailActivity extends AppCompatActivity {

    ViewPager pager;
    FloatingActionMenu fab;
    DetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_layout);

        Intent i = getIntent();
        final String mType = i.getStringExtra("Type");
        final String mBandName = i.getStringExtra("BandName");
        final String mPart = i.getStringExtra("Part");
        final String mLocation = i.getStringExtra("Location");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            Transition exitTrans = new Explode();

            Transition reenterTrans = new Explode();

            window.setExitTransition(exitTrans);
            window.setEnterTransition(reenterTrans);
            window.setReenterTransition(reenterTrans);
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        //memberImage.setImageResource(imageResValue);

        pager = (ViewPager) findViewById(R.id.viewpager);
        //mAdapter = new MyPagerAdapter(this);
        mAdapter = new DetailAdapter(getSupportFragmentManager());

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

        callJackson();
        fab = (FloatingActionMenu) findViewById(R.id.fab);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    private void callJackson() {
        Volleyer.volleyer().get("http://52.78.95.102:3000/persons")
                .withTargetClass(DetailItems.class)
                .withListener(listener)
                .withErrorListener(errorListener)
                .execute();
    }

    ArrayList receiveServerData;
    Vector<String> partArray;
    Vector<String> genreArray;
    private Response.Listener<DetailItems> listener = new Response.Listener<DetailItems>() {
        @Override
        public void onResponse(DetailItems detailItems) {
            int itemCount = 0;
            for (DetailItem data : detailItems.userDatas) {
                receiveServerData = new ArrayList();
                partArray = new Vector<String>();
                genreArray = new Vector<String>();
                receiveServerData.add(0, data.id);
                receiveServerData.add(1, data.type);
                for (String genre : data.genre) {
                    genreArray.add(genre);
                }
                receiveServerData.add(2, genreArray);
                for (String part : data.part) {
                    partArray.add(part);
                }
                receiveServerData.add(3, partArray);
                receiveServerData.add(4, data.content);
                receiveServerData.add(5, data.videoURL);
                receiveServerData.add(6, data.userId);
                receiveServerData.add(7, data.detailObject.get(0));
                receiveServerData.add(8, data.detailObject.get(1));
                receiveServerData.add(9, data.detailObject.get(2));
                receiveServerData.add(10, data.detailObject.get(3));
                receiveServerData.add(11, data.detailObject.get(4));
            }
            mAdapter.add("" + itemCount++, receiveServerData);
        }
    };


    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("e", error.getLocalizedMessage());
        }
    };
}




