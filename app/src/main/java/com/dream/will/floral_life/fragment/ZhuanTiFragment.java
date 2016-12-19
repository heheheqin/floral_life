package com.dream.will.floral_life.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dream.will.floral_life.MyApp;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.adapter.MenuAdapter;
import com.dream.will.floral_life.adapter.ZhuanTiListAdapter;
import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.Article;
import com.dream.will.floral_life.bean.MenuBean;
import com.dream.will.floral_life.content.Conten;
import com.dream.will.floral_life.customview.DividerGridItemDecoration;
import com.dream.will.floral_life.customview.HandView;
import com.dream.will.floral_life.inter.IZhuanTi;
import com.dream.will.floral_life.ui.DetailActivity;
import com.dream.will.floral_life.ui.RankingActivity;
import com.dream.will.floral_life.ui.VideoDetailActivity;
import com.dream.will.floral_life.utils.OkUtils;

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

/**
 * Author：Will on 2016/12/12 14:12
 * Mail：heheheqin.will@gmail.com
 */

public class ZhuanTiFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener,AdapterView.OnItemClickListener {


    //    post  请求数据  action=mainList_NewVersion&isVideo=false&currentPageIndex=0&cateId=
    public String action = "mainList_NewVersion";
    public int currentPageIndex = 0;
    public String cateId = "";
    public boolean isVideo = false;
    List<Article.ResultBean> articleData;
    List<Article.ResultBean> videoData;
    List<Article.ResultBean> dataShow;
    List<MenuBean.ResultBean> menuData;
    List<MenuBean.ResultBean> menuHeadData;
    private ListView listview;
    private ZhuanTiListAdapter zhuanTiListAdapter;
    private ImageView image_menu;
    private ImageView image_find;
    private TextView zhuanti_title;
    private CheckBox CheckBox_list;
    private PtrClassicFrameLayout refresh;
    private boolean isRefresh;
    private View popupWindowView;
    private View menuHeadView;
    private View menuView;
    private PopupWindow popupWindow;
    private PopupWindow menuPopupWindow;
    private boolean isMore;
    private MenuAdapter menuAdapter;
    private TextView name;
    private TextView enName;
    private ImageView image;
    private RelativeLayout image1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhuanti, container, false);
        // 一个自定义popup的布局，作为title显示的内容
        popupWindowView = inflater.inflate(
                R.layout.fragment_zhuanti_xuanze,
                container,
                false);
        // 自定义一个popup布局 作为 显示菜单
        menuView = inflater.inflate(R.layout.fragment_zhuanti_menu, container, false);
        name = (TextView) menuView.findViewById(R.id.name);
        enName = (TextView) menuView.findViewById(R.id.enName);
        image = (ImageView) menuView.findViewById(R.id.image);
        image1 = (RelativeLayout) menuView.findViewById(R.id.image1);
        image1.setOnClickListener(this);
        menuHeadView = inflater.inflate(R.layout.fragment_zhuanti_menu_head, container, false);
        listview = (ListView) view.findViewById(R.id.listview);
        image_menu = (ImageView) view.findViewById(R.id.image_menu);
        image_menu.setOnClickListener(this);
        image_find = (ImageView) view.findViewById(R.id.image_find);
        image_find.setOnClickListener(this);
        zhuanti_title = (TextView) view.findViewById(R.id.zhuanti_title);
        CheckBox_list = (CheckBox) view.findViewById(R.id.CheckBox_list);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        zhuanTiListAdapter = new ZhuanTiListAdapter(getActivity(),
                dataShow,
                R.layout.fragment_zhuanti_list_item1,
                R.layout.fragment_zhuanti_list_item2);
        listview.setAdapter(zhuanTiListAdapter);
        listview.setOnItemClickListener(this);
        CheckBox_list.setOnCheckedChangeListener(this);
//        refresh = (PtrFrameLayout) view.findViewById(R.id.refresh); //需要自己添加头部
        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.refresh); //包含默认头部
        zhuanti_title.setOnClickListener(this);
        //设置刷新头
        initRefresh();
        //设置上啦加载
        initPull();
        //初始化PopupWindow
        initTitlePopupWindow();
        //初始化PopupWindow
        initMenu();
        //获取菜单 数据
        initMenuData();
    }

    //初始化 数据集合
    private void initData() {
        articleData = new ArrayList<>();
        videoData = new ArrayList<>();
        dataShow = new ArrayList<>();
        menuData = new ArrayList<>();
        menuHeadData = new ArrayList<>();
    }

    /**
     * 加载listView数据
     */
    private void getArticle() {
//        if (!InternetUtils.isNetworkReachable(getContext())){
//            Toast.makeText(getActivity(), "网络不可用", Toast.LENGTH_LONG).show();
//            refresh.refreshComplete();
//            return;
//        }
        //标志为下拉刷新
        if (isRefresh) {
            currentPageIndex = 0;
        }
        Retrofit retrofit = new Retrofit.Builder()
//                .client(OkUtils.genericClient("zhuanti"))  ///设置缓存
                .baseUrl(ApiManger.HOST_POST)//★这里最后面必须能带“/”
                .addConverterFactory(GsonConverterFactory.create())//设置将json解析为javabean所用的方式
                .build();
        IZhuanTi call = retrofit.create(IZhuanTi.class);
        final Call<Article> article = call.getArticle(action, isVideo, currentPageIndex, cateId);
        article.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                if (isRefresh) {
                    articleData.clear();
                    isRefresh = false;
                    if (isVideo) {
                        videoData.clear();
                    }
                }
                Article body = response.body();
                Log.i("TAG", "onResponse: ---------" + body);
                if (body == null) {
                    Toast.makeText(getActivity(), "没有更多啦！", Toast.LENGTH_SHORT).show();
                    return;
                }
                try{

                    for (Article.ResultBean resultBean : body.getResult()) {
                        if (isVideo) {
                            videoData.add(resultBean);
                        } else {
                            articleData.add(resultBean);
                        }
                    }
                    dataShow.clear();
                    if (isVideo){
                        dataShow.addAll(videoData);
                    }else {
                        dataShow.addAll(articleData);
                    }
                    zhuanTiListAdapter.notifyDataSetChanged();
                }catch (Exception e){

                }
                //结束刷新状态
                Log.i("TAG", "onResponse: ---1------");
                refresh.refreshComplete();
                Log.i("TAG", "onResponse: ---2------");
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Toast.makeText(getActivity(), "刷新失败", Toast.LENGTH_SHORT).show();
                Log.i("TAG", "onFailure: ---------");
                //结束刷新状态
                refresh.refreshComplete();
            }
        });
    }

    /**
     * 加载菜单数据
     */
    private void initMenuData() {
//        if (InternetUtils.isNetworkReachable(getContext())){
//            Toast.makeText(getActivity(), "网络不可用", Toast.LENGTH_LONG).show();
//            refresh.refreshComplete();
//            return;
//        }

        final Retrofit re = new Retrofit.Builder()
                .client(OkUtils.genericClient("menu"))  ///设置缓存
                .baseUrl(ApiManger.HOST_POST)//★这里最后面必须能带“/”
                .addConverterFactory(GsonConverterFactory.create())//设置将json解析为javabean所用的方式
                .build();
        IZhuanTi menu = re.create(IZhuanTi.class);
        Call<MenuBean> call = menu.getMenu("getListNew");
        call.enqueue(new Callback<MenuBean>() {
            @Override
            public void onResponse(Call<MenuBean> call, Response<MenuBean> response) {
                MenuBean body = response.body();
                Log.i("TAG", "onResponse: -------获取menu 数据成功--"+body);
                if (body == null){
                    return;
                }
                MenuBean.ResultBean e = body.getResult().get(0);
                menuHeadData.add(e);
                for (MenuBean.ResultBean resultBean : body.getResult()) {
                    menuData.add(resultBean);
                }
                menuData.remove(0);
                name.setText(e.getName());
                enName.setText(e.getEnName());
                Glide.with(getActivity())
                        .load(e.getImg())
                        .error(R.drawable.banner_zhanwei)
                        .placeholder(R.drawable.banner_zhanwei)
                        .into(image);
                menuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MenuBean> call, Throwable t) {
                Log.i("TAG", "onFailure: ------获取menu数据失败---");
            }
        });
    }


    /**
     * 设置listView 滚动监听  上拉加载
     */
    private void initPull() {
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == 0 && isMore) {
                    Log.i("TAG", "onScrollStateChanged: ------shangla---");
                    //上啦加载
                    currentPageIndex++;
                    getArticle();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //滑动到底部时候  设置标志位为true
                isMore = firstVisibleItem + visibleItemCount == totalItemCount;
            }
        });
    }

    /**
     * 设置刷新控件头部
     */
    private void initRefresh() {

        //创建刷新头
        HandView handView = new HandView(getActivity());
//        MaterialHeader pullRoRefreshHeadView = new MaterialHeader(getActivity());
        //添加头
        refresh.setHeaderView(handView);
        //添加属性头控件
        refresh.addPtrUIHandler(handView);
        //刷新事件
        refresh.setLastUpdateTimeRelateObject(this);
        refresh.setPtrHandler(new PtrDefaultHandler() {
            //属性方法
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //刷新代码
                isRefresh = true;
                getArticle();
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
                refresh.autoRefresh();
            }
        });
    }

    /**设置点击事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuanti_title: {
                //显示popupwindow
                showPopupOnclick(v);
                //改变图标为 向上  在popupwindow触摸事件中  改变为向下
                Drawable addr = ContextCompat.getDrawable(getActivity(), R.drawable.main_filter_up);
                addr.setBounds(0, 0, addr.getMinimumWidth(), addr.getMinimumHeight());
                zhuanti_title.setCompoundDrawables(null, null, addr, null);
            }
            break;
            case R.id.image_menu: {
                Log.i("TAG", "onClick: --image_menu-------");
                setImageRotateAnimation(90);
                showMenu(v);
            }
            break;
            case R.id.image_find: {

            }
            break;
            case R.id.wenzhang: {
                isRefresh = true;  //获取最新视频资源
                isVideo = false;  //设置获取文章资源
                Toast.makeText(getActivity(), "wenzhang", Toast.LENGTH_SHORT).show();
                getArticle();
//                Intent intent = new Intent(getActivity(), ArticleActivity.class);
//                startActivity(intent);
                popupWindow.dismiss();
            }
            break;
            case R.id.shiping: {
                isRefresh = true;  //获取最新视频资源
                isVideo = true;  //设置获取视频资源
                getArticle();
                Toast.makeText(getActivity(), "shiping", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
         break;
            case R.id.image1:
            {
                startActivity(new Intent(getActivity(), RankingActivity.class));
                Log.i("TAG", "onClick: -----menuImage------");
                Toast.makeText(getActivity(), "menu", Toast.LENGTH_SHORT).show();
            }
         break;
        }
    }

    //   显示菜单
    private void showMenu(View view) {
        // 设置好参数之后再show
        menuPopupWindow.showAsDropDown(view);
    }
    //  初始化菜单
    private void initMenu() {

        menuPopupWindow = new PopupWindow(menuView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        menuPopupWindow.setTouchable(true);
        menuPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("menuPopupWindow", "onTouch : ");
                //吧标题栏的图标转换成  向下图标
                setImageRotateAnimation(-90);
                return false;
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug   设置一个透明颜色  不影响
        menuPopupWindow.setBackgroundDrawable(new ColorDrawable(0xffffffff));

        //初始化menu布局中的  recyclerView

        RecyclerView mRecyclerView = (RecyclerView) menuView.findViewById(R.id.recyclerView);
//创建默认的线性LayoutManager
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
        GridLayoutManager layout = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(layout);
//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
//创建并设置Adapter
        menuAdapter = new MenuAdapter(menuData, getActivity());
        mRecyclerView.setAdapter(menuAdapter);
        //增加分割线
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
//        设置监听⌚️事件
        menuAdapter.setOnItemClickListener(new MenuAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String name = menuData.get(position).getName();
                String id = menuData.get(position).getId();
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                intent.putExtra(Conten.KEY_MENU_DETAIL_NAME,name);
                intent.putExtra(Conten.KEY_MENU_DETAIL_ID,id);
                startActivity(intent);
            }
        });

    }

    //设置菜单按钮动画
    private void setImageRotateAnimation(int degrees) {
        RotateAnimation rotateAnimation = new RotateAnimation(0,degrees,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);  //停在最后地方
        rotateAnimation.setRepeatCount(0);  //设置循环次数
        rotateAnimation.setInterpolator(new LinearInterpolator());
        image_menu.startAnimation(rotateAnimation);
    }

    //初始化 title PopupWindow
    private void initTitlePopupWindow() {
        // 设置按钮的点击事件
        TextView wenzhang = (TextView) popupWindowView.findViewById(R.id.wenzhang);
        TextView shiping = (TextView) popupWindowView.findViewById(R.id.shiping);
        wenzhang.setOnClickListener(this);
        shiping.setOnClickListener(this);
        popupWindow = new PopupWindow(popupWindowView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("mengdd", "onTouch : ");
                //吧标题栏的图标转换成  向下图标
                Drawable addr = ContextCompat.getDrawable(getActivity(), R.drawable.main_filter_down);
                addr.setBounds(0, 0, addr.getMinimumWidth(), addr.getMinimumHeight());
                zhuanti_title.setCompoundDrawables(null, null, addr, null);
                return false;
//                 这里如果返回true的话，touch事件将被拦截
//                 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug   设置一个透明颜色  不影响
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
//        showPopupOnclick(view);
//        popupWindow.showAsDropDown(view);
    }

    //显示 title popupWindow
    private void showPopupOnclick(View view) {
        // 设置好参数之后再show
        popupWindow.showAsDropDown(view, -14, 8);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /** 设置全局布局切换监听
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.i("TAG", "onCheckedChanged: ---------");
        //切换布局
        if (isChecked) {
            MyApp.getInstance().itemViewType = 0;
        } else {
            MyApp.getInstance().itemViewType = 1;
        }
        zhuanTiListAdapter.notifyDataSetChanged();
    }

    /**
     *  listView Item 监听   跳转到VideoDetailActivity 界面
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Article.ResultBean resultBean = dataShow.get(position);
        Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
        intent.putExtra(Conten.KEY_HEADIMAGE,resultBean.getAuthor().getHeadImg());
        intent.putExtra(Conten.KEY_SMALLIMAGE,resultBean.getSmallIcon());
        intent.putExtra(Conten.KEY_USERNAME,resultBean.getAuthor().getUserName());
        intent.putExtra(Conten.KEY_SUBSCIBENUM,resultBean.getAuthor().getSubscibeNum());
        intent.putExtra(Conten.KEY_WENZHANGBIAOTI,resultBean.getTitle());
        intent.putExtra(Conten.KEY_WENZHANGXIAOBIAOTI,"#"+resultBean.getCategory().getName()+"#");
        intent.putExtra(Conten.KEY_DESC,resultBean.getDesc());
        intent.putExtra(Conten.KEY_ISVIDEO,resultBean.isVideo());
        intent.putExtra(Conten.KEY_VIDEOURL,resultBean.getVideoUrl());
        intent.putExtra(Conten.KEY_JUMP_ID,resultBean.getId());
        intent.putExtra(Conten.KEY_SHAREUTL,resultBean.getSharePageUrl());
        startActivity(intent);
    }
}
