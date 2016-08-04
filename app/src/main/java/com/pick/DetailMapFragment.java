package com.pick;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.daum.mf.map.api.MapView;

import java.util.HashMap;

/**
 * Created by 10 on 2016-07-28.
 */
public class DetailMapFragment extends Fragment {
    RelativeLayout mapContainer;
    MapView mapView;
    private static Bundle b;
    static HashMap<String,String> serverData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message = getArguments().getString("message");
        }

        //다음이 제공하는 MapView객체 생성 및 API Key 설정
        mapView = new MapView( (ItemDetailActivity) getActivity());
        mapView.setDaumMapApiKey("ad90cc564f5afb2e77ba554576fbe147");
    }


    public DetailMapFragment() {
        // Required empty public constructor
    }

    //팩토리 패턴
    public static DetailMapFragment newInstance(String count, HashMap<String,String> receiveServerData) {
        b = new Bundle();
        b.putString("receive", count);
        serverData = receiveServerData;
        DetailMapFragment f = new DetailMapFragment();
        f.setArguments(b);
        return f;
    }


    String message;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_detail_map, container, false);
        //xml에 선언된 map_view 레이아웃을 찾아온 후, 생성한 MapView객체 추가
        mapContainer = (RelativeLayout) view.findViewById(R.id.map_view);
        mapContainer.addView(mapView);
        final TextView partTextView = (TextView) view.findViewById(R.id.part_text_view);
        final TextView genreTextView = (TextView) view.findViewById(R.id.genre_text_view);
        final TextView teamTextView = (TextView) view.findViewById(R.id.team_text_view);
        final TextView contentTextView = (TextView) view.findViewById(R.id.content_text_view);

        partTextView.setText(serverData.get("part"));
        genreTextView.setText(serverData.get("genre"));
        teamTextView.setText("기타 1 드럼 2 피아노 3");
        contentTextView.setText(serverData.get("content"));
        return view;
    }
}
