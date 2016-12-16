package com.dream.will.floral_life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.will.floral_life.R;

/**
 * Author：Will on 2016/12/12 14:13
 * Mail：heheheqin.will@gmail.com
 */

public class CommunityQuanZiFragment extends Fragment {
    String[] titles = {"最新", "花艺", "植物", "家居", "情感", "杂物"
    };
    private ViewPager viewpaget;
    private TabLayout tabLayout;
    private Fragment[] fragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_quanzi, container, false);
        //1 初始化shuju
        viewpaget = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        return view;
    }

    private void initData() {
        fragments = new Fragment[titles.length];
        for (int i = 0; i < fragments.length; i++) {
            CommunityDingyueFragment communityDingyueFragment = new CommunityDingyueFragment();
            fragments[i] = communityDingyueFragment;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        //2 设置适配器
        // TODO: 2016/12/16 fragment里面管理fragment 需要使用到 getChildFragmentManager
        viewpaget.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            // 4. 设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        //3 联动
        tabLayout.setupWithViewPager(viewpaget);
    }


}
