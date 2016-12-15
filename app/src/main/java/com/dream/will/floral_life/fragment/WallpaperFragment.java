package com.dream.will.floral_life.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.adapter.WallpaperAdaper;
import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.WallpaperBanner;
import com.dream.will.floral_life.bean.WallpaperList;
import com.dream.will.floral_life.content.Conten;
import com.dream.will.floral_life.customview.HandView;
import com.dream.will.floral_life.inter.IWallpaper;
import com.dream.will.floral_life.ui.WallpaperDetailActivity;
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

public class WallpaperFragment extends Fragment implements AbsListView.OnScrollListener{


    private View view;
    private ListView listview;
    private PtrClassicFrameLayout refresh;
    //action=getList & phoneType=android & imgScale=1.0 & pageIndex=0
    private  String action = "getList";
    private  String phoneType = "android";
    private  double imgScale =  1.0;
    private int pageIndex = 0;

    private List<WallpaperList.ResultBean> dataList;
    private List<WallpaperBanner.ResultBean> dataBanner;
    private WallpaperAdaper adpater;
    private View benner;
    private ImageView bannerImage;
    private boolean isAddMore;
    private boolean isFresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList = new ArrayList<>();
        dataBanner = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_wallpaper, container, false);
        benner = inflater.inflate(R.layout.fragment_wallpaper_banner, container, false);
        bannerImage = (ImageView) benner.findViewById(R.id.wallpaperBanner);
        listview = (ListView) view.findViewById(R.id.listview);
        adpater = new WallpaperAdaper(getActivity(),dataList,R.layout.fragment_wallpaper_list_item);
        adpater.setmOnclick(new WallpaperAdaper.OnClick() {
            @Override
            public void OnClickItem(String position,String name) {
                Log.i("TAG", "OnClickItem: --image-------" + position);
                Intent intent = new Intent(getActivity(), WallpaperDetailActivity.class);
                intent.putExtra(Conten.KEY_WALLPAPER_URL,position);
                intent.putExtra(Conten.KEY_WALLPAPER_NAME,name);
                startActivity(intent);
            }
        });
        listview.setAdapter(adpater);
        listview.addHeaderView(benner);
        listview.setOnScrollListener(this);
        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.refresh);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initfresh();
    }

    ///////////////////////////////////////////////////////////////////////////
    // 初始化刷新控件
    ///////////////////////////////////////////////////////////////////////////
    private void initfresh() {
        //创建刷新头
        HandView handView = new HandView(getActivity());
        //添加头
        refresh.setHeaderView(handView);
        //添加属性头控件
        refresh.addPtrUIHandler(handView);
        refresh.setPtrHandler(new PtrDefaultHandler() {
            //属性方法
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //刷新代码
                isFresh = true;
                initDataList();
                initDataBanner();
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
//                getArticle();
                refresh.autoRefresh();
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // 初始化bannershuj
    ///////////////////////////////////////////////////////////////////////////
    private void initDataBanner() {
        final Retrofit re = new Retrofit.Builder()
                .baseUrl(ApiManger.HOST_POST)//★这里最后面必须能带“/”
                .addConverterFactory(ScalarsConverterFactory.create())//设置将json解析为javabean所用的方式
                .build();
        IWallpaper menu = re.create(IWallpaper.class);
        Call<String> call = menu.getWallpaperBanner("getBanner");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                Log.i("TAG", "onResponse: --wallpaperbanner-------" + body);
                Gson gson = new Gson();
                WallpaperBanner wallpaperBean = gson.fromJson(body, new TypeToken<WallpaperBanner>() {
                }.getType());
                List<WallpaperBanner.ResultBean> result = wallpaperBean.getResult();
                for (WallpaperBanner.ResultBean resultBean : result) {
//                    dataBanner.add(resultBean);
                }
                Log.i("TAG", "onResponse: -dataBanner--------" + dataBanner.size());
                Glide.with(getActivity())
                        .load(result.get(0).getImgUrl())
                        .error(R.drawable.banner_zhanwei)
                        .placeholder(R.drawable.banner_zhanwei)
                        .into(bannerImage);
                refresh.refreshComplete();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("TAG", "onResponse: --wallpaper-----error--"  );
                Toast.makeText(getActivity(), "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });

    }
    ///////////////////////////////////////////////////////////////////////////
    // 初始化list
    ///////////////////////////////////////////////////////////////////////////

    private void initDataList() {
         /*
    listview
	/servlet/SysWallpaperServlet
	     type=click & action=wallpaperLog
	action=getList & phoneType=android & imgScale=1.0 & pageIndex=0

     */
        if (isFresh) {
            pageIndex = 0;
        }
        final Retrofit re = new Retrofit.Builder()
                .baseUrl(ApiManger.HOST_POST)//★这里最后面必须能带“/”
                .addConverterFactory(ScalarsConverterFactory.create())//设置将json解析为javabean所用的方式
                .build();
        IWallpaper menu = re.create(IWallpaper.class);
        Call<String> call = menu.getWallpaper(action,pageIndex,imgScale,phoneType);
//        Call<String> call = menu.getWallpaperBanner("getBanner");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                //把字符串解析层HomePageBean
                Gson gson = new Gson();
                WallpaperList wallpaperBean = gson.fromJson(body, new TypeToken<WallpaperList>() {
                }.getType());
                List<WallpaperList.ResultBean> wallpapers = wallpaperBean.getResult();
                if (isFresh) {
                    dataList.clear();
                    isFresh = false;
                }
                for (WallpaperList.ResultBean resultBean : wallpapers) {
                    dataList.add(resultBean);
                }
                Log.i("TAG", "onResponse: -dataList--------" + dataList.size());
                adpater.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("TAG", "onResponse: --wallpaper-----error--"  );
                Toast.makeText(getActivity(), "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // 上拉加载
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (isAddMore  && scrollState == 0) {
            pageIndex ++;
            initDataBanner();
            initDataList();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        isAddMore =
                firstVisibleItem + visibleItemCount == totalItemCount;
    }
}
