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
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ccei on 2016-07-20.
 */
public class ApplicantFragment extends android.support.v4.app.Fragment {
    public static int increment;
    private static MainActivity owner;
    private static Bundle bundle;
    private static int type=0;
    private static boolean isPress = false;

    public ApplicantFragment() {
    }



    public static ApplicantFragment newInstance(int initValue) {
        ApplicantFragment fragment = new ApplicantFragment();
        bundle = new Bundle();
        bundle.putInt("value", initValue);
        ApplicantFragment.type = initValue;
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.main_video_list, container, false);
        Bundle initBundle = getArguments();
        //타입구분용으로 썼기때문에 중가량에 차이가 생겨 삼항연산문으로 조건을 줌으로써 증가량에 차이가 없도록 함
        int temp = initBundle.getInt("value");
        increment += (temp==0?temp+1:temp);
        owner = (MainActivity) getActivity();//
        rv.setLayoutManager(new LinearLayoutManager(PickApplication.getItemContext()));
        rv.setAdapter(new RecyclerViewAdpater(PickApplication.getItemContext()));
        return rv;
    }


    public static class RecyclerViewAdpater extends RecyclerView.Adapter<RecyclerViewAdpater.ViewHolder> {
        private ArrayList<Integer> girlsImages;

        public RecyclerViewAdpater(Context itemContext) {
        }

       /* public RecyclerViewAdpater(Context context, ArrayList<Integer> resources) {
            girlsImages = resources;
        }*/

        /*public static class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final Surface ;
            public final TextView memberName;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                girlsImage = (ImageView)view.findViewById(R.id.girls_group_member_image);
                memberName = (TextView)view.findViewById(R.id.member_name);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new ViewHolder(view);
        }*/




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
                mBandName = (TextView) view.findViewById(R.id.item_band_name);
                mPart = (TextView) view.findViewById(R.id.item_part);
                mLocation = (TextView) view.findViewById(R.id.item_location);
                mBookMark = (ImageButton) view.findViewById(R.id.book_mark_icon);
                mBookMark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isPress){
                            mBookMark.setBackgroundResource(R.drawable.fav_select_btn);
                            isPress = false;
                        }

                        else {
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
             holder.mType.setText("구직");
             holder.mBandName.setText("어반자카파");
             holder.mPart.setText("키보드파트");
             holder.mLocation.setText("대전시");

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PickApplication.getItemContext(), ItemDetailActivity.class);
                    //intent.putExtra("memberImage", girlsImages.get(position));
                    intent.putExtra("Type", holder.mType.getText().toString());
                    intent.putExtra("BandName", holder.mBandName.getText().toString());
                    intent.putExtra("Part", holder.mPart.getText().toString());
                    intent.putExtra("Location", holder.mLocation.getText().toString());

                    // ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(owner, holder.girlsImage, ViewCompat.getTransitionName(holder.girlsImage));
                    //ActivityCompat.startActivity(owner, intent, options.toBundle());
                    ActivityCompat.startActivity(owner, intent, bundle);
                }
            });
        }

//        @Override
//        public int getItemCount() {
//            return girlsImages.size();
//        }
        @Override
        public int getItemCount() {
            return 5;
       }
    }
}
