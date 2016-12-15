package com.dream.will.floral_life.customview;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.StoreRecommendBanner;
import com.dream.will.floral_life.content.Conten;
import com.dream.will.floral_life.inter.IStore;
import com.dream.will.floral_life.ui.StoreShopDetailActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：Will on 2016/12/15 11:02
 * Mail：heheheqin.will@gmail.com
 */

public class StoreRecommendBannerView extends LinearLayout  {

    private OnClickRecommendBannerListener  mOnClickRecommendBannerListener;
    private LinearLayout content;
    private  StoreRecommendBanner alldata;


    public StoreRecommendBannerView(Context context) {
        super(context);
        initData(context);
    }


    public StoreRecommendBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    /**
     * @param context 初始化视图
     */
    private void initData(Context context) {
        inflate(context, R.layout.fragment_store_recommend_banner, this);
        content = (LinearLayout) findViewById(R.id.content);
        startShow();
    }

    /**
     * 提供向外方法  开启网络获取数据
     */
    public void startShow() {
        //网络请求
        final Retrofit re = new Retrofit.Builder()
                .baseUrl(ApiManger.HOST_GET)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IStore iStore = re.create(IStore.class);
        Call<StoreRecommendBanner> call = iStore.getRecommedBannerBean();
        call.enqueue(new Callback<StoreRecommendBanner>() {
            @Override
            public void onResponse(Call<StoreRecommendBanner> call, Response<StoreRecommendBanner> response) {
                setRes(response.body());
                alldata = response.body();
            }

            @Override
            public void onFailure(Call<StoreRecommendBanner> call, Throwable t) {
                Log.i("TAG", "onFailure: -StoreThemeBannerView--------");
            }
        });
    }

    /**设置各个控件资源
     * @param data   资源
     */
    private void setRes(StoreRecommendBanner data) {
        int with = getResources().getDisplayMetrics().widthPixels * 2 / 3;
        LayoutParams param = new LayoutParams(with, with);
        param.setMargins(20,0,0,0);
        final List<StoreRecommendBanner.DataBean.ViewBean> view = data.getData().getView();
        for (final StoreRecommendBanner.DataBean.ViewBean viewBean : view) {
            ImageView imageView = new ImageView(getContext());
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mOnClickRecommendBannerListener.getOnClickThemeBannerListener(viewBean.getJumpId(),viewBean.getTitle());
                    Intent intent = new Intent(getContext(), StoreShopDetailActivity.class);
                    intent.putExtra(Conten.KEY_STORE_BANNER_TYPE,viewBean.getType());
                    intent.putExtra(Conten.KEY_STORE_BANNER_URL,viewBean.getJumpId());
                    intent.putExtra(Conten.KEY_STRORE_BANNER_TITLE,viewBean.getTitle());
                    getContext().startActivity(intent);
                }
            });
            Glide.with(getContext()).load(viewBean.getImageUrl()).into(imageView);
            content.addView(imageView,param);
        }
    }


    /**向外提供方法  设置监听
     * @param mOnClickRecommendBannerListener 监听
     */
    public void setOnClickRecommedBannerListener(OnClickRecommendBannerListener mOnClickRecommendBannerListener) {
        this.mOnClickRecommendBannerListener = mOnClickRecommendBannerListener;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 设置接口回调
    ///////////////////////////////////////////////////////////////////////////
    public interface OnClickRecommendBannerListener {
        void getOnClickThemeBannerListener(String jumpId, String name);
    }


}
