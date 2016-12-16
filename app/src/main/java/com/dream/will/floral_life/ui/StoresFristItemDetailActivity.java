package com.dream.will.floral_life.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.will.floral_life.R;
import com.dream.will.floral_life.content.Conten;

public class StoresFristItemDetailActivity extends BaseSwipeBackActivityActivity implements View.OnClickListener {

    private TextView city;
    private TextView text_find;
    private ImageView image_shop_car;
    private LinearLayout activity_store_shop_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_shop_fristitem_detail);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(Conten.KEY_STORE_FRIST_ITEM_ID);
        initView();
    }

    private void initView() {
        city = (TextView) findViewById(R.id.city);
        text_find = (TextView) findViewById(R.id.text_find);
        image_shop_car = (ImageView) findViewById(R.id.image_shop_car);
        activity_store_shop_detail = (LinearLayout) findViewById(R.id.activity_store_shop_detail);

        city.setOnClickListener(this);
        city.setText("");
        city.setBackgroundResource(R.drawable.main_detail_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city:

                break;
        }
    }
}
