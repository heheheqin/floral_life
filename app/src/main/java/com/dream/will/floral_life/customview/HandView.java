package com.dream.will.floral_life.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.will.floral_life.R;
import com.dream.will.floral_life.utils.DateUtil;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Author：Will on 2016/12/4 14:20
 * Mail：heheheqin.will@gmail.com
 */

public class HandView extends FrameLayout implements PtrUIHandler {

    private ImageView image;
    private TextView text;
    private TextView time;
    private TextView refresh_now;


    public HandView(Context context) {
        super(context);
        init(context);
    }

    public HandView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.default_ptr_head,this,false);
        image = (ImageView) view.findViewById(R.id.default_ptr_rotate);
        text = (TextView) view.findViewById(R.id.refresh_text);
        time = (TextView) view.findViewById(R.id.refresh_time_text);
        refresh_now = (TextView) view.findViewById(R.id.refresh_now);
        addView(view);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

        String currentDate = DateUtil.getDate("HH:mm:ss");
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        image.startAnimation(rotateAnimation);
        time.setText("上次更新时间："+currentDate);
        text.setVisibility(View.GONE);
        time.setVisibility(View.GONE);
        refresh_now.setVisibility(View.VISIBLE);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        text.setVisibility(View.VISIBLE);
        text.setText("下拉刷新...");
        time.setVisibility(View.VISIBLE);
        refresh_now.setVisibility(View.GONE);
        image.clearAnimation();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        //如果当前状态是刷新状态就不执行后面代码
        if(status == PtrFrameLayout.PTR_STATUS_LOADING){
            return;
        }
        float currentPercent = ptrIndicator.getCurrentPercent();
        if (currentPercent >1.2){
            text.setText("放开以刷新...");
        }
//        Log.i("TAG", "onUIPositionChange: ---------" + currentPercent);
        float pre =  currentPercent * 80;
//        Log.i("TAG", "pre: ---------" + pre);
        image.setRotation(pre);

    }
}
