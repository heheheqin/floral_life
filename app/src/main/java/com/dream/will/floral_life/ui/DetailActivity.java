package com.dream.will.floral_life.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.will.floral_life.MyApp;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.adapter.ZhuanTiListAdapter;
import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.Article;
import com.dream.will.floral_life.content.Conten;
import com.dream.will.floral_life.inter.IZhuanTi;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends BaseSwipeBackActivityActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener,AbsListView.OnScrollListener {

    private String cateId;
    private String name;
    private ImageView image_menu;
    private TextView zhuanti_title;
    private CheckBox CheckBox_list;
    private ListView listview;
    private PtrClassicFrameLayout refresh;
    private  String action = "mainList_NewVersion";
    private  int currentPageIndex = 0;
    List<Article.ResultBean> data;
    private ZhuanTiListAdapter zhuanTiListAdapter;
    private boolean isRefresh;
    private boolean isAddMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        cateId = intent.getStringExtra(Conten.KEY_MENU_DETAIL_ID);
        name = intent.getStringExtra(Conten.KEY_MENU_DETAIL_NAME);
        data = new ArrayList<>();
        initView();
        initData();
    }

    private void initData() {
         /* menu_detail
            花艺学堂
	        /servlet/SysArticleServlet
	    post  action=mainList_NewVersion & currentPageIndex=0 & cateId=8dba5958-7da0-4ce9-b1e9-5b92343519a7
        */
        final Retrofit re = new Retrofit.Builder()
                .baseUrl(ApiManger.HOST_POST)//★这里最后面必须能带“/”
                .addConverterFactory(GsonConverterFactory.create())//设置将json解析为javabean所用的方式
                .build();
        IZhuanTi menu = re.create(IZhuanTi.class);
        Call<Article> call = menu.getMenuDetail(action,currentPageIndex,cateId);
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                List<Article.ResultBean> result = response.body().getResult();
                for (Article.ResultBean resultBean : result) {
                    data.add(resultBean);
                }
                refresh.refreshComplete();
                zhuanTiListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        image_menu = (ImageView) findViewById(R.id.image_menu);
        zhuanti_title = (TextView) findViewById(R.id.zhuanti_title);

        //设置选择按钮 选择图标
        CheckBox_list = (CheckBox) findViewById(R.id.CheckBox_list);
        CheckBox_list.setOnCheckedChangeListener(this);
        if (MyApp.getInstance().itemViewType == MyApp.getInstance().CHECKBOX_CHECKED) {
            CheckBox_list.setChecked(true);
        } else {
            CheckBox_list.setChecked(false);
        }

        //设置listView
        listview = (ListView) findViewById(R.id.listview);
        zhuanTiListAdapter = new ZhuanTiListAdapter(this,
                data,
                R.layout.fragment_zhuanti_list_item1,
                R.layout.fragment_zhuanti_list_item2);
        listview.setAdapter(zhuanTiListAdapter);
        listview.setOnScrollListener(this);
        //设置刷新
        refresh = (PtrClassicFrameLayout) findViewById(R.id.refresh);
        //刷新时间
        refresh.setLastUpdateTimeRelateObject(this);
        refresh.setPtrHandler(new PtrDefaultHandler() {
            //属性方法
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //刷新代码
                currentPageIndex = 0;
                initData();
            }

            //
            //解决Listview 和下拉刷新冲突冲突
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header);
            }

        });
        //解决 banner左右滑动冲突
//        refresh.disableWhenHorizontalMove(true);
        image_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_menu:
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.i("TAG", "onCheckedChanged: ---------");
        //切换布局
        if (isChecked) {
            MyApp.getInstance().itemViewType = MyApp.getInstance().CHECKBOX_CHECKED;
        } else {
            MyApp.getInstance().itemViewType = MyApp.getInstance().CHECKBOX_DIS_CHECKED;
        }
        zhuanTiListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (isAddMore && scrollState == 0) {
            currentPageIndex++;
            initData();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        isAddMore = firstVisibleItem + visibleItemCount == totalItemCount;
    }
}
