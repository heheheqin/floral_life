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
import com.dream.will.floral_life.bean.StoreFirtItemList;
import com.dream.will.floral_life.inter.IStore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：Will on 2016/12/15 01:10
 * Mail：heheheqin.will@gmail.com
 */

public class StoreFirstItemView extends LinearLayout implements View.OnClickListener {
    private ImageView fnPic1;
    private TextView fnName1;
    private LinearLayout renqiehua1;
    private ImageView fnPic2;
    private TextView fnName2;
    private LinearLayout renqiehua2;
    private ImageView fnPic3;
    private TextView fnName3;
    private LinearLayout renqiehua3;
    private ImageView fnPic4;
    private TextView fnName4;
    private LinearLayout renqiehua4;
    private ImageView fnPic5;
    private TextView fnName5;
    private LinearLayout renqiehua5;
    private ImageView fnPic6;
    private TextView fnName6;
    private LinearLayout renqiehua6;
    private ImageView fnPic7;
    private TextView fnName7;
    private LinearLayout renqiehua7;
    private ImageView fnPic8;
    private TextView fnName8;
    private LinearLayout renqiehua8;


    private OnFirstItemClick onFirstItemClick;
    private List<StoreFirtItemList.DataBean> data1;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.renqiehua1:{
                onFirstItemClick.Onclick(data1.get(0).getItemId());}break;
            case R.id.renqiehua2:{
                onFirstItemClick.Onclick(data1.get(1).getItemId());}break;
            case R.id.renqiehua3:{
                onFirstItemClick.Onclick(data1.get(2).getItemId());}break;
            case R.id.renqiehua4:{
                onFirstItemClick.Onclick(data1.get(3).getItemId());}break;
            case R.id.renqiehua5:{
                onFirstItemClick.Onclick(data1.get(4).getItemId());}break;
            case R.id.renqiehua6:{
                onFirstItemClick.Onclick(data1.get(5).getItemId());}break;
            case R.id.renqiehua7:{
                onFirstItemClick.Onclick(data1.get(6).getItemId());}break;
            case R.id.renqiehua8:{
                onFirstItemClick.Onclick(data1.get(7).getItemId());}break;
        }

    }
    public interface OnFirstItemClick {
        void Onclick(String itemId);
    }
    public StoreFirstItemView(Context context) {
        super(context);
        init(context);
    }
    public StoreFirstItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.fragment_store_list_fristitem, this);
        fnPic1 = (ImageView) findViewById(R.id.fnPic1);
        fnName1 = (TextView) findViewById(R.id.fnName1);
        renqiehua1 = (LinearLayout) findViewById(R.id.renqiehua1);
        fnPic2 = (ImageView) findViewById(R.id.fnPic2);
        fnName2 = (TextView) findViewById(R.id.fnName2);
        renqiehua2 = (LinearLayout) findViewById(R.id.renqiehua2);
        fnPic3 = (ImageView) findViewById(R.id.fnPic3);
        fnName3 = (TextView) findViewById(R.id.fnName3);
        renqiehua3 = (LinearLayout) findViewById(R.id.renqiehua3);
        fnPic4 = (ImageView) findViewById(R.id.fnPic4);
        fnName4 = (TextView) findViewById(R.id.fnName4);
        renqiehua4 = (LinearLayout) findViewById(R.id.renqiehua4);
        fnPic5 = (ImageView) findViewById(R.id.fnPic5);
        fnName5 = (TextView) findViewById(R.id.fnName5);
        renqiehua5 = (LinearLayout) findViewById(R.id.renqiehua5);
        fnPic6 = (ImageView) findViewById(R.id.fnPic6);
        fnName6 = (TextView) findViewById(R.id.fnName6);
        renqiehua6 = (LinearLayout) findViewById(R.id.renqiehua6);
        fnPic7 = (ImageView) findViewById(R.id.fnPic7);
        fnName7 = (TextView) findViewById(R.id.fnName7);
        renqiehua7 = (LinearLayout) findViewById(R.id.renqiehua7);
        fnPic8 = (ImageView) findViewById(R.id.fnPic8);
        fnName8 = (TextView) findViewById(R.id.fnName8);
        renqiehua8 = (LinearLayout) findViewById(R.id.renqiehua8);

        renqiehua1.setOnClickListener(this);
        renqiehua2.setOnClickListener(this);
        renqiehua3.setOnClickListener(this);
        renqiehua4.setOnClickListener(this);
        renqiehua5.setOnClickListener(this);
        renqiehua6.setOnClickListener(this);
        renqiehua7.setOnClickListener(this);
        renqiehua8.setOnClickListener(this);
    }

    private   void setData(StoreFirtItemList data){

        data1 = data.getData();
        fnName1.setText(data1.get(0).getFnName());
        Glide.with(getContext()).load(data1.get(0).getFnPic()).into(fnPic1);
        fnName2.setText(data1.get(1).getFnName());
        Glide.with(getContext()).load(data1.get(1).getFnPic()).into(fnPic2);
        fnName3.setText(data1.get(2).getFnName());
        Glide.with(getContext()).load(data1.get(2).getFnPic()).into(fnPic3);
        fnName4.setText(data1.get(3).getFnName());
        Glide.with(getContext()).load(data1.get(3).getFnPic()).into(fnPic4);
        fnName5.setText(data1.get(4).getFnName());
        Glide.with(getContext()).load(data1.get(4).getFnPic()).into(fnPic5);
        fnName6.setText(data1.get(5).getFnName());
        Glide.with(getContext()).load(data1.get(5).getFnPic()).into(fnPic6);
        fnName7.setText(data1.get(6).getFnName());
        Glide.with(getContext()).load(data1.get(6).getFnPic()).into(fnPic7);
        fnName8.setText(data1.get(7).getFnName());
        Glide.with(getContext()).load(data1.get(7).getFnPic()).into(fnPic8);


    }

    public void initData() {
        final Retrofit re = new Retrofit.Builder()
                .baseUrl(ApiManger.HOST_GET)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IStore iStore = re.create(IStore.class);
        Call<StoreFirtItemList> call = iStore.getFirtItemList(1+"");
        call.enqueue(new Callback<StoreFirtItemList>() {
            @Override
            public void onResponse(Call<StoreFirtItemList> call, Response<StoreFirtItemList> response) {
                setData(response.body());
            }

            @Override
            public void onFailure(Call<StoreFirtItemList> call, Throwable t) {
                Log.i("TAG", "onFailure: -------error--");
            }
        });
    }

    public void setOnFirstItemLis(OnFirstItemClick onFirstItemClick){
        this.onFirstItemClick = onFirstItemClick;
    }

}
