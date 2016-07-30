package com.pick;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    private final long FINSH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //탭에 필요한 뷰 페이지가 없을 경우 설정
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        if(viewPager != null) {
            setupTabViewPager(viewPager);
        }

        //구인 , 구직 추가 플로팅 버튼
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Here's a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });



        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

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


        //탭 몇개 필요한지 설정하는 부분

    private void setupTabViewPager(ViewPager pager){
        PagerAdapter girlsAdapter = new PagerAdapter(getSupportFragmentManager());
        girlsAdapter.appendFragment(
                HelpWantedFragment.newInstance(1), "구인");
        girlsAdapter.appendFragment(
                ApplicantFragment.newInstance(1),"구직");
        pager.setAdapter(girlsAdapter);
    }

    //탭 바 설정
    private static class PagerAdapter extends FragmentPagerAdapter {


        private final ArrayList<Fragment> tabFragment =
                new ArrayList<Fragment>();
        private final ArrayList<String> tabTitles  = new ArrayList<String>();

//        private static int[] ICONS = new int[] {
//                R.drawable.ic_perm_identity_black_24dp,
//                R.drawable.ic_record_voice_over_black_24dp
//        };

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

//        public int getDrawableId(int position) {
//            return ICONS[position];
//        }

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
}
