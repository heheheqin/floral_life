package com.dream.will.floral_life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dream.will.floral_life.R;
import com.dream.will.floral_life.customview.BannerViewForCommunity;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Author：Will on 2016/12/12 14:13
 * Mail：heheheqin.will@gmail.com
 */

public class CommunityJingxuanFragment extends Fragment {

    private PtrClassicFrameLayout refresh;
    private ListView listView;
    private LinearLayout inflate;
    List<String> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("heheh");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_jignxuan, container, false);
        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.refresh);
        listView = (ListView) view.findViewById(R.id.communityJingxuanlistView);
        inflate = (LinearLayout) inflater.inflate(R.layout.fragment_community_jingxuan_list_head, null);
        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BannerViewForCommunity bannerViewForCommunity = new BannerViewForCommunity(getActivity());
        listView.addHeaderView(bannerViewForCommunity);
        TextView textView = new TextView(getActivity());
        textView.setText("每日精选");
        textView.setTextSize(20);

        listView.addHeaderView(inflate);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data);
        listView.setAdapter(stringArrayAdapter);
    }

}
