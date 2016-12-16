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

import com.bumptech.glide.Glide;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.adapter.AbsBaseAdapter2;
import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.SpecialColumnBean;
import com.dream.will.floral_life.inter.IZhuanTi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dream.will.floral_life.R.id.title1;

/**
 * Author：Will on 2016/12/14 10:16
 * Mail：heheheqin.will@gmail.com
 */

public class RankingSpecialColumnFragment extends Fragment {

    List<SpecialColumnBean.ResultBean> data;
    private ListView listview;
    private AbsBaseAdapter2<SpecialColumnBean.ResultBean> adapter;
    private ImageView backImage;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();

        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManger.HOST_POST)//★这里最后面必须能带“/”
                .addConverterFactory(GsonConverterFactory.create())//设置将json解析为javabean所用的方式
                .build();
        IZhuanTi call = retrofit.create(IZhuanTi.class);
        //post  pageSize=10 & action=topContents & currentPageIndex=0
        Call<SpecialColumnBean> topContents = call.getSpecialColumn("topContents", 10, 0);
        topContents.enqueue(new Callback<SpecialColumnBean>() {
            @Override
            public void onResponse(Call<SpecialColumnBean> call, Response<SpecialColumnBean> response) {
//                Log.i("TAG", "onResponse: -RankingSpecialColumnFragment--------" + response.body()+"-----------");
                List<SpecialColumnBean.ResultBean> result = response.body().getResult();
                for (SpecialColumnBean.ResultBean resultBean : result) {
                    data.add(resultBean);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<SpecialColumnBean> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ranking_specialcolumn_auchor, container, false);
        listview = (ListView) view.findViewById(R.id.special_listview);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new AbsBaseAdapter2<SpecialColumnBean.ResultBean>(getActivity(),
                data,
                R.layout.fragment_ranking_specialcolumn_item1,
                R.layout.fragment_ranking_specialcolumn_item2) {

            @Override
            public int getItemViewType(int position) {
                switch (position) {
                    case 0:
                    case 1:
                    case 2:
                        return 0;
                    default:
                        return 1;
                }
            }

            @Override
            public void bindData(int position, ViewHolder viewHolder) {
                SpecialColumnBean.ResultBean resultBean = data.get(position);
                switch (position) {
                    case 0:
                    case 1:
                    case 2: {
                        TextView title = (TextView) viewHolder.findViewBid(R.id.title);
                        title.setText(resultBean.getTitle());
                        ImageView list_image = (ImageView) viewHolder.findViewBid(R.id.list_image);
                        Glide.with(getActivity())
                                .load(resultBean.getSmallIcon())
                                .error(R.drawable.banner_zhanwei)
                                .placeholder(R.drawable.banner_zhanwei)
                                .dontAnimate()
                                .into(list_image);
                    }
                    break;
                    default: {
                        TextView title = (TextView) viewHolder.findViewBid(title1);
                        TextView numb = (TextView) viewHolder.findViewBid(R.id.numb);
                        numb.setText(position+"");
                        title.setText(resultBean.getTitle());
                        ImageView list_image = (ImageView) viewHolder.findViewBid(R.id.list_image1);
                        Glide.with(getActivity())
                                .load(resultBean.getSmallIcon())
                                .error(R.drawable.banner_zhanwei)
                                .placeholder(R.drawable.banner_zhanwei)
                                .dontAnimate()
                                .into(list_image);
                    }
                    break;
                }


            }
        };
        listview.setAdapter(adapter);
    }


}
