package com.dream.will.floral_life.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.dream.will.floral_life.R;
import com.dream.will.floral_life.fragment.StoreFragment;
import com.dream.will.floral_life.fragment.CommunityFragment;
import com.dream.will.floral_life.fragment.WallpaperFragment;
import com.dream.will.floral_life.fragment.WoDeFragment;
import com.dream.will.floral_life.fragment.ZhuanTiFragment;
import com.dream.will.floral_life.utils.SharedUtils;

public class HomeActivity extends BaseActivity {

    private FrameLayout fragment;
    private FrameLayout tabcontent;
    private FragmentTabHost tabhost;
    private LayoutInflater inflater;

    //TabHost使用Fragment类
    private Class[] fragments = {
            ZhuanTiFragment.class, WallpaperFragment.class, CommunityFragment.class, StoreFragment.class, WoDeFragment.class
    };
    //tab标题
    private String[] tabTextsName = {
            "专题", "壁纸", "社区", "商城","我的"
    };
    //tab图标
    private int[] imgIds = {
            R.drawable.home_select_tab_03,
            R.drawable.home_select_tab_05,
            R.drawable.home_select_tab_04,
            R.drawable.home_select_tab_02,
            R.drawable.home_select_tab_01
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedUtils.saveFirstRun(this);
        initView();
        
    }


    private void initView() {
        inflater = LayoutInflater.from(this);
        fragment = (FrameLayout) findViewById(R.id.fragment);
        tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        tabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        /**
         *参数三：要现实Fragment容器ID
         */
        tabhost.setup(this, getSupportFragmentManager(), R.id.fragment);
        //给tabhost添加Tab
        for (int i = 0; i < tabTextsName.length; i++) {
            //创建新的tab
            TabHost.TabSpec tabItem = tabhost.newTabSpec(i + "");
            //设置内容   ?content
            tabItem.setIndicator(getTabItemView(i));
            /**
             * 参数一：tab标签
             * 参数二：tab内通Fragment类
             * 参数三： Bundle
             */
            tabhost.addTab(tabItem, fragments[i], null);
            //取消分割线
            tabhost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        }
    }

    private View getTabItemView(int index) {
        View view = inflater.inflate(R.layout.home_tab_layout_item, null);
        //拿到控件值
        ImageView iv = (ImageView) view.findViewById(R.id.tab_img);
        iv.setImageResource(imgIds[index]);
        TextView textView = (TextView) view.findViewById(R.id.tab_text);
        textView.setText(tabTextsName[index]);
        return view;
    }
}
