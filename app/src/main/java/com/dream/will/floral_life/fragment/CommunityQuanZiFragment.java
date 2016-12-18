package com.dream.will.floral_life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
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
    /**
     * 最新
     type=fff61da9-5360-4c9b-a428-a147f42b654e &pageSize=20&action=getBbsCircle&currentPageIndex=0
     花艺
     type=afe78aff-8c30-4beb-a7e2-95d0cbeea3c6&pageSize=20&action=getBbsCircle&currentPageIndex=0
     植物
     type=b72c2b5d-07cf-4b98-a6db-ed2ed58b33b6&pageSize=20&action=getBbsCircle&currentPageIndex=0
     type=b72c2b5d-07cf-4b98-a6db-ed2ed58b33b6&pageSize=20&action=getBbsCircle&currentPageIndex=1
     家居
     type=6cf666ac-641f-4e76-981d-c94742264ce8&pageSize=20&action=getBbsCircle&currentPageIndex=0
     情感
     type=55e7d2bb-5c61-40ca-a82b-00d0d4f74535&pageSize=20&action=getBbsCircle&currentPageIndex=0
     杂物
     type=1                                   &pageSize=20&action=getBbsCircle&currentPageIndex=0
     */

    String[]  type = {
            "fff61da9-5360-4c9b-a428-a147f42b654e", //最新
            "afe78aff-8c30-4beb-a7e2-95d0cbeea3c6", //花艺
            "b72c2b5d-07cf-4b98-a6db-ed2ed58b33b6", //植物
            "6cf666ac-641f-4e76-981d-c94742264ce8", //家居
            "55e7d2bb-5c61-40ca-a82b-00d0d4f74535", //情感
            "1"}; //  杂物
    private ViewPager viewpaget;
    private TabLayout tabLayout;
    private Fragment[] fragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Community", "onCreate: -CommunityQuanZiFragment--------");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_quanzi, container, false);
        //1 初始化shuju
        viewpaget = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        initData();
        Log.i("Community", "onCreateView: -CommunityQuanZiFragment--------");
        return view;
    }

    private void initData() {
        fragments = new Fragment[titles.length];
        for (int i = 0; i < fragments.length; i++) {
            CommunityQuanZiDetailFragment communityDingyueFragment = new CommunityQuanZiDetailFragment();
            Bundle bundle =new Bundle();
            bundle.putString("type",type[i]);
            communityDingyueFragment.setArguments(bundle);
            fragments[i] = communityDingyueFragment;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Community", "onViewCreated: -CommunityQuanZiFragment--------");
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
    String community = "Community";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.i(community, "onActivityCreated: -CommunityQuanZiFragment--------");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(community, "onResume: -CommunityQuanZiFragment--------");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(community, "onPause: --CommunityQuanZiFragment-------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(community, "onDestroyView: -CommunityQuanZiFragment--------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(community, "onDestroy: -CommunityQuanZiFragment--------");
    }



}
