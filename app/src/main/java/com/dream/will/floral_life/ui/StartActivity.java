package com.dream.will.floral_life.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.utils.SharedUtils;

public class StartActivity extends AppCompatActivity {

    private ImageView start_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();

        //延迟两秒跳转
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是第一次启动，如果是a第一个跳转到引导界面
                if (SharedUtils.isFirstRun(StartActivity.this)) {
                    Intent intent = new Intent(StartActivity.this, GuideActivity.class);
                    startActivity(intent);
                } else {
                    //不是第一个启动跳转到主界面
//                    Intent intent1 = new Intent(StartActivity.this, .class);
//                    startActivity(intent1);
                    Intent intent = new Intent(StartActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 1000);
        //设置图片动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f,1);
        alphaAnimation.setDuration(800);
        alphaAnimation.setRepeatCount(0);
        start_image.setAnimation(alphaAnimation);
        alphaAnimation.start();
    }

    private void initView() {
        start_image = (ImageView) findViewById(R.id.start_image);
        Glide.with(this)
                .load(R.drawable.start_bg)
                .into(start_image);
    }
}
