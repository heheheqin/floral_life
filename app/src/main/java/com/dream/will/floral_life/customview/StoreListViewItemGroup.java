package com.dream.will.floral_life.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.LinearLayout;

/**
 * Author：Will on 2016/12/15 11:02
 * Mail：heheheqin.will@gmail.com
 */

public class StoreListViewItemGroup extends GridLayout {


    OnClickRecommendBannerListener mOnClickRecommendBannerListener;
    private LinearLayout content;

    public StoreListViewItemGroup(Context context) {
        super(context);
        initData(context);
    }


    public StoreListViewItemGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    /**
     * @param context 初始化视图
     */
    private void initData(Context context) {

    }

//    public void setGrid(List<StoreThemeGoods.DataBean.ThemeGoodsViewsBean> themeGoodsViews) {
//        int size = themeGoodsViews.size();
//        int with = getResources().getDisplayMetrics().widthPixels;
//        if (size == 1) {
//            with = with / 2;
//        }
//        for (int i = 0; i < size; i++) {
//            StoreListViewItemSingle storeListViewItemSingle = new StoreListViewItemSingle(getContext());
//            storeListViewItemSingle.setRes(themeGoodsViews.get(i), with);
//            addView(storeListViewItemSingle);
//        }
//    }


    /**
     * 设置各个控件资源
     *
     * @param themeGoodsViews 资源
     */
//    private void setResGroup(List<StoreThemeGoods.DataBean.ThemeGoodsViewsBean> themeGoodsViews) {
//        int size = themeGoodsViews.size();
//        int with = getResources().getDisplayMetrics().widthPixels;
//        //只有一个商品  就显示一张大图
//        if (size == 1) {
//            Log.i("TAG", "setResGroup: -1--------" + with);
//            StoreListViewItemSingle storeListViewItemSingle = new StoreListViewItemSingle(getContext());
//            storeListViewItemSingle.setRes(themeGoodsViews.get(0), with);
//            content.addView(storeListViewItemSingle);
//        } else if (size == 2) {  //有两个商品  并排显示
//            Log.i("TAG", "setResGroup: -2--------" + with);
//            LinearLayout linearLayout = new LinearLayout(getContext());
//            linearLayout.setOrientation(HORIZONTAL);
//            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            for (int i = 0; i < size; i++) {
//                StoreListViewItemSingle storeListViewItemSingle = new StoreListViewItemSingle(getContext());
//                storeListViewItemSingle.setRes(themeGoodsViews.get(i), with / 2);
//                linearLayout.addView(storeListViewItemSingle);
//            }
//            content.addView(linearLayout);
//        } else {    //有两个以上商品就 一列两个显示
//            Log.i("TAG", "setResGroup: -more--------");
//            //1.首先创建  size／2 个 水平线性布局 LinearLayout
//            List<LinearLayout> layouts = new ArrayList<>();
//            for (int i = 0; i < size; i++) {
//                if (i % 2 == 0) {
//                    LinearLayout linearLayout = new LinearLayout(getContext());
//                    linearLayout.setOrientation(HORIZONTAL);
//                    linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                    layouts.add(linearLayout);
//                }
//            }
//            //2。每个LinearLayout  放入两个 StoreListViewItemSingle    并加入到content 垂直布局中
//            for (int i = 0; i < layouts.size(); i++) {
//                for (int i1 = 0; i1 < 2; i1++) {
//                    StoreListViewItemSingle storeListViewItemSingle = new StoreListViewItemSingle(getContext());
//                    storeListViewItemSingle.setRes(themeGoodsViews.get(i), with / 2);
//                    layouts.get(i).addView(storeListViewItemSingle);
//                }
//                content.addView(layouts.get(i));
//            }
//        }
//
//    }

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
