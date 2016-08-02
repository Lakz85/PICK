package com.pick;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import net.daum.mf.map.api.MapView;

/**
 * Created by 10 on 2016-07-28.
 */
public class ItemDetailFragment2 extends Fragment {
    RelativeLayout mapContainer;
    MapView mapView;

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


    public ItemDetailFragment2() {
        // Required empty public constructor
    }
    //팩토리 패턴
    public static ItemDetailFragment2 newInstance(String message) {
        Bundle b = new Bundle();
        b.putString("message", message);
        ItemDetailFragment2 f = new ItemDetailFragment2();
        f.setArguments(b);
        return f;
    }

    String message;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_detail_2, container, false);
        //xml에 선언된 map_view 레이아웃을 찾아온 후, 생성한 MapView객체 추가
        mapContainer = (RelativeLayout) view.findViewById(R.id.map_view);
        mapContainer.addView(mapView);
        return view;
    }
}
