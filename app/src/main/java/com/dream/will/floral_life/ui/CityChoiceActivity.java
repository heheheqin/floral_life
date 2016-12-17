package com.dream.will.floral_life.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.dream.will.floral_life.R;
import com.dream.will.floral_life.adapter.CityChoiceAdapter;
import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.CityBean;
import com.dream.will.floral_life.content.Conten;
import com.dream.will.floral_life.customview.SlideLetterView;
import com.dream.will.floral_life.customview.SlideView;
import com.dream.will.floral_life.inter.ICity;
import com.dream.will.floral_life.utils.CityJsonUtils;
import com.dream.will.floral_life.utils.SDUtils;
import com.dream.will.floral_life.utils.SharedUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * 1。自定义侧滑选择快速选择字母控件、显示控件
 * 2。带头部悬停功能 listView StickyListHeadersListView
 * 3。快捷城市搜索功能
 * 4。右上角取消按钮 如果正在查询退出查询，如果不在查询退出activity
 */
public class CityChoiceActivity extends BaseSwipeBackActivityActivity implements AdapterView.OnItemClickListener,SlideView.SlideClickCallback {

    private static final int YES = 419;
    List<CityBean> data;
    CityChoiceAdapter adapter;
    String str_srarch;
    int currentPosition = -1;
    private StickyListHeadersListView stickyListView;
    private SlideView slideView;
    private SlideLetterView letterView;
    //清除文本消息  延迟搜索
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case YES: {
                    letterView.setVisibility(View.GONE);
                }
                break;
            }
        }
    };
    private EditText editText;
    private ImageView editText_cancel;

    Handler han = new Handler();
    Runnable searchRunable = new Runnable() {
        @Override
        public void run() {
//            adapter.search(str_srarch);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_citychoice);
        initView();
        initData();
        adapter = new CityChoiceAdapter(this, data);
        stickyListView.setAdapter(adapter);
        getCityData();


    }

    private void getCityData() {
        Log.i("TAG", "getCityData: ---------" + SharedUtils.isString("/storage/emulated/0/DCIM/floral_life/cityName.txt"));
        if (SharedUtils.isString("/storage/emulated/0/DCIM/floral_life/cityName.txt")){
            String body = SDUtils.getStringToFile("/storage/emulated/0/DCIM/floral_life/cityName.txt");
            Log.i("TAG", "getCityData: ---------"+body);
            List<CityBean> citys = null;
            try {
                citys = CityJsonUtils.getCityByJson(body);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            data.clear();
            data.addAll(citys);
            adapter.setAllData(citys);
            //解析成功打印长度
            adapter.notifyDataSetChanged();
            return;
        }
        //联网下载数据
        //1。定义Retrofit对象，构建者模式
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManger.HOST_CITY)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        //2。创建接口ICityChoice实例
        ICity iCityChoice = retrofit.create(ICity.class);
        //3。调用接口实例
        Call<String> call = iCityChoice.getCity();
        //4。发请求
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                String cityName = SDUtils.saveFile(body.getBytes(), "cityName.txt", Conten.KEY_APP_NAME);
                Log.i("TAG", "onResponse: -city--------" + cityName);
                SharedUtils.saveString(cityName);
                List<CityBean> citys = null;
                try {
                    citys = CityJsonUtils.getCityByJson(body);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                data.clear();
                data.addAll(citys);
                adapter.setAllData(citys);
                //解析成功打印长度
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void initData() {
        data = new ArrayList<>();
    }

    private void initView() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_city_list_item_gps, null);
        stickyListView = (StickyListHeadersListView) findViewById(R.id.stickyListView);
        stickyListView.addHeaderView(inflate);
        //设施监听
        stickyListView.setOnItemClickListener(this);
        slideView = (SlideView) findViewById(R.id.slideView);
        letterView = (SlideLetterView) findViewById(R.id.leterView);
        //设置  互动字母监听
        slideView.setOnSlideClike(this);
        editText = (EditText) findViewById(R.id.editText);
        editText_cancel = (ImageView) findViewById(R.id.editText_cancel);
        //开始时候隐藏  叉叉图标
        editText_cancel.setVisibility(View.GONE);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_cancel.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //触发handler 实现搜索功能
                str_srarch = s.toString();
                handler.removeCallbacks(searchRunable);
                han.postDelayed(searchRunable,500);
            }
        });
    }

    /**
     * 侧滑是回调
     * @param position
     * @param str
     */
    @Override
    public void slideOnClick(int position, String str) {
        //显示leterView控件
        //设置文本
        letterView.setVisibility(View.VISIBLE);
        letterView.setText(str);
        //从data集合中遍历到第一条数据以str开图数据位置
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getLetter().equals(str)) {
                //设置lsitView位置
                stickyListView.setSelection(i);
                //找到符合条件数据返回
                return;
            }
        }
        //设置   文本在一秒钟之后消失  首先清除消息后面发送
        handler.removeMessages(YES);
        Message m = Message.obtain();
        m.what = YES;
        handler.sendMessageDelayed(m, 1000);
    }

//     字母 控件回调方法  手抬起时 字母取消显示
    @Override
    public void slideUp() {
        letterView.setVisibility(View.GONE);
    }

    public void cancel(View c) {
        editText.setText("");
        editText_cancel.setVisibility(View.GONE);
    }
    //触摸 城市选择标题  退出 软键盘
    public void closeInputMethod(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    // 功能栏上  取消按钮
    public void cancelCity(View vw) {
        //输入的时候 退出输入    没有输入的时候退出城市选择
        if (editText.isFocused()) {
            editText.setText("");
            editText.clearFocus();
        }else {
            finish();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       if (SharedUtils.isFirstRun(this)){
//           L.d("citychoies --- sharePre");
           Intent intent =new Intent(this,HomeActivity.class);
           startActivity(intent);
           finish();
       }else {
           //获取intent  返回到home界面
           Intent intent= getIntent();
           String cityN = null;
           String cityI = null;
//           if (position == 0){
//               cityN = ((TextView)view.findViewById(R.id.item_city)).getText().toString().trim();
//               for (int i = 0; i < data.size(); i++) {
//                   if (data.get(i).getCityname().equals(cityN)) {
//                       cityI = data.get(i).getCityid();
//                   }
//               }
//           }else {
//               cityN = data.get(position-1).getCityname();
//               cityI = data.get(position-1).getCityid();
//           }
//           L.d("onItemClick+cityId:::"+cityI);
//           L.d("onItemClick+cityName:::"+cityN);
//           intent.putExtra(IntentUtils.KEY_CITYNAME,cityN);
//           intent.putExtra(IntentUtils.KEY_CITYID,cityI);
//           setResult(1,intent);
           finish();
       }
    }
}
