package com.pick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    private final long FINSH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    private static ViewPager pager;


    FloatingActionMenu fabMenu;
    FloatingActionButton minifab1;
    FloatingActionButton minifab2;


    private Button listViewButton, videoViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        listViewButton = (Button) findViewById(R.id.view_block_list);
        videoViewButton = (Button) findViewById(R.id.view_video_list);

        //Floating Action Buttons
        fabMenu = (FloatingActionMenu) findViewById(R.id.fab);
        minifab1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        minifab2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);


        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

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
                        intent = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.bookmark_list:
                        intent = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.messagebox:
                        intent = new Intent(MainActivity.this,MessageListActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.setting:
                        intent = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                }

                return true;
            }
        });

        //탭에 필요한 뷰 페이지가 없을 경우 설정
        ViewPager mainViewPager = (ViewPager)findViewById(R.id.viewpager);
        if(mainViewPager != null) {
            setupTabViewPager(mainViewPager);
        }

        /*//구인 , 구직 추가 플로팅 버튼
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (FAB_Status == false) {
                    //Display FAB menu
                    expandFAB();
                    FAB_Status = true;
                } else {
                    //Close FAB menu
                    hideFAB();
                    FAB_Status = false;
                }
            }
        });*/

        minifab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WriteIndividualActivity.class);
                startActivity(intent);
            }
        });

        minifab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WriteBandActivity.class);
                startActivity(intent);
            }
        });

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mainViewPager);

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

    Bitmap image = null;
    Bitmap image2 = null;

    //탭 몇개 필요한지 설정하는 부분
    private void setupTabViewPager(ViewPager pager){
        image = BitmapFactory.decodeResource(getResources(), R.drawable.ic_perm_identity_black_24dp);
        image2 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_perm_identity_black_24dp);

        PagerAdapter tabAdapter = new PagerAdapter(getSupportFragmentManager());
        tabAdapter.appendFragment(
                HelpWantedFragment.newInstance(0), "구인", image); // 처음설정은 동영상보기로 하기위해 0으로 설정
        tabAdapter.appendFragment(
                ApplicantFragment.newInstance(0),"구직", image2); // 처음설정은 동영상보기로 하기위해 0으로 설정
        pager.setAdapter(tabAdapter);
        this.pager = pager;
    }

    //탭 바 설정
    private static class PagerAdapter extends FragmentPagerAdapter {

        private final ArrayList<Fragment> tabFragment =
                new ArrayList<Fragment>();
        private final ArrayList<String> tabTitles  = new ArrayList<String>();
        private final ArrayList<Bitmap> tabIcon = new ArrayList<Bitmap>();

//        private static int[] ICONS = new int[] {
//                R.drawable.ic_perm_identity_black_24dp,
//                R.drawable.ic_record_voice_over_black_24dp
//        };

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void appendFragment(Fragment fragment , String title , Bitmap icon){
            tabFragment.add(fragment);
            tabTitles.add(title);
            tabIcon.add(icon);
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

    public void viewVideo(View v){
        if(v != videoViewButton) return;
        PagerAdapter tabAdapter = new PagerAdapter(getSupportFragmentManager());
        tabAdapter.appendFragment(
                HelpWantedFragment.newInstance(0), "구인", image);
        tabAdapter.appendFragment(
                ApplicantFragment.newInstance(0),"구직", image2);
        pager.setAdapter(tabAdapter);
    }

    public void viewList(View v){
        if(v != listViewButton) return;
        PagerAdapter tabAdapter = new PagerAdapter(getSupportFragmentManager());
        tabAdapter.appendFragment(
                HelpWantedFragment.newInstance(1), "구인", image);
        tabAdapter.appendFragment(
                ApplicantFragment.newInstance(1),"구직", image2);
        pager.setAdapter(tabAdapter);

    }


   /* public void expandFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin += (int) (fab1.getWidth() * 1);
        layoutParams.bottomMargin += (int) (fab1.getHeight() * 0.25);
        minifab1.setLayoutParams(layoutParams);
        minifab1.startAnimation(show_fab_1);
        minifab1.setClickable(true);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin += (int) (fab2.getWidth() * 0.25);
        layoutParams2.bottomMargin += (int) (fab2.getHeight() * 0.75);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(show_fab_2);
        fab2.setClickable(true);
    }


    public void hideFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin -= (int) (fab1.getWidth() * 1);
        layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(hide_fab_1);
        fab1.setClickable(false);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin -= (int) (fab2.getWidth() * 0.25);
        layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 0.75);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(hide_fab_2);
        fab2.setClickable(false);
    }
*/

}
