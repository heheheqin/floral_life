package com.dream.will.floral_life.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.StoreThemeBanner;
import com.dream.will.floral_life.inter.IStore;

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

public class StoreThemeBannerView extends LinearLayout implements View.OnClickListener {
    private ImageView theme_banner_image1;
    private TextView theme_banner_text1;
    private ImageView theme_banner_image2;
    private TextView theme_banner_text2;
    private ImageView theme_banner_image3;
    private TextView theme_banner_text3;
    private ImageView theme_banner_image4;
    private TextView theme_banner_text4;
    private StoreThemeBanner alldata;
    private OnClickThemeBannerListener mOnClickThemeBannerListener;

    public StoreThemeBannerView(Context context) {
        super(context);
        initData(context);
    }


    public StoreThemeBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    /**
     * @param context 初始化视图
     */
    private void initData(Context context) {
        inflate(context, R.layout.fragment_store_theme_banner, this);
        theme_banner_image1 = (ImageView) findViewById(R.id.theme_banner_image1);
        theme_banner_text1 = (TextView) findViewById(R.id.theme_banner_text1);
        theme_banner_image2 = (ImageView) findViewById(R.id.theme_banner_image2);
        theme_banner_text2 = (TextView) findViewById(R.id.theme_banner_text2);
        theme_banner_image3 = (ImageView) findViewById(R.id.theme_banner_image3);
        theme_banner_text3 = (TextView) findViewById(R.id.theme_banner_text3);
        theme_banner_image4 = (ImageView) findViewById(R.id.theme_banner_image4);
        theme_banner_text4 = (TextView) findViewById(R.id.theme_banner_text4);
        theme_banner_text1.setOnClickListener(this);
        theme_banner_text2.setOnClickListener(this);
        theme_banner_text3.setOnClickListener(this);
        theme_banner_text4.setOnClickListener(this);
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
        Call<StoreThemeBanner> call = iStore.getThemeBannerBean();
        call.enqueue(new Callback<StoreThemeBanner>() {
            @Override
            public void onResponse(Call<StoreThemeBanner> call, Response<StoreThemeBanner> response) {
                setRes(response.body());
                alldata = response.body();
            }

            @Override
            public void onFailure(Call<StoreThemeBanner> call, Throwable t) {
                Log.i("TAG", "onFailure: -StoreThemeBannerView--------");
            }
        });
    }

    /**设置各个控件资源
     * @param data   资源
     */
    private void setRes(StoreThemeBanner data) {
        List<StoreThemeBanner.DataBean.ViewBean> view = data.getData().getView();
        theme_banner_text1.setText("");
        Glide.with(getContext()).load(view.get(0).getImageUrl()).error(R.drawable.shop_theme_fenlei).into(theme_banner_image1);
        theme_banner_text2.setText("");
        Glide.with(getContext()).load(view.get(1).getImageUrl()).error(R.drawable.shop_theme_fenlei).into(theme_banner_image2);
        theme_banner_text3.setText("");
        Glide.with(getContext()).load(view.get(2).getImageUrl()).error(R.drawable.shop_theme_fenlei).into(theme_banner_image3);
        theme_banner_text4.setText("");
        Glide.with(getContext()).load(view.get(3).getImageUrl()).error(R.drawable.shop_theme_fenlei).into(theme_banner_image4);
    }

    @Override
    public void onClick(View v) {
        //设置 相应点击事件
        switch (v.getId()) {
            case R.id.theme_banner_text1: {
                mOnClickThemeBannerListener.getOnClickThemeBannerListener(alldata.getData().getView().get(0).getJumpId(), alldata.getData().getView().get(0).getTitle());
            }
            break;
            case R.id.theme_banner_text2: {
                mOnClickThemeBannerListener.getOnClickThemeBannerListener(alldata.getData().getView().get(1).getJumpId(), alldata.getData().getView().get(0).getTitle());
            }
            break;
            case R.id.theme_banner_text3: {
                mOnClickThemeBannerListener.getOnClickThemeBannerListener(alldata.getData().getView().get(2).getJumpId(), alldata.getData().getView().get(0).getTitle());
            }
            break;
            case R.id.theme_banner_text4: {
                mOnClickThemeBannerListener.getOnClickThemeBannerListener(alldata.getData().getView().get(3).getJumpId(), alldata.getData().getView().get(0).getTitle());
            }
            break;
        }
    }


    /**向外提供方法  设置监听
     * @param mOnClickThemeBannerListener 监听
     */
    public void setOnClickThemeBannerListener(OnClickThemeBannerListener mOnClickThemeBannerListener) {
        this.mOnClickThemeBannerListener = mOnClickThemeBannerListener;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 设置接口回调
    ///////////////////////////////////////////////////////////////////////////
    public interface OnClickThemeBannerListener {
        void getOnClickThemeBannerListener(String jumpId, String name);
    }


}
