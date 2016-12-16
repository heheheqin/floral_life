package com.dream.will.floral_life.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.content.Conten;
import com.dream.will.floral_life.other.MyAsyncTack;

public class WallpaperDetailActivity extends BaseSwipeBackActivityActivity implements View.OnClickListener {

    private String url;
    private String name;
    private ImageView mainImage;
    private ImageView down;
    private View line;
    private TextView title;
    private ImageView back;
    private RelativeLayout activity_wallpaper;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        initView();
        Intent intent = getIntent();
        url = intent.getStringExtra(Conten.KEY_WALLPAPER_URL);
        name = intent.getStringExtra(Conten.KEY_WALLPAPER_NAME);
        Glide.with(this)
                .load(url)
                .into(mainImage);
        title.setText(name);

    }

    private void initView() {
        mainImage = (ImageView) findViewById(R.id.mainImage);
        down = (ImageView) findViewById(R.id.down);
        title = (TextView) findViewById(R.id.title);
        back = (ImageView) findViewById(R.id.back);
        activity_wallpaper = (RelativeLayout) findViewById(R.id.activity_wallpaper);
        down.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.down:{
                downImage();


            }break;
            case  R.id.back:{
                finish();
            }break;
        }
    }

    private void downImage() {
        initProgroess();
        MyAsyncTack nAsyncTack = new MyAsyncTack();
        nAsyncTack.setPrgressShow(new MyAsyncTack.ProgressShow() {
            @Override
            public void finish(String progress) {
                progressDialog.setProgress(100);
                progressDialog.dismiss();
                Toast.makeText(WallpaperDetailActivity.this,
                        "保存图片在："+progress, Toast.LENGTH_LONG).show();
            }
        });
        nAsyncTack.execute(url);

    }

    private void initProgroess() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //设置是否进度显示   false为精确设置
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("正在下载："+name);
        //设置对话框不可取消 false  不能取消
//		progressDialog.setCancelable(false);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }


}
