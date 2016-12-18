package com.dream.will.floral_life.ui;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.VideoDetailBean;
import com.dream.will.floral_life.content.Conten;
import com.dream.will.floral_life.customview.AppBarStateChangeListener;
import com.dream.will.floral_life.inter.IZhuanTi;
import com.dream.will.floral_life.utils.OkUtils;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoDetailActivity extends BaseSwipeBackActivityActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnInfoListener,NestedScrollView.OnScrollChangeListener {

    private ImageView back;
    private TextView title;
    private ImageView share;
    private ImageView headImg;
    private ImageView menu_v;
    private TextView userName;
    private TextView subscibe_text;
    private Button subscibe_button;
    private WebView webView;
    private ImageView time;
    private TextView text_time;
    private TextView fnCommentNum;
    private ImageView conmmentImage;
    private ImageView shareImage;
    private TextView read;
    private ImageView readImage;
    private LinearLayout activity_video_detail;
    private TextView wenzhangbiaoti;
    private TextView wenzhangxiaobiaoti;
    private TextView desc;
    private ImageView smallImage;
    private ImageView videoImage;
    private String videoUrl;
    private String shareUrl;
    private String jumpId;
    private boolean isVideo;
    private VideoView videoView;
    private TextView percentT;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //缓冲完毕 消失
            percentT.setVisibility(View.GONE);

        }
    };
    private TextView contentTitle1;
    private View imaginary1;
    private View linerView2;
    private TextView contentTitle2;
    private View imaginary2;
    private WebView webView2;
    private View linerView3;
    private TextView contentTitle3;
    private View imaginary3;
    private WebView webView3;
    private View linerView1;
    private AppBarLayout app_bar;
    private Toolbar toolbar;
    private String userNameT;
    private String headImageUrl;
    private NestedScrollView ns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_article_detail);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.main_detail_back);
        app_bar = (AppBarLayout) findViewById(R.id.app_bar);
        ns = (NestedScrollView) findViewById(R.id.ns);
        ns.setOnScrollChangeListener(this);

        initAppBar();
        Intent intent = getIntent();
        headImageUrl = intent.getStringExtra(Conten.KEY_HEADIMAGE);
        String smallImageUrl = intent.getStringExtra(Conten.KEY_SMALLIMAGE);
        userNameT = intent.getStringExtra(Conten.KEY_USERNAME);
        String biaoTi = intent.getStringExtra(Conten.KEY_WENZHANGBIAOTI);
        String xiaoBiaoti = intent.getStringExtra(Conten.KEY_WENZHANGXIAOBIAOTI);
        String description = intent.getStringExtra(Conten.KEY_DESC);
        String subscibeNum = intent.getStringExtra(Conten.KEY_SUBSCIBENUM);
        shareUrl = intent.getStringExtra(Conten.KEY_SHAREUTL);
        jumpId = intent.getStringExtra(Conten.KEY_JUMP_ID);
        isVideo = intent.getBooleanExtra(Conten.KEY_ISVIDEO, false);
        initView();
//        title.setText("详情");
        Glide.with(this).load(headImageUrl).into(headImg);
        Glide.with(this).load(smallImageUrl).dontAnimate().into(smallImage);
        userName.setText(userNameT);
        wenzhangbiaoti.setText(biaoTi);
        wenzhangxiaobiaoti.setText(xiaoBiaoti);
        desc.setText(description);
        subscibe_text.setText("订阅人数" + subscibeNum);
        if (!isVideo) {
            videoImage.setVisibility(View.GONE);
        } else {
            videoImage.setVisibility(View.VISIBLE);
            videoUrl = intent.getStringExtra(Conten.KEY_VIDEOURL);
        }

        initWebView();
        //网络请求数据
        initData();
    }

    private void initAppBar() {
        app_bar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if( state == State.EXPANDED ) {
                    //展开状态
                    toolbar.setTitle("");
                    Log.i("TAG", "onStateChanged: -EXPANDED--------");
                }else if(state == State.COLLAPSED){
                    //折叠状态
                    // TODO: 2016/12/18 设置了 标题之后不能设置回来
                    Log.i("TAG", "onStateChanged: -COLLAPSED--------");
                    toolbar.setTitle("");
                }else {
                    //中间状态
                    Log.i("TAG", "onStateChanged: ---------");
                    toolbar.setTitle("");
                }
            }
        });
    }

    /**
     * webView设置
     */
    private void initWebView() {
//        webView.setWebChromeClient(new WebChromeClient());
//        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setSupportZoom(false);
        webView2.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView2.getSettings().setSupportZoom(false);
        webView3.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView3.getSettings().setSupportZoom(false);
    }

    /**
     * 控件初始化
     */
    private void initView() {
        linerView1 = (View) findViewById(R.id.linerView1);
        percentT = (TextView) findViewById(R.id.percent);
        videoView = (VideoView) findViewById(R.id.videoView);
        videoImage = (ImageView) findViewById(R.id.videoImage);
//        back = (ImageView) findViewById(R.id.back);
//        title = (TextView) findViewById(R.id.title);
//        share = (ImageView) findViewById(R.id.share);
        headImg = (ImageView) findViewById(R.id.headImg);
        menu_v = (ImageView) findViewById(R.id.menu_v);
        userName = (TextView) findViewById(R.id.userName);
        subscibe_text = (TextView) findViewById(R.id.subscibe_text);
        subscibe_button = (Button) findViewById(R.id.subscibe_button);
        webView = (WebView) findViewById(R.id.webView);
        time = (ImageView) findViewById(R.id.time);
        text_time = (TextView) findViewById(R.id.text_time);
        fnCommentNum = (TextView) findViewById(R.id.fnCommentNum);
        conmmentImage = (ImageView) findViewById(R.id.conmmentImage);
        shareImage = (ImageView) findViewById(R.id.shareImage);
        read = (TextView) findViewById(R.id.read);
        readImage = (ImageView) findViewById(R.id.readImage);
//        activity_video_detail = (LinearLayout) findViewById(R.id.activity_video_detail);

//        back.setOnClickListener(this);
        subscibe_button.setOnClickListener(this);
//        share.setOnClickListener(this);
        headImg.setOnClickListener(this);
        wenzhangbiaoti = (TextView) findViewById(R.id.wenzhangbiaoti);
        wenzhangxiaobiaoti = (TextView) findViewById(R.id.wenzhangxiaobiaoti);
        desc = (TextView) findViewById(R.id.desc);
        smallImage = (ImageView) findViewById(R.id.smallImage);
        smallImage.setOnClickListener(this);
        contentTitle1 = (TextView) findViewById(R.id.contentTitle1);
        contentTitle1.setOnClickListener(this);
        imaginary1 = (View) findViewById(R.id.imaginary1);
        imaginary1.setOnClickListener(this);
        linerView2 = (View) findViewById(R.id.linerView2);
        linerView2.setOnClickListener(this);
        contentTitle2 = (TextView) findViewById(R.id.contentTitle2);
        contentTitle2.setOnClickListener(this);
        imaginary2 = (View) findViewById(R.id.imaginary2);
        imaginary2.setOnClickListener(this);
        webView2 = (WebView) findViewById(R.id.webView2);
        webView2.setOnClickListener(this);
        linerView3 = (View) findViewById(R.id.linerView3);
        linerView3.setOnClickListener(this);
        contentTitle3 = (TextView) findViewById(R.id.contentTitle3);
        contentTitle3.setOnClickListener(this);
        imaginary3 = (View) findViewById(R.id.imaginary3);
        imaginary3.setOnClickListener(this);
        webView3 = (WebView) findViewById(R.id.webView3);
        webView3.setOnClickListener(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // 网络请求数据 请求成功后 调用setContent（） 显示页面方法
    ///////////////////////////////////////////////////////////////////////////
    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(OkUtils.genericClient("videoDetail"))  ///设置缓存
                .baseUrl(ApiManger.HOST_POST)//★这里最后面必须能带“/”
                .addConverterFactory(GsonConverterFactory.create())//设置将json解析为javabean所用的方式
                .build();
        IZhuanTi call = retrofit.create(IZhuanTi.class);
        //post  action=getArticleDetail&articleId=7c8c95b1-7d95-427d-b8f2-36ed8761e8a0
        Call<VideoDetailBean> callT = call.getVideoArticleDetail("getArticleDetail", jumpId);
        callT.enqueue(new Callback<VideoDetailBean>() {
            @Override
            public void onResponse(Call<VideoDetailBean> call, Response<VideoDetailBean> response) {
//                Log.i("TAG", "onResponse: -RankingSpecialColumnFragment--------" + response.body()+"-----------");
                VideoDetailBean.ResultBean result = response.body().getResult();
                setContent(result);
            }

            @Override
            public void onFailure(Call<VideoDetailBean> call, Throwable t) {

            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // 网络请求后  设置数据
    ///////////////////////////////////////////////////////////////////////////
    private void setContent(VideoDetailBean.ResultBean result) {
        subscibe_text.setText("订阅人数" + result.getAuthor().getSubscibeNum());
        text_time.setText(result.getCreateDate().substring(0, 10));
        fnCommentNum.setText(result.getFnCommentNum() + "");
        read.setText(result.getRead() + "");

        String content = result.getContent();
        if (content != null){
            String contentTitle1String = result.getContentTitle1();
            if (!"".equals(contentTitle1String)){
                contentTitle1.setVisibility(View.VISIBLE);
                imaginary1.setVisibility(View.VISIBLE);
                contentTitle1.setText("-"+contentTitle1String+"-");
                linerView2.setVisibility(View.VISIBLE);
            }
            linerView1.setVisibility(View.VISIBLE);
            content = "<style>img{max-width:100%;height:auto;}</style>" + content;
            webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        }

        String content2 = result.getContent2();
        if (content2 != null){
            String contentTitle2String = result.getContentTitle2();
            if (!"".equals(contentTitle2String)){
                contentTitle2.setVisibility(View.VISIBLE);
                imaginary2.setVisibility(View.VISIBLE);
                linerView2.setVisibility(View.VISIBLE);
                contentTitle2.setText("-"+contentTitle2String+"-");
                linerView3.setVisibility(View.VISIBLE);
            }
            content2 = "<style>img{max-width:100%;height:auto;}</style>" + content2;
            webView2.loadDataWithBaseURL(null, content2, "text/html", "utf-8", null);
        }


        String content3 = result.getContent3();
        if (content3 != null){
            String contentTitle3String = result.getContentTitle3();
            if (!"".equals(contentTitle3String)){
                contentTitle3.setVisibility(View.VISIBLE);
                imaginary3.setVisibility(View.VISIBLE);
                linerView3.setVisibility(View.VISIBLE);
                contentTitle3.setText("-"+contentTitle3String+"-");
            }

            content3 = "<style>img{max-width:100%;height:auto;}</style>" + content3;
            webView3.loadDataWithBaseURL(null, content3, "text/html", "utf-8", null);
        }





    }


    ///////////////////////////////////////////////////////////////////////////
    // 按钮监听
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.subscibe_button:

                break;
            case R.id.shareImage:

                break;
            case R.id.share:

                break;
            case R.id.headImg:

                break;
            case R.id.smallImage:
                if (isVideo) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(Uri.parse(videoUrl), "video/mp4");
//                    startActivity(intent);
//                    break;
                    if (Build.VERSION.SDK_INT >= 21) {
                        getWindow().setStatusBarColor(getResources().getColor(R.color.status));
                        RelativeLayout.LayoutParams layoutParams =
                                (RelativeLayout.LayoutParams) videoView.getLayoutParams();
                        layoutParams.setMargins(0,getStatusBarHeight(),0,0);
                        videoView.setLayoutParams(layoutParams);
                        videoView.requestLayout();
                    }
                    videoImage.setVisibility(View.GONE);
                    videoView.setVisibility(View.VISIBLE);
                    percentT.setVisibility(View.VISIBLE);
                    videoView.setVideoURI(Uri.parse(videoUrl));
                    videoView.setOnPreparedListener(this);
                    videoView.setOnCompletionListener(this);
                    videoView.setOnInfoListener(this);
//                    videoView.setMediaController(new MediaController(this));
                    videoView.start();
                    AlphaAnimation a = new AlphaAnimation(1.0f, 0.1f);
                    a.setDuration(2000);
                    a.setRepeatCount(0);
                    a.setFillAfter(true);  //停在最后地方
                    a.setInterpolator(new LinearInterpolator());
                    smallImage.setAnimation(a);
                    a.start();
                }
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////
    // videoView
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Log.i("TAG", "onBufferingUpdate: ---------" + percent + "%");
//        percentT.setText("正在缓冲:"+percent+"%");
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        videoImage.setVisibility(View.VISIBLE);
        smallImage.setVisibility(View.VISIBLE);
        videoView.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        smallImage.clearAnimation();
        smallImage.setVisibility(View.GONE);
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) { //缓冲开始
            percentT.setText("当前网速:" + extra + "kb/s");
        } else if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {//缓冲结束
            percentT.setText("缓冲结束");
            handler.sendEmptyMessageDelayed(0, 2000);
        } else if (what == MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED) { //下载速率变化
            percentT.setText("当前网速:" + extra + "kb/s");
        }
        return false;
    }


    ///////////////////////////////////////////////////////////////////////////
    // NestedScrollView监听
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//        ns.scrollTo(0,scrollY+500);
    }
}
