package com.pick;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.navercorp.volleyextensions.volleyer.Volleyer;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    private static int checkConnectedServer = 0; // 0: before connection  , 1: connection succeeded

    private final long FINSH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    private static ViewPager pager;
    Vector<ArrayList> container = new Vector();

    FloatingActionMenu fabMenu;
    FloatingActionButton intentIndividualFabButton;
    FloatingActionButton intentBandFabButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // modify
        startActivity(new Intent(this, SplashActivity.class));
        ExampleTask exampleTask = new ExampleTask();
        exampleTask.execute();
        //callJackson();
        // end

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ham_btn);
        ab.setDisplayHomeAsUpEnabled(true);

        //Floating Action Buttons
        fabMenu = (FloatingActionMenu) findViewById(R.id.fab);
        intentIndividualFabButton = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        intentBandFabButton = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);


        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        intentIndividualFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WriteIndividualActivity.class);
                startActivity(intent);
            }
        });

        intentBandFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WriteBandActivity.class);
                startActivity(intent);
            }
        });
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent = null;
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.news_feed:
                        intent = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.apply_list:
                        break;
                    case R.id.bookmark_list:
                        break;
                    case R.id.messagebox:
                        intent = new Intent(MainActivity.this,MessageListActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.setting:
                        break;
                }

                return true;
            }
        });

        // 롤리팝 이상부터 애니메이션 적용
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            Transition exitTrans = new Explode();
            Transition reenterTrans = new Explode();

            window.setExitTransition(exitTrans);
            window.setReenterTransition(reenterTrans);
            window.setAllowEnterTransitionOverlap(true);
            window.setAllowReturnTransitionOverlap(true);
        }
    }

    private void setTab() {
        //탭에 필요한 뷰 페이지가 없을 경우 설정
        ViewPager mainViewPager = (ViewPager)findViewById(R.id.viewpager);
        if(mainViewPager != null) {
            setupTabViewPager(mainViewPager);
        }

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mainViewPager);
    }

    //탭 몇개 필요한지 설정하는 부분
    private void setupTabViewPager(ViewPager pager) {
        PagerAdapter tabAdapter = new PagerAdapter(getSupportFragmentManager());
        tabAdapter.appendFragment(MainFragment.newInstance(1), "구인");
        tabAdapter.appendFragment(MainFragment.newInstance(1), "구직");
        pager.setAdapter(tabAdapter);
    }

    //탭 바 설정
    private static class PagerAdapter extends FragmentPagerAdapter {

        private final ArrayList<Fragment> tabFragment = new ArrayList();
        private final ArrayList<String> tabTitles  = new ArrayList();
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void appendFragment(Fragment fragment , String title){
            tabFragment.add(fragment);
            tabTitles.add(title);
        }

        public android.support.v4.app.Fragment getItem(int position){
            return tabFragment.get(position);
        }

        @Override
        public int getCount() {
            return tabFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return tabTitles.get(position);
        }
    }

    // 네비게이션 처음 설정
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 뒤로가기 버튼 이벤트
    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        long intervalTime = currentTime - backPressedTime;

        if(0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime){
            super.onBackPressed();
        }else{
            backPressedTime = currentTime;
            Toast.makeText(getApplicationContext(),
                    "'뒤로' 버튼 한번 더 누르시면 종료됩니다.",
                    Toast.LENGTH_SHORT).show();
        }
        super.onBackPressed();
    }

    /*
    private void callJackson() {
        Volleyer.volleyer().get("http://52.78.95.102:3000/persons/")
                .withTargetClass(DataItems.class)
                .withListener(listener)
                .withErrorListener(errorListener)
                .execute();
    }
    */

    ArrayList receiveServerData;
    StringBuffer partString;


    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("e", error.getLocalizedMessage());
        }
    };


    class ExampleTask extends AsyncTask<Void, Void, Void> {

        // 설정한 제네릭들은 아래와 같습니다.
        // Params : 비동기 작업시 필요한 input이 없어 Void로 설정
        // Progress: 중간 결과를 보여줄 필요가 없어 Void로 설정
        // Result: 비동기 작업 결과가 필요하지 않아 Void로 설정

        public ExampleTask() {
        }

        @Override
        protected void onPreExecute() {
            // 비동기 작업을 시작하기 전에 화면으로 알립니다.
            return;
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // 이 메소드에 실제로 처리할 일을 코드로 작성하세요.
            MainActivity.checkConnectedServer = 0;

            Response.Listener<DataItems> listener = new Response.Listener<DataItems>() {
                @Override
                public void onResponse(DataItems dataItems) {
                    for (DataItem data : dataItems.datas) {
                        receiveServerData = new ArrayList();
                        partString = new StringBuffer("");
                        receiveServerData.add(0,data.personId);
                        receiveServerData.add(1,data.personName.get(0));
                        receiveServerData.add(2,data.personType);
                        receiveServerData.add(3,data.personVideoURL);
                        for(String part : data.personPart ){
                            partString.append(part+", ");
                        }
                        receiveServerData.add(4,partString.toString());
                        container.add(receiveServerData);
                    }
                    MainFragment.setReceiveServerData(container);
                    MainActivity.checkConnectedServer = 1;
                }
            };

            Volleyer.volleyer().get("http://52.78.95.102:3000/persons/")
                    .withTargetClass(DataItems.class)
                    .withListener(listener)
                    .withErrorListener(errorListener)
                    .execute();

            while (MainActivity.checkConnectedServer == 0);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // 비동기 작업이 끝난 후 화면으로 알립니다.
            setTab();
            return;
        }
    }
}
