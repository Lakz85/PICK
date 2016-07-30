package com.pick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ccei on 2016-07-20.
 */
public class ApplicantFragment extends android.support.v4.app.Fragment {
    public static int increment;
    static MainActivity owner;
    static Bundle bundle;

    public ApplicantFragment() {
    }

    public static ApplicantFragment newInstance(int initValue) {
        ApplicantFragment fragment = new ApplicantFragment();
        bundle = new Bundle();
        bundle.putInt("value", initValue);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.main_video_list, container, false);
        Bundle initBundle = getArguments();
        increment += initBundle.getInt("value");
        owner = (MainActivity) getActivity();
        rv.setLayoutManager(new LinearLayoutManager(PickApplication.getItemContext()));
        rv.setAdapter(new GirlsGroupRecyclerViewAdpater(PickApplication.getItemContext()));
        return rv;
    }

    public static class GirlsGroupRecyclerViewAdpater extends RecyclerView.Adapter<GirlsGroupRecyclerViewAdpater.ViewHolder> {
        private ArrayList<Integer> girlsImages;

        public GirlsGroupRecyclerViewAdpater(Context itemContext) {
        }

       /* public GirlsGroupRecyclerViewAdpater(Context context, ArrayList<Integer> resources) {
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

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mVideo = (SurfaceView) view.findViewById(R.id.surfaceView);
                mType = (TextView) view.findViewById(R.id.item_type);
                mBandName = (TextView) view.findViewById(R.id.item_band_name);
                mPart = (TextView) view.findViewById(R.id.item_part);
                mLocation = (TextView) view.findViewById(R.id.item_location);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_video_main_fragment, parent, false);
            return new ViewHolder(view);
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
