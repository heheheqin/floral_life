package com.dream.will.floral_life.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dream.will.floral_life.R;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_store_recommend_banner);
        initView();
    }

    private void initView() {
    }

    @Override
    public void onClick(View v) {

    }
}
