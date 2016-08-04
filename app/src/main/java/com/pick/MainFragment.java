package com.pick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by ccei on 2016-07-20.
 */
public class MainFragment extends android.support.v4.app.Fragment {
    public static int increment;
    private static MainActivity owner;
    private static Bundle bundle;
    private static int type = 0;
    private static boolean isPress = false;
    static RecyclerViewAdapter rv = new RecyclerViewAdapter();
    static Vector<ArrayList> receiveServerData;
    private Button listViewButton, videoViewButton;


    public MainFragment(){

    }

    public static MainFragment newInstance(int initValue) {
        MainFragment fragment = new MainFragment();
        bundle = new Bundle();
        bundle.putInt("value", initValue);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static void setReceiveServerData(Vector receive){
        receiveServerData = receive;
    }

    public void setType(int changeType){
        type= changeType;
        rv.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_video_list, container, false);
        final LinearLayout inLayout = (LinearLayout) v.findViewById(R.id.main_video_list);

        listViewButton = (Button) v.findViewById(R.id.view_block_list);
        videoViewButton = (Button) v.findViewById(R.id.view_video_list);
        listViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view != videoViewButton) return;
                setType(0);
            }
        });
        videoViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view != listViewButton) return;
                setType(1);
            }
        });

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.recyclerview);
        ((ViewGroup)rv.getParent()).removeView(rv);
        Bundle initBundle = getArguments();
        increment += initBundle.getInt("value");
        owner = (MainActivity) getActivity();
        rv.setLayoutManager(new LinearLayoutManager(PickApplication.getItemContext()));
        rv.setAdapter(new RecyclerViewAdapter(PickApplication.getItemContext()));
        inLayout.addView(rv);
        return v;
    }


    public static class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        ArrayList<String> items = new ArrayList();

        public RecyclerViewAdapter() {

        }

        public RecyclerViewAdapter(Context itemContext) {
        }


        public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final SurfaceView mVideo;
            public final TextView mType;
            public final TextView mBandName;
            public final TextView mPart;
            public final TextView mLocation;
            public final ImageButton mBookMark;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mVideo = (SurfaceView) view.findViewById(R.id.surfaceView);
                mType = (TextView) view.findViewById(R.id.item_type);
                mBandName = (TextView) view.findViewById(R.id.item_name);
                mPart = (TextView) view.findViewById(R.id.item_part);
                mLocation = (TextView) view.findViewById(R.id.item_location);
                mBookMark = (ImageButton) view.findViewById(R.id.book_mark_icon);
//                버튼 누름 이벤트
                mBookMark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isPress) {
                            mBookMark.setBackgroundResource(R.drawable.fav_select_btn);
                            isPress = false;
                        } else {
                            mBookMark.setBackgroundResource(R.drawable.star_fav_btn);
                            isPress = true;
                        }
                    }
                });
            }

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            switch (type) {
                /* 보는 방식 변하게 하기 위해 구분 */
                case 0:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_video_main_fragment, parent, false);
                    return new ViewHolder(view);
                case 1:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_list_main_fragment, parent, false);
                    return new ViewHolder(view);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            ArrayList serverData = receiveServerData.get(position);
            setData(holder,serverData);
            /*
            switch (serverData.get(2).toString()) {
                case "0":
                    break;
                case "구직":

            }*/
        }
        public void setData(ViewHolder holder, ArrayList serverData){

            holder.mType.setText(serverData.get(2).toString());
            holder.mBandName.setText(serverData.get(1).toString());
            holder.mPart.setText(serverData.get(4).toString());
            holder.mLocation.setText("대전시");

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PickApplication.getItemContext(), ItemDetailActivity.class);
                    //                    서버에서 상세보기 던져야 함


                    // ActiviyOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(owner, holder.girlsImage, ViewCompat.getTransitionName(holder.girlsImage));
                    //ActivityCompat.startActivity(owner, intent, options.toBundle());
                    ActivityCompat.startActivity(owner, intent, bundle);
                }
            });
        }

        @Override
        public int getItemCount() {
            if(receiveServerData == null ) return 0;
            return receiveServerData.size();
        }
    }
}
