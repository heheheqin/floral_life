package com.dream.will.floral_life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.adapter.AbsBaseAdapter2;
import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.CommunityJingxuanListBean;
import com.dream.will.floral_life.customview.BannerViewForCommunity;
import com.dream.will.floral_life.inter.ICommunity;
import com.dream.will.floral_life.utils.OkUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Author：Will on 2016/12/12 14:13
 * Mail：heheheqin.will@gmail.com
 */

public class CommunityJingxuanFragment extends Fragment implements AbsListView.OnScrollListener {

    private PtrClassicFrameLayout refresh;
    private ListView listView;
    private LinearLayout inflate;
    List<CommunityJingxuanListBean> data;

    private int currentPageIndex = 0;
    private AbsBaseAdapter2<CommunityJingxuanListBean> stringArrayAdapter;
    private boolean isAddMore;
    private boolean isRefresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_jignxuan, container, false);
        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.refresh);
        listView = (ListView) view.findViewById(R.id.communityJingxuanlistView);
        // TODO: 2016/12/17 fragment在viewPager中不能使用查找父控件来生成view 在添加给listview头部
        inflate = (LinearLayout) inflater.inflate(R.layout.fragment_community_jingxuan_list_head, null);
//        inflate = (LinearLayout) inflater.inflate(R.layout.fragment_community_jingxuan_list_head, container,false);
        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //添加头部   BannerView
        BannerViewForCommunity bannerViewForCommunity = new BannerViewForCommunity(getActivity());
        listView.addHeaderView(bannerViewForCommunity);
        //添加头部字样  "每日精选"
        listView.addHeaderView(inflate);
        getNetListData();
        stringArrayAdapter = new AbsBaseAdapter2<CommunityJingxuanListBean>(getActivity(),data,R.layout.community_jiangxuan_list_item){
            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public void bindData(int position, ViewHolder viewHolder) {
                CommunityJingxuanListBean bean = data.get(position);
                ImageView image1 = (ImageView) viewHolder.findViewBid(R.id.jingxuan_image1);
                ImageView image2 = (ImageView) viewHolder.findViewBid(R.id.jingxuan_image2);
                ImageView image3 = (ImageView) viewHolder.findViewBid(R.id.jingxuan_image3);
                ImageView image4 = (ImageView) viewHolder.findViewBid(R.id.jingxuan_image4);
                String attachmentSnap = bean.getResult().get(0).getAttachmentSnap();

                String attachmentSnap1 = bean.getResult().get(1).getAttachmentSnap();
                String attachmentSnap2 = bean.getResult().get(2).getAttachmentSnap();
                String attachmentSnap3 = bean.getResult().get(3).getAttachmentSnap();
                String[] split = attachmentSnap.split(",");
                String[] split1 = attachmentSnap1.split(",");
                String[] split2 = attachmentSnap2.split(",");
                String[] split3 = attachmentSnap3.split(",");

                Log.i("TAG", "bindData: -jingxuan--0------" + attachmentSnap);
                Log.i("TAG", "bindData: -jingxuan---1-----" + attachmentSnap1);
                Log.i("TAG", "bindData: -jingxuan----2----" + attachmentSnap3);
                Log.i("TAG", "bindData: -jingxuan-----3---" + attachmentSnap2);
                Glide.with(getActivity())
                        .load(split[0])
                        .centerCrop()
                        .into(image1);
                Glide.with(getActivity())
                        .load(split1[0])
                        .centerCrop()
                        .into(image2);
                Glide.with(getActivity())
                        .load(split2[0])
                        .centerCrop()
                        .into(image3);
                Glide.with(getActivity())
                        .load(split3[0])
                        .into(image4);

            }
        };
        listView.setAdapter(stringArrayAdapter);
        listView.setOnScrollListener(this);
        refresh.setLastUpdateTimeRelateObject(this);
        refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getNetListData();
                isRefresh = true;
            }
        });
        refresh.post(new Runnable() {
            @Override
            public void run() {
                refresh.autoRefresh();
            }
        });
    }


    /**
     * 获取网络数据
     */
    public void getNetListData() {
        if (isRefresh){
            currentPageIndex = 0;
        }

            final Retrofit re = new Retrofit.Builder()
                    .client(OkUtils.genericClient("jingxuan"))  ///设置缓存
                    .baseUrl(ApiManger.HOST_POST)//★这里最后面必须能带“/”
                    .addConverterFactory(ScalarsConverterFactory.create())//设置将json解析为javabean所用的方式
                    .build();
        ICommunity iCommunity = re.create(ICommunity.class);
        //type=%E8%8D%90&pageSize=4&action=getJianOrJingList&currentPageIndex=0
        Call<String> call = iCommunity.getCommunityJingxuanList("getJianOrJingList","%E8%8D%90",4,currentPageIndex);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (isRefresh){
                        data.clear();
                        isRefresh = false;
                    }
                    String body = response.body();
                    Log.i("TAG", "onResponse: --community list-------" + body);

                    Gson gson = new Gson();
                    CommunityJingxuanListBean mBean = gson.fromJson(body, new TypeToken<CommunityJingxuanListBean>() {
                    }.getType());
                    data.add(mBean);
//
//                    Glide.with(getActivity())
//                            .load(result.get(0).getImgUrl())
//                            .error(R.drawable.banner_zhanwei)
//                            .placeholder(R.drawable.banner_zhanwei)
//                            .into(bannerImage);
                    stringArrayAdapter.notifyDataSetChanged();
                    refresh.refreshComplete();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.i("TAG", "onResponse: --community-----error--"  );
                    Toast.makeText(getActivity(), "请检查网络", Toast.LENGTH_SHORT).show();
                }
            });

    }

    ///////////////////////////////////////////////////////////////////////////
    // 设置滑动监听
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (isAddMore && scrollState == 0) {
            currentPageIndex++;
            getNetListData();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        isAddMore = firstVisibleItem + visibleItemCount == totalItemCount;
    }
}
