package com.dream.will.floral_life.customview;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.CommunityJingxuanBanner;
import com.dream.will.floral_life.inter.ICommunity;
import com.dream.will.floral_life.ui.StoreShopDetailActivity;
import com.dream.will.floral_life.utils.OkUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Author：Will on 2016/11/23 16:29
 * Mail：heheheqin.will@gmail.com
 * 实现自动轮播 向外提供设置城市id，
 * 只要设置城市id，就能自动获取数据，得到图片进行轮播
 */

public class BannerViewForCommunity extends FrameLayout implements OnBannerClickListener {

    Banner banner;
    private List<CommunityJingxuanBanner> allData;

    public BannerViewForCommunity(Context context) {
        super(context);
        init();
    }

    public BannerViewForCommunity(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        allData = new ArrayList<>();
        LayoutInflater.from(getContext()).inflate(R.layout.fragment_community_jingxuan_banner, this, true);
        banner = (Banner) findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
//        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(false);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
//        banner.setIndicatorGravity(BannerConfig.RIGHT);
//        //banner设置方法全部调用完毕时最后调用
        banner.setOnBannerClickListener(this);
//        banner.start();
        setCityId();
    }

    //提供城市id 设置方法
    public  void  setCityId(){
        //retrofit 联网加载数据
        final Retrofit re = new Retrofit.Builder()
                .client(OkUtils.genericClient("bannerViewCommunity"))  ///设置缓存
                .baseUrl(ApiManger.HOST_POST)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        //获取接口实例  action=getAdList
        ICommunity iCommunity = re.create(ICommunity.class);
        Call<String> call = iCommunity.getCommunityBanner("getAdList");
        //发请求
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //得到BannerBean list
                //解析 list 得到url  标题
                List<String> imgUrls = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                String body = response.body();
                Log.i("TAG", "onResponse: -community banner--------" + body);
                Gson gson = new Gson();
                CommunityJingxuanBanner bean = gson.fromJson(body, new TypeToken<CommunityJingxuanBanner>() {
                }.getType());
                List<CommunityJingxuanBanner.ResultBean> result = bean.getResult();
                for (CommunityJingxuanBanner.ResultBean resultBean : result) {
                    imgUrls.add(resultBean.getImageUrl());
//                    titles.add(resultBean.getTitle());
                }
                allData.clear();
                allData.add(bean);
                banner.setImages(imgUrls);
//                banner.setBannerTitles(titles);
                banner.start();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("TAG", "onFailure: --community banner-----error--");
            }
        });

    }
    //设置banner监听
    @Override
    public void OnBannerClick(int position) {
        Log.i("TAG", "OnBannerClick: --------community Click:-" + position);
        Toast.makeText(getContext(), "Banner Click::"+position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), StoreShopDetailActivity.class);
//        getContext().startActivity(intent);
    }


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(path).into(imageView);
            Log.i("TAG", "displayImage: ---------"+path);
        }
    }
}
