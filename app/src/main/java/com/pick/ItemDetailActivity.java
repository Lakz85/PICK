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

import java.util.HashMap;

/**
 * Created by 10 on 2016-07-19.
 */
public class ItemDetailActivity extends AppCompatActivity {

    ViewPager pager;
    FloatingActionMenu fab;
    DetailAdapter mAdapter;
    private String id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_layout);

        Intent i = getIntent();
        id = i.getStringExtra("ID");

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
        Volleyer.volleyer().get("http://52.78.95.102:3000/persons/"+id)
                .withTargetClass(DetailItems.class)
                .withListener(listener)
                .withErrorListener(errorListener)
                .execute();
    }

    HashMap<String , String> receiveServerData;
    StringBuffer partArray;
    StringBuffer genreArray;
    private Response.Listener<DetailItems> listener = new Response.Listener<DetailItems>() {
        @Override
        public void onResponse(DetailItems detailItems) {
            int itemCount = 0;
            for (DetailItem data : detailItems.userDatas) {
                receiveServerData = new HashMap();
                partArray = new StringBuffer();
                genreArray = new StringBuffer();
                receiveServerData.put("id", data.id);
                receiveServerData.put("type", String.valueOf(data.type));
                for (String genre : data.genre) {
                    genreArray.append(genre);
                }
                receiveServerData.put("genre", genreArray.toString());
                for (String part : data.part) {
                    partArray.append(part);
                }
                receiveServerData.put("part", partArray.toString());
                receiveServerData.put("content", data.content);
                receiveServerData.put("videoUPL", data.videoURL);
                receiveServerData.put("userId", data.userId);
                receiveServerData.put("name", data.detailObject.get(0).userName);
                receiveServerData.put("gender", data.detailObject.get(0).userGender);
                receiveServerData.put("age", String.valueOf(data.detailObject.get(0).userAge));
                receiveServerData.put("area_do", data.detailObject.get(0).area_first);
                receiveServerData.put("area_gu", data.detailObject.get(0).area_gu);

            }
            mAdapter.add(DetailProfileFragment.newInstance("" + itemCount++,receiveServerData));
            mAdapter.add(DetailMapFragment.newInstance("" + itemCount++,receiveServerData));
        }
    };


    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("e", error.getLocalizedMessage());
        }
    };
}




