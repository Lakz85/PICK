package com.pick;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class MessageListActivity extends AppCompatActivity {
    RecyclerView messageItemRecyclerView;
    MessageRecyclerViewAdapter messageRecyclerViewAdapter;
    Resources res;
    static Context messageListContext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_list);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setTitle("메세지 함");
        ab.setDisplayHomeAsUpEnabled(true);
        // important!!!!
        // Actionbar에서 Toolbar 사용함으로 actionbar에 타이틀을 바꾸거나 뒤로가기 버튼등을 만들려면 setSupportActionBar에서 toolbar를 설정해주고 나서 해야함!

        res = getResources();
        messageListContext = getApplicationContext();
        messageItemRecyclerView = (RecyclerView) findViewById(R.id.message_list);

        //아이템을 배치할 레이아웃 매니저를 등록한다.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false/*거꾸로*/);
        messageItemRecyclerView.setLayoutManager(layoutManager);

        messageRecyclerViewAdapter = new MessageRecyclerViewAdapter();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            messageItemRecyclerView.addItemDecoration(
                    new DividerItemDecoration(getDrawable(R.drawable.divider),
                            true, true));
        }else{
            messageItemRecyclerView.addItemDecoration(
                    new DividerItemDecoration(res.getDrawable(R.drawable.divider),
                            true, true));
        }
        messageItemRecyclerView.setAdapter(messageRecyclerViewAdapter);
        initData();


    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    };


    public static Context getItemContext() {
        return messageListContext;//MessageRecyclerViewAdapter가 실질적으로 레이아웃을 가지고 있는 owner인 MessageListAcitivity의 Context를 가지고 Intent해야 하기때문에 이 클래스가 접근할 수 있도록 마련함
    }


    private void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            messageRecyclerViewAdapter.messageItemInset((new MessageContent("어반자카파", "똑같은 사랑 똑같은 이별", res.getDrawable(R.drawable.circle_thumb, null))));
            messageRecyclerViewAdapter.messageItemInset((new MessageContent("FT아일랜드", "ftft", res.getDrawable(R.drawable.circle_thumb, null))));

        } else {
            messageRecyclerViewAdapter.messageItemInset((new MessageContent("어반자카파", "똑같은 사랑 똑같은 이별", res.getDrawable(R.drawable.circle_thumb))));
            messageRecyclerViewAdapter.messageItemInset((new MessageContent("FT아일랜드", "ftft", res.getDrawable(R.drawable.circle_thumb))));
        }
    }
}