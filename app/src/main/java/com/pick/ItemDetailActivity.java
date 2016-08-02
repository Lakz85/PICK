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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.clans.fab.FloatingActionMenu;
import com.navercorp.volleyextensions.volleyer.Volleyer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by 10 on 2016-07-19.
 */
public class ItemDetailActivity extends AppCompatActivity {

    ViewPager pager;
    FloatingActionMenu fab;
    ItemDetailAdapter mAdapter;

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
        callJackson();
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

    private Response.Listener<PersonItem> listener = new Response.Listener<PersonItem>() {
        @Override
        public void onResponse(PersonItem personItem) {
            for (DataItem item : personItem.datas) {
                System.out.println(item.personId + ", " + item.personType + "***\n");
            }
        }
    };


    private void callJackson() {
        Volleyer.volleyer().get("http://52.78.95.102:3000/persons")
                .withTargetClass(PersonItem.class)
                .withListener(listener)
                .execute();
    }


    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            try {
                String responseBody = new String(error.networkResponse.data, "utf-8");
                JSONObject jsonObject = new JSONObject(responseBody);
            } catch (JSONException e) {
                //Handle a malformed json response
            } catch (UnsupportedEncodingException er) {

            }
        }
    };
};




