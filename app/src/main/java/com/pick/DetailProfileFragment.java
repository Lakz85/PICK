package com.pick;

/**
 * Created by 10 on 2016-07-28.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.util.HashMap;
import java.util.LinkedList;

public class DetailProfileFragment extends Fragment {
    String message;
    private int mMaxProgress = 100;
    private LinkedList<ProgressType> mProgressTypes;
    private Handler mUiHandler = new Handler();
    private static Bundle b;
    static HashMap<String,String> serverData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_detail_profile, container, false);

    }

    public DetailProfileFragment() {
        // Required empty public constructor

    }

    //팩토리 패턴
    public static DetailProfileFragment newInstance(String count, HashMap<String,String> receiveServerData) {
        b = new Bundle();
        b.putString("receive", count);
        serverData = receiveServerData;
        DetailProfileFragment f = new DetailProfileFragment();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message = getArguments().getString("argument가 넘어오지 않았다.");
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Locale[] availableLocales = Locale.getAvailableLocales();
        mProgressTypes = new LinkedList<>();
        for (ProgressType type : ProgressType.values()) {
            mProgressTypes.offer(type);

        }

        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        final TextView ageTextView = (TextView) view.findViewById(R.id.age_text_view);
        final TextView partTextView = (TextView) view.findViewById(R.id.part_text_view);
        final TextView genreTextView = (TextView) view.findViewById(R.id.genre_text_view);
        final TextView locationTextView = (TextView) view.findViewById(R.id.location_text_view);

        ageTextView.setText(serverData.get("age"));
        partTextView.setText(serverData.get("part"));
        genreTextView.setText(serverData.get("genre"));
        locationTextView.setText(serverData.get("area_do")+" "+ serverData.get("area_gu"));

        fab.setMax(mMaxProgress);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressType type = mProgressTypes.poll();
                switch (type) {
                    case INDETERMINATE:
                        fab.setShowProgressBackground(true);
                        fab.setIndeterminate(true);
                        mProgressTypes.offer(ProgressType.INDETERMINATE);
                        break;
                    case PROGRESS_POSITIVE:
                        fab.setIndeterminate(false);
                        fab.setProgress(70, true);
                        mProgressTypes.offer(ProgressType.PROGRESS_POSITIVE);
                        break;
                    case PROGRESS_NEGATIVE:
                        fab.setProgress(30, true);
                        mProgressTypes.offer(ProgressType.PROGRESS_NEGATIVE);
                        break;
                    case HIDDEN:
                        fab.hideProgress();
                        mProgressTypes.offer(ProgressType.HIDDEN);
                        break;
                    case PROGRESS_NO_ANIMATION:
                        increaseProgress(fab, 0);
                        break;
                    case PROGRESS_NO_BACKGROUND:
                        fab.setShowProgressBackground(false);
                        fab.setIndeterminate(true);
                        mProgressTypes.offer(ProgressType.PROGRESS_NO_BACKGROUND);
                        break;
                }
            }
        });

    }

    private void increaseProgress(final FloatingActionButton fab, int i) {
        if (i <= mMaxProgress) {
            fab.setProgress(i, false);
            final int progress = ++i;
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    increaseProgress(fab, progress);
                }
            }, 30);
        } else {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    fab.hideProgress();
                }
            }, 200);
            mProgressTypes.offer(ProgressType.PROGRESS_NO_ANIMATION);
        }
    }



    private enum ProgressType {
        INDETERMINATE, PROGRESS_POSITIVE, PROGRESS_NEGATIVE, HIDDEN, PROGRESS_NO_ANIMATION, PROGRESS_NO_BACKGROUND
    }
}
