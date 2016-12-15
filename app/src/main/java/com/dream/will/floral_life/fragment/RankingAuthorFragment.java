package com.dream.will.floral_life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dream.will.floral_life.R;
import com.dream.will.floral_life.adapter.AbsBaseAdapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Will on 2016/12/14 10:16
 * Mail：heheheqin.will@gmail.com
 */

public class RankingAuthorFragment extends Fragment {


    private ListView listview;

    List<String> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("heh");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking_specialcolumn_auchor,container,false);
        listview = (ListView) view.findViewById(R.id.special_listview);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listview.setAdapter(new AbsBaseAdapter2<String>(getActivity(),data,R.layout.fragment_ranking_auchor_item) {
            private TextView userName;
            private TextView ranking;
            private ImageView headImage;
            private ImageView menu_v;

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public void bindData(int position, ViewHolder viewHolder) {
                userName = (TextView) viewHolder.findViewBid(R.id.userName);
                ranking = (TextView) viewHolder.findViewBid(R.id.ranking);
                headImage = (ImageView) viewHolder.findViewBid(R.id.headImg);
                menu_v = (ImageView) viewHolder.findViewBid(R.id.menu_v);
                userName.setText(data.get(position));
            }
        });
    }
}
