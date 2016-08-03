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

        callJackson();
        fab = (FloatingActionMenu) findViewById(R.id.fab);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    private void callJackson() {
        Volleyer.volleyer().get("http://52.78.95.102:3000/persons")
                .withTargetClass(PersonItem.class)
                .withListener(listener)
                .withErrorListener(errorListener)
                .execute();
    }

    private Response.Listener<PersonItem> listener = new Response.Listener<PersonItem>() {
        @Override
        public void onResponse(PersonItem personItem) {
            int i = 0 ;
            for (DataItem data : personItem.datas) {
                Log.e("d", data.personPart.toString() + ", " + data.personId + "***\n");
                mAdapter.add("item " + i);
                i++;
            }
        }
    };


    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("e", error.getLocalizedMessage());
        }
    };
}




