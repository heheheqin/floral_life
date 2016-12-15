package com.dream.will.floral_life.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.will.floral_life.R;
import com.dream.will.floral_life.content.Conten;
import com.dream.will.floral_life.customview.HandView;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class StoreShopDetailActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView back;
    private TextView title;
    private ImageView share;
    private WebView webView;
    private TextView money;
    private TextView add_shop_car;
    private TextView pay;
    private String url;
    private String type;
    private String title1;
    private LinearLayout botton_li;
    private PtrClassicFrameLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_shop_detail);
        Intent intent = getIntent();
        url = intent.getStringExtra(Conten.KEY_STORE_BANNER_URL);
        type = intent.getStringExtra(Conten.KEY_STORE_BANNER_TYPE);
        title1 = intent.getStringExtra(Conten.KEY_STRORE_BANNER_TITLE);
        initView();
        if ("1".equals(type)) {
//            botton_li.setBackgroundColor(Color.parseColor("#00000000"));
            money.setVisibility(View.GONE);
            add_shop_car.setVisibility(View.GONE);
            pay.setVisibility(View.GONE);
        }
        title.setText(title1);
        initWeb();
        initfresh();
    }

    ///////////////////////////////////////////////////////////////////////////
// 初始化webView
///////////////////////////////////////////////////////////////////////////
    private void initWeb() {
        webView.canGoBack();
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override//网页加载结束回调
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                refresh.refreshComplete();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setJavaScriptEnabled(true);



    }

    ///////////////////////////////////////////////////////////////////////////
// 显示wwbView
///////////////////////////////////////////////////////////////////////////
    private void showWeb() {
        //加载网址
        webView.loadUrl("http://app.htxq.net/shop/PGoodsAction/goodsDetail.do?goodsId=" + url);
    }

    ///////////////////////////////////////////////////////////////////////////
// 初始化刷新头部
///////////////////////////////////////////////////////////////////////////
    private void initfresh() {
        //创建刷新头
        HandView handView = new HandView(this);
        //添加头
        refresh.setHeaderView(handView);
        //添加属性头控件
        refresh.addPtrUIHandler(handView);
        refresh.setPtrHandler(new PtrDefaultHandler() {
            //属性方法
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //刷新代码
                showWeb();
            }

            //
            //解决Listview 和下拉刷新冲突冲突
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header);
            }

        });
        //解决 banner左右滑动冲突
        refresh.disableWhenHorizontalMove(true);
        refresh.setPullToRefresh(true);

        refresh.post(new Runnable() {
            @Override
            public void run() {
                //第一次进来 自动刷新  不需要调用getArticle方法
                //因为会自动调用下拉刷新代码
//                getArticle();
                showWeb();
                refresh.autoRefresh();
            }
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        title = (TextView) findViewById(R.id.title);
        title.setOnClickListener(this);
        share = (ImageView) findViewById(R.id.share);
        share.setOnClickListener(this);
        webView = (WebView) findViewById(R.id.webview);
        money = (TextView) findViewById(R.id.money);
        money.setOnClickListener(this);
        add_shop_car = (TextView) findViewById(R.id.add_shop_car);
        add_shop_car.setOnClickListener(this);
        pay = (TextView) findViewById(R.id.pay);
        pay.setOnClickListener(this);
        botton_li = (LinearLayout) findViewById(R.id.botton_li);

        refresh = (PtrClassicFrameLayout) findViewById(R.id.refresh);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back: {
                finish();
            }
            break;
            case R.id.share: {
                Toast.makeText(this, "正在分享", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
}
