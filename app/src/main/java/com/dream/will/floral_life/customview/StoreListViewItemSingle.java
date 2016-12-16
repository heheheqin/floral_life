package com.dream.will.floral_life.customview;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.bean.StoreRecommendBanner;
import com.dream.will.floral_life.bean.StoreThemeGoods;
import com.dream.will.floral_life.content.Conten;
import com.dream.will.floral_life.ui.StoreShopDetailActivity;

/**
 * Author：Will on 2016/12/15 11:02
 * Mail：heheheqin.will@gmail.com
 */

public class StoreListViewItemSingle extends LinearLayout {

    private OnClickRecommendBannerListener mOnClickRecommendBannerListener;
    private ImageView imageView;
    private StoreRecommendBanner alldata;
    private TextView goodsNmage;
    private TextView price;


    public StoreListViewItemSingle(Context context) {
        super(context);
        initData(context);
    }


    public StoreListViewItemSingle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    /**
     * @param context 初始化视图
     */
    private void initData(Context context) {
        inflate(context, R.layout.fragment_store_list_item_sig, this);
        imageView = (ImageView) findViewById(R.id.goodsBigImg);
        goodsNmage = (TextView) findViewById(R.id.goodsName);
        price = (TextView) findViewById(R.id.price);
    }

    /**
     * 设置各个控件资源
     *
     * @param bean 资源
     */
    public void setRes(final StoreThemeGoods.DataBean.ThemeGoodsViewsBean bean, int wide, int high) {
        Log.i("TAG", "setRes: ---------" + wide);
        LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
        layoutParams.width = wide;
        layoutParams.height = high;
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(getContext()).load(bean.getGoodsBigImg()).into(imageView);
        final String goodsName = bean.getGoodsName();
        goodsNmage.setText(goodsName);
        final double price = bean.getPrice();
        this.price.setText("$" + price);
        imageView.requestLayout();
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StoreShopDetailActivity.class);
                intent.putExtra(Conten.KEY_STORE_BANNER_TYPE,"0");
                intent.putExtra(Conten.KEY_STORE_BANNER_URL,bean.getGoodsId());
                intent.putExtra(Conten.KEY_STRORE_BANNER_TITLE,goodsName);
                getContext().startActivity(intent);
                Toast.makeText(getContext(), "选中："+goodsName+" 价钱："+price, Toast.LENGTH_SHORT).show();
            }
        });
//
    }

    /**
     * 向外提供方法  设置监听
     *
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
