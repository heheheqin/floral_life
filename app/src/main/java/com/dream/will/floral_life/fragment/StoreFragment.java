package com.dream.will.floral_life.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.will.floral_life.R;
import com.dream.will.floral_life.adapter.StoreListViewAdaper;
import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.StoreThemeGoods;
import com.dream.will.floral_life.content.Conten;
import com.dream.will.floral_life.customview.BannerView;
import com.dream.will.floral_life.customview.HandView;
import com.dream.will.floral_life.customview.StoreFirstItemView;
import com.dream.will.floral_life.customview.StoreRecommendBannerView;
import com.dream.will.floral_life.customview.StoreThemeBannerView;
import com.dream.will.floral_life.inter.IStore;
import com.dream.will.floral_life.ui.CityChoiceActivity;
import com.dream.will.floral_life.ui.StoresFristItemDetailActivity;
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
 * Author：Will on 2016/12/12 14:14
 * Mail：heheheqin.will@gmail.com
 */

public class StoreFragment extends Fragment implements View.OnClickListener {

    private BannerView bannerView;
    private TextView find;
    private TextView city;
    private ListView listView;
    private PtrClassicFrameLayout refresh;
    private StoreFirstItemView storeFristItemView;

    private List<StoreThemeGoods.DataBean> listData1;
    private StoreListViewAdaper storeListViewAdaper;
    private ImageView shop_car;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listData1 = new ArrayList<>();
    }


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        city = (TextView) view.findViewById(R.id.city);
        city.setOnClickListener(this);
        shop_car = (ImageView) view.findViewById(R.id.image_shop_car);
        shop_car.setOnClickListener(this);
        find = (TextView) view.findViewById(R.id.text_find);
        find.setOnClickListener(this);
        //刷新控件设置
        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.refresh);
        initRefresh();
        listView = (ListView) view.findViewById(R.id.listview);
        initListViewHead(); //设置ListView头部
        return view;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 初始化listView头
    ///////////////////////////////////////////////////////////////////////////
    private void initListViewHead() {
        //1。启动  广告条banner
        bannerView = new BannerView(getActivity());
        bannerView.setCityId("banner");
        listView.addHeaderView(bannerView);
        //2。 启动 listView   第一项设置
        storeFristItemView = new StoreFirstItemView(getActivity());
        storeFristItemView.setOnFirstItemLis(new StoreFirstItemView.OnFirstItemClick() {
            @Override
            public void Onclick(String itemId) {
                Toast.makeText(getContext(), "" + itemId, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), StoresFristItemDetailActivity.class);
                intent.putExtra(Conten.KEY_STORE_FRIST_ITEM_ID, itemId);
                startActivity(intent);
            }
        });
        storeFristItemView.initData();
        listView.addHeaderView(storeFristItemView);
        //3。启动  精选活动 view
        StoreThemeBannerView storeThemeBannerView = new StoreThemeBannerView(getActivity());
        storeThemeBannerView.setOnClickThemeBannerListener(new StoreThemeBannerView.OnClickThemeBannerListener() {
            @Override
            public void getOnClickThemeBannerListener(String jumpId, String name) {
                Intent intent = new Intent(getActivity(), StoresFristItemDetailActivity.class);
                intent.putExtra(Conten.KEY_STORE_THEME_BANNER_ID, jumpId);
                intent.putExtra(Conten.KEY_STORE_THEME_BANNER_NAME, name);
//                startActivity(intent);
            }
        });
        listView.addHeaderView(storeThemeBannerView);
        //4.本周推荐
        StoreRecommendBannerView storeRecommendBannerView = new StoreRecommendBannerView(getActivity());
        listView.addHeaderView(storeRecommendBannerView);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        storeListViewAdaper = new StoreListViewAdaper(getActivity(), listData1);
        listView.setAdapter(storeListViewAdaper);

    }


    ///////////////////////////////////////////////////////////////////////////
    // 设置刷新控件头部
    ///////////////////////////////////////////////////////////////////////////
    private void initRefresh() {

        //创建刷新头
        HandView handView = new HandView(getActivity());
//        MaterialHeader pullRoRefreshHeadView = new MaterialHeader(getActivity());
        //添加头
        refresh.setHeaderView(handView);
        //添加属性头控件
        refresh.addPtrUIHandler(handView);
        //刷新事件
        refresh.setLastUpdateTimeRelateObject(this);
        refresh.setPtrHandler(new PtrDefaultHandler() {
            //属性方法
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                initListData();
            }

            //
            //解决Listview 和下拉刷新冲突冲突
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header);
            }

        });
        //解决 banner左右滑动冲突
        refresh.disableWhenHorizontalMove(true);
        refresh.setPullToRefresh(true);
        refresh.post(new Runnable() {
            @Override
            public void run() {
                //第一次进来 自动刷新  不需要调用getArticle方法
                //因为会自动调用下拉刷新代码
                refresh.autoRefresh();
                initListData();
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // 下载listView数据
    ///////////////////////////////////////////////////////////////////////////
    private void initListData() {
        //http://api.htxq.net/cactus/index/getThemeGoods?city=%E5%85%A8%E5%9B%BD
        final Retrofit re = new Retrofit.Builder()
                .baseUrl(ApiManger.HOST_GET)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        IStore iStore = re.create(IStore.class);
        Call<String> call = iStore.getThemeGoodsData("%E5%85%A8%E5%9B%BD");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                Log.i("TAG", "onResponse: -StoreThemeGoods--------" + body);
                //Gson 转换
                Gson gson = new Gson();
                StoreThemeGoods mStoreThemeGoods = gson.fromJson(body, new TypeToken<StoreThemeGoods>() {
                }.getType());
                List<StoreThemeGoods.DataBean> data = mStoreThemeGoods.getData();
                listData1.clear();
                listData1.addAll(data);
                storeListViewAdaper.notifyDataSetChanged();
                refresh.refreshComplete();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("TAG", "onFailure: -StoreThemeGoods--------");
                refresh.refreshComplete();
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // 点击事件监听
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city: {
                Toast.makeText(getActivity(), "city", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CityChoiceActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.text_find: {
            }
            break;
            case R.id.image_shop_car: {
            }
            break;
        }
    }
}
