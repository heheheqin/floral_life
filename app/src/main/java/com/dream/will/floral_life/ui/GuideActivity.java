package com.dream.will.floral_life.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dream.will.floral_life.R;
import com.dream.will.floral_life.content.GuideContent;
import com.dream.will.floral_life.fragment.GuideFragment;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class GuideActivity extends AppCompatActivity {

    List<Fragment> fragments;
    //做动画的view的id数组
    int[] viewId = {
            R.drawable.yindao_bg_04, R.drawable.yindao_bg_05, R.drawable.yindao_bg_06
    };
    private ViewPager viewpager;
    private CircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
    }

    private void initData() {
        fragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            GuideFragment guideFragment = new GuideFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(GuideContent.KEY_GUIDEFRAGMENT, viewId[i]);
            bundle.putInt(GuideContent.KEY_GUIDE_LAYOUT, i);
            guideFragment.setArguments(bundle);
            fragments.add(guideFragment);
        }
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        indicator.setViewPager(viewpager);
    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        indicator = (CircleIndicator) findViewById(R.id.indicator);
    }
}
